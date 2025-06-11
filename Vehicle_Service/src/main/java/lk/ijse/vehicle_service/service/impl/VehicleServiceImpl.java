package lk.ijse.vehicle_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.vehicle_service.entity.Vehicle;
import lk.ijse.vehicle_service.repo.VehicleRepository;
import lk.ijse.vehicle_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesByUserId(UUID userId) {
        return vehicleRepository.findByUserId(userId);
    }

    public String simulateEntry(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() ->
                new RuntimeException("Vehicle not found"));
        vehicle.setParked(true);
        vehicleRepository.save(vehicle);
        return "Vehicle " + vehicleId + " has entered the parking.";
    }

    public String simulateExit(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() ->
                new RuntimeException("Vehicle not found"));
        vehicle.setParked(false);
        vehicleRepository.save(vehicle);
        return "Vehicle " + vehicleId + " has exited the parking.";
    }

    public Vehicle updateVehicle(UUID id, Vehicle updated) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setLicensePlate(updated.getLicensePlate());
        existing.setBrand(updated.getBrand());
        existing.setModel(updated.getModel());
        // userId can be updated too, if allowed
        existing.setUserId(updated.getUserId());

        return vehicleRepository.save(existing);
    }

    public String deleteVehicle(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicleRepository.delete(vehicle);
        return "Vehicle with ID " + id + " has been deleted.";
    }
}
