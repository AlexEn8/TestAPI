import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex8TokensTest {

    @Test
    public void testGetRequestTypeWithParam() throws InterruptedException {

        JsonPath response1 = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response1.get("token");
        if (token == null) {
            System.out.println("The key 'token' is absent");
        } else{
            System.out.println("The token is: " +token);
        }

        JsonPath response2 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response2.get("status");
        assertEquals(status, "Job is NOT ready");
        if (status == null) {
            System.out.println("The key 'status' is absent");
        } else{
            System.out.println("The status is: " +status);
        }

        sleep(5000);

        JsonPath response3 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status2 = response3.get("status");
        assertEquals(status2, "Job is ready");
        if (status == null) {
            System.out.println("The key 'status' is absent");
        } else{
            System.out.println("The status is: " +status2);
        }
    }
}
