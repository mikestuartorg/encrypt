/*
 * Copyright (c) 2015 CoreLogic.  All rights reserved.
 */

package com.corelogic.tax.admin.config;

/**
 * Application constants.
 *
 * @author Mike Stuart
 */
public final class Constants {

  private Constants() {
  }

  public static final String SPRING_PROFILE_LOCAL = "local";
  public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
  public static final String SPRING_PROFILE_TEST = "test";
  public static final String SPRING_PROFILE_STAGE = "stage";
  public static final String SPRING_PROFILE_PRODUCTION = "prod";
  public static final String SPRING_PROFILE_CLOUD = "cloud";

  public static final String SYSTEM_ACCOUNT = "system";

  public static final long DEFAULT_TIMEOUT = 20;

}
