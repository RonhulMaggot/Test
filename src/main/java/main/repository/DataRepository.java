package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, String>, DataEntityRepository {

    @Override
    DataEntity findByName(String name);
}