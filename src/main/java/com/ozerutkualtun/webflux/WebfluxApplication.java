package com.ozerutkualtun.webflux;

import com.ozerutkualtun.webflux.model.Employee;
import com.ozerutkualtun.webflux.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class WebfluxApplication {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void prepareDatabase() {

        if(employeeRepository.count().block() == 0) {

            IntStream.range(0,100)
                    .mapToObj(this::generate)
                    .map(employeeRepository::save)
                    .collect(Collectors.toList())
                    .forEach(item -> item.subscribe(System.out::println));
        }
    }

    public Employee generate(int i) {
        return Employee.builder()
                .firstName("FirstName " + i)
                .lastName("LastName" + i)
                .birthdate(LocalDate.now())
                .build();
    }
}
