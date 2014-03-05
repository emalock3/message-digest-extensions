package com.github.emalock3.digest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import lombok.experimental.ExtensionMethod;

import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

@ExtensionMethod(MessageDigestExtensions.class)
public class EmployeeTest {

	@Test
	public void test() {
		Employee emp = new Employee("1234-4321", "Jiro Fusafusa", "jiro@test.hoge", 1234, 1.234);
		String sha256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
		assertThat(emp.toHexMessageDigest(), is(sha256));
		String sha384 = 
				"38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b";
		assertThat(emp.toHexMessageDigest(Algorithm.SHA384), is(sha384));
	}

}
