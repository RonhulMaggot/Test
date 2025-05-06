package main.service;

import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import main.repository.DataEntity;
import main.dto.Request;
import main.dto.Response;
import main.dto.ResponseSum;
import main.repository.DataRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Primary
public class DatabasePostService implements DatabaseService {
    private final DataRepository repository;

    @Transactional
    public Response saveData(DataEntity entity) {
        try {
            if (entity == null || entity.getName() == null) {
                throw new NullPointerException("Entity or name is null");
            }

            if (repository.findByName(entity.getName()) != null) {
                return new Response(1);
            }

            repository.saveData(entity);

            return new Response(0);
        } catch (NullPointerException e) {
            return new Response(2);
        } catch (RuntimeException e) {
            return new Response(-1);
        }
    }

    @Transactional
    public Response deleteById(Request name) {
        try {
            if (name == null || name.getFirst() == null) {
                throw new NullPointerException("Request or name is null");
            }

            DataEntity entity = repository.findByName(name.getFirst());
            if (entity == null) {
                return new Response(4);
            }

            repository.deleteById(entity);

            return new Response(0);
        } catch (NullPointerException e) {
            return new Response(2);
        } catch (RuntimeException e) {
            return new Response(-1);
        }
    }

    @Transactional
    public ResponseSum getValueSum(String first, String second) {
        try {
            if (first == null || second == null) {
                throw new NullPointerException("First or second name is null");
            }

            DataEntity firstEntity = repository.findByName(first);
            DataEntity secondEntity = repository.findByName(second);
            if (firstEntity == null || secondEntity == null) {
                throw new NoResultException("One or both entities not found");
            }

            return new ResponseSum(0, firstEntity.getValue(), secondEntity.getValue());
        } catch (NullPointerException e) {
            return new ResponseSum(2);
        } catch (NoResultException e) {
            return new ResponseSum(3);
        } catch (RuntimeException e) {
            return new ResponseSum(-1);
        }
    }
}
