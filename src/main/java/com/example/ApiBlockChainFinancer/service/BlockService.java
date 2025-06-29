package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.model.Block;
import com.example.ApiBlockChainFinancer.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    public Block saveBlock(Block block){
        return blockRepository.save(block);
    }

    public List<Block> getAllBlocks(){
        return blockRepository.findAll();
    }

    public Block findByHash(String hash){
        return blockRepository.findByHash(hash);
    }
}
