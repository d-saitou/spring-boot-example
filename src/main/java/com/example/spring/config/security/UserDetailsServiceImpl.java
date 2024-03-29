package com.example.spring.config.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.domain.entity.MUser;
import com.example.spring.domain.entity.VUserAuthority;
import com.example.spring.domain.repository.MUserMapper;
import com.example.spring.domain.repository.VUserAuthorityMapper;
import com.example.spring.utility.StringUtility;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Custom UserDetailsService.
 */
@Service
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MUserMapper userRepo;

	private final VUserAuthorityMapper userAuthRepo;

	/**
	 * Loads user-specific data.
	 * @param username user ID
	 * @return UserDetailsImpl UserDetailsImpl
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = -1)
	public UserDetails loadUserByUsername(String username) {
		MUser user = null;
		List<VUserAuthority> userAuthList = null;

		// Get user information
		try {
			user = userRepo.findByUserId(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Can not get user data of login id: " + username);
		}
		if (user == null) {
			throw new UsernameNotFoundException("User not found for login id: " + username);
		}
		if (!user.isEnabled()) {
			throw new UsernameNotFoundException("Invalid user: " + username);
		}

		// Get authority information
		try {
			userAuthList = userAuthRepo.findByUserId(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Can not get user authority data for login id: " + username);
		}
		if (userAuthList.size() == 0) {
			throw new UsernameNotFoundException("User authority data not found for login id: " + username);
		}

		// Create authority list
		List<String> roles = new LinkedList<String>();
		for (VUserAuthority v : userAuthList) {
			roles.add(v.getAuthorityName());
		}
		String[] rolesArray = StringUtility.convertListToStringArray(roles);

		// Set UserDetails
		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUsername(user.getUserId());
		userDetails.setDisplayname(user.getUserName());
		userDetails.setPassword(user.getPassword());
		userDetails.setEnabled(user.isEnabled());
		userDetails.setAuthorities(AuthorityUtils.createAuthorityList(rolesArray));
		userDetails.setSessiontimeout(user.getSessionTimeout());
		return userDetails;
	}

}
