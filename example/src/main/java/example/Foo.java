package example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.github.emalock3.digest.MessageDigestExtensions;
import com.github.emalock3.digest.MessageDigestable;
import com.github.emalock3.digest.annotation.DefaultMessageDigestAlgorithm;
import com.github.emalock3.digest.annotation.DigestKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DefaultMessageDigestAlgorithm(MessageDigestExtensions.Algorithm.SHA256)
public class Foo implements MessageDigestable {
	private @DigestKey String foo;
	private @DigestKey int bar;
	private String hoge;
}
