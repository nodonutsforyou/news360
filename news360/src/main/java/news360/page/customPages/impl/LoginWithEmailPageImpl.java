package news360.page.customPages.impl;

import lombok.Getter;
import news360.page.Impl.News360PageImpl;
import news360.page.customPages.LoginWithEmailPage;
import org.openqa.selenium.support.FindBy;
import platform.independent.elements.primitives.Button;
import platform.independent.elements.primitives.TextField;

import java.util.List;

/**
 * Created by MVostrikov on 03.11.2016.
 */
public class LoginWithEmailPageImpl extends News360PageImpl implements LoginWithEmailPage  {

    @Getter
    @FindBy(xpath = "//div[contains(@style,'display: block;')]//form[contains(@class,'signin')]//input[@placeholder='Email']")
    private TextField email;

    @Getter
    @FindBy(xpath = "//div[contains(@style,'display: block;')]//form[contains(@class,'signin')]//input[@placeholder='Password']")
    private TextField password;

    @Getter
    @FindBy(xpath = "//div[contains(@style,'display: block;')]//button[contains(text(),'Sign in')]")
    private Button submit;

    @Getter
    @FindBy(xpath = "//div[@class='close']")
    private Button crossButtton;

    public void submit() {
        submit.click();
    }

    public void clickClose() {crossButtton.click();}
}
