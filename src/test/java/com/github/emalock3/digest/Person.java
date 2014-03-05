package com.github.emalock3.digest;

import com.github.emalock3.digest.annotation.DigestKey;

import lombok.Value;

@Value
public class Person implements MessageDigestable {
	@DigestKey String firstName;
	@DigestKey String lastName;
	@DigestKey int age;
	long foo;
	double bar;
	boolean hoge;
	@DigestKey byte[] bytes;
}
