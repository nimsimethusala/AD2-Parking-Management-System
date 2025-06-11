package lk.ijse.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private String brand;
    private String model;
    private Long userId;
    private boolean isParked = false;
}
