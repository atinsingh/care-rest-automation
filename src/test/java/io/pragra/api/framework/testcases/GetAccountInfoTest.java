package io.pragra.api.framework.testcases;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.pragra.api.framework.apis.APIManger;
import io.pragra.api.framework.config.Config;
import io.pragra.api.framework.reports.HTMLReport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import groovy.json.StringEscapeUtils;
import io.cucumber.core.gherkin.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;


import static org.hamcrest.Matchers.*;

/**
 * @project api-framework
 * Created By Rsingh on 2019-05-13.
 */

public class GetAccountInfoTest {
    private ExtentTest extent;
    Response response;

    Logger logger = LogManager.getLogger(GetAccountInfoTest.class);

    @BeforeStep
    public void before() {
       // logger.info("Running Scenario  - {} ", scenario.getName());
        extent = HTMLReport.getInstance().getReports().createTest("AccountInfo");
    }

    @After
    public void after(){
        HTMLReport.getInstance().getReports().flush();
    }

    /**
     * Scenario 1 Implementation-GET Retrieves the account info with invalid id.
     */

    @Given("user enter the invalid course id in api path.")
    public void user_enter_the_invalid_contactId_with_wrong_api_path() {

    }

    @When("User calls course-info with {string} as path param")
    public void user_submit_the_Get_request(String contactId) {
        logger.info("Got following courseId {}" , contactId);
        Map<String, Object> map = Collections.singletonMap("id", contactId);
        logger.info("Making call to URL {} with param {}", Config.getProperty("api.course-info"), contactId);
        response = APIManger.getInstance().doGet(Config.getProperty("api.course-info"), map, null);
        extent.log(Status.INFO, "API called with contact ID  " + contactId);
    }

    @Then("status code should be {int} in the response")
    public void status_code_should_be_in_the_response(Integer statusCode) {
        logger.info("Status code to be matched from Scenario-  {}", statusCode);
        //System.out.println(response.body().print());
        int code = response.then().extract().statusCode();
        if (statusCode == code) {
            extent.log(Status.PASS, String.format("Response Status code is %d - it matches with the desired code %d", code, statusCode));
        } else {
            extent.log(Status.FAIL, String.format("Response Status code is %d -it does't Matches with desired code %d", code, statusCode));
        }
        response.then().assertThat().statusCode(statusCode);
    }


    @And("response code should be {string} in response body")
    public void responseCodeShouldBeInResponseBody(String errCode) {
        JsonPath path = response.then().extract().jsonPath();
        String code = path.get("error");
         if (code.equals(errCode)) {
            extent.log(Status.PASS, String.format("Code in response messages- %s  - it matches with desired Code - %s", code, errCode));
        } else {
            extent.log(Status.FAIL, String.format("Code in response messages - %s  -it doesn't matches with desired Code - %s", code, errCode));
        }
        response.then().assertThat().body("error", equalTo(errCode));
    }


    @And("response type should be {string} in response body")
    public void response_type_should_be_in_response_body(String errType) {
        System.out.println("Got Errot type " + errType);
        JsonPath path = response.then().extract().jsonPath();

        String type = path.get("message");

    }



    /**
     * Scenario 2
     *
     * @throws Throwable
     */

    @Given("user enter the valid course id in api path.")
    public void user_enter_the_valid_course_id_in_api_path() {
        // Write code here that turns the phrase above into concrete actions

    }

}
