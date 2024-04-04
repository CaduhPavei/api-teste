package com.example.apiteste.resourse;

import com.example.apiteste.model.Pais;
import com.example.apiteste.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity create(@RequestBody Pais entity){
        repository.save(entity);

        ResponseEntity<String> responseEntity = restTemplate.
                postForEntity("http://localhost:8088/api/paises-replica",
                entity, String.class);
        System.out.println(responseEntity.getStatusCode());

        return ResponseEntity.ok().body(entity);
    }

}
