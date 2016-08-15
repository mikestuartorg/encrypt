package com.example.encrypt.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mike on 8/14/16.
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
    app.run(args);
  }

}