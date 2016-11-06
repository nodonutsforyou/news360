package news360.page.Impl;

import cucumber.api.java.gl.E;
import news360.page.News360Page;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.independent.elements.primitives.Button;
import platform.independent.elements.primitives.Label;
import platform.independent.elements.primitives.Select;
import platform.independent.elements.primitives.TextField;
import platform.independent.elements.primitives.impl.SelectImpl;
import platform.independent.pageobjects.AbstractPage;
import utilities.FrameworkConfig;

import java.util.List;

/**
 * Created by Max on 20.09.2016.
 */
public class News360PageImpl extends AbstractPage implements News360Page {

    @FindBy(xpath = "//input")
    private List<TextField> textImputs;

    @FindBy(xpath = "//button | //a")
    private List<Button> buttons;

    @FindBy(xpath = "//span[contains(@class, 'username')]")
    private Label usernamelabel;

    @FindBy(xpath = "//span[text()='Sign out']")
    private Button logoff;


    public boolean usernameLabelExists() {
        try {
            return usernamelabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogOffButtonExists() {
        try {
            return logoff.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logOff() {
        logoff.click();
    }

    /**
     * Basic function to click button by text inside. A good way to make true BDD
     * of course clicks only first button on page. We'll need more complex function, if it will be an issue
     */
    public void clickButtonByText(String text) throws Exception {
        logger.debug("click button with text ["+text+"]");
        for (Button button : buttons) {
            try {
                if (button.isDisplayed() && button.getText().contains(text)) {
                    new WebDriverWait(driver, FrameworkConfig.getInstance().getDefaultDriverImplicitlyWaitSec()).until(ExpectedConditions.elementToBeClickable(button.getWrappedElement()));
                    button.click();
                    return;
                }
            } catch (Exception e) {
                //realy not a problem - button is not reacheable - we'll take next
            }
        }
        throw new Exception("Button with text ["+text+"] not found");
    }

    public boolean assertTextExistsOnPage(String text) {
        assert isTextExistsOnPage(text) : "text ["+text+"] doesn't exists on page";
        return true;
    }
    public boolean isTextExistsOnPage(String text) {
        boolean ret = false;
        if (text.contains("'")) throw new NotImplementedException("this code won't work if we'll have text with ' mark on it.");//TODO there are multiple ways to deal with it
        String xpath = "//*[contains(text(),'"+text+"')]";
        logger.debug("css = ["+xpath+"]");
        try {
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            for(WebElement element:elements) {
                ret = ret || element.isDisplayed();
            }
        } catch (Exception e) {
            ret = false;
        }

        return ret;
    }


}
