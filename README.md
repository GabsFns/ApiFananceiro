# 🛡️ API Distribuída para Operações Financeiras com Blockchain e Tecnologias de Alta Performance

**Sistema backend escalável para gerenciamento de transações financeiras, segurança, mensageria, cache e monitoramento, integrando tecnologias de mercado.**

---
## 📄 Documentação Técnica

A documentação de requisitos técnicos está disponível para download abaixo:
------Em andamento------
➡️ [📥 Download — Documentação de Requisitos Técnicos (em andamento)](./EspecificacoesTecnicasAPIfinancerBlock.docx)

## 📌 Descrição do Projeto

Este projeto propõe o desenvolvimento de uma **API REST distribuída e segura**, direcionada para o setor financeiro, implementada com **Spring Boot** e baseada em arquitetura moderna, modular e desacoplada.

A aplicação oferece recursos como:

- **Blockchain** para validação, integridade e imutabilidade de transações.
- **WebSocket** para comunicação em tempo real com clientes e administradores.
- **RabbitMQ** para mensageria assíncrona e desacoplamento de serviços.
- **Redis** para caching, gerenciamento de sessões e tokens JWT.
- **Prometheus + Grafana** para monitoramento, métricas e observabilidade.
- **Micrometer e Actuator** para health checks e métricas customizadas.
- **JavaMailSender** para envio de e-mails transacionais e de notificação.
- **Swagger/OpenAPI** para documentação interativa e centralizada das rotas.
- **Spring Security + JWT** para autenticação, autorização e controle de acesso.

O projeto está em constante evolução, com novas rotas, módulos e serviços sendo adicionados.

> 📌 **Status:** 🚧 *Em desenvolvimento — novas funcionalidades, integrações e ajustes de infraestrutura estão em andamento.*

---
---

## 📦 Como Executar o Projeto

### 🐳 Com Docker Compose:

```bash
docker-compose up --build
```
A aplicação ficará disponível nas seguintes portas:

Serviço	Porta<br>
API	8080<br>
PostgreSQL	5432<br>
Redis	6379<br>
RabbitMQ	5672 / 15672<br>
Swagger UI	http://localhost:8080/swagger-ui/index.html<br>

### 📖 Documentação da API
A documentação interativa das rotas está disponível via Swagger:
```bash
http://localhost:8080/swagger-ui/index.html
```
### 📊 Métricas e Observabilidade
Métricas via Prometheus disponíveis em:
```bash
http://localhost:8080/actuator/prometheu
```

### 📄 Observações
Projeto em evolução contínua.

Novas rotas, serviços e integrações de segurança, escalabilidade e alta disponibilidade serão adicionadas.

A documentação será atualizada progressivamente durante o desenvolvimento.
