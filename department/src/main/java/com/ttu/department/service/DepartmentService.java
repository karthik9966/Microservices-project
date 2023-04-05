package com.ttu.department.service;

import com.ttu.department.dto.DepartmentDto;

public interface DepartmentService {
    public DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
}
