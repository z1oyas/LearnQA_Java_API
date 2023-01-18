import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CookieTest {

    @Test
    public void testCookie(){
        Response cookie_response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String,String> auth_cookies =cookie_response.getCookies();
        String ck = auth_cookies.keySet().toString();
        String cookie_key= ck.replaceAll("[^\\w]", "");
        String cv = auth_cookies.values().toString();
        String cookie_value= cv.replaceAll("[^\\w]", "");
        assertNotNull(auth_cookies,"no cookies");
        assertEquals("hw_value",cookie_value,"wrong value");
        assertEquals("HomeWork",cookie_key,"wrong key");
    }
}
