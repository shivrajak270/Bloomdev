package com.shivraj.medassist.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="medicine_sequence_generator"
    )
    @SequenceGenerator(
            name="med icine_sequence_generator",
            sequenceName = "med_sequence_gen",
            allocationSize=50
    )
    private Long medicine_id;
    @Column(name="medicine_name")
    private String medicineName;
    private String medicine_manufacturer;
}
