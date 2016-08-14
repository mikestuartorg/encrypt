package com.example.encrypt.lib.annotation;

import com.example.encrypt.lib.config.EncryptionConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that pulls in Java configuration for Encryption support.
 *
 * @author Mike Stuart
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@Import({EncryptionConfiguration.class})
public @interface EnableEncryption {
}
