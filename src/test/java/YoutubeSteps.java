import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.locators.WaitForWebElements;
import net.thucydides.core.pages.PageObject;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;
@DefaultUrl("https://www.youtube.com")
public class YoutubeSteps extends PageObject {
    EventFiringWebDriver driver = null;
    TestEngine te = TestEngine.getInstance();

    @FindBy(xpath = "//input[@id='search']")
            WebElement txtsearch;
    @FindBy(id="search-icon-legacy")
    WebElement btnSearch;
    @FindBy(xpath = "//*[@id='contents']/descendant::a[@href='/user/stepinforum']/div[@id='avatar']")
    WebElement lnkStepIn;
    @FindBy(xpath = "//div[@id='tabsContent']/descendant::div[@class='tab-content style-scope paper-tab']")
    List<WebElement> lnkTabs;

    /*By txtSearch = By.xpath("//input[@id='search']");
    By btnSearch = By.id("search-icon-legacy");

    By lnkStepIn = By.xpath("//*[@id='contents']/descendant::a[@href='/user/stepinforum']/div[@id='avatar']");
    By lnkTabs = By.xpath("//div[@id='tabsContent']/descendant::div[@class='tab-content style-scope paper-tab']");*/

    @Given("launch browser and navigate to url")
    public void openURL(){
        YoutubeSteps yt = null;
        yt.open();
    }
    @Given("search for $text")
    public void search(String $text){
        txtsearch.sendKeys($text);
        btnSearch.click();
    }
    @When("click on channel link")
    public void selectchannel(){
        lnkStepIn.click();
    }
    @Then("select videos tab")
    public void videostab(){
        List<WebElement> links = lnkTabs;
        for(WebElement lnk:links){
            System.out.println(lnk.getText());
            if(lnk.getText().contains("VIDEOS")){
                lnk.click();
                break;
            }
        }
    }

    @Then("find video and play $videoName")
    public void playvideo(String $videoName){
        String lnkVideo = "//a[@id='video-title' and @title='"+$videoName+"']";
        String VideoToSee = "return document.evaluate(\""+lnkVideo+"\",document, null, XPathResult.ANY_TYPE, null).iterateNext();";
        do {
            TestEngine.js.executeScript("window.scrollTo(0,10000)");
        }while(TestEngine.js.executeScript(VideoToSee, "") == null);
        if(TestEngine.js.executeScript(VideoToSee, "") != null){
            TestEngine.js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(lnkVideo)));
            driver.findElement(By.xpath(lnkVideo)).click();
        }
    }



}
