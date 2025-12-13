package com.shivraj.medassist.Repository;

import com.shivraj.medassist.Models.Pharmacists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PharmasistRepo extends JpaRepository<Pharmacists,Long> {
}
