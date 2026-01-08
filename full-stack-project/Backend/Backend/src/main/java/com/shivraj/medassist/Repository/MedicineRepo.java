package com.shivraj.medassist.Repository;

import com.shivraj.medassist.Models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine,Long> {

    public Medicine findById(long id);
    public Medicine findByMedicineName(String name);
}
