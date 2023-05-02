package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class EmployeeSteps {

    private RequestSpecification request;
    private Response response;

    @Given("^employees endpoint$")
    public void employees_endpoint() throws Throwable {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/";
        RestAssured.basePath = "v1";
    }

    @When("^i request all employees$")
    public void i_request_all_employees() throws Throwable {
         request = given().contentType(ContentType.JSON);
         response = request.when().get("employees");
    }

    @Then("^I should get a (\\d+) status$")
    public void i_should_get_a_status(int status) throws Throwable {
        response.then().statusCode(status);
    }

    @When("^add new employee$")
    public void add_new_employee() throws Throwable {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "test");
        params.put("salary", "60000");
        params.put("age", "60");

        request = given().contentType(ContentType.JSON).body(params);
        response = request.when().post("/create");


//        JSONObject requestParams = new JSONObject();
//        requestParams.put("name", "test");
//        requestParams.put("salary", "60000");
//        requestParams.put("salary", "60000");
//        requestParams.put("age", "60");
//        request.body(requestParams.toJSONString());
//        Response response = request.put("/create");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());


        request = given().contentType(ContentType.JSON).body(params);
        response = request.when().post();
    }


    @When("delete an employee")
    public void delete_an_employee() {
        request = given().contentType(ContentType.JSON);
        response = request.when().delete("employee/2");
    }

    @Then("employee was delete")
    public void employee_was_delete() {
        response.then().statusCode(200);
    }

}