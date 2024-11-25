package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query("select employee from Employee employee where employee.employeeCode = :code")
    Employee findByEmployeeCode(@Param("code") String code);
    Employee findEmployeeByEmail(String mail);
    @Query("select employee from Employee employee " +
            "where (employee.role.roleName = 'ROLE_STAFF' ) and" +
            "(employee.email like %:email% or " +
            "employee.fullName like  %:fullName% or" +
            " employee.employeeCode like %:code% or " +
            "employee.phoneNumber like %:phoneNumber%)")
    Page<Employee> findEmployeeByKeyWord(Pageable pageable,@Param("email")String email,
                               @Param("fullName")String fullName,
                               @Param("code")String code,
                               @Param("phoneNumber")String phoneNumber
    );
    @Query("select e FROM Employee e where e.role.roleName = 'ROLE_STAFF'")
    Page<Employee> findEmployee(Pageable pageable);
    @Query("select e FROM Employee e where e.role.roleName = 'ROLE_STAFF'and(e.employeeStatus = :status or :status is null)")
    Page<Employee> findEmployeeByStatus(Pageable pageable,@Param("status") Integer status);
}
