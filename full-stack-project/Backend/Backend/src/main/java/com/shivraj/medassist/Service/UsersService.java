package com.shivraj.medassist.Service;


import com.shivraj.medassist.Dto.PharmasistDTO;
import com.shivraj.medassist.Dto.ShopDto;
import com.shivraj.medassist.Dto.UsersDTO;
import com.shivraj.medassist.Models.Medicine;
import com.shivraj.medassist.Models.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    public UsersDTO createUsers(Users users);
    public PharmasistDTO createPharmasist(PharmasistDTO PharmasistDTO);
    public String verify(Users users);

    List<Medicine> searchmedicine();

    List<ShopDto> searchShop(long id, String medicine_name);
}
