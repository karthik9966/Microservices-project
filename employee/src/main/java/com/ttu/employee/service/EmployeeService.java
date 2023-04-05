package com.ttu.employee.service;

import com.ttu.employee.dto.EmployeeDto;
import com.ttu.employee.dto.EmployeeResponseDto;

public interface EmployeeService {
    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeResponseDto getEmployeeById(Long id);
}
