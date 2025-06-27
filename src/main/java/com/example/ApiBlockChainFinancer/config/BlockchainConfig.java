package com.example.ApiBlockChainFinancer.config;

import com.example.ApiBlockChainFinancer.blockchain.Blockchain;
import org.springframework.cglib.core.Block;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockchainConfig {
    @Bean
    public Blockchain blockchain(){
        Blockchain blockchain = new Blockchain();
        //blockchain.createGenesisBlock;
        return blockchain;
    }
}
