package com.selimsahin.garage.service;

import com.selimsahin.garage.model.Status;
import com.selimsahin.garage.model.Ticket;
import com.selimsahin.garage.model.VehicleType;
import com.selimsahin.garage.repository.TicketRepository;
import com.selimsahin.garage.request.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;


    public String createTicket(TicketRequest ticketRequest) {
        List<Integer> availableSlots = getAvailableSlots(ticketRequest.getVehicleType());
        if (availableSlots.isEmpty()) {
            return "There is no available slot";
        }

        try {
            setTicket(ticketRequest, availableSlots);

            if (ticketRequest.getVehicleType().getText().equals("CAR")) {
                return "Allocated one slot.";
            } else
                return "Allocated " + ticketRequest.getVehicleType().getValue() + " slots.";
        } catch (Exception e) {
            return "Garage is full.";
        }
    }

    public String removeTicket(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (ticketOptional.isEmpty()) {
            return "Ticket is unavailable.";
        }

        Ticket ticket = ticketOptional.get();
        try {
            ticket.setStatus(Status.GONE);
            ticketRepository.save(ticket);
        } catch (Exception e) {
            return "removing ticket is failed.";
        }

        if (ticket.getVehicleType().getValue() == 1) {
            return "1 slot is empty after removing ticket";
        } else
            return ticket.getVehicleType().getValue() + " slots is empty after removing ticket.";
    }

    public List<Ticket> status() {
        return ticketRepository.findAllByStatus(Status.INGARAGE);
    }

    public void setTicket(TicketRequest ticketRequest, List<Integer> availableSlots) {
        Ticket ticket = new Ticket();
        ticket.setColor(ticketRequest.getColor());
        ticket.setPlate(ticketRequest.getPlate());
        ticket.setVehicleType(ticketRequest.getVehicleType());
        ticket.setSlots(availableSlots);
        ticketRepository.save(ticket);
    }

    public List<Boolean> fillSlots() {
        List<Boolean> garageArray = new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
        Collections.fill(garageArray, Boolean.FALSE);
        List<Ticket> fullSlots = ticketRepository.findAllByStatus(Status.INGARAGE);
        fullSlots.forEach(parkingLot -> parkingLot.getSlots().forEach(integer -> garageArray.set(integer, true)));
        return garageArray;
    }

    public List<Integer> getAvailableSlots(VehicleType vehicleType) {
        List<Boolean> slots = fillSlots();
        List<Boolean> searchPattern = new ArrayList<>(Arrays.asList(new Boolean[vehicleType.getValue()]));
        Collections.fill(searchPattern, Boolean.FALSE);
        List<Integer> reservedSlots = new ArrayList<>();

        for (int i = 0; i <= slots.size() - searchPattern.size(); i++) {
            List<Boolean> tempArray = slots.subList(i, i + searchPattern.size());
            if (tempArray.equals(searchPattern)) {
                reservedSlots = IntStream
                        .range(i, i + searchPattern.size()).boxed()
                        .collect(Collectors.toList());
                break;
            }
        }
        return reservedSlots;
    }
}
