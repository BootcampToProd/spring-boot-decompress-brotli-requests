package com.bootcamptoprod.controller;

import com.bootcamptoprod.dto.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrotliDecompressController {

    @PostMapping(value = "/decompress")
    public ResponseEntity<Employee> decompress(@RequestBody Employee employee) {

        System.out.println("Employee Id: " + employee.getId());
        System.out.println("Employee Name: " + employee.getName());

        return ResponseEntity.ok(employee);
    }
}
