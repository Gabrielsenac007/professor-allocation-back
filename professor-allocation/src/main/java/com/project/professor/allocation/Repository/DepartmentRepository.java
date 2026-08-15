package com.project.professor.allocation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.professor.allocation.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	//select * from department where name like'%ia%';
	List<Department> findByNameContaining (String partName);

}
