package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "lease_expiry")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LeaseExpiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "action_status")
    private String actionStatus;
}
