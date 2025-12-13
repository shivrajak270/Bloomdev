package com.shivraj.medassist.ServiceImpl;

import com.shivraj.medassist.Converters.PharmacistsConverter;
import com.shivraj.medassist.Converters.UsersToDTOconverter;
import com.shivraj.medassist.Dto.PharmasistDTO;
import com.shivraj.medassist.Dto.UsersDTO;
import com.shivraj.medassist.Models.Pharmacists;
import com.shivraj.medassist.Models.Users;
import com.shivraj.medassist.Repository.PharmasistRepo;
import com.shivraj.medassist.Repository.UsersRepo;
import com.shivraj.medassist.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PharmasistRepo pharmasistRepo;


    @Autowired
    private UsersToDTOconverter UsersToDTOconverter;

    @Autowired
    private PharmacistsConverter PharmacistsConverter;


    @Override
    public UsersDTO createUsers(Users users) {
        Users user=usersRepo.save(users);
        return UsersToDTOconverter.converttoUsersDTO(user);
    }

    @Override
    public PharmasistDTO createPharmasist(PharmasistDTO PharmasistDTO) {
        Pharmacists pharmacists=pharmasistRepo.save(PharmacistsConverter.convertDTOtoPharmasist(PharmasistDTO));
        return PharmacistsConverter.convertPharmasisttoDTO(pharmacists);
    }


}
