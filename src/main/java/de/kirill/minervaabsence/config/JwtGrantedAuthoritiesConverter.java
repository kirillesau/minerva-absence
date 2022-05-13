package de.kirill.minervaabsence.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtGrantedAuthoritiesConverter extends JwtAuthenticationConverter {


  @Override
  protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {

    Collection<GrantedAuthority> authorities = super.extractAuthorities(jwt);
    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
    Map<String, Object> resource;
    Collection<String> resourceRoles;
    if (resourceAccess != null &&
        (resource = (Map<String, Object>) resourceAccess.get("minerva")) !=
            null && (resourceRoles = (Collection<String>) resource.get("roles")) != null)
      authorities.addAll(resourceRoles.stream()
          .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
          .collect(Collectors.toSet()));
    return authorities;
  }

}

