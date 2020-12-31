import domain.model.Contact;
import domain.model.Person;
import domain.service.ContactTracingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.controller.Controller;


import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
public class Story5Test {
    /***
     * zorg dat je een test gebruiker hebt waar je contacten aan kunt toevoegen
     * zorg dat je een 2de test gebruiker hebt zonder contacten
     */
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        // pad moet aangepast worden
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aron\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        // driver.get(path + "?command=IndexHandler");
    }
    @After
    public void clean() {
        driver.quit();
    }
    @Test
    public void not_logged_user_is_redirected_to_error_page_when_link_is_used(){
        new ContactPage(driver);
        String title = driver.getTitle();
        assertEquals(title,"error");
    }

    @Test
    public void aantal_contacten_klopt()  {
        loginTestGebruiker();
        ContactPage page = new ContactPage(driver);
        int firstCount = page.getAantalContacten();
        for (int i = 0; i < 5; i++){
            page.addContact("firstname", "lastname", "test@email.com", "0477777777", "29112020", "120001");
        }
        int secondCount = page.getAantalContacten();
        assertEquals(firstCount + 5,secondCount);

    }
    @Test
    public void gebruiker_zonder_contacten_weergeeft_geen_contacten() {
        loginTestGebruikerGeenContacten();
        ContactPage page = new ContactPage(driver);
        assertEquals(page.getAantalContacten(),0);
    }
    @Test
    public void van_home_naar_contacten_werkt_bij_ingelogde_gebruiker() {
        HomePage page = new HomePage(driver);
        loginTestGebruiker();
        page.goToContact();
        assertEquals(driver.getTitle(),"Contact");
    }
    @Test
    public void van_contacten_naar_home_werkt_bij_ingelogde_gebruiker() {
        loginTestGebruiker();
        ContactPage page = new ContactPage(driver);
        page.goToHome();
        assertEquals(driver.getTitle(),"Home");
    }
    @Test(expected = org.openqa.selenium.NoSuchElementException.class)
    public void van_home_naar_contacten_bij_niet_ingelogde_gebruiker_exception()  {
        HomePage page = new HomePage(driver);
        page.goToContact();

    }
    @Test(expected = org.openqa.selenium.NoSuchElementException.class)
    public void van_contacten_naar_home_bij_niet_ingelogde_gebruiker_exception() {
        ContactPage page = new ContactPage(driver);
        page.goToHome();
        assertEquals(driver.getTitle(),"error");
    }

    public void loginTestGebruiker(){
        HomePage page = new HomePage(driver);
        //test gebruiker waaraan je contacten toevoegd
        page.login("test55@mail.com", "t");
    }
    public void loginTestGebruikerGeenContacten(){
        HomePage page = new HomePage(driver);
        //test gebruiker zonder contacten
        page.login("test2@mail.com", "t");
    }
}
