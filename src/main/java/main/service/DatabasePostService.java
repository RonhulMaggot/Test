package main.service;

import lombok.AllArgsConstructor;
import main.repository.DataEntity;
import main.dto.Request;
import main.dto.Response;
import main.dto.ResponseSum;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import main.repository.DataRepository;

@Service
@AllArgsConstructor
@Primary
public class DatabasePostService implements DatabaseService {
    private final DataRepository repository;

    public Response saveData(DataEntity entity) throws NullPointerException, InvalidDataAccessApiUsageException {
        if (repository.findById(entity.getName()).isPresent()) {
            return new Response(1);
        }

        repository.save(entity);
        return new Response(0);
    }

    public Response deleteById(Request name) throws NullPointerException, InvalidDataAccessApiUsageException {
        if (repository.findById(name.getFirstName()).isEmpty()) {
            return new Response(1);
        }

        repository.deleteById(name.getFirstName());
        return new Response(0);
    }

    public ResponseSum getValueSum(String firstName, String secondName) throws NullPointerException,
            InvalidDataAccessApiUsageException {
        if (repository.findById(firstName).isEmpty() || repository.findById(secondName).isEmpty()) {
            throw new NullPointerException();
        }

        return new ResponseSum(0, repository.getDataEntitiesByName(firstName).getValue(),
                repository.getDataEntitiesByName(secondName).getValue());
    }
}
