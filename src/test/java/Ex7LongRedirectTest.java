import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex7LongRedirectTest {

    @Test
    public void testEx7LongRedirect(){
        Boolean doRequest = true;
        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";
        int amountOfRedirects = 0;

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
                amountOfRedirects +=1;
            } else {
                doRequest = false;
            }
        }
        System.out.println(amountOfRedirects);
    }
}


