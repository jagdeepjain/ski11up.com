package org.skill1up.hsql.dbunit.example.properties;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

@Getter
public class DataSourceProperties {

  private static final Logger logger = Logger.getLogger(DataSourceProperties.class.getName());

  private String url;
  private String driverClassName;
  private String username;
  private String password;

  public DataSourceProperties() {
    String path = "src/test/resources/test.properties";
    try (InputStream input = Files.newInputStream(Paths.get(path))) {
      Properties prop = new Properties();
      prop.load(input);

      this.url = prop.getProperty("url");
      this.driverClassName = prop.getProperty("driver.class.name");
      this.username = prop.getProperty("username");
      this.password = prop.getProperty("password");

      logger.log(Level.INFO, "Properties loaded successfully");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error loading properties", e);
    }
  }
}
