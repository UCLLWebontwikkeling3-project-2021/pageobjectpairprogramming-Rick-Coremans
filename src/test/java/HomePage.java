import domain.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage  extends Page{
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=IndexHandler");
    }
    public void login(String email, String password){
        submitForm(email,password);
    }
    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }
    private void submitForm(String email, String password) {
        fillOutField("userid", email);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("signUp"));
        button.click();
    }
    public void goToContact(){
        WebElement webElement = driver.findElement(By.linkText("Contact"));
        webElement.click();
    }
}
