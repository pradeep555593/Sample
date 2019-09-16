import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestEngine {

    private static WebDriver webDriver;
    private static EventFiringWebDriver driver;
    private static TestEngine singletonInstance = null;
    public static WebDriverWait wait = null;
    public static JavascriptExecutor js = null;

    private TestEngine(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Pradeep_Avadhanam\\IdeaProjects\\Sample\\Drivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(webDriver);
        TestEngineSupport myListner = new TestEngineSupport();
        driver.register(myListner);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
        js =  ((JavascriptExecutor) driver);
    }
    public static TestEngine getInstance(){
        if(singletonInstance == null){
            singletonInstance = new TestEngine();
        }
        return singletonInstance;
    }
    public EventFiringWebDriver getDriver()
    {
        return driver;
    }

    public static void tearDown(){
        driver.quit();
        driver = null;
    }

}
