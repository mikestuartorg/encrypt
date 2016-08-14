# Encrypt Library

## Overview

**This library was just a proof of concept that the JCE could be replaced by Bouncy Castle and
no other dependencies would be needed from Spring Cloud**

### Symmetric key

#### Non-cloud

Make an application.properties available on the classpath to all applications in an environment,
change the encrypt.key value so that it is unique between environments, and finally,
be sure the file is readable only by the PID owner (such as DevOps only account)

#### Cloud

Set an environment variable for ENCRYPT_KEY.


### Asymmetric key

TODO

## Usage

Include the following dependency:

```
  <dependency>
    <groupId>com.corelogic.tax</groupId>
    <artifactId>tax-encryption</artifactId>
    <version>${project.version}</version>
  </dependency>
```
 
 Apply the `@EnableEncryption` annotation to one of your Java Configuration files:

```
@SpringBootApplication
@EnableEncryption
public class Application {
```

### Trouble shooting

1. If you get `parsing a block mapping` error when trying to load yml properties
then ensure the property value is provided in single quotes, such as:

```
  username: '{cipher}8Tr5djcMMerw3XginwhprFkISv/RAYP7iwhTUyGbGAo='
```

Property files do not required quotes around the value.

2. If you get `Caused by: org.jasypt.exceptions.EncryptionOperationNotPossibleException`,
this is often caused by configuration problems, with most likely causes being either
@EnableEnryption not being applied to Spring Configuration or an attempt was made to decrypt a
value using a secret password that was not originally used to encrypt the value (such as, you are
trying to decrypt a value that was encrypted to work in another environment which uses a different
key)
