package lk.ijse.payment_service.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.payment_service.dto.PaymentRequestDTO;
import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.repo.PaymentRepository;
import lk.ijse.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus("SUCCESS");
        payment.setPaymentTime(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public Payment getByBookingId(UUID bookingId) {
        return paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found for booking ID " + bookingId));
    }
}
