import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccordionTest {
    private WebDriver driver;

    private MainPage mainPage;
    private final String accordionHeaderText;
    private final String accordionText;
    private final boolean result;

    public AccordionTest(String accordionHeaderText, String accordionText, boolean result) {
        this.accordionHeaderText = accordionHeaderText;
        this.accordionText = accordionText;
        this.result = result;
    }

    @Before
    public void setup() {
        switch (String.valueOf(System.getProperty("browser"))) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                this.mainPage = new MainPage(driver);
                break;
            case "chrome":
            default:
//                WebDriverManager.chromedriver().setup();
//                this.driver = new ChromeDriver();
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                this.mainPage = new MainPage(driver);
        }
//        WebDriverManager.chromedriver().setup();
//        this.driver = new ChromeDriver();
//        this.mainPage = new MainPage(driver);
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", true},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", true},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", true},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", true},
        };
    }

    @Test
    public void accordionTest() {
        mainPage.openMainPage();
        String textFromAccordionItem = mainPage.clickOnAccordionItem(accordionHeaderText).split("\n")[1];
        assertEquals(result, textFromAccordionItem.equals(accordionText));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
