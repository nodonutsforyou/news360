package cucumber.glue;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.CucumberBasicTest;
import news360.page.News360Page;
import news360.page.PageFactory;
import news360.page.customPages.LoginWithEmailPage;
import news360.page.data.dataEntities.Login;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

/**
 * Created by MVostrikov on 19.07.2016.
 */
public class StepDefinitions extends CucumberBasicTest {

    @Before
    public void BeforeCucumber() throws Exception {
        init();
    }


    @Given("^I am at login with email page$")
    public void  I_am_at_front_page() throws Exception {
        driver.navigate().to(usedUrl);

        News360Page page = getPage();

        if (page.isLogOffButtonExists()) {
            page.logOff();
            reopenDriver();

            driver.navigate().to(usedUrl);

            page = getPage(); //TODO need another check if we still logged in
        }

        page.clickButtonByText("Start reading");
        page.clickButtonByText("Sign in with email");
    }


    @Then("^I login with \"([\\w@.]*)/(\\w*)\"$")
    public void  i_Login_with(String email, String pass) throws Exception {
        logger.info("I login with " + email + "/" + pass);
        LoginWithEmailPage page = PageFactory.getLoginWithEmailPage(driver);

        page.getEmail().sendKeys(email);
        page.getPassword().sendKeys(pass);

        page.submit();
    }

    @Then("^I should be logged in$")
    public void  I_should_be_logged_in() throws Exception {
        logger.info("I should be logged in");

        News360Page page = getPage();
        assert page.usernameLabelExists() : "Apparently users is not logged in";
    }


    @Then("^I login with (active) user$")
    public void  i_Login_with_activeUser(String active) throws Exception {
        logger.info("I login with active user");
        iLoginWithUserFromPool(Login.LoginBehaviourPattern.active);
    }

    public void iLoginWithUserFromPool(Login.LoginBehaviourPattern lbp) {
        LoginWithEmailPage page = PageFactory.getLoginWithEmailPage(driver);
        List<Login> loginsFromPool = testData.getLogins().getLoginList(lbp);
        Login login = null;
        if (loginsFromPool.size()>0) {
            login=loginsFromPool.get(0);
        } else {
            switch (lbp) {
                case wrongEmail: Login.RANDOM();
                    break;
                case notValidEmail: login = Login.NOT_VALID_EMAIL();
                    break;
                case empty: Login.EMPTY();
                    break;
                case tooLong:
                case active:
                case wrongPassword:
                    throw new NotImplementedException("Not implemented user generation with role " + lbp.toString());
            }
        }

        page.getEmail().sendKeys(login.getEmail());
        page.getPassword().sendKeys(login.getPassword());

        page.submit();
    }


    @Then("^I should get \"([^\"']+)\" message$")
    public void  I_should_get_message(String message) throws Exception {
        logger.info("I should get ["+message+"] message");

        News360Page page = getPage();

        page.assertTextExistsOnPage(message);
    }


    @Then("^click \"([^\"]+)\"$")
    public void  click(String text) throws Exception {
        logger.info("click ["+text+"] button");

        News360Page page = getPage();

        page.clickButtonByText(text);
    }


    @Then("^I press cross button$")
    public void  click() throws Exception {
        logger.info("click cross button");

        LoginWithEmailPage page = PageFactory.getLoginWithEmailPage(driver);

        page.clickClose();
    }

    @After
    public void AfterCucumber() {

    }
}
