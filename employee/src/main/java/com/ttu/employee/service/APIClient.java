package com.ttu.employee.service;

import com.ttu.employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/app/department/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code);

}
