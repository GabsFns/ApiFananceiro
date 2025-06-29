package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.model.SupportTicket;
import com.example.ApiBlockChainFinancer.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportTicketService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public SupportTicket saveTicket(SupportTicket ticket) {
        return supportTicketRepository.save(ticket);
    }

    public List<SupportTicket> getTicketsByUser(Long userId) {
        return supportTicketRepository.findByUserId(userId);
    }

    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }
}
