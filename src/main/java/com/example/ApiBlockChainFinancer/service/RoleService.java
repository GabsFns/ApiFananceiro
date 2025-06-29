package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.model.Role;
import com.example.ApiBlockChainFinancer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
