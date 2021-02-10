package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.model.Role;
import com.ironhack.bankingsystem.model.ThirdParty;
import com.ironhack.bankingsystem.model.User;
import com.ironhack.bankingsystem.repository.RoleRepository;
import com.ironhack.bankingsystem.repository.ThirdPartyRepository;
import com.ironhack.bankingsystem.repository.UserRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        if(userRepository.findByUsername(thirdParty.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username already exists");
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            thirdParty.setPassword(passwordEncoder.encode(thirdParty.getPassword()));

            thirdPartyRepository.save(thirdParty);
            roleRepository.save(new Role("THIRD_PARTY", thirdParty));
            return thirdParty;
        }
    }
}
