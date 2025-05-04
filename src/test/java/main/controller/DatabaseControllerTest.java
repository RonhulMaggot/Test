package main.controller;

import main.dto.Request;
import main.dto.RequestTwo;
import main.repository.DataEntity;
import main.service.DatabaseService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseControllerTest {
    private final DatabaseService service;

    @Autowired
    public DatabaseControllerTest(DatabaseService service) {
        this.service = service;
    }

    @Test
    void saveData() {
        DataEntity data = new DataEntity("Aboba", 54);
        DataEntity data2 = new DataEntity("Boba", 228);
        DataEntity data3 = new DataEntity("Sissi", 14);
        DataEntity dataNull = new DataEntity();

        assertEquals(0, service.saveData(data2).getCode());
        assertEquals(0, service.saveData(data3).getCode());
        assertEquals(0, service.saveData(data).getCode());
        assertEquals(1, service.saveData(data).getCode());
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            service.saveData(dataNull);
        });
        assertThrows(NullPointerException.class, () -> {
            service.saveData(null);
        });
    }

    @Test
    void deleteById() {
        Request request = new Request("Aboba");
        Request requestNull = new Request();

        assertEquals(0, service.deleteById(request).getCode());
        assertEquals(1, service.deleteById(request).getCode());
        assertThrows(NullPointerException.class, () -> {
            service.deleteById(null);
        });
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            service.deleteById(requestNull);
        });
    }

    @Test
    void getValueSum() {
        RequestTwo request = new RequestTwo("Aboba", "Sissi");
        RequestTwo request2 = new RequestTwo("Sissi", "Boba");
        RequestTwo request3 = new RequestTwo(null, "Boba");

        assertEquals(0, service.getValueSum(request2.getFirstName(), request2.getSecondName()).getCode());
        assertEquals(270, service.getValueSum(request2.getFirstName(), request2.getSecondName()).getSum());
        assertThrows(NullPointerException.class, () -> {
            service.getValueSum(request.getFirstName(), request.getSecondName());
        });
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            service.getValueSum(request3.getFirstName(), request3.getSecondName());
        });
    }
}