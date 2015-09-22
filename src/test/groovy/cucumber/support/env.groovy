import Utilities.WebDriverFactory;

import static cucumber.api.groovy.Hooks.*

Before(){
    wd = WebDriverFactory.getSauceDriver("Windows 7", "41", "chrome", null, null, it.scenarioId);
}

After(){
    String sessionId = wd.getSessionId();
    ArrayList results = it.stepResults;
    WebDriverFactory.markPassFail(sessionId, results);
    wd.quit();
}