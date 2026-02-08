package com.shivraj.medassist.Controller;


import com.shivraj.medassist.Converters.UsersToDTOconverter;
import com.shivraj.medassist.Dto.MedicineRequestDTO;
import com.shivraj.medassist.Dto.ShopDto;
import com.shivraj.medassist.Dto.UsersDTO;
import com.shivraj.medassist.Models.Medicine;
import com.shivraj.medassist.Models.Users;
import com.shivraj.medassist.Repository.UsersRepo;
import com.shivraj.medassist.Service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")

@CrossOrigin("*")
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?>delete(@PathVariable Long id){
//
//
//    }

    @GetMapping("/")
    public ResponseEntity<?> getPharmacists(){
        return new ResponseEntity<>("this is users", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getMedicines(){
       List<Medicine>medicines= usersService.searchmedicine();
       return new ResponseEntity<>(medicines,HttpStatus.OK);
    }

    @PostMapping("/getmedicine")
    public ResponseEntity<?> getMedicine(@RequestBody MedicineRequestDTO medicineRequestDTO){
        List<ShopDto>shopList=usersService.searchShop(medicineRequestDTO.getId(), medicineRequestDTO.getMedicineName());
        if(shopList.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shopList,HttpStatus.OK);
    }





}
