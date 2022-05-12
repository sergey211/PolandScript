package org.example;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import vis.litva.org.example.VisaConfProperties;

public class VisaSpain2022 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", VisaConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");
        driver = new ChromeDriver();

        baseUrl = "https://blsspain-belarus.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() throws Exception {
        driver.get(baseUrl + "/book_appointment.php");
        new Select(driver.findElement(By.id("centre"))).selectByVisibleText("Minsk");
        new Select(driver.findElement(By.id("category"))).selectByVisibleText("Normal");
   //     driver.findElement(By.id("phone_code")).clear();
        driver.findElement(By.id("phone_code")).sendKeys("375");
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("phone")).sendKeys("336621228");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("sergey211@gmail.com");
      //  driver.findElement(By.linkText("Запросить проверочный код")).click();
        driver.findElement(By.id("otp")).clear();
        driver.findElement(By.id("otp")).sendKeys("2431");

     //   System.out.println("пауза 15 сек чтобы войти");

     //   Thread.sleep(15000);

        JavascriptExecutor Js = (JavascriptExecutor) driver;
        Js.executeScript("window.scrollBy(0,1000)");                   //scroll 1000 pixel verticaal


      //  driver.findElement(By.name("save")).click();
        System.out.println("Пауза 30 секунд до перехода");
        Thread.sleep(30000);

        // Verification code is expired.

        JavascriptExecutor Js2 = (JavascriptExecutor) driver;
        Js2.executeScript("window.scrollBy(0,2000)");                   //scroll 1000 pixel verticaal


        System.out.println("30 сек ждем зеленую кнопку");
        driver.findElement(By.name("agree")).click();


        for (int j=0;j<300;j++) {

            Date dateNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy.HH-mm-ss");
            System.out.println("попытка:"+j+" , дата: "+ft.format(dateNow));


            driver.findElement(By.id("app_date")).sendKeys("2022-04-15");

            Thread.sleep(5000);
/*
        Число 21 Slots Full
        Число 22 Slots Full
        Число 23 Not Allowed
        Число 24 Not Allowed
        Число 25 Not Allowed
        Число 26 Not Allowed
*/
            for (int k = 4; k < 6; k++) {
            for (int i = 2; i < 8; i++) {
                String chislo = driver.findElement(By.xpath("//tr["+k+"]/td[" + i + "]")).getText();
                System.out.print("Число: " + chislo);
                String p = driver.findElement(By.xpath("//tr["+k+"]/td[" + i + "]")).getAttribute("title");
                System.out.println(" статус " + p);

                if ((p.equals("Slots Full"))|(p.equals(("Off Day")))|(p.equals(("Not Allowed"))))
                {
                    // it is okay
                }
                else {
                    System.out.println("чтото другое");  // Book - запись

                    for (j = 0; j < 10; j++) {
                        Sound.playSound("c://visa//sound//mario.wav").join();
                    }
                    driver.findElement(By.xpath("//tr["+k+"]/td[" + i + "]")).click();
            //        driver.findElement(By.name("save")).click();
                    for (j = 0; j < 5; j++) {
                        Sound.playSound("c://visa//sound//mario.wav").join();
                    }
                    j=1000;
                    break;
                }
            }}

          //  JavascriptExecutor Js = (JavascriptExecutor) driver;
       //     Js.executeScript("window.scrollBy(0,-1000)");




            //  driver.findElement(By.id("VisaTypeId")).click();
            Thread.sleep(1000);

            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.id("pptIssuePalace"));
            action.moveToElement(we).moveToElement(driver.findElement(By.id("pptIssuePalace"))).click().build().perform();


            new Select(driver.findElement(By.id("VisaTypeId"))).selectByVisibleText("Schengen Type C - 5 Years");
            driver.findElement(By.id("first_name")).clear();


            Thread.sleep(3000);
            driver.findElement(By.id("first_name")).sendKeys("ANDREI");
            driver.findElement(By.id("last_name")).sendKeys("RYMASHEUSKI");
            driver.findElement(By.id("passport_no")).sendKeys("MC3112788");
            driver.findElement(By.id("pptIssuePalace")).sendKeys("MINISTRY OF INTERNAL AFFAIRS");

            driver.findElement(By.id("pptIssueDate")).sendKeys("2018-11-22");
    //      driver.findElement(By.xpath("//div[3]/table/thead/tr/th")).click();
            driver.findElement(By.cssSelector("div.datepicker-years > table.table-condensed > thead > tr > th.prev")).click();

            driver.findElement(By.xpath("//span[text()='2018']")).click();
            driver.findElement(By.xpath("//span[text()='Nov']")).click();
            driver.findElement(By.xpath("//td[text()='22']")).click();
            driver.findElement(By.id("pptExpiryDate")).sendKeys("2028-11-22");
            driver.findElement(By.xpath("//span[text()='2028']")).click();
            driver.findElement(By.xpath("//span[text()='Nov']")).click();
            driver.findElement(By.xpath("//td[text()='22']")).click();


            // выбираем ДР

            driver.findElement(By.id("dateOfBirth")).sendKeys("1984-01-09");
            driver.findElement(By.cssSelector("div.datepicker-years > table.table-condensed > thead > tr > th.prev")).click();
            driver.findElement(By.cssSelector("div.datepicker-years > table.table-condensed > thead > tr > th.prev")).click();
            driver.findElement(By.cssSelector("div.datepicker-years > table.table-condensed > thead > tr > th.prev")).click();
            driver.findElement(By.cssSelector("div.datepicker-years > table.table-condensed > thead > tr > th.prev")).click();
            driver.findElement(By.xpath("//span[text()='1984']")).click();
            driver.findElement(By.xpath("//span[text()='Jan']")).click();
            driver.findElement(By.xpath("//td[text()='9']")).click();

            JavascriptExecutor Js3 = (JavascriptExecutor) driver;
            Js3.executeScript("window.scrollBy(1100,0)");                   //scroll 1000 pixel verticaal


            // дата вьезда
            driver.findElement(By.id("travelDate")).sendKeys("2022-04-15");
            Thread.sleep(1000);
            //     driver.findElement(By.xpath("//td[text()='8']")).click();

       //     driver.findElement(By.xpath("//tr[6]/td[6]")).click();
            driver.findElement(By.cssSelector("th.next")).click();  // следующий месяц
            driver.findElement(By.xpath("//tr[3]/td[6]")).click(); // 15 апреля - 3я строка календаря, 6й ряд


            Thread.sleep(1000);

            // driver.findElement(By.id("app_date")).click();
            //   driver.findElement(By.id("app_date")).clear();



  /*          Число: 26 статус Off Day
            Число: 28 статус Slots Full
            Число: 1 статус Not Allowed
*/
     /*           if (((chislo.equals("21")) & (p.equals("Slots Full")))) {
     //               System.out.println("21 full");
                } else if (((chislo.equals("22")) & (p.equals("Slots Full")))) {
     //               System.out.println("22 full");
                } else if (((chislo.equals("23")) & (p.equals("Not Allowed")))) {
      //              System.out.println("23 not allowed");
                } else if (((chislo.equals("24")) & (p.equals("Not Allowed")))) {
      //              System.out.println("24 not allowed");
                } else if (((chislo.equals("25")) & (p.equals("Not Allowed")))) {
      //              System.out.println("25 not allowed");
                } else if (((chislo.equals("26")) & (p.equals("Not Allowed")))) {
      //              System.out.println("26 not allowed");
                } else {
                    System.out.println("чтото другое");

                    for (j = 0; j < 5; j++) {
                        Sound.playSound("c://visa//sound//mario.wav").join();
                    }
                    break;

                }


            }
*/

            System.out.println("В настоящее время нет свободных мест для записи");
            Sound.playSound("c://visa//sound//coin.wav").join();

            if (j<1000) {driver.navigate().refresh();}
            System.out.println("пауза 5 сек ");
            Thread.sleep(5000);
        }

    }

    @After
    public void tearDown() throws Exception {
       // driver.quit();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy.HH-mm-ss");
        FileUtils.copyFile(scrFile, new File("c:\\VISA\\ERROR-"+ft.format(dateNow)+".png"));
        // FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
        Sound.playSound("c://visa//sound//err.wav").join();


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
