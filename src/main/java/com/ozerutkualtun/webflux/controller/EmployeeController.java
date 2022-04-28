package com.ozerutkualtun.webflux.controller;

import com.ozerutkualtun.webflux.model.Employee;
import com.ozerutkualtun.webflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping
    public Flux<Employee> getEmployees(){
        return repository.findAll();
    }
}
