import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex6RedirectTestDraft {

    @Test
    public void testEx6Redirect(){
        String url = "https://playground.learnqa.ru/api/long_redirect";
        Response response = RestAssured

                .given()
                .redirects()
                .follow(false)
                .when()
                .get(url)
                .andReturn();
        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);


        Response response2 = RestAssured

                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru")
                .andReturn();
        String locationHeader2 = response2.getHeader("Location");
        System.out.println(locationHeader2);

        Response response3 = RestAssured

                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://learnqa.ru")
                .andReturn();
        String locationHeader3 = response3.getHeader("Location");
        System.out.println(locationHeader3);

        Response response4 = RestAssured

                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://www.learnqa.ru")
                .andReturn();
        String locationHeader4 = response4.getHeader("Location");
        System.out.println(locationHeader4);
        System.out.println(response4.getHeader("X-Host"));
    }

    public void printDestinationUrl(){
        String url = "https://playground.learnqa.ru/api/long_redirect";
        Response response = RestAssured

                .given()
                .redirects()
                .follow(false)
                .when()
                .get(url)
                .andReturn();
        String locationHeader = response.getHeader("Location");
        if (locationHeader != null){
            url = locationHeader;
        }else {
            System.out.println(response.getHeader("X-Host"));
        }
    }
}
