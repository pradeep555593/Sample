import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

public class ScenarioSteps extends YoutubeSteps {

    YoutubeSteps ytsteps;

    @Step
    public void openyoutube(){
        ytsteps.openURL();
    }
    @Step
    public void searchchannel(String chnlToSearch){
        ytsteps.search(chnlToSearch);
        ytsteps.selectchannel();
    }
    @Step
    public void play(String vdName){
        ytsteps.videostab();
        ytsteps.playvideo(vdName);
    }

}
