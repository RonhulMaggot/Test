package main.controller;

import lombok.AllArgsConstructor;
import main.repository.DataEntity;
import main.dto.Request;
import main.dto.RequestTwo;
import main.dto.Response;
import org.springframework.web.bind.annotation.*;
import main.service.DatabaseService;

@RestController
@RequestMapping
@AllArgsConstructor
public class DatabaseController {
    private final DatabaseService service;

    @PostMapping("/add")
    public Response saveData(@RequestBody DataEntity entity) {
        return service.saveData(entity);
    }

    @PostMapping("/remove")
    public Response deleteById(@RequestBody Request name) {
        return service.deleteById(name);
    }

    @PostMapping("/sum")
    public Response getValueSum(@RequestBody RequestTwo request) {
        return service.getValueSum(request.getFirst(), request.getSecond());
    }
}