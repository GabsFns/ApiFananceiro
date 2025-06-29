package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.model.Transaction;
import com.example.ApiBlockChainFinancer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsBySender(Long senderId){
        return transactionRepository.findBySenderId(senderId);
    }

    public List<Transaction> getTransactionsByReceiver(Long receiverId){
        return transactionRepository.findByReceiverId(receiverId);
    }
}
