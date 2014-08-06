package training.osms.business.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.persistence.UserDao;

@Component
public class UserController {

	private @Autowired
	UserDao dao;

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void save(User user) {
		if (dao.containsEntity(user.getName())) {
			throw new BusinessException("There is a user named "
					+ user.getName() + " already");
		} else if (dao.containsEntity(user.getEmail())) {
			throw new BusinessException("There is a email named "
					+ user.getEmail() + " already");
		}

		else {
			dao.insertEntity(user);

		}
	}

	public List<User> searchUser(UserSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void delete(User user) {
		if (user.getProducts().isEmpty()) {
			dao.deleteEntity(user);
		} else {

			throw new BusinessException("The user can't deleted. Probably "
					+ "have products!");
		}
	}

	@Transactional
	public void updateUser(User user) {
		User databaseUser = dao.searchOneEntity(user.getId());
		if (databaseUser == null) {
			dao.updateEntity(user);
		} else {
			if (user.getId().equals(databaseUser.getId())) {
				dao.updateEntity(user);
			} else {
				throw new BusinessException("There is a named user "
						+ user.getName() + " allready");
			}

		}
	}

	public Integer searchUserCount(UserSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public User searchOneUser(Integer catId) {
		return dao.searchOneEntity(catId);
	}
}
