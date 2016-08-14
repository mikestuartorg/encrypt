/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.example.encrypt.lib.config;

import com.example.encrypt.lib.StringEncryptor;
import com.example.encrypt.lib.StringEncryptorImpl;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Encryption configuration.
 *
 * @author Mike Stuart
 */
public class EncryptionConfiguration implements EnvironmentAware {
  private static final Logger log = LoggerFactory.getLogger(EncryptionConfiguration.class);

  private RelaxedPropertyResolver propertyResolver;

  @Override
  public void setEnvironment(Environment env) {
    this.propertyResolver = new RelaxedPropertyResolver(env, "encrypt.");
  }

  @Bean
  public static EncryptablePropertiesPostProcessor enableEncryptedPropertiesPostProcessor() {
    return new EncryptablePropertiesPostProcessor();
  }

  @Bean
  public StringEncryptor stringEncryptor() {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setProvider(new BouncyCastleProvider());
    encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
    encryptor.setPasswordCharArray(propertyResolver.getProperty("key", "secret").toCharArray());
    return new StringEncryptorImpl(encryptor);
  }

}
