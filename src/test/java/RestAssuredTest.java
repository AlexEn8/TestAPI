import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredTest {

    @Test
    public void testRestAssured(){
        Map<String, String> params = new HashMap<>();
        params.put("name", "Alex");

        Response response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void testJsonResponse(){
        Map<String, String> params = new HashMap<>();
        params.put("name", "Alex");

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String name = response.get("answer2");
        if (name == null) {
            System.out.println("The key 'answer2' is absent");
        } else{
            System.out.println(name);
        }
    }

    @Test
    public void testGetRequestTypeWithParam(){

        Response response = RestAssured
                .given()
                .queryParam("param1", "value1")
                .queryParam("param2", "value2")
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }

    @Test
    public void testPostRequestTypeWithParam(){

        Response response = RestAssured
                .given()
                .body("param1=value1&param2=value2")
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }

    @Test
    public void testPostRequestTypeWithParamJson(){

        Response response = RestAssured
                .given()
                .body("{\"param1\":\"value1\", \"param2\":\"value2\"}")
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }

    @Test
    public void testPostRequestTypeWithParamJsonMap(){

        Map<String, Object> body = new HashMap<>();
        body.put("param1", "value1");
        body.put("param2", "value2");

        Response response = RestAssured
                .given()
                .body(body)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }

    @Test
    public void testResponseCode200(){

        Response response = RestAssured

                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void testResponseCode500(){

        Response response = RestAssured

                .get("https://playground.learnqa.ru/api/get_500")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void testResponseCode303(){

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void testHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("myHeader1", "myValue1");
        headers.put("myHeader2", "myValue2");

        Response response = RestAssured
                .given()
                .headers(headers)
                .when()
                .get("https://playground.learnqa.ru/api/show_all_headers")
                .andReturn();

        response.prettyPrint();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
    }

    @Test
    public void testRedirectHeader(){

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        response.prettyPrint();

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);
    }

    @Test
    public void testCookies(){
        Map<String,String> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .get("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        System.out.println("\nPretty text:");
        response.prettyPrint();

        System.out.println("\nHeaders:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        System.out.println("\nCookies:");
        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);

        System.out.println("\nOnly Cookie's value:");
        String responseValueCookie = response.getCookie("auth_cookie");
        System.out.println(responseValueCookie);
    }

    @Test
    public void testPassCookieForGet(){
        Map<String,String> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response responseToGetCookie = RestAssured
                .given()
                .body(data)
                .when()
                .get("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        String responseCookie = responseToGetCookie.getCookie("auth_cookie");

        Map<String, String> cookies = new HashMap<>();
        if(responseCookie != null){
            cookies.put("auth_cookie", responseCookie);
        }

        Response responseToCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();
        responseToCheck.print();
        responseToCheck.prettyPrint();
    }
}
