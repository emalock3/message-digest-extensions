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
		String sha256 = "82f5c445da9b67aaf8dbcee421fc396399f897870c24220511b2eab5a32f80b9";
		Foo foo = new Foo("foo", 123, "hoge");
		assertThat(foo.toHexMessageDigest(), is(sha256));
		String sha1 = "27f82383143a3c21015451af32ea315c1a282838";
		assertThat(foo.toHexMessageDigest(Algorithm.SHA1), is(sha1));
	}

}
