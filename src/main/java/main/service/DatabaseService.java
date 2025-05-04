package main.service;

import main.repository.DataEntity;
import main.dto.Request;
import main.dto.Response;
import main.dto.ResponseSum;

public interface DatabaseService {
    Response saveData(DataEntity entity);

    Response deleteById(Request name);

    ResponseSum getValueSum(String firstName, String secondName);
}
