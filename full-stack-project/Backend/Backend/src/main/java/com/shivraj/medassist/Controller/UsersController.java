package com.shivraj.medassist.Controller;


import com.shivraj.medassist.Converters.UsersToDTOconverter;
import com.shivraj.medassist.Dto.UsersDTO;
import com.shivraj.medassist.Models.Users;
import com.shivraj.medassist.Repository.UsersRepo;
import com.shivraj.medassist.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?>delete(@PathVariable Long id){
//
//
//    }

    @GetMapping("/")
    public ResponseEntity<?> getPharmacists(){
        return new ResponseEntity<>("this is users", HttpStatus.OK);
    }





}
