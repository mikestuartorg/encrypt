/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.example.encrypt.lib.config;

import com.example.encrypt.lib.util.PropertyEncryptionUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MapPropertySource;

/**
 * Wrapper to a PropertySource that checks if a property value is encrypted and decrypts if needed.
 *
 * <p>Encrypted properties are indicated with a "{cipher}" prefix.
 *
 * @author Mike Stuart
 */
public class EncryptablePropertySource extends MapPropertySource {
  private static final Logger log = LoggerFactory.getLogger(EncryptablePropertySource.class);

  private final MapPropertySource delegate;
  private final StringEncryptor encryptor;

  public EncryptablePropertySource(MapPropertySource delegate, StringEncryptor encryptor) {
    super(delegate.getName(), delegate.getSource());
    if (delegate == null) {
      throw new IllegalArgumentException("MapPropertySource delegate cannot be null");
    }
    if (encryptor == null) {
      throw new IllegalArgumentException("StringEncryptor cannot be null");
    }
    this.delegate = delegate;
    this.encryptor = encryptor;
  }

  @Override
  public Object getProperty(String name) {
    Object value = delegate.getProperty(name);
    if (value instanceof String && PropertyEncryptionUtils.isEncryptedValue((String) value)) {
      log.trace("Decrypting {}", name);
      value = PropertyEncryptionUtils.decrypt((String) value, encryptor);
    }
//    else {
//      log.trace("getProperty({}) returns value {}", name, value);
//    }
    return value;
  }

}
