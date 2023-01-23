import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserAgentTest {

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    public void testUserAgent(String argument) {
        JsonPath request = RestAssured
                .given()
                .header("User-Agent",argument)
                .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                .jsonPath();

        String platform = request.get("platform");
        String browser = request.get("browser");
        String device = request.get("device");
        String[] ExpectedParams ={"Mobile;No;Android","Mobile;Chrome;iOS","Googlebot;Unknown;Unknown","Web;Chrome;No","Mobile;No;iPhone"};
        String Responceparams =platform+";"+browser+";"+device;
        String ParamsSet = "";
        switch (argument) {
            case  ("Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"):
                ParamsSet =ExpectedParams[0];
                break;
            case  ("Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1"):
                ParamsSet =ExpectedParams[1];
                break;
            case  ("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"):
                ParamsSet =ExpectedParams[2];
                break;
            case  ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0"):
                ParamsSet =ExpectedParams[3];
                break;
            case  ("Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"):
                ParamsSet =ExpectedParams[4];
                break;
        }
        assertEquals(ParamsSet,Responceparams,"params doesn't match");
        System.out.println(Responceparams);
        System.out.println(ParamsSet);
    }
static Stream<String> argsProviderFactory() {
    return Stream.of("Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
            "Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0",
            "Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
    }
}
