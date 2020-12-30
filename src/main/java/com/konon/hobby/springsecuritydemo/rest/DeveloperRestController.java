package com.konon.hobby.springsecuritydemo.rest;

import com.konon.hobby.springsecuritydemo.model.Developer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/developers")
public class  DeveloperRestController {
    List<Developer> developers = new ArrayList<>(Arrays.asList(
            new Developer(1L, "Sergey", "Sergeev"),
            new Developer(2L, "Anton", "Antonov"),
            new Developer(3L, "Maxim", "Maximov")
    ));

    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id) {
        return developers.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Developer create(@RequestBody Developer developer) {
        this.developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.developers.removeIf(developer -> developer.getId().equals(id));
    }
}
