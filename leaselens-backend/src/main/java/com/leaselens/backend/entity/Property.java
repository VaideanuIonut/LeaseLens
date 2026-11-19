package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "property_type_id")
    private Integer propertyTypeId;
    @Column(name = "asset_manager_id")
    private Integer assetManagerId;
    @Column(name = "total_area")
    private BigDecimal totalArea;
    @Column(name = "number_of_units")
    private Integer numberOfUnits;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
