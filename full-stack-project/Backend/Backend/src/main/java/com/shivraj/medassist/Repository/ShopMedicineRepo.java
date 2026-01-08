package com.shivraj.medassist.Repository;


import com.shivraj.medassist.Models.ShopMedicineStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMedicineRepo extends JpaRepository<ShopMedicineStock,Long> {

    List<ShopMedicineStock> findByShopId(Long shopId);
    ShopMedicineStock findByShopIdAndMedicineId(Long shopId,Long medicineId);
    ShopMedicineStock deleteByShopIdAndMedicineId(Long shopId,Long medicineId);

}
