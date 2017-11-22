package org.malagu.linq;


import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import org.malagu.linq.domain.User;
import org.malagu.linq.lin.Lind;


@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class LindTests {


	@Before
	public void setUp() {
		
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LindTests.class, args);
	}
	
	@Test
	@Transactional
	public void testLinq() {
		JpaUtil.lind(User.class);
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("tom");
		JpaUtil.persist(user);
		
		user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("lisi");
		JpaUtil.persist(user);
		Lind lind = JpaUtil.lind(User.class);
		lind.equal("name", "lisi");
		Assert.isTrue(lind.delete() == 1, "Not Success.");
		JpaUtil.removeAll(User.class);
	}
	
}