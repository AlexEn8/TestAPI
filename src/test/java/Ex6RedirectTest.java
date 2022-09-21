import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex6RedirectTest {

    @Test
    public void testProba(){
        Boolean doRequest = true;
        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";

        while (doRequest) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(requestUrl)
                    .andReturn();
            String locationHeader = response.getHeader("Location");
            if (locationHeader != null) {
                requestUrl = locationHeader;
            } else {
                doRequest = false;
            }
            if (response.getStatusCode() == 200){
                System.out.println(response.getHeader("X-Host"));
            }
        }
    }
}
