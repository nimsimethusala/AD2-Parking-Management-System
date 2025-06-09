package lk.ijse.vehicle_service.repo;

import lk.ijse.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
