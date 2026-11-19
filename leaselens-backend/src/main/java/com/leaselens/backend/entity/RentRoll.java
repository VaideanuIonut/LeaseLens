package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "rent_roll")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RentRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "amount_due")
    private BigDecimal amountDue;
    @Column(name = "amount_paid")
    private BigDecimal amountPaid;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "status")
    private String status;
}
