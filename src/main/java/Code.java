import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Locale;
import java.util.NoSuchElementException;

public class Code {
    static WebDriver browser;
    public static final String link = "https://learner.demo.edunext.co/";
    public static final String idFName = "register-name";
    public static final String idPUsername = "register-username";
    public static final String email = "register-email";
    public static final String idPassword = "register-password";
    public static final String idTerms = "register-terms_of_service";
    public static final String idHonor = "register-honor_code";
    public static final String xPatchCreate = "/html/body/div[2]/div[2]/div[3]/main/div/div[3]/section[2]/div/form/div[3]/button";

    public static void main(String[] args) {
        System.out.println("Registracija");
    }

    public static void setup() {
        //sync code with webdrivers and open with addBlock
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/block/uBlock-Origin.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //susikuriame narsykles objekta pagal atitinkamai atsiustus driverius
        browser = new ChromeDriver(capabilities);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //nurodomas narsykles url
        browser.get(link);
    }

    public static void register() {
        //Finds register now buttom
        WebElement register = browser.findElement(By.xpath("//a[contains(text(),'Register Now')]"));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", register);
    }

    public static void fNamePName(String firstName, String publicUsername) {
        //Finds patch to first name
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement fName = browser.findElement(By.id(idFName));
        //Faker
        Faker faker = new Faker();
        String firstNames = faker.name().firstName();
        //Types random firstName
        fName.sendKeys(firstNames);
        //Finds patch to public username
        WebElement pName = browser.findElement(By.id(idPUsername));
        //Faker
        Faker faker1 = new Faker();
        String firstNames1 = faker1.name().lastName();
        //Types random publicUsername
        pName.sendKeys(firstNames1);
    }

    public static void email(String fakeEmails) {
        //Finds patch to email
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement emailWeb = browser.findElement(By.id(email));
        //Fake
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        String email = fakeValuesService.bothify("????##@gmail.com");
        //Types email
        emailWeb.sendKeys(email);
    }

    public static void password(String password) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds patch to password
        WebElement passwordWeb = browser.findElement(By.id(idPassword));
        //Types password
        passwordWeb.sendKeys(password);
    }

    public static void termsHonor() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds patch to terms of service
        WebElement terms = browser.findElement(By.id(idTerms));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Presses with javascript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", terms);
        //Finds patch to honor code
        WebElement honor = browser.findElement(By.id(idHonor));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Presses with javascript
        JavascriptExecutor executor1 = (JavascriptExecutor) browser;
        executor1.executeScript("arguments[0].click();", honor);
    }

    public static boolean createAccount() {
        WebElement create = browser.findElement(By.xpath(xPatchCreate));
        //Presses with javascript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", create);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return trueFalse();
    }

    public static boolean trueFalse() {
        boolean success;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            browser.findElement(By.className("my-courses"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        return success;
    }

    public static void browserClose() {
        browser.close();
    }
}