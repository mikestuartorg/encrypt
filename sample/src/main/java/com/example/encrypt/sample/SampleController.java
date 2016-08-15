package com.example.encrypt.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mike on 8/14/16.
 */
@RestController
public class SampleController {

  @Value("${sample.cipher}")
  private String encrypt;

  @RequestMapping("/")
  public String index() {
    return encrypt;
  }
}
