import Tests.WebDriverFactory;

import static cucumber.api.groovy.Hooks.*

Before(){
    wd = WebDriverFactory.getSauceDriver("Windows 7", "41", "chrome", null, null);
}

After(){
    wd.quit();
}