# Encrypt API

## Overview

*You must have the [JCE](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) installed.* 

### Exposing via HTTPS only

The build creates a keystore.jks for you in the target folder. You must be in the target folder to
run `java -jar encryption-1.jar` so that the keystore file can be found.

Otherwise, you can provide a location (such as DevOps could provide a cert that is not self-signed):

``` 
java -Dserver.ssl.key-store=target/keystore.jks -jar target/encryption-1.jar
```

### Symmetric key

When deployed to a managed environment such as dev, test, etc., DevOps should provide an 
application.properties file with a `encrypt.key=` set with a secret value.  This file should then be
made read-only by just the DevOps group so that it can not be seen by others.

If set from command line:

```
ENCRYPT_KEY=darwin java -Dserver.ssl.key-store=target/keystore.jks -jar target/encryption-1.jar
```

## Using

Encrypt a property using the REST endpoint appropriate for the environment. Use a REST client
like SoapUI, Postman, or curl:

```
$ curl -k -i -H "Content-Type:text/plain" -d '6bx%S4=p#W' https://localhost:8443/api/service/encrypt
/localhost:8443/api/service/encrypt
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 72
Date: Sun, 14 Aug 2016 13:56:38 GMT

{cipher}d657cba4408e4beafb341bce83430d6cc8817e9a226e2fdd329357fd8d02860b
```

__IMPORTANT NOTES:__

1. You will get a different value each time if you submit this request more than once, but all
responses would decrypt to the original clear text.
 
2. If an environment uses a different key, then an encrypted value will not decrypt in these
different environments even if they have the same clear text value.

#### Best Practices

Encrypt both user IDs and associated passwords. Not only is this more secure, it will save you in
case something is off with just an encrypted password and in such a case where a user ID is not also
encrypted -- it will most likely lock the account with failed login attempts trying to fill a pool,
etc. But with an encrypted user ID this problem will not happen.

```
spring:
    datasource:
        username: '{cipher}4c03abeb46629137ae60d635252ca9fab673590a36e2f082e335ef38dce593c1'
        password: '{cipher}21934d7e29629afd5d4849e9beb201ae3ca6dc11753d31ffe085a3cc562039a3'
```

#### Dependencies

Add dependency management:

```
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-parent</artifactId>
                <version>Brixton.SR4</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
and the following dependencies:

```
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter</artifactId>
         </dependency>
         <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-rsa</artifactId>
            <version>1.0.2.RELEASE</version>
        </dependency>
```
