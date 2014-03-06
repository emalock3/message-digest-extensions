package example;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import lombok.experimental.ExtensionMethod;

import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions;
import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

@ExtensionMethod({MessageDigestExtensions.class})
public class FooTest {

	@Test
	public void test() {
		String sha256 = "471686a68e99d5e8a88f3d056c86c0a7c11418f1aa258a62f06c8c117b6c60e8e4db35e662a852f406a169f705afc630a3cb3834a656849d6fbd4dfa9f5dfa39";
		Foo foo = new Foo("foo", 123, "hoge");
		assertThat(foo.toHexMessageDigest(Algorithm.SHA512), is(sha256));
		String sha1 = "27f82383143a3c21015451af32ea315c1a282838";
		assertThat(foo.toHexMessageDigest(Algorithm.SHA1), is(sha1));
	}

}
