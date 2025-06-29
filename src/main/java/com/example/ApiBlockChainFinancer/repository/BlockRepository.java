package com.example.ApiBlockChainFinancer.repository;

import com.example.ApiBlockChainFinancer.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Block findByHash(String hash);
}
