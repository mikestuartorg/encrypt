/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.corelogic.tax.admin;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.security.KeyStore;

/**
 * The Spring Boot application - which is the primary Configuration class for the application that
 * enables AutoConfiguration and ComponentScan to automatically pickup all Spring components,
 * including other Configuration classes.
 *
 * @author Mike Stuart
 */
@SpringBootApplication
public class Application {

  /**
   * Main method, used to run the application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setShowBanner(false);
    app.run(args);
  }

}
