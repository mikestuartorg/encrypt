package com.example.encrypt.lib;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Command line interface for encryption.
 *
 * @author Mike Stuart
 */
public class Main {

  private StandardPBEStringEncryptor encryptor;

  /**
   * Main method.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption(Option.builder("p").longOpt("password").hasArg().build());
    OptionGroup action = new OptionGroup();
    action.addOption(Option.builder("e").longOpt("encrypt").build());
    action.addOption(Option.builder("d").longOpt("decrypt").build());
    action.setRequired(true);
    options.addOptionGroup(action);
    try {
      CommandLine line = parser.parse(options, args);
      String arg = line.getArgList().get(0);
      Main main = new Main(line.getOptionValue("p"));
      System.out.println(line.hasOption("e") ? main.encrypt(arg) : main.decrypt(arg));
    }
    catch(ParseException e) {
      System.err.println(e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("tax-encryption", options);
    }
  }

  public Main(String password) {
    encryptor = new StandardPBEStringEncryptor();
    encryptor.setProvider(new BouncyCastleProvider());
    encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
    encryptor.setPassword(password != null ? password : "secret");
  }

  public String encrypt(String arg) {
    return encryptor.encrypt(arg);
  }

  public String decrypt(String arg) {
    return encryptor.decrypt(arg);
  }

}
