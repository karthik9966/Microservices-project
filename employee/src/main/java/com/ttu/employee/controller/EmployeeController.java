package com.ttu.employee.controller;
import com.ttu.employee.dto.EmployeeDto;
import com.ttu.employee.dto.EmployeeResponseDto;
import com.ttu.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/employee")
public class EmployeeController {

    public EmployeeService employeeService;

    EmployeeController (EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createDepartment(@RequestBody EmployeeDto employeeDto)
    {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id)
    {
        return employeeService.getEmployeeById(id);
    }
}
