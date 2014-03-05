package com.github.emalock3.digest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import lombok.experimental.ExtensionMethod;

import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

@ExtensionMethod(MessageDigestExtensions.class)
public class PersonTest {

	@Test
	public void toHexMessageDigest() {
		String sha512 = 
				"cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";
		Charset utf8 = Charset.forName("UTF-8");
		Person p = new Person("Taro", "Fusafusa", 33, Long.MAX_VALUE, Double.MAX_VALUE, true, "hoge".getBytes(utf8));
		assertThat(p.toHexMessageDigest(), is(sha512));
		String md5 = "d41d8cd98f00b204e9800998ecf8427e";
		assertThat(p.toHexMessageDigest(Algorithm.MD5), is(md5));
	}

}
