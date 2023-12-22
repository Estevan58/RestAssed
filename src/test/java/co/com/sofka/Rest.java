package co.com.sofka;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class Rest {


    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void getSinglelsertest() {
        ResponseData responseData = given()
                .get("unknown/5")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ResponseData.class);

        ResponseData.Data data = responseData.getData();

        System.out.println("################Datos################################");
        System.out.println("Nombre: " + data.getName());
        System.out.println("Año: " + data.getYear());
        System.out.println("Color: " + data.getColor());
        System.out.println("Pantone Value: " + data.getPantone_value());
    }


    @Test
    public void putSinglelsertest() {

        String nameupdated = given()
                .when()
                .body("{\n" +
                        "         \"name\": \"morpheus\",\n" +
                        "         \"job\": \"zion resident\" \n" +
                        "}")

                .put("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("job");
        assertThat(nameupdated, equalTo("zion resident"));

    }

    @Test
    public void postRequestTest() {


        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", " +
                        "\"password\": \"\" }")
                .when()
                .post("register")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}






