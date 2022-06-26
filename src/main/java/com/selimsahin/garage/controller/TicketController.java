package com.selimsahin.garage.controller;

import com.selimsahin.garage.model.Ticket;
import com.selimsahin.garage.request.RemoveTicketRequest;
import com.selimsahin.garage.request.TicketRequest;
import com.selimsahin.garage.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "create")
    public String createTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.createTicket(ticketRequest);
    }

    @PostMapping(value = "leave")
    public String removeTicket(@RequestBody RemoveTicketRequest removeTicketRequest) {
        return ticketService.removeTicket(removeTicketRequest.getTicketId());
    }

    @GetMapping(value = "status")
    public List<Ticket> statusTickets() {
        return ticketService.status();
    }
}
