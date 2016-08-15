# Encrypt Sample

## Overview

This sample Spring Boot REST application simply outputs the clear text value for an
encrypted property.

The encryptied property is provided by the API, but could have just been done with the 
Spring Cloud Security CLI.

The API does not have to be running, decryption happens within the Sample app.

*You do have to use the same encrypt key!*

## Configuration

### Encrypted properties

The application.properties has:

```
sample.cipher={cipher}d657cba4408e4beafb341bce83430d6cc8817e9a226e2fdd329357fd8d02860b
```

### Key

The key is being set with bootstrap.properties:

```
# usually set on command line, as env var, but below provided values match README.md from API module
# or at a minimum is an external file read protected by DevOps
encrypt.key=darwin
```

## Demonstration

### Starting

```
gradle bootRun
```

### Return decrypted value

```
$ curl http://localhost:8080
6bx%S4=p#W
```
