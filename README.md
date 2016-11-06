framework as test task for news360 job application

How to use:
Maven goal test to run.
Selenium chrome driver must be in path. Selenium chromedriver can be downloaded here: http://chromedriver.storage.googleapis.com/index.html?path=2.24/
Easiest way - just to pit chromedriver.exe (chromedriver for linux) in path directory. SeleniumFW\src\main\resources\configs\core.config.xml contains WEBDRIVER_CHROME_DRIVER field, which can set expilict path to driver.

Bright points:
1) It uses pageobjects. Parent pageobject - News360Page. Each unique page has it own pageobject, with specific methods.
Parent function has wide range of methods, that could be used anywere and they are quick way to write test.
Child objects contain more complex and specific methods
2) Screenshot maker. TestListener class makes screenshots of failed tests, screnshot are stored in Cucumber\target\screenshots folder.
3) StaleElementReferenceException handling. Each page object is wraped in proxy class - PageReloaderProxy. And if we get StaleElementReferenceException we reload page object, so we can forget about it.
StaleElementReferenceException happens when selenium works with anchored element, and when this element is gone - when JS chages element for example.
4) Data objects. Good way to make right BDD is to make real-like objects. For example - Login class. Login class contains method to randomly generate data for Login information.
Login class instance with that information then stored, so can be then used again in further tests and beck-end testing.
5) Parallel - all driver classes can run in parallel, TestNG settings must be made for that.
6) Cucumber features stored in Cucumber\src\test\resources\features folder:
Example.feature - shore smoke test
AS.feature - alternative cases
7) Log4j - all logs made with log4j. Log streams into 3 plases - stdout with INFO log, INFO log file, DEBUG log file.

Limitations/TODO list
I had limited time. I tried to show what I can, but I can do more. Here is list I could make easily and what I've deliberately shortcuted
1) Selenium. Selenium is not an instrument to automate such task. Simple cases, like login must be tested by unit test. Selenium's best practice is UI end-to-end test.
So, best fit for those test cases are unit tests - but I don't know what's your platform what you are developing on, so can't get a guess which will fit you best.
2) Russian comments. Most of code I've made myself, some was rewritten by me from different sources. Large portion was written some time ago, when I worked with russian-speaking team.
So some comments are in russian. I've translated some of comments, but not all of them. If this is a problem, I could translate them - but it will take moderate amount of time.
3) Some //TODOs - I've writted comments with //TODO mark everywhere I could do more. Those moments need some investigation and specification. So I implemented quick go-thru
4) Chrome. I have experience in making autotest framework for multiple browsers. But my current project is chrome-only by design (system is designed to work in chrome only).
 I could have demonstrate my ability to work with different brousers, but I can't be sure it will work the best way posible. So I desided to stick with what I am sure about and not try to cover them all.
5) Waiting to load. Most important thing in good autotest framework - good method to wait for page to load. I could have made funtions, wich finds load image, and them waits for load image to gone. But I didn't catch that image while it loaded.