package com.selimsahin.garage.service;

import com.selimsahin.garage.model.Ticket;
import com.selimsahin.garage.model.VehicleType;
import com.selimsahin.garage.repository.TicketRepository;
import com.selimsahin.garage.request.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createCarTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setColor("Red");
        ticketRequest.setPlate("34DFT510");
        ticketRequest.setVehicleType(VehicleType.CAR);

        Ticket ticket = new Ticket();
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.CAR);

        List<Integer> slots = List.of(0);

        ticket.setSlots(slots);

        Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

        String result = ticketService.createTicket(ticketRequest);

        assertEquals(result, "Allocated one slot.");
    }

    @Test
    void createJeepTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setColor("Red");
        ticketRequest.setPlate("34DFT510");
        ticketRequest.setVehicleType(VehicleType.JEEP);

        Ticket ticket = new Ticket();
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.JEEP);

        List<Integer> slots = List.of(0, 1);

        ticket.setSlots(slots);

        Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

        String result = ticketService.createTicket(ticketRequest);

        assertEquals(result, "Allocated 2 slots.");
    }

    @Test
    void createTruckTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setColor("Red");
        ticketRequest.setPlate("34DFT510");
        ticketRequest.setVehicleType(VehicleType.TRUCK);

        Ticket ticket = new Ticket();
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.TRUCK);

        List<Integer> slots = List.of(0, 1, 2, 3);

        ticket.setSlots(slots);

        Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

        String result = ticketService.createTicket(ticketRequest);

        assertEquals(result, "Allocated 4 slots.");
    }

    @Test
    void park_When_Garage_Is_Full() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setColor("Red");
        ticketRequest.setPlate("34DFT510");
        ticketRequest.setVehicleType(VehicleType.TRUCK);

        Ticket ticket = new Ticket();
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.TRUCK);

        List<Integer> slots = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);

        ticket.setSlots(slots);

        Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

        String result = ticketService.createTicket(ticketRequest);

        assertEquals(result, "Garage is full.");
    }

    @Test
    void leave_When_Ticket_Found_For_One_Slot() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.CAR);

        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optionalTicket);

        String result = ticketService.removeTicket(1L);
        assertEquals("1 slot is empty after removing ticket.", result);
    }

    @Test
    void leave_When_Ticket_Found_For_More_Than_One_Slot() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.TRUCK);

        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optionalTicket);

        String result = ticketService.removeTicket(1L);
        assertEquals(ticket.getVehicleType().getValue() + " slots is empty after removing ticket.", result);
    }

    @Test
    void leave_When_Ticket_Failed() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setColor("Red");
        ticket.setPlate("34DFT510");
        ticket.setVehicleType(VehicleType.TRUCK);

        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optionalTicket);
        when(ticketRepository.save(ArgumentMatchers.any(Ticket.class))).thenThrow(new RuntimeException());

        String result = ticketService.removeTicket(2L);
        assertEquals("removing ticket is failed.", result);
    }
}
