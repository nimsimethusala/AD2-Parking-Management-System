package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.entity.Booking;

import java.util.UUID;

public interface BookingService {
    Booking reserve(UUID userId, UUID vehicleId, UUID spotId);
}
