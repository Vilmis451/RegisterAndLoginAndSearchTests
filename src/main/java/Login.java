import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.NoSuchElementException;

public class Login {
    static WebDriver browser;
    public static final String linkk = "https://learner.demo.edunext.co/";
    public static final String idLName = "login-email";
    public static final String idLPassword = "login-password";
    public static final String xpatchSignIn = "/html/body/div[2]/div[2]/div[3]/main/div/div[3]/section[1]/div/form/button";

    public static void main(String[] args) {
        System.out.println("Prisijungimas");
    }

    public static void loginSetup() {
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
        browser.get(linkk);
    }

    public static void pressSign() {
        //Finds sign in now buttom
        WebElement login = browser.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", login);
    }

    public static void loginTyper(String realMail, String realPassword) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds email element to login
        WebElement email = browser.findElement(By.id(idLName));
        //Sends mail
        email.sendKeys(realMail);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds password element to login
        WebElement password = browser.findElement(By.id(idLPassword));
        //Sends password
        password.sendKeys(realPassword);
    }

    public static boolean signInPress() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds patch to sign in button
        WebElement button = browser.findElement(By.xpath(xpatchSignIn));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", button);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return LoginTrueFalse();
    }

    public static boolean LoginTrueFalse() {
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

    public static void loginClose() {
        browser.close();
    }

}
