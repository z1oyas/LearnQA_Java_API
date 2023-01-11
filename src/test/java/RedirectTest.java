import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class RedirectTest {
    @Test
    public void testRedirect() {
        RestAssured.baseURI ="https://playground.learnqa.ru/api";
        String response = given()
                .redirects()
                .follow(true)
                .when()
                .get("/long_redirect")
                .getHeader("X-Host");

        System.out.println(response);
    }
}