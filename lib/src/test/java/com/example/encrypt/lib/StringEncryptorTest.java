package com.example.encrypt.lib;

import com.example.encrypt.lib.util.PropertyEncryptionUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Test the encrypt/decryptProperty helpers.
 *
 * @author Mike Stuart
 */
public class StringEncryptorTest {

  private StandardPBEStringEncryptor encryptor;

  @Before
  public void setUp() {
    encryptor = new StandardPBEStringEncryptor();
    // required JCE to be installed
//    encryptor.setProvider(new BouncyCastleProvider());
//    encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
    encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    encryptor.setPassword("password");
  }

  @Test
  public void testEncryptProperty() {
    StringEncryptor stringEncryptor = new StringEncryptorImpl(encryptor);

    String encodedValue = stringEncryptor.encryptProperty("jdoe");
    System.out.println(encodedValue);
    assertThat(PropertyEncryptionUtils.isEncryptedValue(encodedValue), is(true));
    assertThat(PropertyEncryptionUtils.decrypt(encodedValue, encryptor), is("jdoe"));
  }

  @Test
  public void testDecryptProperty() {
    StringEncryptor stringEncryptor = new StringEncryptorImpl(encryptor);

//    String decodedValue = stringEncryptor.decryptProperty("{cipher}uyoBaBLiO0VeDn9XwxueMrgPqZsmNtIxYlTj2rtOGnM");
    String decodedValue = stringEncryptor.decryptProperty("{cipher}KDPGx33t8hQZ1RTI3jLcHg==");
    System.out.println(decodedValue);
    assertThat(PropertyEncryptionUtils.isEncryptedValue(decodedValue), is(false));
    assertThat(decodedValue, is("jdoe"));
  }

}
