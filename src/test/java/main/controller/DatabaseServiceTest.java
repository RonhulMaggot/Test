package main.controller;

import jakarta.persistence.EntityManager;
import main.dto.Request;
import main.dto.RequestTwo;
import main.dto.ResponseSum;
import main.repository.DataEntity;
import main.service.DatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DatabaseServiceTest {

    private final DatabaseService service;
    private final EntityManager entityManager;

    @Autowired
    public DatabaseServiceTest(DatabaseService service, EntityManager entityManager) {
        this.service = service;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        entityManager.createQuery("DELETE FROM DataEntity").executeUpdate();
    }

    @Test
    void saveData() {
        DataEntity data = new DataEntity("Aboba", 54);
        DataEntity data2 = new DataEntity("Boba", 229);
        DataEntity data3 = new DataEntity("Sissi", 14);
        DataEntity dataNull = new DataEntity(null, 0);

        assertEquals(0, service.saveData(data2).getCode());
        assertEquals(0, service.saveData(data3).getCode());
        assertEquals(0, service.saveData(data).getCode());
        assertEquals(1, service.saveData(data).getCode());
        assertEquals(2, service.saveData(dataNull).getCode());
        assertEquals(2, service.saveData(null).getCode());
    }

    @Test
    void deleteById() {
        DataEntity data = new DataEntity("Aboba", 54);
        service.saveData(data);

        Request request = new Request("Aboba");
        Request requestNull = new Request(null);

        assertEquals(0, service.deleteById(request).getCode());
        assertEquals(4, service.deleteById(request).getCode());
        assertEquals(2, service.deleteById(null).getCode());
        assertEquals(2, service.deleteById(requestNull).getCode());
    }

    @Test
    void getValueSum() {
        service.saveData(new DataEntity("Aboba", 54));
        service.saveData(new DataEntity("Boba", 229));
        service.saveData(new DataEntity("Sissi", 14));

        RequestTwo request = new RequestTwo("Aboba", "Sissi");
        RequestTwo request2 = new RequestTwo("Sissi", "Boba");
        RequestTwo request3 = new RequestTwo(null, "Boba");
        RequestTwo requestNo = new RequestTwo("Valery", "Jmishenko");

        ResponseSum sum = service.getValueSum(request2.getFirst(), request2.getSecond());
        assertEquals(0, sum.getCode());
        assertEquals(243, sum.getSum()); // 14 + 229
        assertEquals(3, service.getValueSum("Oleg", request.getSecond()).getCode());
        assertEquals(2, service.getValueSum(request3.getFirst(), request3.getSecond()).getCode());
        assertEquals(3, service.getValueSum(requestNo.getFirst(), requestNo.getSecond()).getCode());
    }
}