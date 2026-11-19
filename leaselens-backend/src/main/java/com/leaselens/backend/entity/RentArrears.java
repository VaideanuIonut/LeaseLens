package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "rent_arrears")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RentArrears {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "default_date")
    private LocalDate defaultDate;
    @Column(name = "days_overdue")
    private Integer daysOverdue;
    @Column(name = "total_amount_owed")
    private BigDecimal totalAmountOwed;
    @Column(name = "cure_deadline")
    private LocalDate cureDeadline;
    @Column(name = "status")
    private String status;
}
