package news360.page;

import news360.page.Impl.News360PageImpl;
import news360.page.customPages.LoginWithEmailPage;
import news360.page.customPages.impl.LoginWithEmailPageImpl;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.independent.pageobjects.PageReloaderProxy;

/**
 * Creation of page objects.
 * most interesting part - PageReloaderProxy. Comments on them inside of PageReloaderProxy class
 * Created by MVostrikov on 17.07.2015.
 */
public class PageFactory {
    protected static final Logger logger = LoggerFactory.getLogger(PageFactory.class);


    public static News360Page getNews360Page(WebDriver driver) {
        logger.debug("pageobject News360Page creation");
        News360Page page = (News360Page) PageReloaderProxy.newInstance(new News360PageImpl());
        page.init(driver);
        return page;
    }

    public static LoginWithEmailPage getLoginWithEmailPage(WebDriver driver) {
        logger.debug("pageobject News360Page creation");
        LoginWithEmailPage page = (LoginWithEmailPage) PageReloaderProxy.newInstance(new LoginWithEmailPageImpl());
        page.init(driver);
        return page;
    }

}
