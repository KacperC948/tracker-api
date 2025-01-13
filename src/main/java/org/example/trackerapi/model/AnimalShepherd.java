package org.example.trackerapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animal_shepherds")
public class AnimalShepherd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "animal_type")
    private String animalType;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;
}
