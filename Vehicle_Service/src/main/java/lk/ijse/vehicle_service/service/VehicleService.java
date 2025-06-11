package lk.ijse.vehicle_service.service;

import lk.ijse.vehicle_service.entity.Vehicle;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    List<Vehicle> getVehiclesByUserId(UUID userId);
    String simulateEntry(UUID vehicleId);
    String simulateExit(UUID vehicleId);
    Vehicle updateVehicle(UUID id, Vehicle updated);
    String deleteVehicle(UUID id);
}
