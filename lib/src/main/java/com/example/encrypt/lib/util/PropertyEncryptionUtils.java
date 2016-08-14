package com.example.encrypt.lib.util;

import org.jasypt.util.text.TextEncryptor;

/**
 * Utility class to encrypt/decrypt values in properties files which could be encrypted.
 *
 * <p>A value is considered "encrypted" when it appears prefixed by <tt>{cipher}*</tt>
 *
 * @author Mike Stuart
 */
public class PropertyEncryptionUtils {

  private static final String ENCRYPTED_VALUE_PREFIX = "{cipher}";

  private PropertyEncryptionUtils() {
    super();
  }

  public static boolean isEncryptedValue(final String value) {
    if (value == null) {
      return false;
    }
    final String trimmedValue = value.trim();
    return (trimmedValue.startsWith(ENCRYPTED_VALUE_PREFIX));
  }

  private static String getInnerEncryptedValue(final String value) {
    return value.substring(ENCRYPTED_VALUE_PREFIX.length());
  }

  public static String decrypt(final String encodedValue,
                               final org.jasypt.encryption.StringEncryptor encryptor) {
    return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
  }

  public static String encrypt(final String decodedValue,
                               final org.jasypt.encryption.StringEncryptor encryptor) {
    return ENCRYPTED_VALUE_PREFIX + encryptor.encrypt(decodedValue);
  }

}
