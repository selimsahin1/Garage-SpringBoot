package com.selimsahin.garage.request;

import com.selimsahin.garage.model.VehicleType;
import lombok.Data;

@Data
public class TicketRequest {
    private String color;
    private String plate;
    private VehicleType vehicleType;
}
