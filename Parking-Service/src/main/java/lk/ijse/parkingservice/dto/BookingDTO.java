package lk.ijse.parkingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private UUID id;
    private Long userId;
    private Long vehicleId;
    private Long parkingSpotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
}
