package com.example.springboot.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * Custom UserDetails.
 */
@Data
public class UserDetailsImpl implements UserDetails {

	private String username;

	private String displayname;

	private String password;

	private boolean enabled;

	private Collection<GrantedAuthority> authorities;

	private final boolean accountNonExpired = true;

	private final boolean accountNonLocked = true;

	private final boolean credentialsNonExpired = true;

	private Integer sessiontimeout;

}
