import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class TokenTest {
    @Test
    public void testToken() throws InterruptedException {
        RestAssured.baseURI ="https://playground.learnqa.ru/ajax/api/longtime_job";

        JsonPath createTask = given()
                .when()
                .get()
                .jsonPath();
        String token = createTask.get("token");

        JsonPath taskStatus = given()
                .when()
                .queryParam("token",token)
                .get()
                .jsonPath();
        String status = taskStatus.get("status");

        Thread.sleep(15000);
        JsonPath taskResponse = given()
                .when()
                .queryParam("token", token)
                .get()
                .jsonPath();
        String answer = taskResponse.get("result");
        String finalStatus = taskResponse.get("status");

        if (((status.equals("Job is NOT ready"))& finalStatus.equals("Job is ready"))&(answer!=null))
        {
            System.out.println("test passed");
        }
        else
        {
            System.out.println("test failed");
        }
    }
}
