package com.project.professor.allocation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import com.project.professor.allocation.Repository.AllocationRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(value = false)
public class AllocationRepositoryTest {

	@Autowired
	AllocationRepository allocRepo;

	//@Test
	//public void createAllocation() {}
	

}
