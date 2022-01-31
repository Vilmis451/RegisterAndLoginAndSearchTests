import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.NoSuchElementException;

public class Search {
    static WebDriver browser;
    public static final String searchLoginLink = "https://learner.demo.edunext.co/";
    public static final String searchIdLName = "login-email";
    public static final String searchIdLPassword = "login-password";
    public static final String searchXpatchSignIn = "/html/body/div[2]/div[2]/div[3]/main/div/div[3]/section[1]/div/form/button";
    public static final String searchXpatchCourses = "/html/body/div[2]/div[2]/main/div/div[2]/div/div/a";
    public static final String searchXpatchCourses1 = "/html/body/div[2]/div[2]/section/section/div[1]/form/input";
    public static final String searchXpatchIcon = "/html/body/div[2]/div[2]/section/section/div[1]/form/button/i";


    public static void searchLoginSetup() {
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
        browser.get(searchLoginLink);
    }

    public static void searchPressSign() {
        //Finds sign in now buttom
        WebElement login = browser.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", login);
    }

    public static void searchLoginTyper(String realMail, String realPassword) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds email element to login
        WebElement email = browser.findElement(By.id(searchIdLName));
        //Sends mail
        email.sendKeys(realMail);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds password element to login
        WebElement password = browser.findElement(By.id(searchIdLPassword));
        //Sends password
        password.sendKeys(realPassword);
    }

    public static void searchSignInPress() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Finds patch to sign in button
        WebElement button = browser.findElement(By.xpath(searchXpatchSignIn));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", button);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean searchCourses() {
        //Finds patch to courses
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement courses = browser.findElement(By.xpath(searchXpatchCourses));
        //Clicks on it with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", courses);
        //Finds patch
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement searchCourse = browser.findElement(By.xpath(searchXpatchCourses1));
        searchCourse.sendKeys("Advanced");
        //Finds patch to search icon
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement icon = browser.findElement(By.xpath(searchXpatchIcon));
        //Presses with javaScript
        JavascriptExecutor executor1 = (JavascriptExecutor) browser;
        executor1.executeScript("arguments[0].click();", icon);
        return searchTrueFalse();
    }

    public static boolean searchTrueFalse() {
        boolean success;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            browser.findElement(By.className("query"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        return success;
    }

    public static void searchLoginClose() {
        browser.close();
    }
}
