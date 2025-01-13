package org.example.trackerapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geo_reads")
public class GeoRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "animal_id")
    private long animalId;

    @Column(name = "tracker_id")
    private long trackerId;

    @Column(name = "created_date")
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "current_temp")
    private double currentTemp;

    @Column(name = "is_temp_exceeded")
    private boolean isTempExceeded;

    @Column(name = "is_animal_in_shepherd")
    private boolean isAnimalInShepherd;

    @Column(name = "is_temp_exceeded_confirmed")
    private boolean isTempExceededConfirmed;

    @Column(name = "is_animal_in_shepherd_confirmed")
    private boolean isAnimalInShepherdConfirmed;

}
