package book.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
