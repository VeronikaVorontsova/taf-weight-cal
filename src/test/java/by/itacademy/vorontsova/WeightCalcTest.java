package by.itacademy.vorontsova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.Thread.sleep;


public class WeightCalcTest {
    @Test
    public void testFormWithEmptyValues() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        // click "Calculate" button
        WebElement calculateButtonWebElement = driver.findElement(By.xpath("//input[@value='Рассчитать']"));
        calculateButtonWebElement.click();
        // get warming message text from WebElement
        WebElement warningMessageEmptyFields = driver.findElement(By.xpath("//tbody/tr[1]/td/b"));

        // set actual and expected results
        String actual = warningMessageEmptyFields.getText();
        String expected = """
                Не указано имя.
                Рост должен быть в диапазоне 50-300 см.
                Вес должен быть в диапазоне 3-500 кг.
                Не указан пол.""";

        //compare actual and expected results
        Assertions.assertEquals(expected, actual);

        //quit browser
        sleep(3000);
        driver.quit();
    }
}

