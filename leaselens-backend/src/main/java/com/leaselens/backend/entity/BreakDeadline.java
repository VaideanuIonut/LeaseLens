package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "break_deadline")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BreakDeadline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "window_start_date")
    private LocalDate windowStartDate;
    @Column(name = "window_end_date")
    private LocalDate windowEndDate;
    @Column(name = "effective_date")
    private LocalDate effectiveDate;
    @Column(name = "status")
    private String status;
}
