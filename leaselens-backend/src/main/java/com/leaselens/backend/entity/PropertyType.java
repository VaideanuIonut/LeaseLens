package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "property_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
}
