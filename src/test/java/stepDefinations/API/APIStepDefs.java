package stepDefinations.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import libraries.APIEndPoints;
import libraries.ConfigReader;
import libraries.pojoClasses.*;
import org.testng.Assert;
import stepDefinations.TestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIStepDefs extends TestBase {

    static Response response;
    RequestSpecification requestSpecification;

    @Before
    public void setUp() throws IOException
    {
        RestAssured.baseURI = ConfigReader.getConfigValue("URL_API");
        requestSpecification= RestAssured.given();
    }



    @When("I submit GET request for listUserAPI")
    public void iSubmitGETRequestForUsersAPI() throws IOException {

        response = given().log().all().spec(requestSpecification).param("page","1").when().get(APIEndPoints.userApi.getResource()).then().log().all().extract().response();

      //   logger.info("Response2: "+response.asString());
//        logger.info("Response3: "+response.prettyPrint());

    }

    @Then("I verify status code is {int}")
    public void iVerifyStatusCodeIs(int arg0) {
        Assert.assertEquals(response.getStatusCode(),arg0);

    }

    @Then("I get a list of users")
    public void iGetAListOfUsers() {
        Users users=response.getBody().as(Users.class);
        System.out.println("List of users");
        System.out.println("=============================================\n");
        for (User user:users.getData())
        {

            System.out.println("First Name: "+user.getfirst_name());
            System.out.println("Last Name: "+user.getlast_name());
            System.out.println("Email: "+user.getEmail());
            System.out.println("Avatar: "+user.getAvatar());
            System.out.println("--------------------------------------------\n");
        }
    }


    @When("I submit GET request for invalidUserApi")
    public void iSubmitGETRequestForInvalidUserApi() {
        response = given().log().all().spec(requestSpecification).when().get(APIEndPoints.userApi.getResource()+"/23").then().log().all().extract().response();

    }

    @When("I submit GET request for singleUserApi")
    public void iSubmitGETRequestForSingleUserApi() {
        response = given().log().all().spec(requestSpecification).when().get(APIEndPoints.userApi.getResource()+"/2").then().log().all().extract().response();
    }

    @Then("I get a single user data")
    public void iGetASingleUserData() {
        SingleUser singleUserdata=response.getBody().as(SingleUser.class);
        System.out.println("======================================================================");
        System.out.println("user data for userid: "+singleUserdata.getData().getId());
        System.out.println("First Name: "+singleUserdata.getData().getfirst_name());
        System.out.println("Last Name: "+singleUserdata.getData().getlast_name());
        System.out.println("Email: "+singleUserdata.getData().getEmail());
        System.out.println("Avatar: "+singleUserdata.getData().getAvatar());
        System.out.println("--------------------------------------------\n");
    }


    @When("I submit POST request with details {string} {string}")
    public void iSubmitPOSTRequestWithDetails(String arg0, String arg1) {
        PostUserRequest request=new PostUserRequest();
        request.setName("morpheus");
        request.setJob("leader");
        response=given().log().all().spec(requestSpecification).contentType(ContentType.JSON).body(request).when().post(APIEndPoints.userApi.getResource()).then().log().all().extract().response();
    }

    @And("verify response contains correct posted details {string} {string}")
    public void verifyResponseContainsCorrectPostedDetails(String arg0, String arg1) {
        PostUserResponse postResponse=response.getBody().as(PostUserResponse.class);
        Assert.assertEquals(postResponse.getName(),arg0);
        Assert.assertEquals(postResponse.getJob(),arg1);
    }

    @When("I submit PUT request with details {string} {string} {string}")
    public void iSubmitPUTRequestWithDetails(String arg0, String arg1, String arg2) {
        PostUserRequest request=new PostUserRequest();
        request.setName("morpheus");
        request.setJob("zion resident");
        response=given().log().all().spec(requestSpecification).contentType(ContentType.JSON).body(request).when().put(APIEndPoints.userApi.getResource()+"/2").then().log().all().extract().response();

    }

    @When("I submit DELETE request for id {string}")
    public void iSubmitDELETERequestForId(String arg0) {
        response=given().log().all().spec(requestSpecification).when().delete(APIEndPoints.userApi.getResource()+"/"+arg0).then().log().all().extract().response();
    }


    @When("I submit login request with email {string} and password {string}")
    public void iSubmitLoginRequestWithEmailAndPassword(String arg0, String arg1) {
        if(arg0.isEmpty())
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"password\": \""+arg1+"\"}").when().post(APIEndPoints.loginApi.getResource()).then().log().all().extract().response();
        }
        else if(arg1.isEmpty())
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"email\": \""+arg0+"\"}").when().post(APIEndPoints.loginApi.getResource()).then().log().all().extract().response();
        }
        else
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"email\": \""+arg0+"\",\"password\": \""+arg1+"\"}").when().post(APIEndPoints.loginApi.getResource()).then().log().all().extract().response();
        }



    }

    @When("I submit register request with email {string} and password {string}")
    public void iSubmitRegisterRequestWithEmailAndPassword(String arg0, String arg1) {
        if(arg0.isEmpty())
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"password\": \""+arg1+"\"}").when().post(APIEndPoints.registerApi.getResource()).then().log().all().extract().response();
        }
        else if(arg1.isEmpty())
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"email\": \""+arg0+"\"}").when().post(APIEndPoints.registerApi.getResource()).then().log().all().extract().response();
        }
        else
        {
            response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body("{\"email\": \""+arg0+"\",\"password\": \""+arg1+"\"}").when().post(APIEndPoints.registerApi.getResource()).then().log().all().extract().response();
        }

    }

    @Then("verify statusCode is {string} and error message is {string}")
    public void verifyStatusCodeIsAndErrorMessageIs(String arg0, String arg1) {
        Assert.assertEquals(response.statusCode(),Integer.parseInt(arg0));
        if(!arg1.isEmpty())
        Assert.assertEquals(arg1,response.getBody().path("error"));
    }

    @And("verify response contains correct put details {string} {string}")
    public void verifyResponseContainsCorrectPutDetails(String arg0, String arg1) {
        PutResponse putResponse=response.getBody().as(PutResponse.class);
        Assert.assertEquals(putResponse.getName(),arg0);
        Assert.assertEquals(putResponse.getJob(),arg1);
    }

    @When("I submit GET request for listUserApi with {int} seconds delay")
    public void iSubmitGETRequestForListUserApiWithSecondsDelay(int arg0) {
        response = given().log().all().spec(requestSpecification).param("delay",arg0).when().get(APIEndPoints.userApi.getResource()).then().log().all().extract().response();
    }


    @Then("I verify response time is {string} seconds")
    public void iVerifyResponseTimeIsSeconds(String arg0)
    {
        Assert.assertEquals(String.valueOf(response.getTimeIn(TimeUnit.SECONDS)),arg0);
    }
}
