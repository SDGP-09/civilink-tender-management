package com.civilink.tender_management.services;

import org.springframework.security.core.Authentication;

public interface UserService {
    String getUserCompany(Authentication authentication);
}
