package cucumber;

import news360.page.News360Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import news360.autotest.BasicTest;
import news360.page.PageFactory;

/**
 * Created by MVostrikov on 19.07.2016.
 */
public class CucumberBasicTest extends BasicTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public News360Page getPage() {
        return PageFactory.getNews360Page(driver);
    }



    public void init() {
        testName = "cucumberTest";
        usedUrl = CucumberTest.url;
        initDriver();
        try {
            initTestData(testName, usedUrl);
        } catch (Exception e) {
            logger.error("не удалось инициализировать конфиг", e);
        }
    }

}
