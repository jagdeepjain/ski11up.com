package org.skill1up.hsql.dbunit.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Logger;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skill1up.hsql.dbunit.example.properties.DataSourceProperties;

/**
 * DBUnitExampleTest is a test class that uses DBUnit to test database operations. It initializes
 * the database schema and data, and performs various tests on the CONTACTS table.
 */
public class DBUnitExampleTest extends DBTestCase {

  private static final Logger logger = Logger.getLogger(DBUnitExampleTest.class.getName());

  /**
   * Constructor for DBUnitExampleTest. Initializes the database and sets DBUnit properties.
   *
   * @throws IOException if an I/O error occurs
   * @throws ClassNotFoundException if the database driver class is not found
   * @throws SQLException if a database access error occurs
   * @throws SqlToolError if an error occurs while executing SQL scripts
   */
  public DBUnitExampleTest()
      throws IOException, ClassNotFoundException, SQLException, SqlToolError {
    super();
    // Initialize the HSQLDB database
    initializeDatabase();
    // Set the DBUnit properties
    DataSourceProperties properties = new DataSourceProperties();
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getUrl());
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, properties.getDriverClassName());
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getUsername());
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getPassword());
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "PUBLIC");
    logger.info("DBUnit properties set successfully.");
  }

  /**
   * Initializes the database by executing the schema and data SQL scripts.
   *
   * @throws ClassNotFoundException if the database driver class is not found
   * @throws SQLException if a database access error occurs
   * @throws IOException if an I/O error occurs
   * @throws SqlToolError if an error occurs while executing SQL scripts
   */
  private void initializeDatabase()
      throws ClassNotFoundException, SQLException, IOException, SqlToolError {
    DataSourceProperties properties = new DataSourceProperties();
    Class.forName(properties.getDriverClassName());
    Connection conn =
        DriverManager.getConnection(
            properties.getUrl(), properties.getUsername(), properties.getPassword());
    SqlFile schema = new SqlFile(Paths.get("src/test/resources/schema.sql").toFile());
    schema.setConnection(conn);
    schema.execute();
    schema.closeReader();
    SqlFile data = new SqlFile(Paths.get("src/test/resources/data.sql").toFile());
    data.setConnection(conn);
    data.execute();
    data.closeReader();
  }

  /**
   * Returns the dataset for the test.
   *
   * @return the dataset
   * @throws Exception if an error occurs while loading the dataset
   */
  @Override
  protected IDataSet getDataSet() throws Exception {
    try (InputStream xmlFile =
        Files.newInputStream(Paths.get("src/test/resources/expected-dataset.xml"))) {
      return new FlatXmlDataSetBuilder().build(xmlFile);
    }
  }

  /**
   * Returns the dataset for the test from the specified file path.
   *
   * @param filePath the file path of the dataset
   * @return the dataset
   * @throws Exception if an error occurs while loading the dataset
   */
  protected IDataSet getDataSet(String filePath) throws Exception {
    try (InputStream xmlFile = Files.newInputStream(Paths.get(filePath))) {
      return new FlatXmlDataSetBuilder().build(xmlFile);
    }
  }

  /**
   * Returns the setup operation for the test.
   *
   * @return the setup operation
   * @throws Exception if an error occurs
   */
  @Override
  protected DatabaseOperation getSetUpOperation() throws Exception {
    return DatabaseOperation.CLEAN_INSERT;
  }

  /**
   * Returns the teardown operation for the test.
   *
   * @return the teardown operation
   * @throws Exception if an error occurs
   */
  @Override
  protected DatabaseOperation getTearDownOperation() throws Exception {
    return DatabaseOperation.NONE;
  }

  /**
   * Tests the number of records in the CONTACTS table.
   *
   * @throws Exception if an error occurs during the test
   */
  @Test
  public void testRowsCount() throws Exception {
    int actualNumberOfRecords = getDataSet().getTable("CONTACTS").getRowCount();
    int expectedNumberOfRecords =
        getConnection().createDataSet().getTable("CONTACTS").getRowCount();
    Assertions.assertEquals(expectedNumberOfRecords, actualNumberOfRecords);
  }

  /**
   * Tests the first name of the contact with ID 1000.
   *
   * @throws Exception if an error occurs during the test
   */
  @Test
  public void testContactNameForGivenID() throws Exception {
    Statement statement = getConnection().getConnection().createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTACTS WHERE ID = 1000");
    String actualDataValue = "";
    while (resultSet.next()) {
      actualDataValue = resultSet.getString("FIRST_NAME");
    }
    ITable expectedData =
        getDataSet("src/test/resources/expected-contact.xml").getTable("CONTACTS");
    String expectedDataValue = (String) expectedData.getValue(0, "FIRST_NAME");
    Assertions.assertEquals(expectedDataValue, actualDataValue);
  }

  /**
   * Tests the data in the CONTACTS table against the expected dataset.
   *
   * @throws Exception if an error occurs during the test
   */
  @Test
  public void testContactsTableDataSet() throws Exception {
    ITable actualDataSet = getDataSet().getTable("CONTACTS");
    ITable expectedDataSet = getConnection().createDataSet().getTable("CONTACTS");
    Assertion.assertEquals(expectedDataSet, actualDataSet);
  }
}
