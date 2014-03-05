package com.github.emalock3.digest;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;
import com.github.emalock3.digest.annotation.DefaultMessageDigestAlgorithm;
import com.github.emalock3.digest.annotation.DigestKey;

import lombok.Value;

@Value
@DefaultMessageDigestAlgorithm(Algorithm.SHA256)
public class Employee implements MessageDigestable {
	@DigestKey String id;
	@DigestKey String name;
	@DigestKey String mail;
	int hoge;
	double foo;
}
