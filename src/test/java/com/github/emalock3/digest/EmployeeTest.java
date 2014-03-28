package com.github.emalock3.digest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

public class EmployeeTest {

	@Test
	public void test() {
		Employee emp = new Employee("1234-4321", "Jiro Fusafusa", "jiro@test.hoge", 1234, 1.234);
		String sha256 = "5ddd8083b67bb5fbb5ecca4949c7ca8c4173d087bbf9114b008ed7e428599c43";
		assertThat(emp.toHexMessageDigest(), is(sha256));
		String sha384 = 
				"20198b9deb94be0f3423647b436a0e49013688b8d4e8478be9fb0ca40dc72a4ac0ed449a48c543d267b9ed18b3de3406";
		assertThat(emp.toHexMessageDigest(Algorithm.SHA384), is(sha384));
	}

}
