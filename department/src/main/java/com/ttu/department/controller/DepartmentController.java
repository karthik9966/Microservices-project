package com.ttu.department.controller;

import com.ttu.department.dto.DepartmentDto;
import com.ttu.department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/department")
public class DepartmentController {

    public DepartmentService departmentService;

    DepartmentController (DepartmentService departmentService)
    {
        this.departmentService=departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto)
    {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public DepartmentDto getDepartmentByCode(@PathVariable String code)
    {
        return departmentService.getDepartmentByCode(code);
    }
}
