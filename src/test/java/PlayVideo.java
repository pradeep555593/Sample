import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;

public class PlayVideo {

    By txtSearch = By.xpath("//input[@id='search']");
    By btnSearch = By.id("search-icon-legacy");

    By lnkStepIn = By.xpath("//*[@id=\"contents\"]/descendant::a[@href='/user/stepinforum']/div[@id='avatar']");
    By lnkTabs = By.xpath("//div[@id='tabsContent']/descendant::div[@class='tab-content style-scope paper-tab']");
    //By videoTime = By.xpath("//span[@class='ytp-time-current']");

    String lnkVideo = "//a[@id='video-title' and @title='STeP-IN SUMMIT 2014 Making']";
    private WebDriver driver = null;

    @BeforeClass
    public void setup() {
        TestEngine te = TestEngine.getInstance();
        driver = te.getDriver();
        driver.get(Constants.url);

    }
    @AfterClass
    public void exit(){
        TestEngine.tearDown();
        driver = null;
    }
    @Test
    public void testcase() throws  Exception{

        driver.findElement(txtSearch).sendKeys("Step-up forum testing conference");
        driver.findElement(btnSearch).click();

        driver.findElement(lnkStepIn).click();
        List<WebElement> links = driver.findElements(lnkTabs);
        for(WebElement lnk:links){
            System.out.println(lnk.getText());
            if(lnk.getText().contains("VIDEOS")){
                lnk.click();
                break;
            }
        }
        String VideoToSee = "return document.evaluate(\""+lnkVideo+"\",document, null, XPathResult.ANY_TYPE, null).iterateNext();";
        do {
            TestEngine.js.executeScript("window.scrollTo(0,10000)");
        }while(TestEngine.js.executeScript(VideoToSee, "") == null);
        if(TestEngine.js.executeScript(VideoToSee, "") != null){
            TestEngine.js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(lnkVideo)));
            driver.findElement(By.xpath(lnkVideo)).click();
        }
        /*String timelaps;
        do{
            timelaps = driver.findElement(videoTime).getText();
        }while(timelaps.equals("0:30"));*/
        Thread.sleep(30000);



    }
}