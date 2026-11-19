package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "renewal_deadline")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RenewalDeadline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "decision_deadline")
    private LocalDate decisionDeadline;
    @Column(name = "renewal_effective_date")
    private LocalDate renewalEffectiveDate;
    @Column(name = "status")
    private String status;
}
