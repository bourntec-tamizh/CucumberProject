package stepDefinations.API;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
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


public class APIStepDefs extends TestBase {

    static Response response;
    RequestSpecification requestSpecification;
    ObjectMapper objectMapper=new ObjectMapper();


    @Before
    public void setUp() throws IOException
    {
        RestAssured.baseURI = ConfigReader.getConfigValue("URL_API");
        requestSpecification= RestAssured.given();
    }



    @When("I submit GET request for listUserAPI")
    public void iSubmitGETrequestforlistUserAPI() throws IOException {

        response = given().log().all().spec(requestSpecification).param("page","1").when().get(APIEndPoints.userApi.getResource()).then().log().all().extract().response();
        test.info("GET request uri: "+APIEndPoints.userApi.getResource()+"?page=1");
      //   logger.info("Response2: "+response.asString());
//        logger.info("Response3: "+response.prettyPrint());

    }

    @Then("I verify status code is {int}")
    public void iVerifyStatusCodeIs(int arg0) {
        Assert.assertEquals(response.getStatusCode(),arg0);
        test.pass("status code is: "+response.statusCode());

    }

    @Then("I get a list of users")
    public void iGetAListOfUsers() {
        Users users=response.getBody().as(Users.class);
        test.info("Response Body:");
        test.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        logger.info("List of users");
        logger.info("=============================================\n");
        for (User user:users.getData())
        {

            logger.info("First Name: "+user.getfirst_name());
            logger.info("Last Name: "+user.getlast_name());
            logger.info("Email: "+user.getEmail());
            logger.info("Avatar: "+user.getAvatar());
            logger.info("--------------------------------------------\n");
        }
    }


    @When("I submit GET request for invalidUserApi")
    public void iSubmitGETRequestForInvalidUserApi() {
        response = given().log().all().spec(requestSpecification).when().get(APIEndPoints.userApi.getResource()+"/23").then().log().all().extract().response();
        test.info("GET request uri: "+APIEndPoints.userApi.getResource()+"/23");
    }

    @When("I submit GET request for singleUserApi")
    public void iSubmitGETRequestForSingleUserApi() {
        response = given().log().all().spec(requestSpecification).when().get(APIEndPoints.userApi.getResource()+"/2").then().log().all().extract().response();
        test.info("GET request uri: "+APIEndPoints.userApi.getResource()+"/2");
    }

    @Then("I get a single user data")
    public void iGetASingleUserData() {
        SingleUser singleUserdata=response.getBody().as(SingleUser.class);
        test.info("Response Body:");
        test.info(MarkupHelper.createCodeBlock(response.asString(),CodeLanguage.JSON));
        logger.info("======================================================================");
        logger.info("user data for userid: "+singleUserdata.getData().getId());
        logger.info("First Name: "+singleUserdata.getData().getfirst_name());
        logger.info("Last Name: "+singleUserdata.getData().getlast_name());
        logger.info("Email: "+singleUserdata.getData().getEmail());
        logger.info("Avatar: "+singleUserdata.getData().getAvatar());
        logger.info("--------------------------------------------\n");
    }


    @When("I submit POST request with details {string} {string}")
    public void iSubmitPOSTRequestWithDetails(String arg0, String arg1) throws JsonProcessingException {
        PostUserRequest request=new PostUserRequest();
        request.setName(arg0);
        request.setJob(arg1);
        response=given().log().all().spec(requestSpecification).contentType(ContentType.JSON).body(request).when().post(APIEndPoints.userApi.getResource()).then().log().all().extract().response();
        test.info("POST request uri: "+APIEndPoints.userApi.getResource());
        test.info("Request Body:");
        test.info(MarkupHelper.createCodeBlock(objectMapper.writeValueAsString(request),CodeLanguage.JSON));
    }

    @And("verify response contains correct posted details {string} {string}")
    public void verifyResponseContainsCorrectPostedDetails(String arg0, String arg1) {
        PostUserResponse postResponse=response.getBody().as(PostUserResponse.class);
        Assert.assertEquals(postResponse.getName(),arg0);
        Assert.assertEquals(postResponse.getJob(),arg1);
        test.pass("Response:");
        test.info(MarkupHelper.createCodeBlock(response.asString(),CodeLanguage.JSON));
    }

