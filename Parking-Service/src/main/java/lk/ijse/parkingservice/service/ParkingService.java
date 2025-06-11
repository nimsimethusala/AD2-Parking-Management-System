package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.dto.ParkingSpotDTO;
import lk.ijse.parkingservice.entity.ParkingSpot;

import java.util.List;
import java.util.UUID;

public interface ParkingService {
    ParkingSpot updateSpotStatus(UUID id, boolean isAvailable);
    List<ParkingSpot> getAvailableSpots();
    ParkingSpot addSpot(ParkingSpotDTO dto);
    String deleteSpot(UUID id);
}
