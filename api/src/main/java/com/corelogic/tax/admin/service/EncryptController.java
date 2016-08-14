/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.corelogic.tax.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.encrypt.EncryptorFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST interface for staff to encrypt values to place in property files.
 *
 * @author Mike Stuart
 */
@RestController
public class EncryptController {
  private static final Logger log = LoggerFactory.getLogger(EncryptController.class);

  @Value("${encrypt.key}")
  private String key;

  // DO NOT expose GET as this is too error prone should URL encoded not be used when needed

  @RequestMapping(value = "/api/service/encrypt", method = RequestMethod.POST, consumes = "text/plain")
//  @PreAuthorize("hasRole('ROLE_USER')")
  public String encryptBody(@RequestBody String text) {
    log.debug("Encrypting");
    TextEncryptor encryptor = new EncryptorFactory().create(key);
    return "{cipher}" + encryptor.encrypt(text);
  }

  // DO NOT expose the decrypt method

}
