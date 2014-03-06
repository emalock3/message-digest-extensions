package com.github.emalock3.digest;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;
import com.github.emalock3.digest.annotation.DefaultMessageDigestAlgorithm;
import com.github.emalock3.digest.annotation.DigestKey;

@DefaultMessageDigestAlgorithm(Algorithm.SHA256)
public class Hoge implements MessageDigestable {
	@DigestKey private final String name;
	private final int foo;
	private final String bar;
	public Hoge(String name, int foo, String bar) {
		super();
		this.name = name;
		this.foo = foo;
		this.bar = bar;
	}
	
	public String getName() {
		return name;
	}
	
	@DigestKey
	public int getFoo() {
		return foo;
	}
	
	public String getBar() {
		return bar;
	}
}
