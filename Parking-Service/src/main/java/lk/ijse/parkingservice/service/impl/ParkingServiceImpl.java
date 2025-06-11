package lk.ijse.parkingservice.service.impl;

import lk.ijse.parkingservice.dto.ParkingSpotDTO;
import lk.ijse.parkingservice.entity.ParkingSpot;
import lk.ijse.parkingservice.repo.ParkingSpotRepository;
import lk.ijse.parkingservice.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findByIsAvailableTrue();
    }

    public ParkingSpot addSpot(ParkingSpotDTO dto) {
        ParkingSpot spot = new ParkingSpot();
        spot.setLocation(dto.getLocation());
        spot.setIsAvailable(true); // new spots are available by default
        spot.setOwnerId(dto.getOwnerId());

        return parkingSpotRepository.save(spot);
    }


    public ParkingSpot updateSpotStatus(UUID id, boolean isAvailable) {
        ParkingSpot spot = parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));
        spot.setIsAvailable(isAvailable);
        return parkingSpotRepository.save(spot);
    }

    public String deleteSpot(UUID id) {
        ParkingSpot spot = parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));

        if (!spot.isAvailable()) {
            throw new RuntimeException("Cannot delete an occupied spot");
        }

        parkingSpotRepository.deleteById(id);
        return "Parking spot with ID " + id + " has been deleted.";
    }

}
