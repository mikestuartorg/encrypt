package com.example.encrypt.lib;

import com.example.encrypt.lib.util.PropertyEncryptionUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String encryptor with helpers for properties marked as encrypted with cipher prefix.
 *
 * @author Mike Stuart
 */
public class StringEncryptorImpl implements StringEncryptor {
  private static final Logger log = LoggerFactory.getLogger(StringEncryptorImpl.class);

  private org.jasypt.encryption.StringEncryptor delegate;

  public StringEncryptorImpl(org.jasypt.encryption.StringEncryptor delegate) {
    this.delegate = delegate;
  }

  @Override
  public String encrypt(String message) {
    return delegate.encrypt(message);
  }

  @Override
  public String decrypt(String encryptedMessage) {
    return delegate.decrypt(encryptedMessage);
  }

  public String encryptProperty(String message) {
    if (message == null || "".equals(message)) return message;
    try {
      return PropertyEncryptionUtils.encrypt(message, delegate);
    } catch (EncryptionOperationNotPossibleException e) {
      log.error("Failed to encrypt request", e);
      return message;
    }
  }

  public String decryptProperty(String encryptedMessage) {
    if (encryptedMessage == null || "".equals(encryptedMessage)) return encryptedMessage;
    try {
      return PropertyEncryptionUtils.decrypt(encryptedMessage, delegate);
    } catch (EncryptionOperationNotPossibleException e) {
      log.error("Failed to decrypt request", e);
      return encryptedMessage;
    }
  }
}
