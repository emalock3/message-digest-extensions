package com.github.emalock3.digest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

/**
 * @author Shinobu Aoki
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultMessageDigestAlgorithm {
	Algorithm value();
}
