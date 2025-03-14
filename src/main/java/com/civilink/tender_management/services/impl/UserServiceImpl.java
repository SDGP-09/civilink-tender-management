package com.civilink.tender_management.services.impl;

import com.civilink.tender_management.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserCompany(Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        List<String> groups = jwt.getClaimAsStringList("group");
        return groups.get(0);
    }
}
