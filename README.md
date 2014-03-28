# MessageDigestExtensions

MessageDigestExtensions is a Java library. It provides a way to easily generate a cryptographic hash value from Java object that implements MessageDigestable interface.

## Build with Maven

### Prerequisites

- Java 1.8+
- Maven 3+

### pom.xml configuration

#### Add dependency

	<dependencies>
		
		...
		
		<dependency>
			<groupId>com.github.emalock3.digest</groupId>
			<artifactId>message-digest-extensions</artifactId>
			<version>1.0.0</version>
		</dependency>
		
		...
		
	</dependencies>

#### Add repository

	<repositories>
		
		...
		
		<repository>
			<id>emalock3-mvn-repo</id>
			<url>https://raw.githubusercontent.com/emalock3/mvn-repo/master/</url>
		</repository>
		
		...
		
	</repositories>

## Usage

### Source Object

    @Value
	public class Foo implements MessageDigestable {
		@DigestKey String foo;
		@DigestKey int bar;
		String hoge;
	}

### Example

	public class FooTest {
		@Test
		public void test() {
			String sha512 = "471686a68e99d5e8a88f3d056c86c0a7c11418f1aa258a62f06c8c117b6c60e8e4db35e662a852f406a169f705afc630a3cb3834a656849d6fbd4dfa9f5dfa39";
			Foo foo = new Foo("foo", 123, "hoge");
			assertThat(foo.toHexMessageDigest(), is(sha512));
			String sha1 = "27f82383143a3c21015451af32ea315c1a282838";
			assertThat(foo.toHexMessageDigest(Algorithm.SHA1), is(sha1));
		}
	}

`mvn clean test`

See also [Example](https://github.com/emalock3/message-digest-extensions/tree/master/example).

## License

Apache License, Version 2.0
