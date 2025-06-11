package lk.ijse.parkingservice.repo;

import lk.ijse.parkingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

}
