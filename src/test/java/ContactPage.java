import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ContactPage extends Page{
    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=ContactHandler");
    }
    // zorg dat elke lijn in je overview de id contact heeft
   public int getAantalContacten(){
       ArrayList<WebElement> contactlist = (ArrayList<WebElement>)  driver.findElements(By.id("contact"));
       return contactlist.size();
   }
   public void addContact(String firstName, String lastName, String email, String phonenumber, String date, String time){
        submitForm( firstName,  lastName,  email,  phonenumber,  date,  time);
   }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String firstName, String lastName, String email, String phonenumber, String date, String time) {
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("email", email);
        fillOutField("phonenumber", phonenumber);
        fillOutField("date", date);
        fillOutField("time", time);

        WebElement button = driver.findElement(By.id("signUp"));
        button.click();
    }
    public void goToHome(){
        WebElement webElement = driver.findElement(By.linkText("Home"));
        webElement.click();
    }

}
