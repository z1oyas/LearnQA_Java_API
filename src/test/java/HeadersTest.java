import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeadersTest {

    @Test
    public void testHeaders(){
        Response cookie_response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        List headers =cookie_response.getHeaders().asList();
        assertNotNull(headers,"no headers");
        String target_header ="";
        for (Object h:headers){
            if(h.toString().contains("homework")){
                target_header = h.toString();
            }
        }

        int k = target_header.indexOf("=");
        String header_value = target_header.substring(k+1);
        String header_key = target_header.substring(0,k);
        assertEquals(header_value,"Some secret value","wrong header value");
        assertEquals(header_key,"x-secret-homework-header","wrong header");
    }
}
