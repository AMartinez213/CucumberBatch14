package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.management.resource.ResourceRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
//    CRUD operations we perform
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzM4Mzk4MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3Mzg4MzAyMiwidXNlcklkIjoiNDczOCJ9.KXb3O35V1pM9m8xVuA6fcCrJP3AMCf-jnCj7sTkrWGo";
    static String employee_id;

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification preparedRequest=
                given().header("Content-Type", "application/json").
                        header("Authorization", token).
                        queryParam("employee_id",employee_id);

        Response response=preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();



    }

    @Test
//    to create a regular/normal employee
    public void acreateEmployee(){
//        prepare the request
//        POST
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "  \"emp_firstname\": \"Kobe08\",\n" +
                "  \"emp_lastname\": \"Bryant08\",\n" +
                "  \"emp_middle_name\": \"Bean08\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2000-06-14\",\n" +
                "  \"emp_status\": \"employed\",\n" +
                "  \"emp_job_title\": \"CEO\"\n" +
                "}");

//        hitting the endpoint
        Response response = preparedRequest.when().post("/createEmployee.php");
//        printing the response in console
        response.prettyPrint();

//        assertions and validation
//        verifying the correct status code of the request
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("Bean08"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Kobe08"));
        response.then().assertThat().body("Employee.emp_lastname",equalTo("Bryant08"));
        response.then().assertThat().body("Message",equalTo("Employee Created"));

//        verify the response headers
        response.then().assertThat().header("Server","Apache/2.4.39 (Win64) PHP/7.2.18");

//        to get the employee id from the body
       employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println("Employee ID # = "+employee_id);
    }
    @Test
    public void cUpdateEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Allan\",\n" +
                        "  \"emp_lastname\": \"GOAT\",\n" +
                        "  \"emp_middle_name\": \"R41D3R\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2003-01-18\",\n" +
                        "  \"emp_status\": \"Probation\",\n" +
                        "  \"emp_job_title\": \"Boss\"\n" +
                        "}");
        Response response=preparedRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);

    }

}
