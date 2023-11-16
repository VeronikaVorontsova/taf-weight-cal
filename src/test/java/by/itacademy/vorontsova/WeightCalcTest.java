package by.itacademy.vorontsova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WeightCalcTest {
    @Test //Test1 - All fields are empty
    public void testFormWithEmptyValues() throws InterruptedException {
        CalcElements calcElements = new CalcElements();
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        driver.findElement(By.xpath(calcElements.calculateButtonLocator)).click();

        String actual = driver.findElement(By.xpath(calcElements.warningMessageEmptyFieldsLocator)).getText();
        String expected = """
                Не указано имя.
                Рост должен быть в диапазоне 50-300 см.
                Вес должен быть в диапазоне 3-500 кг.
                Не указан пол.""";

        Assertions.assertEquals(expected, actual);
        Thread.sleep(2000);
        driver.quit();
    }

    @Test //Test2 - Name field is filled
    public void testFormNameOnly() throws InterruptedException {
        CalcElements calcElements = new CalcElements();
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        driver.findElement(By.xpath(calcElements.nameFieldLocator)).sendKeys("Steve");
        driver.findElement(By.xpath(calcElements.calculateButtonLocator)).click();
        Thread.sleep(1000);

        String actual = driver.findElement(By.xpath(calcElements.warningMessageEmptyFieldsLocator)).getText();
        Assertions.assertEquals("Рост должен быть в диапазоне 50-300 см.\n" + "Вес должен быть в диапазоне 3-500 кг.\n" + "Не указан пол.", actual);

        Thread.sleep(2000);
        driver.quit();
    }

    @Test  //Test3 - Name and height fields are filled
    public void testFormNameAndHeight() throws InterruptedException {
        CalcElements calcElements = new CalcElements();
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        WebElement nameField = driver.findElement(By.xpath(calcElements.nameFieldLocator));
        nameField.sendKeys("Steve");

        WebElement heightField = driver.findElement(By.xpath(calcElements.heightFieldLocator));
        heightField.sendKeys("185");

        WebElement calculateButtonWebElement = driver.findElement(By.xpath(calcElements.calculateButtonLocator));
        calculateButtonWebElement.click();
        Thread.sleep(1000);

        WebElement warningMessageEmptyFields = driver.findElement(By.xpath(calcElements.warningMessageEmptyFieldsLocator));
        String actual = warningMessageEmptyFields.getText();
        Assertions.assertEquals("Вес должен быть в диапазоне 3-500 кг.\n" + "Не указан пол.", actual);

        Thread.sleep(2000);
        driver.quit();
    }

    @Test //Test4 - gender value is empty
    public void testFormGenderEmpty() throws InterruptedException {
        CalcElements calcElements = new CalcElements();
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        WebElement nameField = driver.findElement(By.xpath(calcElements.nameFieldLocator));
        nameField.sendKeys("Steve");

        WebElement heightField = driver.findElement(By.xpath(calcElements.heightFieldLocator));
        heightField.sendKeys("185");

        WebElement weightField = driver.findElement(By.xpath(calcElements.weightFieldLocator));
        weightField.sendKeys("86");

        WebElement calculateButtonWebElement = driver.findElement(By.xpath(calcElements.calculateButtonLocator));
        calculateButtonWebElement.click();
        Thread.sleep(1000);

        WebElement warningMessageEmptyFields = driver.findElement(By.xpath(calcElements.warningMessageEmptyFieldsLocator));
        String actual = warningMessageEmptyFields.getText();
        Assertions.assertEquals("Не указан пол.", actual);

        Thread.sleep(2000);
        driver.quit();
    }

    @Test //Test5 - all values are filled correctly
    public void testFormFieldsCorrect() throws InterruptedException {
        CalcElements calcElements = new CalcElements();
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        WebElement nameField = driver.findElement(By.xpath(calcElements.nameFieldLocator));
        nameField.sendKeys("Steve");

        WebElement heightField = driver.findElement(By.xpath(calcElements.heightFieldLocator));
        heightField.sendKeys("185");

        WebElement weightField = driver.findElement(By.xpath(calcElements.weightFieldLocator));
        weightField.sendKeys("86");

        WebElement genderMradio = driver.findElement(By.xpath(calcElements.genderMRadioLocator));
        genderMradio.click();

        WebElement calculateButtonWebElement = driver.findElement(By.xpath(calcElements.calculateButtonLocator));
        calculateButtonWebElement.click();
        Thread.sleep(1000);

        WebElement successMessage = driver.findElement(By.xpath(calcElements.successMessageLocator));
        String actual = successMessage.getText();
        Assertions.assertEquals("Идеальная масса тела", actual);

        Thread.sleep(2000);
        driver.quit();

    }

}

