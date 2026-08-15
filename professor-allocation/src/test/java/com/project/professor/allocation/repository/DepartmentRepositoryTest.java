package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.Repository.DepartmentRepository;
import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(value = false)
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository depRepo;

	@Test
	public void createDepartment() {
		
//		Department dep = new Department();
//		dep.setName("História Antiga");
//		
//		Department dep2 = depRepo.save(dep);
//		
//		System.out.println(dep2);
		
		
		List<Department> list = depRepo.findByNameContaining("ia");
		System.out.println(list);
	}
	

}
