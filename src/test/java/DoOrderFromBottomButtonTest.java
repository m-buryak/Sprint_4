import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MainPage;
import org.example.OrderPage;
import org.example.RentPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DoOrderFromBottomButtonTest {
    @Parameterized.Parameter
    public String browserType;
    private WebDriver driver;
    @Before
    public void setup() {
        if (this.browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
            this.driver = new ChromeDriver();
        } else if (this.browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
            this.driver = new FirefoxDriver();
        }
    }

    @Parameterized.Parameters
    public static Object[][] browser() {
        return new Object[][]{{"chrome"}, {"firefox"}};
    }

    @Test
    public void doOrderFromBottomButtonTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnBottomOrderButton();

        orderPage.setNameValue("Людмила");
        orderPage.setSurnameValue("Петрова");
        orderPage.setMetroValue("Черкизовская");
        orderPage.setAddressValue("г. Москва");
        orderPage.setTelephoneValue("+79188556000");
        orderPage.clickOnNextButton();

        rentPage.setRentalPeriod();
        rentPage.setDate();
        rentPage.chooseBlackColor();
        rentPage.doOrder();
        boolean result = rentPage.isDisplayedModalProcessedWindow();
        assertEquals(true, result);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
