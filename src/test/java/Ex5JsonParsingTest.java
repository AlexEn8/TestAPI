import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

public class Ex5JsonParsingTest {

    @Test
    public void testEx5JsonResponse(){

        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        ArrayList<Map<String, String>> messages = response.get("messages");
        Map<String, String> message2 = messages.get(1);
        String ex5message = message2.get("message");
        System.out.println(ex5message);
    }
}
