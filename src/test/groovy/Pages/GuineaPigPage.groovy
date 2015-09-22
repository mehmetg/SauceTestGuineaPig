package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mehmetgerceker on 9/20/15.
 */
class GuineaPigPage extends PageBase{

    @FindBy(id="unchecked_checkbox")
    private WebElement uncheckedCheckbox;
    @FindBy(id="checked_checkbox")
    private WebElement checkedCheckbox;
    @FindBy(id="i am a link")
    private WebElement theActiveLink;
    @FindBy(id="i_am_a_textbox")
    private WebElement textInput;
    @FindBy(id="your_comments")
    private WebElement yourCommentsSpan;
    @FindBy(id="fbemail")
    private WebElement emailTextInput;
    @FindBy(id="comments")
    private WebElement commentsTextAreaInput;
    @FindBy(id="submit")
    private WebElement submitButton;

    public static GuineaPigPage navigateTo(WebDriver driver, String url){
        driver.get(url);
        //This will fail if we get redirected.
        assert(driver.getCurrentUrl() == url);
        return PageFactory.initElements(driver, GuineaPigPage.class);
    }


    public void checkUncheckedCheckBox(){
        setCheckCheckBoxState(this.uncheckedCheckbox, true);
    }
    public boolean getUncheckedCheckBoxState(){
        return this.uncheckedCheckbox.isSelected();
    }
    public void uncheckCheckedCheckBox(){
        setCheckCheckBoxState(this.checkedCheckbox, false);
    }
    public boolean getCheckedCheckBoxState(){
        return this.checkedCheckbox.isSelected();
    }

    public void setCommentText(String text){
        this.commentsTextAreaInput.click();
        this.commentsTextAreaInput.sendKeys(text);
    }
    public void getCommentText(String text){
        this.commentsTextAreaInput.getAttribute("value");
    }
    public void submitForm(){
        clickButton(this.submitButton);
    }
    public String getSubmittedCommentText(){
        return this.yourCommentsSpan.getText();
    }

    public void enterEmailText(String email){
        setTextInputValue(this.emailTextInput, email);
    }

    public String getEmailText()
    {
        return this.emailTextInput.getAttribute("value");
    }


}

