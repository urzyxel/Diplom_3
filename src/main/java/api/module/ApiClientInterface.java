package api.module;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public interface ApiClientInterface {

    static void initializeBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    default Response get(String urlMethod, Map<String, String> headers, Map<String, String> params) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .log().all()
                .headers(headers)
                .params(params)
                .when()
                .get(urlMethod);
    }

    default Response post(String urlMethod, Map<String, String> headers, Map<String, String> params, Object json) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .log().all()
                .headers(headers)
                .params(params)
                .body(json)
                .when()
                .post(urlMethod);
    }

    default Response patch(String url, Map<String, String> headers, Map<String, String> params, Object body) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .log().all()
                .headers(headers)
                .params(params)
                .body(body)
                .when()
                .patch(url);
    }

    default Response delete(String urlMethod, Map<String, String> headers, Map<String, String> params) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .log().all()
                .headers(headers)
                .params(params)
                .when()
                .delete(urlMethod);
    }
}
