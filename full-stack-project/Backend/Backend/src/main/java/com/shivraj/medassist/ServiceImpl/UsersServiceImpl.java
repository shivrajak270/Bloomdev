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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;


    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10);


    @Override
    public UsersDTO createUsers(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        Users user=usersRepo.save(users);
        return UsersToDTOconverter.converttoUsersDTO(user);
    }

    @Override
    public PharmasistDTO createPharmasist(PharmasistDTO PharmasistDTO) {
        Pharmacists pharmacists=pharmasistRepo.save(PharmacistsConverter.convertDTOtoPharmasist(PharmasistDTO));
        return PharmacistsConverter.convertPharmasisttoDTO(pharmacists);
    }


    @Override
    public String verify(Users users){


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        if(authentication.isAuthenticated()){
            Users dbUser=usersRepo.findByUsername(users.getUsername());

            return  jwtServiceImpl.generateToken(dbUser.getUsername(),dbUser.getRole());
        }
        else{
            throw new BadCredentialsException("Invalid username or password");
        }

    }




}
