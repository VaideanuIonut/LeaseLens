package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "leases")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "property_id")
    private Integer propertyId;
    @Column(name = "tenant_id")
    private Integer tenantId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "initial_rent_amount")
    private BigDecimal initialRentAmount;
    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
