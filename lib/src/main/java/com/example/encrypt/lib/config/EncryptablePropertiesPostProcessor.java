/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.example.encrypt.lib.config;

import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Post process property sources and make support encryptable properties.
 *
 * @author Mike Stuart
 */
public class EncryptablePropertiesPostProcessor implements BeanFactoryPostProcessor,
    EnvironmentAware, Ordered {

  private static final Logger log =
      LoggerFactory.getLogger(EncryptablePropertiesPostProcessor.class);

  private ConfigurableEnvironment env;


  private MapPropertySource makeEncryptable(MapPropertySource propertySource,
                                                ConfigurableListableBeanFactory registry) {
    StringEncryptor encryptor = registry.getBean(StringEncryptor.class);
    log.debug("Converting PropertySource {}[{}] to EncryptablePropertySource", propertySource
        .getName(), propertySource.getClass().getName());
    return new EncryptablePropertySource(propertySource, encryptor);
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws
      BeansException {
    log.debug("Post-processing PropertySource instances");
    MutablePropertySources propSources = env.getPropertySources();
    for (PropertySource<?> ps : propSources) {
      if (ps instanceof MapPropertySource) {
        PropertySource<?> eps = makeEncryptable((MapPropertySource) ps, beanFactory);
        propSources.replace(ps.getName(), eps);
      }
    }
  }

  @Override
  public void setEnvironment(Environment env) {
    this.env = (ConfigurableEnvironment) env;
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE; //.LOWEST_PRECEDENCE;
  }

}
