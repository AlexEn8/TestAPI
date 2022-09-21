import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex6RedirectTest {

    @Test
    public void testEx6Redirect() {

        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";

        Response response = RestAssured
                .given()
                .redirects()
                .follow(true)
                .when()
                .get(requestUrl)
                .andReturn();

        System.out.println(response.getHeader("X-Host"));
    }
}


