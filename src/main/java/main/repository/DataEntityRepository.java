package main.repository;

public interface DataEntityRepository {

    void deleteById(DataEntity entity);
    DataEntity findByName(String name);
    void saveData(DataEntity entity);
}
