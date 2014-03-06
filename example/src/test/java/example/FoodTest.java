package example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.github.emalock3.digest.MessageDigestExtensions;
import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

public class FoodTest {

	@Test
	public void test() {
		String sha512 = "71548b6790af1691907292b2086b5d235e3ebc233df666b9f9b349711801e98a511615e99b8aec116c9eeae052402fb309ff869ce099174a1be7050367cbd112";
		Food food = new Food(new Food.Geo(35.65928, 139.699236), 
				"Gyudon", 280.0, DateTime.parse("2014-03-06T12:00:00+09:00").toDate(), 
				new String[]{"tag1", "tag2"}, new float[]{4.5f, 3.0f, 3.0f, 5.0f, 2.5f}, 
				new String[]{"review1", "review2"});
		assertThat(MessageDigestExtensions.toHexMessageDigest(food), is(sha512));
		String md5 = "9b2b94c222ec6710e6138f36b3924acf";
		assertThat(MessageDigestExtensions.toHexMessageDigest(food, Algorithm.MD5), is(md5));
	}

}
