package com.github.emalock3.digest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import lombok.experimental.ExtensionMethod;

import org.junit.Test;

@ExtensionMethod({MessageDigestExtensions.class})
public class HogeTest {

	@Test
	public void test() {
		String sha256 = "7a3c8c96f7c886b988298d72ef86296d3c979fff822c77892f76278a6e9f23a8";
		Hoge hoge = new Hoge("hoge", 321, "bar");
		assertThat(hoge.toHexMessageDigest(), is(sha256));
		Hoge barHoge = new Hoge("hoge", 321, "barbarBAR");
		assertThat(hoge.toHexMessageDigest(), is(barHoge.toHexMessageDigest()));
	}

}
