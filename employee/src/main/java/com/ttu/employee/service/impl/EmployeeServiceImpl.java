package com.ttu.employee.service.impl;

import com.ttu.employee.dto.DepartmentDto;
import com.ttu.employee.dto.EmployeeDto;
import com.ttu.employee.dto.EmployeeResponseDto;
import com.ttu.employee.model.Employee;
import com.ttu.employee.repository.EmployeeRepository;
import com.ttu.employee.service.APIClient;
import com.ttu.employee.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    private APIClient apiClient;
   // private RestTemplate restTemplate;
   // private WebClient webClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getEmployeeByIdFallback")
    //@Retry(name = "${spring.application.name}",fallbackMethod = "getEmployeeByIdFallback" )
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });

//        DepartmentDto departmentDto = restTemplate.getForEntity("http://localhost:8080/app/department/" + employee.getDepartmentCode(),
//                DepartmentDto.class).getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/app/department/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        return new EmployeeResponseDto(modelMapper.map(employee,EmployeeDto.class), departmentDto);
    }

    public EmployeeResponseDto getEmployeeByIdFallback(Long id,Exception e)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
        DepartmentDto departmentDto = new DepartmentDto(2,"ECE","Electronic and Communications","EC001");
        return new EmployeeResponseDto(modelMapper.map(employee,EmployeeDto.class), departmentDto);
    }
}
