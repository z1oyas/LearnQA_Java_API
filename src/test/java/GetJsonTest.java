import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;

public class GetJsonTest {
    @Test
    public void testGetJson() {
        RestAssured.baseURI ="https://playground.learnqa.ru/api";
        Response response = given().
                get("/get_json_homework");

        JsonPath answer = new JsonPath(response.asString());
        String message = answer.getString("messages[1].message");
        System.out.println(message);
    }
}
