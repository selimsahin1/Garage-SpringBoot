package com.selimsahin.garage.request;

import com.selimsahin.garage.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String color;
    private String plate;
    private VehicleType vehicleType;
}
