import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CodeTest {
    String fName;
    String vUsername;
    String email;
    String password;
    String realMail;
    String realPassword;
    String searchMail;
    String searchPassword;

    @BeforeMethod(onlyForGroups = "register")
    public void setup() {
        Code.setup();
    }

    @BeforeMethod(onlyForGroups = "login")
    public void loginSetup() {
        Login.loginSetup();
    }

    @BeforeMethod(onlyForGroups = "search")
    public void searchLoginSetup() {
        Search.searchLoginSetup();
    }

    @AfterMethod(onlyForGroups = "register")
    public void close() {
        Code.browserClose();
    }

    @AfterMethod(onlyForGroups = "login")
    public void loginClose() {
        Login.loginClose();
    }

    @AfterMethod(onlyForGroups = "search")
    public void searchLoginClose() {
        Search.searchLoginClose();
    }

    @Test(groups = "register")
    public void registerPositive() {
        fName = "";
        vUsername = "";
        email = "\\w{4}\\d{2}@gmail.com";
        password = "gaidiena123";
        Code.register();
        Code.fNamePName(fName, vUsername);
        Code.email(email);
        Code.password(password);
        Code.termsHonor();
        Assert.assertEquals(Code.createAccount(), true);
    }

    @Test(groups = "register")
    public void registerNegative() {
        fName = "";
        vUsername = "";
        email = "\\w{4}\\d{2}@gmail.com";
        password = "gaidiena123";
        Code.register();
        Code.fNamePName(fName, vUsername);
        Code.email(email);
        Code.password(password);
        Code.termsHonor();
        Assert.assertNotEquals(Code.createAccount(), false);
    }

    @Test(groups = "login")
    public void loginPositiveTest() {
        realMail = "re.saduikis@gmail.com";
        realPassword = "gaidiena123";
        Login.loginSetup();
        Login.pressSign();
        Login.loginTyper(realMail, realPassword);
        Assert.assertEquals(Login.signInPress(), true);
    }

    @Test(groups = "login")
    public void loginNegativeTest() {
        realMail = "re.saduikis@gmail.com";
        realPassword = "gaidiena123";
        Login.loginSetup();
        Login.pressSign();
        Login.loginTyper(realMail, realPassword);
        Assert.assertNotEquals(Login.signInPress(), false);
    }

    @Test(groups = "search")
    public void searchPositiveTest() {
        searchMail = "re.saduikis@gmail.com";
        searchPassword = "gaidiena123";
        Search.searchLoginSetup();
        Search.searchPressSign();
        Search.searchLoginTyper(searchMail, searchPassword);
        Search.searchSignInPress();
        Search.searchCourses();
        Assert.assertEquals(Search.searchTrueFalse(), true);
    }

    @Test(groups = "search")
    public void searchNegativeTest() {
        searchMail = "re.saduikis@gmail.com";
        searchPassword = "gaidiena123";
        Search.searchLoginSetup();
        Search.searchPressSign();
        Search.searchLoginTyper(searchMail, searchPassword);
        Search.searchSignInPress();
        Search.searchCourses();
        Assert.assertNotEquals(Search.searchTrueFalse(), false);
    }
}
