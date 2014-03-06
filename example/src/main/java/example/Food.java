package example;

import java.util.Date;

import lombok.Value;

import com.github.emalock3.digest.MessageDigestable;
import com.github.emalock3.digest.annotation.DigestKey;

@Value
public class Food implements MessageDigestable {
	@Value
	public static class Geo {
		double latitude;
		double longitude;
	}
	
	@DigestKey Geo place;
	@DigestKey String name;
	@DigestKey double price;
	@DigestKey Date date;
	String[] tags;
	float[] stars;
	String[] reviews;
}
