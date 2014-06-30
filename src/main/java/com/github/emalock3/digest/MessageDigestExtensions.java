package com.github.emalock3.digest;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.github.emalock3.digest.annotation.DigestKey;
import com.github.emalock3.digest.annotation.DefaultMessageDigestAlgorithm;

/**
 * The class Provides utility methods for java.security.MessageDigest.
 * 
 * @author Shinobu Aoki
 */
public final class MessageDigestExtensions {
	
	/**
	 * Algorithm for MessageDigest
	 */
	public static enum Algorithm {
		MD2("MD2"), 
		MD5("MD5"), 
		SHA1("SHA-1"), 
		SHA256("SHA-256"), 
		SHA384("SHA-384"), 
		SHA512("SHA-512");
		
		private final String name;
		private Algorithm(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	
	static final Algorithm DEFAULT_ALGORITHM = Algorithm.SHA512;
	private static final ConcurrentHashMap<Class<?>, Algorithm> algorithmCache = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<Class<?>, List<PropertyDescriptor>> digestKeysCache = new ConcurrentHashMap<>();
	
	private MessageDigestExtensions() {
		// do nothing
	}
    
    /**
     * Generate a message digest from a param.
     * 
     * @param param input parameters
	 * @return the array of bytes for the resulting hash value.
     */
    public static byte[] toMessageDigest(MessageDigestParameter param) {
        MessageDigestable input = param.getInput();
		Class<?> c = input.getClass();
        Algorithm algorithm = param.getAlgorithm() == null ? lookupAlgorithm(c) : param.getAlgorithm();
		MessageDigest digest = from(algorithm);
		List<PropertyDescriptor> descriptors = lookupDigestKeys(c);
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
				DataOutputStream dos = new DataOutputStream(baos)) {
			for (PropertyDescriptor pd : descriptors) {
				ReadMethodValueWriter.createWriter(pd).write(dos, input, pd);
			}
            for (Object additionalValue : param.getAdditionalValues()) {
                ReadMethodValueWriter.createWriter(additionalValue).writeValue(dos, additionalValue);
            }
			dos.flush();
			digest.update(baos.toByteArray());
			return digest.digest();
		} catch (IOException ignore) {
			throw new RuntimeException(ignore);
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
    }
	
	private static MessageDigest from(Algorithm algorithm) {
		try {
			return MessageDigest.getInstance(algorithm.getName());
		} catch (NoSuchAlgorithmException ignore) {
			throw new RuntimeException(ignore);
		}
	}

	private static List<PropertyDescriptor> lookupDigestKeys(Class<?> c) {
		List<PropertyDescriptor> descriptors = digestKeysCache.get(c);
		if (descriptors != null) {
			return descriptors;
		}
		Set<String> fields = findDigestKeyFieldNames(c);
		PropertyDescriptor[] descriptorsArray;
		try {
			descriptorsArray = Introspector.getBeanInfo(c).getPropertyDescriptors();
		} catch (IntrospectionException ignore) {
			throw new RuntimeException(ignore);
		}
		descriptors = new ArrayList<>();
		for (PropertyDescriptor pd : descriptorsArray) {
			if (fields.contains(pd.getName()) || pd.getReadMethod().isAnnotationPresent(DigestKey.class)) {
				descriptors.add(pd);
			}
		}
		if (descriptors.isEmpty()) {
			throw new IllegalArgumentException(
					String.format("DigestKey annotation is not found in class[%s]", c.getName()));
		}
		Collections.sort(descriptors, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		digestKeysCache.put(c, descriptors);
		return descriptors;
	}
	
	private static Set<String> findDigestKeyFieldNames(Class<?> c) {
		Field[] fieldsArray = c.getDeclaredFields();
		Set<String> fields = new HashSet<>();
		for (Field f: fieldsArray) {
			if (f.isAnnotationPresent(DigestKey.class)) {
				fields.add(f.getName());
			}
		}
		return fields;
	}
	
	private static Algorithm lookupAlgorithm(Class<?> c) {
		Algorithm algorithm = algorithmCache.get(c);
		if (algorithm != null) {
			return algorithm;
		}
		algorithm = DEFAULT_ALGORITHM;
		DefaultMessageDigestAlgorithm mda = c.getAnnotation(DefaultMessageDigestAlgorithm.class);
		if (mda != null) {
			algorithm = mda.value();
		}
		algorithmCache.put(c, algorithm);
		return algorithm;
	}
	
	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int b : bytes) {
			sb.append(Character.forDigit(b >> 4 & 0xF, 16));
			sb.append(Character.forDigit(b & 0xF, 16));
		}
		return sb.toString();
	}
    
    /**
     * Generate a hex string of message digest from a param.
     * 
     * @param param input parameters
     * @return the hex string for the resulting hash value.
     */
    public static String toHexMessageDigest(MessageDigestParameter param) {
        return toHexString(toMessageDigest(param));
    }
}
