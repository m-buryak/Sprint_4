import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MainPage;
import org.example.OrderPage;
import org.example.RentPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DoOrderFromBottomButtonTest {
    private final String name;
    private final String surname;
    private final String metro;
    private final String address;
    private final String telephone;
    private final boolean result;
    private static WebDriver driver;
    private static MainPage mainPage;
    private static OrderPage orderPage;
    private static RentPage rentPage;

    @BeforeClass
    public static void setup() {
        switch (String.valueOf(System.getProperty("browser"))) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                mainPage = new MainPage(driver);
                orderPage = new OrderPage(driver);
                rentPage = new RentPage(driver);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                mainPage = new MainPage(driver);
                orderPage = new OrderPage(driver);
                rentPage = new RentPage(driver);
        }
    }

    public DoOrderFromBottomButtonTest(String name, String surname, String metro, String address, String telephone, boolean result) {
        this.name = name;
        this.surname = surname;
        this.metro = metro;
        this.address = address;
        this.telephone = telephone;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Мартин", "Мартин","Черкизовская","г.Москва","+79034033083", true},
                {"Людмила", "Петрова","Партизанская","г.Москва","+79188556000", true},
        };
    }

    @Test
    public void doOrderFromBottomButtonTest() {
        mainPage.openMainPage();
        mainPage.clickOnBottomOrderButton();
        orderPage.doOrder(name, surname, metro, address, telephone);
        rentPage.setRentalPeriod();
        rentPage.setDate();
        rentPage.chooseBlackColor();
        rentPage.doOrder();
        boolean actualResult = rentPage.isDisplayedModalProcessedWindow();
        assertEquals(result, actualResult);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
