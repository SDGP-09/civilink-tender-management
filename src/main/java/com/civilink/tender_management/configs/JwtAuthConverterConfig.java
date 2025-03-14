package com.civilink.tender_management.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class JwtAuthConverterConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = defaultConverter.convert(jwt);


            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            System.out.println("Realm Access: " + realmAccess);

            if (realmAccess != null && realmAccess.containsKey("roles")) {
                Object rolesObj = realmAccess.get("roles");

                if (rolesObj instanceof List) {
                    List<String> roles = (List<String>) rolesObj;
                    System.out.println("Extracted Roles: " + roles);

                    List<GrantedAuthority> roleAuthorities = roles.stream()
                            .map(role -> (GrantedAuthority) () -> "ROLE_" + role.toUpperCase())
                            .collect(Collectors.toList());
                    authorities.addAll(roleAuthorities);
                } else {
                    System.out.println("Roles is not a List: " + rolesObj);
                }
            } else {
                System.out.println("No roles found in realm_access.");
            }

            return authorities;
        });

        return converter;
    }
}
