/** @author jagdeepjain */
package com.ski11up.weather.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.response.Response;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeatherCurrentTest {

  protected final Logger log = LogManager.getLogger(this.getClass());

  @Test
  @DisplayName("Request Status Code, Content Type.")
  public void requestStatusContentTypeTest() {
    given()
        .queryParam("access_key", "d637155faf02fb9d9c05d8bd0ba6438b")
        .queryParam("query", "New York")
        .when()
        .post("http://api.weatherstack.com/current")
        .then()
        .statusCode(200)
        .contentType("application/json");
  }

  @Test
  @DisplayName("Request.")
  public void requestTest() {
    /*
     * TODO: Assertions for:
     *  1. request.localtime
     *  2. current.localtime_epoch
     */

    given()
        .queryParam("access_key", "d637155faf02fb9d9c05d8bd0ba6438b")
        .queryParam("query", "New York")
        .when()
        .post("http://api.weatherstack.com/current")
        .then()
        .statusCode(200)
        .assertThat()
        .body(
            "request.type",
            equalTo("City"),
            "request.query",
            equalTo("New York, United States of America"),
            "request.language",
            equalTo("en"),
            "request.unit",
            equalTo("m"));
  }

  @Test
  @DisplayName("Location.")
  public void locationTest() {
    String currentDateTime = getCurrentDateTime("America/New_York");

    given()
        .queryParam("access_key", "d637155faf02fb9d9c05d8bd0ba6438b")
        .queryParam("query", "New York")
        .when()
        .post("http://api.weatherstack.com/current")
        .then()
        .statusCode(200)
        .assertThat()
        .body(
            "location.name",
            equalTo("New York"),
            "location.country",
            equalTo("United States of America"),
            "location.region",
            equalTo("New York"),
            "location.lat",
            equalTo("40.714"),
            "location.lon",
            equalTo("-74.006"),
            "location.timezone_id",
            equalTo(currentDateTime),
            "location.utc_offset",
            equalTo("-4.0"));
  }

  @Test
  @DisplayName("Current.")
  public void currentTest() {
    /*
     * TODO: Assertions for:
     *  1. current.observation_time
     *  2. current.temperature
     *  3. current.weather_code
     *  4. current.weather_descriptions
     *  5. current.weather_icons
     */
    String observationTime = getTime("America/New_York");

    given()
        .queryParam("access_key", "d637155faf02fb9d9c05d8bd0ba6438b")
        .queryParam("query", "New York")
        .when()
        .post("http://api.weatherstack.com/current")
        .then()
        .statusCode(200)
        .assertThat()
        .body(
            "current.wind_speed",
            greaterThanOrEqualTo(0),
            "current.wind_degree",
            greaterThanOrEqualTo(0),
            "current.wind_dir",
            oneOf("EW", "EN", "ES", "WE",
                "WN", "WS", "WE", "NE", "NW", "NS", "SE", "SW", "SN"),
            "current.pressure",
            greaterThan(0),
            "current.precip",
            greaterThanOrEqualTo(0),
            "current.humidity",
            greaterThan(0),
            "current.cloudcover",
            greaterThanOrEqualTo(0),
            "current.feelslike",
            greaterThan(0),
            "current.uv_index",
            greaterThan(0),
            "current.visibility",
            greaterThan(0),
            "current.is_day",
            oneOf("yes", "no"));
  }

  @Test
  @DisplayName("Post Param as HashMap.")
  public void apiCallAsMapTest() {
    Map<String, String> param = new HashMap<>();

    param.put("access_key", "d637155faf02fb9d9c05d8bd0ba6438b");
    param.put("query", "New York");

    given()
        .when()
        .post(
            "http://api.weatherstack.com/current" +
                "?access_key={access_key}&query={query}", param)
        .then()
        .statusCode(200)
        .contentType("application/json")
        .body("request.type", equalTo("City"));
  }

  @Test
  @DisplayName("Schema Validation.")
  public void schemaValidationTest() {
    Map<String, String> param = new HashMap<>();
    param.put("access_key", "d637155faf02fb9d9c05d8bd0ba6438b");
    param.put("query", "New York");

    Response response =
        given()
            .when()
            .post(
                "http://api.weatherstack.com/current"
                    + "?access_key={access_key"
                    + "}&query={query}",
                param);
    String responseBody = response.getBody().asString();

    assertThat(responseBody, matchesJsonSchemaInClasspath("schema.json"));
  }

  private String getCurrentDateTime(String timeZone) {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
    String currentDateTime = sdf.format(date);

    return currentDateTime;
  }

  private String getTime(String timeZone) {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
    String currentDateTime = sdf.format(date);

    return currentDateTime;
  }

  private long getEpochTime(String timeZone) {
    return Clock.system(TimeZone.getTimeZone(timeZone).toZoneId()).millis() / 1000;
  }
}
