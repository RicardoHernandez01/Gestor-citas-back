package com.example.gestorCitas.projectionInterface;

public interface DepartmentProjection {
    String getNameDepartment();
    String getAddressDepartment();
    String getPhoneDepartment();

    InstitutionNameProjection getInstitution();

}
