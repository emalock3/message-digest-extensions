package example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions;
import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

import example.Person.Gender;

public class PersonTest {

	@Test
	public void test() {
		Person p = new Person("Saburo", "Gyudon", Gender.Male, 
				DateTime.parse("2014-03-06T+09:00").toDate(), "saburo@beef.bowl", 
				new String[] {"tag1", "tag2"});
		String sha512 = "fbe4a2f9d7d8800b0a85e63b44f3200b6b693d14bd407a07daea06f67baf7b5463e370ed695a296773a5a65c9d9e66d32676a34ac5f16057ec6b0a5d16cb6b2c";
		assertThat(MessageDigestExtensions.toHexMessageDigest(p), is(sha512));
		Person p2 = new Person(null, null, Gender.NotKnown, null, null, null);
		String sha256 = "144a50c7b1b2f42d6a536a4fb89893ed66e1b571c06b3e84eda590eaeacb5316";
		assertThat(MessageDigestExtensions.toHexMessageDigest(p2, Algorithm.SHA256), is(sha256));
	}

}
