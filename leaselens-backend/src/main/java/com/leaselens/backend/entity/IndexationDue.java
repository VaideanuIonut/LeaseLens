package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "indexation_due")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class IndexationDue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "notification_deadline")
    private LocalDate notificationDeadline;
    @Column(name = "indexation_date")
    private LocalDate indexationDate;
    @Column(name = "applied_percentage")
    private BigDecimal appliedPercentage;
    @Column(name = "new_rent_amount")
    private BigDecimal newRentAmount;
    @Column(name = "status")
    private String status;
}
