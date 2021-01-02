package com.konon.hobby.springsecuritydemo.rest;

import com.konon.hobby.springsecuritydemo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('developers:read')")
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return developers.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        this.developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
        this.developers.removeIf(developer -> developer.getId().equals(id));
    }
}
