package main.controller;

import lombok.AllArgsConstructor;
import main.repository.DataEntity;
import main.dto.Request;
import main.dto.RequestTwo;
import main.dto.Response;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.*;
import main.service.DatabaseService;

@RestController
@RequestMapping
@AllArgsConstructor
public class DatabaseController {
    private final DatabaseService service;

    @PostMapping("/add")
    public Response saveData(@RequestBody DataEntity entity) {
        try {
            return service.saveData(entity);
        } catch (RuntimeException e) {
            return new Response(2);
        }
    }

    @PostMapping("/remove")
    public Response deleteById(@RequestBody Request name) {
        try {
            return service.deleteById(name);
        } catch (RuntimeException e) {
            return new Response(2);
        }
    }

    @PostMapping("/sum")
    public Response getValueSum(@RequestBody RequestTwo request) {
        try {
            return service.getValueSum(request.getFirstName(), request.getSecondName());
        } catch (InvalidDataAccessApiUsageException e) {
            return new Response(2);
        } catch (RuntimeException e) {
            return new Response(3);
        }
    }
}
