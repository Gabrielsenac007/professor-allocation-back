package com.project.professor.allocation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.Repository.ProfessorRepository;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(value = false)
public class ProfessorRepositoryTest {
	
	@Autowired
	ProfessorRepository profRepo;
	
	@Test
	public void creatProfessor() {
		
//		Department dep = new Department();
//		dep.setId(2L);
//		
//		Professor prof = new Professor();
//		prof.setCpf("98765432152");
//		prof.setName("João");
//		prof.setDepartment(dep);
//		
//		Professor prof2 = profRepo.save(prof);
//		
//		System.out.println(prof2);
//		
//		

		
	}

}
