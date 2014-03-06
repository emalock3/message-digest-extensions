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
				"6fa4a1c307147a54c4bf3848cb6ef598f4451e7c8faeb05f16fdd3194adfb3b6ad8119eb9d285c05800668a397df6b8e45601dfd9eb3cd1ba35307af7bbef073";
		Charset utf8 = Charset.forName("UTF-8");
		Person p = new Person("Taro", "Fusafusa", 33, Long.MAX_VALUE, Double.MAX_VALUE, true, "hoge".getBytes(utf8));
		assertThat(p.toHexMessageDigest(), is(sha512));
		String md5 = "9dd6b9ab13e02d5a632c0a9514e1a992";
		assertThat(p.toHexMessageDigest(Algorithm.MD5), is(md5));
	}

}
