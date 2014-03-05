package com.github.emalock3.digest;

import java.nio.charset.Charset;
import java.util.Random;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(MessageDigestExtensions.class)
public class MessageDigestExtensionsPerfTest {

	public static void main(String... args) {
		// warm up
		process(10000);
		long start = System.currentTimeMillis();
		process(1000000);
		System.out.println("elapsed time: " + ((System.currentTimeMillis() - start) / 1000.0) + "sec");
		// no cache [23.401,23.788,23.842,23.555,23.542]
		// cache algorithm [23.042,23.917,23.909,23.675]
		// cache algorithm and PropertyDescriptor [1.822,1.858,1.819,1.793,1.793]
		// cache algorithm and PropertyDescriptor and ObjectWriter [1.761,1.755,1.733,1.729,1.734]
	}
	
	private static void process(int loop) {
		Charset utf8 = Charset.forName("UTF-8");
		Random r = new Random();
		for (int i = 0; i < loop; i++) {
			new Person("Taro", "Fusafusa", r.nextInt(), r.nextLong(), r.nextDouble(), true, "hoge".getBytes(utf8)).toMessageDigest();
		}
	}

}
