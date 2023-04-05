package com.ttu.department.service.impl;

import com.ttu.department.dto.DepartmentDto;
import com.ttu.department.model.Department;
import com.ttu.department.repository.DepartmentRepository;
import com.ttu.department.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;

    DepartmentServiceImpl(ModelMapper modelMapper,DepartmentRepository departmentRepository)
    {
        this.modelMapper=modelMapper;
        this.departmentRepository=departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment,DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(() -> {
            throw new RuntimeException("Department not found");
        });
        return modelMapper.map(department,DepartmentDto.class);
    }
}
