package com.shivraj.medassist.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ShopMedicineStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String medicine_id;
    private String shop_id;
    private long quantity;
    private double price;


    @CreationTimestamp
    @Column(name="creationdate")
    private LocalDateTime creationdate;

    @UpdateTimestamp
    @Column(name="last_updated")
    private LocalDateTime last_updated;



}
