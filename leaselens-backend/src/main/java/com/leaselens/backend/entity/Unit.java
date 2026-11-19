package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "units")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "property_id")
    private Integer propertyId;
    @Column(name = "unit_number")
    private String unitNumber;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "area")
    private BigDecimal area;
    @Column(name = "status")
    private String status;
}
