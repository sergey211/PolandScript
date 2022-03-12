package org.example;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vis.litva.org.example.VisaConfProperties;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class VisaLithuania2022 {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();
        private SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd.hh-mm-ss");

        private int i;

        @Before
        public void setUp() throws Exception {

            System.setProperty("webdriver.chrome.driver", VisaConfProperties.getProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");
            driver = new ChromeDriver();

             baseUrl = "https://visa.vfsglobal.com/blr/ru/ltu/login"; // ЛИТВА
           //  baseUrl = "https://visa.vfsglobal.com/blr/ru/pol/login"; // ПОЛЬША
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testJunit() throws Exception {
            Date dateNow = new Date();
            System.out.println("Ищем даты на ЛИТВУ "+dateNow);

            driver.get(baseUrl);
            driver.findElement(By.cssSelector("div.mat-form-field-infix.ng-tns-c58-0")).click();
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();

            for (i=1;  i<1000; i++)
            {


                dateNow = new Date();
                System.out.println("Текущая дата и время:" + ft.format(dateNow));
                System.out.println("Попытка №"+i);


    //            driver.findElement(By.id("mat-input-0")).click();
    //            driver.findElement(By.id("mat-input-1")).clear();
                driver.findElement(By.xpath("//*[contains(@formcontrolname, 'password')]")).sendKeys("!GRUppa104");
    //            driver.findElement(By.id("mat-input-0")).clear();
                //    driver.findElement(By.id("mat-input-0")).sendKeys("sergey211@gmail.com"); // ТОЛЬКО ПОЛЬША
                driver.findElement(By.xpath("//*[contains(@formcontrolname, 'username')]")).sendKeys("kirmaxik@mail.ru"); // ПОЛЬША И ЛИТВА
                driver.findElement(By.xpath("//i")).click();
                driver.findElement(By.xpath("//form/button")).click();
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                // WebDriverWait t = new WebDriverWait(driver, 10);
                Thread.sleep(500);

                dateNow = new Date();
                FileUtils.copyFile(scrFile, new File("c:\\VISA\\LOGIN-"+ft.format(dateNow)+".п."+i+".png"));

// Произошла ошибка. Пожалуйста, попробуйте еще раз через некоторое время.


                if (isElementPresent(By.xpath("//*[contains(text(), 'Произошла ошибка.')]"))) {

                    System.out.println("произошла ошибка, выходим из цикла");
                    break;

                }

                System.out.println("10 сек пауза после нажатия войти");
                Thread.sleep(10000);
                //    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                if (isElementPresent(By.xpath("//*[contains(@formcontrolname, 'username')]"))) {

                    System.out.println("не удалось залогиниться, выходим из цикла");
                    break;

                }

                if (isElementPresent(By.xpath("//*[contains(text(), 'Записаться на прием')]"))) {

                    System.out.println("успешно залогинились");


                }

                System.out.println("жмем записаться на прием");
                WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Записаться на прием')]"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("нажали, пауза 7 сек");
           //     System.out.println("7 SEC");
                Thread.sleep(7000);
                driver.findElement(By.xpath("//span[contains(text(), 'Выберите свой визовый центр')]")).click();
                driver.findElement(By.xpath("//*[contains(text(), 'Poland Visa Application Center-Minsk')]")).click();
                Thread.sleep(3000);
                JavascriptExecutor Js2 = (JavascriptExecutor) driver;
                Js2.executeScript("window.scrollBy(0,1000)");                   //scroll 1000 pixel verticaal

                driver.findElement(By.xpath("//span[contains(text(), 'Выберите категорию записи')]")).click();
                driver.findElement(By.xpath("//*[contains(text(), 'D-visa')]")).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("//span[contains(text(), 'Выберите подкатегорию')]")).click();
                driver.findElement(By.xpath("//*[contains(text(), 'National D-visa')]")).click();

                Thread.sleep(3000);
                System.out.println("все нажали");
                System.out.println("Текущая дата и время:" + ft.format(dateNow));

                scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                // Now you can do whatever you need to do with it, for example copy somewhere
                FileUtils.copyFile(scrFile, new File("c:\\VISA\\"+ft.format(dateNow)+".п."+i+".png"));
               // FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));


                if (isElementPresent(By.xpath("//*[contains(text(), 'В настоящее время нет свободных мест для записи')]"))) {
                    System.out.println("В настоящее время нет свободных мест для записи");
                    Sound.playSound("c://visa//kap.wav").join();

                    System.out.println(". Делаем логаут");
                    driver.findElement(By.xpath("//*[contains(@id, 'navbarDropdown')]")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[contains(text(), 'Выйти')]")).click();
                    System.out.println("нажали логаут, пауза 15 сек");
                    Thread.sleep(15000);


                } else {
                    System.out.println("Возможно есть места");
                    dateNow = new Date();
                    FileUtils.copyFile(scrFile, new File("c:\\VISA\\DATY-"+ft.format(dateNow)+".png"));
                    // надо запускать что-то, музыку например громко
                    for (int j = 0; j < 30; j++) {
                        Sound.playSound("c://visa//mario.wav").join();
                    }
                 break;
                }

            }
        }

        @After
        public void tearDown() throws Exception {
            // driver.quit();

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            Date dateNow = new Date();
            FileUtils.copyFile(scrFile, new File("c:\\VISA\\ERROR-"+ft.format(dateNow)+".п."+i+".png"));
            // FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
            Sound.playSound("c://visa//err.wav").join();

            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }


