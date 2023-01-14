
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class LongRedirectTest {

    @Test
    public void testRedirect() {
        Response response  = given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();
        String location = response.getHeader("Location");
        int statusCode = response.statusCode();
        int redirectNumber =1;
        while (statusCode!=200){
            Response redirect = given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(location)
                    .andReturn();

            redirectNumber++;
            if (location!=null){
                location = redirect.getHeader("Location");
                statusCode =redirect.getStatusCode();
            }
            if (location==null) {
                String finalUri = redirect.getHeader("X-Host");
                System.out.println(statusCode+ " " +finalUri+" redirect number:"+redirectNumber);
                break;
            }
        }
    }
}