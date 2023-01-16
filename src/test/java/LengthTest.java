import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthTest {
    @Test
    public void testStringLength(){
        String targetString = "qecdfvb";
        int minLength = 15;
        assertTrue(minLength<targetString.length(),"String too short");
    }
}
