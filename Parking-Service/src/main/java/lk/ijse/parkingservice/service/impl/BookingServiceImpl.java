package lk.ijse.parkingservice.service.impl;

import lk.ijse.parkingservice.dto.BookingDTO;
import lk.ijse.parkingservice.entity.Booking;
import lk.ijse.parkingservice.entity.ParkingSpot;
import lk.ijse.parkingservice.repo.BookingRepository;
import lk.ijse.parkingservice.repo.ParkingSpotRepository;
import lk.ijse.parkingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public Booking reserve(UUID userId, UUID vehicleId, UUID spotId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new RuntimeException("Spot not found"));

        if (!spot.isAvailable()) {
            throw new RuntimeException("Spot already occupied");
        }

        // Mark spot as occupied
        spot.setIsAvailable(false);
        parkingSpotRepository.save(spot);

        // Create booking
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setVehicleId(vehicleId);
        booking.setParkingSpotId(spotId);
        booking.setStartTime(LocalDateTime.now());
        booking.setEndTime(null); // Not ended yet
        return bookingRepository.save(booking);
    }
}
