package lk.ijse.parkingservice.controller;

import jakarta.validation.Valid;
import lk.ijse.parkingservice.dto.ParkingSpotDTO;
import lk.ijse.parkingservice.entity.Booking;
import lk.ijse.parkingservice.entity.ParkingSpot;
import lk.ijse.parkingservice.service.BookingService;
import lk.ijse.parkingservice.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @Autowired
    private BookingService bookingService;

    // List available spots
    @GetMapping("/spots")
    @PreAuthorize("isAuthenticated()")
    public List<ParkingSpot> getAvailableSpots() {
        return parkingService.getAvailableSpots();
    }

    @PostMapping("/spots")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ParkingSpot addSpot(@Valid @RequestBody ParkingSpotDTO dto) {
        return parkingService.addSpot(dto);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ParkingSpot updateSpotStatus(@PathVariable String id, @RequestParam boolean available) {
        return parkingService.updateSpotStatus(UUID.fromString(id), available);
    }

    @DeleteMapping("/spots/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public String deleteSpot(@PathVariable String id) {
        return parkingService.deleteSpot(UUID.fromString(id));
    }

    @PostMapping("/reserve")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public Booking reserveSpot(@RequestParam String userId,
                               @RequestParam String vehicleId,
                               @RequestParam String spotId) {
        UUID newUserId = UUID.fromString(userId);
        UUID newVehicleId = UUID.fromString(vehicleId);
        UUID newSpotId = UUID.fromString(spotId);
        return bookingService.reserve(newUserId, newVehicleId, newSpotId);
    }
}
