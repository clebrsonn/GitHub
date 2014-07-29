package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.user.User;
import training.osms.business.user.UserSearchOptions;

@Component
public class UserDao extends EntityDao<User, UserSearchOptions> {

	public UserDao() {
		super(User.class, "user");
	}

	public boolean containsEntity(Integer userId) {
		return super.searchOneEntity(userId) != null;
	}

	@Override
	protected StringBuilder toPredicate(UserSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getUseId() != null && options.getUseId() > 0) {
			predicate.append(" and user.id = :userId");
		}
		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and user.name like :userName");
		}

		if (options.getEmail() != null && options.getEmail().length() > 0) {
			predicate.append(" and user.email >= :userMail");
		}

		return predicate;
	}

	@Override
	protected void setParameters(UserSearchOptions options, TypedQuery<?> query) {
		if (options.getEmail() != null && options.getEmail().length() > 0) {
			query.setParameter("userMail", options.getEmail());

		}

		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("userName", options.getName());

		}

		if (options.getUseId() != null && options.getUseId() > 0) {
			query.setParameter("userId", options.getUseId());

		}

	}

	@Override
	protected void appendOrder(StringBuilder predicate,
			UserSearchOptions options) {
		if (options.getOrder() != null) {
			predicate.append(" order by user.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}
	}

	@Override
	protected String createQueryOne() {
		String query = "select user from User user where upper(user.name) = :entityName";
		return query;
	}
}
