package com.example.encrypt.lib;

/**
 * Created by mike on 6/16/15.
 */
public interface StringEncryptor extends org.jasypt.encryption.StringEncryptor {

  /**
   * Encrypt the input property
   *
   * @param property the property to be encrypted
   * @return the result of encryption
   */
  public String encryptProperty(String property);


  /**
   * Decrypt an encrypted property
   *
   * @param encryptedProperty the encrypted property to be decrypted
   * @return the result of decryption
   */
  public String decryptProperty(String encryptedProperty);

}
