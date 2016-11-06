package news360.page;

import platform.independent.elements.primitives.TextField;
import platform.independent.pageobjects.Page;

/**
 * Created by Max on 20.09.2016.
 */
public interface News360Page extends Page {

    /**
     * Basic function to click button by text inside. A good way to make true BDD
     * of course clicks only first button on page. We'll need more complex function, if it will be an issue
     */
    void clickButtonByText(String text) throws Exception;

    boolean usernameLabelExists();
    boolean isLogOffButtonExists();
    void logOff();

    boolean assertTextExistsOnPage(String text);
    boolean isTextExistsOnPage(String text);
}
