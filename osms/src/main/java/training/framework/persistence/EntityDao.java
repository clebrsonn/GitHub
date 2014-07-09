package training.framework.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.business.AbstractEntitySearchOptions;

@Component
public abstract class EntityDao<Entity, EntitySearchOptions extends AbstractEntitySearchOptions> {
	private @PersistenceContext
	EntityManager manager;

	private final Class<Entity> entityClass;
	private final String entityAlias;

	public EntityDao(Class<Entity> entityClass, String entityAlias) {
		this.entityClass = entityClass;
		this.entityAlias = entityAlias;
	}

	public boolean containsEntity(String entity) {
		return searchOneEntity(entity) != null;
	}

	public void insertEntity(Entity entity) {
		manager.persist(entity);
	}

	public int searchEntityCount(EntitySearchOptions options) {
		StringBuilder predicate = toPredicate(options);
		String jpql = "select count(" + entityAlias + ") from "
				+ entityClass.getName() + " " + entityAlias + " where 1 = 1"
				+ predicate;
		TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
		setParameters(options, query);
		Long result = query.getSingleResult();
		return result.intValue();
	}

	protected abstract StringBuilder toPredicate(EntitySearchOptions options);

	protected abstract void setParameters(EntitySearchOptions options,
			TypedQuery<?> query);

	public List<Entity> searchEntity(EntitySearchOptions options) {
		StringBuilder predicate = toPredicate(options);
		appendOrder(predicate, options);
		String jpql = "select " + entityAlias + " from "
				+ entityClass.getName() + " " + entityAlias + " where 1 = 1"
				+ predicate;
		TypedQuery<Entity> query = manager.createQuery(jpql, entityClass);
		setParameters(options, query);

		if (options.getStartPosition() != null) {
			query.setFirstResult(options.getStartPosition());
		}
		if (options.getMaxResults() != null) {
			query.setMaxResults(options.getMaxResults());
		}
		List<Entity> result = query.getResultList();
		return result;
	}

	protected abstract void appendOrder(StringBuilder predicate,
			EntitySearchOptions options);

	public void updateEntity(Entity entity) {
		manager.merge(entity);
	}

	public void deleteEntity(Entity entity) {
		Object id = manager.getEntityManagerFactory().getPersistenceUnitUtil()
				.getIdentifier(entity);
		Entity managedEntity = manager.find(entityClass, id);
		manager.remove(managedEntity);
	}

	public Entity searchOneEntity(String entity) {
		TypedQuery<Entity> query = manager.createQuery(createQueryOne(),
				entityClass);
		query.setParameter("entityName", entity.toUpperCase());
		List<Entity> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public Entity searchOneEntity(Integer entity) {
		TypedQuery<Entity> query = manager.createQuery(createQueryOne(),
				entityClass);
		query.setParameter("entityName", entity);
		List<Entity> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	protected abstract String createQueryOne();
}
