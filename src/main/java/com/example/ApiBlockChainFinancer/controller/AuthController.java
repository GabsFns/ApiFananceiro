package com.example.ApiBlockChainFinancer.controller;

import com.example.ApiBlockChainFinancer.config.JwtUtil;
import com.example.ApiBlockChainFinancer.config.RabbitMQConfig;
import com.example.ApiBlockChainFinancer.config.SecurityConfig;
import com.example.ApiBlockChainFinancer.messaging.MessagingService;
import com.example.ApiBlockChainFinancer.model.EmailMessage;
import com.example.ApiBlockChainFinancer.model.User;
import com.example.ApiBlockChainFinancer.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class AuthController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RabbitMQConfig rabbitMQConfig;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessagingService messagingService;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil, RedisTemplate redisTemplate){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.redisTemplate = redisTemplate;

    }

    // 1️⃣ Registrar novo usuário
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        userService.saveuser(user);
        EmailMessage emailMessage = new EmailMessage(user.getEmail(), "Cadastro sistema de API", "Seja bem vindo!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

    // 2️⃣ Login e retorno de JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = userService.authenticateAndGenerateToken(user);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    // 3️⃣ Logout — invalidar token no Redis
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            redisTemplate.opsForValue().set(token, "blacklisted", Duration.ofHours(24));
        }
        return ResponseEntity.ok("Logout realizado.");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String oldToken = request.get("token");

        if (!jwtUtil.validateToken(oldToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }

        String username = jwtUtil.getUsernameFromToken(oldToken);
        String newToken = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Collections.singletonMap("token", newToken));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        String resetToken = UUID.randomUUID().toString();

        // Salvar token no Redis com expiração de 5 minutos
        redisTemplate.opsForValue().set("reset-token:" + email, resetToken, Duration.ofMinutes(5));

        EmailMessage emailMessage = new EmailMessage(email, "Recuperação de senha", "Seu token: " + resetToken);
        messagingService.sendEmailMessage(emailMessage);

        return ResponseEntity.ok("Token de recuperação enviado para o e-mail.");
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        String redisToken = (String) redisTemplate.opsForValue().get("reset-token:" + email);

        if (redisToken == null || !redisToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado");
        }

        // Atualiza senha no banco
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.saveuser(user);

        // Remove token do Redis
        redisTemplate.delete("reset-token:" + email);

        return ResponseEntity.ok("Senha atualizada com sucesso.");
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getCuurrentUser(org.springframework.security.core.Authentication authentication){
        String email = authentication.getName();
        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        return ResponseEntity.ok(userOpt.get());
    }

    @PutMapping("/users/me")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody User updatedUser, org.springframework.security.core.Authentication authentication) {
        String email = authentication.getName();
        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        User user = userOpt.get();
        user.setNome(updatedUser.getNome());
        user.setEmail(updatedUser.getEmail());
        user.setTelefone(updatedUser.getTelefone());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userService.saveuser(user);
        return ResponseEntity.ok("Perfil atualizado com sucesso.");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getPublicProfile(@PathVariable Long id) {
        Optional<User> userOpt = userService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        User user = userOpt.get();

        // Retornar só os dados públicos — poderia criar um Map ou outro objeto, mas vou mostrar direto
        Map<String, Object> publicProfile = new HashMap<>();
        publicProfile.put("id", user.getId());
        publicProfile.put("nome", user.getNome());
        publicProfile.put("email", user.getEmail());

        return ResponseEntity.ok(publicProfile);
    }
    @DeleteMapping("/users/me")
    public ResponseEntity<?> deleteMyAccount(org.springframework.security.core.Authentication authentication) {
        // Recupera o email do usuário logado
        String email = authentication.getName();
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        User user = userOpt.get();
        Long userId = user.getId();
        // Remove do banco
        userService.deleteUser(userId);
        messagingService.sendUserMessage("Usuario com ID" + userId + "e email " + email + "deletado");
        return ResponseEntity.ok("Conta removida com sucesso.");
    }
}
