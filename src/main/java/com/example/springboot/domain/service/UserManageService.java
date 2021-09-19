package com.example.springboot.domain.service;

import java.util.List;
import java.util.Map;

import com.example.springboot.config.AppConstants;
import com.example.springboot.domain.entity.MUser;
import com.example.springboot.domain.entity.MUserRole;
import com.example.springboot.domain.repository.MUserMapper;
import com.example.springboot.domain.repository.MUserRoleMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Service that manages users of this application.
 */
@Service
@RequiredArgsConstructor
public class UserManageService {

	private final MUserMapper userRepo;

	private final MUserRoleMapper userRoleRepo;

	/**
	 * Get all users.
	 * @return user entities.
	 */
	public List<MUser> txGetAll() {
		return userRepo.findAllByOrderByUserIdAsc();
	}

	/**
	 * Register user and default role assignments.
	 * @param user user entity.
	 */
	public void txRegistUser(MUser user) {
		userRepo.create(user);

		// Register the role assigned to the user.
		MUserRole userRole = new MUserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(AppConstants.ROLE_ID_FOR_USERS);
		userRoleRepo.create(userRole);
	}

	/**
	 * Update user enable / disable.
	 * @param userMap Map(user id / enable or disable) object.
	 */
	public void txChangeEnableUser(Map<String, Boolean> userMap) {
		for (Map.Entry<String, Boolean> entry : userMap.entrySet()) {
			MUser e = new MUser().setUserId(entry.getKey()).setEnabled(entry.getValue());
			userRepo.setEnabledByUserId(e);
		}
	}

	/**
	 * Delete user.
	 * @param userid user id.
	 */
	public void txDeleteUser(String userid) {
		userRepo.deleteByUserId(userid);
		userRoleRepo.deleteByUserId(userid);
	}

}
