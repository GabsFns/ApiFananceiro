package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.config.JwtUtil;
import com.example.ApiBlockChainFinancer.config.SecurityConfig;
import com.example.ApiBlockChainFinancer.model.User;
import com.example.ApiBlockChainFinancer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SecurityConfig securityConfig;

    public User saveuser(User user){
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public String authenticateAndGenerateToken(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!securityConfig.passwordEncoder().matches(user.getPassword(), existingUser.get().getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(existingUser.get().getEmail());
    }
}
