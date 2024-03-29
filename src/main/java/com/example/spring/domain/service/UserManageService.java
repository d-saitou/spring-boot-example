package com.example.spring.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.spring.config.AppConstants;
import com.example.spring.domain.entity.MUser;
import com.example.spring.domain.entity.MUserRole;
import com.example.spring.domain.repository.MUserMapper;
import com.example.spring.domain.repository.MUserRoleMapper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Service that manages users of this application.
 */
@Service
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
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
		user.setCreatedBy(user.getUserId());
		user.setModifiedBy(user.getUserId());
		userRepo.create(user);

		// Register the role assigned to the user.
		MUserRole userRole = new MUserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(AppConstants.ROLE_ID_FOR_USERS);
		userRole.setCreatedBy(user.getUserId());
		userRole.setModifiedBy(user.getUserId());
		userRoleRepo.create(userRole);
	}

	/**
	 * Update user enable / disable.
	 * @param userMap Map(user id / enable or disable) object.
	 */
	public void txChangeEnableUser(Map<String, Boolean> userMap) {
		for (Map.Entry<String, Boolean> entry : userMap.entrySet()) {
			MUser e = new MUser().setUserId(entry.getKey()).setEnabled(entry.getValue());
			userRepo.updateEnabledByUserId(e);
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
