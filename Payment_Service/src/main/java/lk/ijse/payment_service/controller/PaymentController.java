package lk.ijse.payment_service.controller;

import lk.ijse.payment_service.dto.PaymentRequestDTO;
import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Payment makePayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/booking/{bookingId}")
    public Payment getPaymentByBooking(@PathVariable String bookingId) {
        return paymentService.getByBookingId(UUID.fromString(bookingId));
    }
}
