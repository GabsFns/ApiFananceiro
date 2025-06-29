package com.example.ApiBlockChainFinancer.repository;

import com.example.ApiBlockChainFinancer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
