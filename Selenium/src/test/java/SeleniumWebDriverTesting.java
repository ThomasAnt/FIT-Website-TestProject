import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


@FixMethodOrder
public class SeleniumWebDriverTesting {
    private static WebDriver driver;

    @BeforeClass
    public static void initClass()
    {
        System.setProperty("webdriver.chrome.driver","C:\\opt\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
    }
    @AfterClass
    public static void closeDriver(){
        driver.close();
    }
    @Test
    public void TEST001_openWebsite(){
        driver.get("http://localhost:4200");
        driver.get("http://localhost:4200/admin-tool/login");
    }

    @Test
    public void TEST002_FirmennameÄndern () {
        driver.get("http://localhost:4200");
        driver.findElement(By.id("code-part1")).sendKeys("FirmenToken1");
        driver.findElement(By.id("btnFitRegister")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Daten ändern')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Fortfahren')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Daten bearbeiten')]")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys("FirmenToken1");
        driver.findElement(By.xpath("//span[contains(text(),'Speichern')]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("http://localhost:4200");
        driver.findElement(By.id("code-part1")).sendKeys("FirmenToken1");
        driver.findElement(By.id("btnFitRegister")).click();
        assertTrue(driver.findElement(By.id("companyName")).getAttribute("value").equals("FirmenToken1"));

    }
}
