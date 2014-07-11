package training.osms.persistence;

import javax.persistence.TypedQuery;

import training.framework.persistence.EntityDao;
import training.osms.business.User;
import training.osms.business.UserSearchOptions;

public class UserDao extends EntityDao<User, UserSearchOptions> {

	public UserDao() {
		super(User.class, "user");
	}

	@Override
	protected StringBuilder toPredicate(UserSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setParameters(UserSearchOptions options, TypedQuery<?> query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void appendOrder(StringBuilder predicate,
			UserSearchOptions options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String createQueryOne() {
		// TODO Auto-generated method stub
		return null;
	}
}
