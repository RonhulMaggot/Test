package main.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DataEntityRepositoryImpl implements DataEntityRepository {

    private final EntityManager entityManager;

    public DataEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveData(DataEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public void deleteById(DataEntity entity) {
        entityManager.remove(entity);
    }

    @Override
    public DataEntity findByName(String name) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<DataEntity> query = cb.createQuery(DataEntity.class);
            Root<DataEntity> root = query.from(DataEntity.class);
            query.select(root).where(cb.equal(root.get("name"), name));
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
