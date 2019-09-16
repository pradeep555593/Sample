import net.serenitybdd.jbehave.SerenityStory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Story;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@RunWith(SerenityRunner.class)
@Story(SerenityStory.class)
public class Runner extends SerenityStory{
    @Managed
    WebDriver dri;
    YoutubeSteps ytsteps;
    @Test
    public void run(){
        ytsteps.openURL();
        ytsteps.search("Step-up forum testing conference");
        ytsteps.selectchannel();
        ytsteps.videostab();
        ytsteps.playvideo("STeP-IN SUMMIT 2014 Making");
    }

}