    @When("I submit PUT request with details {string} {string} {string}")
    public void iSubmitPUTRequestWithDetails(String arg0, String arg1, String arg2) throws JsonProcessingException {
        PostUserRequest request=new PostUserRequest();
        request.setName(arg1);
        request.setJob(arg2);
        response=given().log().all().spec(requestSpecification).contentType(ContentType.JSON).body(request).when().put(APIEndPoints.userApi.getResource()+"/"+arg0).then().log().all().extract().response();
        test.info("PUT request uri: "+APIEndPoints.userApi.getResource());
        test.info("Request Body:");
        test.info(MarkupHelper.createCodeBlock(objectMapper.writeValueAsString(request),CodeLanguage.JSON));
    }

    @When("I submit DELETE request for id {string}")
    public void iSubmitDELETERequestForId(String arg0) {
        response=given().log().all().spec(requestSpecification).when().delete(APIEndPoints.userApi.getResource()+"/"+arg0).then().log().all().extract().response();
        test.info("DELETE request uri: "+APIEndPoints.userApi.getResource()+"/"+arg0);
    }


    @When("I submit login request with email {string} and password {string}")
    public void iSubmitLoginRequestWithEmailAndPassword(String arg0, String arg1) throws JsonProcessingException {
        RegisterAndLoginRequest loginRequest=new RegisterAndLoginRequest();
        loginRequest.setEmail(arg0);
        loginRequest.setPassword(arg1);
        response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body(loginRequest).when().post(APIEndPoints.loginApi.getResource()).then().log().all().extract().response();
        test.info("POST request uri: "+APIEndPoints.loginApi.getResource());
        test.info("Request Body:");
        test.info(MarkupHelper.createCodeBlock(objectMapper.writeValueAsString(loginRequest),CodeLanguage.JSON));
    }

    @When("I submit register request with email {string} and password {string}")
    public void iSubmitRegisterRequestWithEmailAndPassword(String arg0, String arg1) throws JsonProcessingException {
        RegisterAndLoginRequest registerRequest=new RegisterAndLoginRequest();
        registerRequest.setEmail(arg0);
        registerRequest.setPassword(arg1);

        response=given().log().all().contentType(ContentType.JSON).spec(requestSpecification).body(registerRequest).when().post(APIEndPoints.registerApi.getResource()).then().log().all().extract().response();
        test.info("POST request uri: "+APIEndPoints.registerApi.getResource());
        test.info("Request Body:");
        test.info(MarkupHelper.createCodeBlock(objectMapper.writeValueAsString(registerRequest),CodeLanguage.JSON));
    }

    @Then("verify statusCode is {string} and error message is {string}")
    public void verifyStatusCodeIsAndErrorMessageIs(String arg0, String arg1) {
        Assert.assertEquals(response.statusCode(),Integer.parseInt(arg0));
        test.pass("status code is "+response.statusCode());
        if(!arg1.isEmpty())
        {
            Assert.assertEquals(response.getBody().path("error"),arg1);
            test.pass("response contains error: "+response.getBody().path("error"));
        }
        test.info("Response Body:");
        test.info(MarkupHelper.createCodeBlock(response.asString(),CodeLanguage.JSON));

    }

    @And("verify response contains correct put details {string} {string}")
    public void verifyResponseContainsCorrectPutDetails(String arg0, String arg1) {
        PutResponse putResponse=response.getBody().as(PutResponse.class);
        Assert.assertEquals(putResponse.getName(),arg0);
        Assert.assertEquals(putResponse.getJob(),arg1);
        test.pass("Response Body:");
        test.info(MarkupHelper.createCodeBlock(response.asString(),CodeLanguage.JSON));

    }

    @When("I submit GET request for listUserApi with {int} seconds delay")
    public void iSubmitGETRequestForListUserApiWithSecondsDelay(int arg0) {
        response = given().log().all().spec(requestSpecification).param("delay",arg0).when().get(APIEndPoints.userApi.getResource()).then().log().all().extract().response();
        test.info("GET request uri: "+APIEndPoints.userApi.getResource()+"?delay="+arg0);
    }


    @Then("I verify response time is {string} seconds")
    public void iVerifyResponseTimeIsSeconds(String arg0)
    {
        Assert.assertEquals(String.valueOf(response.getTimeIn(TimeUnit.SECONDS)),arg0);
        test.pass("response time is: "+response.getTimeIn(TimeUnit.SECONDS)+" seconds");
        test.info("Response body:");
        test.info(MarkupHelper.createCodeBlock(response.asString(),CodeLanguage.JSON));

    }
}
