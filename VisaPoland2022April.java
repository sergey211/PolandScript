package org.example;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ls.LSOutput;
import vis.litva.org.example.VisaConfProperties;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

//import org.apache.http.client.fluent.*;

public class VisaPoland2022April {
    //private static WebElement driver;
    public static WebDriver driver;
    private String baseUrl;
    //  private SecondPage = new secondPage(driver);
    private boolean acceptNextAlert = true;
    public static Date dateNow = new Date();
    private StringBuffer verificationErrors = new StringBuffer();
    public static SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy.HH-mm-ss");
    public static int i;
    public static int g;
    public static boolean quick = false;
    public static int pauza = 1; //sec
    public static int speeding = 0; //sec
    public static int speed = 0; //sec
    public static boolean isSpeeding = false;


    public static String name;
    public static String surname;
    public static String passport;
    public static String birthdate;
    public static String exppaspdate;
    public static String sex;
    public static String phonecode;
    public static String phonenumber;
    public static String email;

    static String login;


    @Before
    public void setUp() throws Exception {


        //       пример скрытия данных:
        //       System.setProperty("webdriver.chrome.driver", VisaConfProperties.getProperty("chromedriver"));


        //          ChromeOptions options = new ChromeOptions();
        //       options.addArguments("--headless");  // вообще не запускает бразуер
        //        options.addArguments("--disable-infobars");
        //       options.addArguments("user-agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', 'Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0\"");
        // с этим агентом не запрашивает куки


        //    options.setBinary("c:\\Program Files\\Opera\\36.0.2130.80\\opera.exe");
        //    options.setBinary("c:\\Program Files\\Opera\\launcher.exe");
        //   ChromeDriver driver = new ChromeDriver(options);


        //    driver = new ChromeDriver(options);

        //  C:\Program Files\Opera\36.0.2130.80

        // Create an object of desired capabilities class with Chrome driver
        //        DesiredCapabilities SSLCertificate = DesiredCapabilities.chrome();

        // Set the pre defined capability – ACCEPT_SSL_CERTS value to true
        //      SSLCertificate.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        // Open a new instance of chrome driver with the desired capability
        //       driver = new ChromeDriver(SSLCertificate);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("c:\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); // БЕЗ ЭТОГО ОШИБКА 1020
        options.addArguments("--disable-infobars"); // ТОЖЕ НАДО
        // options.addArguments("--headless"); // браузер вообще не открывается
        options.merge(capabilities);
        //    Configuration.holdBrowserOpen = true;
        driver = new ChromeDriver(service, options);

        //   driver = new FirefoxDriver();
        //    driver = new OperaDriver();
// Не удается получить доступ к сайту

        //  https://piprogramming.org/articles/How-to-make-Selenium-undetectable-and-stealth--7-Ways-to-hide-your-Bot-Automation-from-Detection-0000000017.html


        // baseUrl = "https://visa.vfsglobal.com/blr/ru/ltu/login"; // ЛИТВА
        baseUrl = "https://visa.vfsglobal.com/blr/ru/pol/book-an-appointment"; // ПОЛЬША
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testPolshaApril() throws Exception {

        System.out.println("Ищем даты на ПОЛЬШУ " + dateNow);
        driver.get(baseUrl);

        System.out.println("ждем 3 сек");
        Thread.sleep(3000);

        if (isElementPresent(By.id("onetrust-accept-btn-handler"))) {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            System.out.println("clicked куки");
            System.out.println("ждем 2 сек");
            Thread.sleep(2000);
        } else {
            System.out.println("кнопки с кукис нету");
        }


        // получаем набор дескрипторов текущих открытых окон
        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldWindowHandle = oldWindowsSet.iterator().next();
        System.out.println("oldset="+oldWindowsSet);
        // нажимаем на ссылку, которая открывает документ в новом окне
        driver.findElement(By.linkText("Записаться сейчас")).click();


        // получаем новый набор дескрипторов, включающий уже и новое окно
        Set<String> newWindowsSet = driver.getWindowHandles();
        System.out.println("set new= "+newWindowsSet);
        // получаем дескриптор нового окна

        newWindowsSet.removeAll(oldWindowsSet); // без этого не переключится
        String newWindowHandle = newWindowsSet.iterator().next();
        driver.switchTo().window(newWindowHandle);
        Thread.sleep(3000);




        if (isElementPresent(By.cssSelector("h2"))) {
            inLine();

        }

       driver.findElement(By.id("EmailId")).sendKeys("kirmaxik@mail.ru");
        //       driver.findElement(By.id("EmailId")).sendKeys("sergey211@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("!GRUppa104");

        System.out.println("Ждем 50 сек для ввода капчи и входа");
        Sound.playSound("c://visa//sound//kap.wav").join();
        Thread.sleep(50000);
        Sound.playSound("c://visa//sound//kap.wav").join();
        System.out.println("проверяем");
        // target frame detached = not found

        zapisNaPodachu();

         }




    private void zapisNaPodachu() throws Exception {



        // Ваша учетная запись заблокирована, войдите в систему после 2 минут.

    /*    WebElement element = driver.findElement(By.linkText("Запись на подачу документов"));
        System.out.println("Кнопка записи найдена, нажмем через 2 сек");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
*/
    //
        // checkErrors();
        if (isElementPresent(By.xpath("//*[contains(text(), 'You are now in line.')]")))
        {
            inLine();
        }
        else {
            //You are now in line.
            System.out.println("ищем кнопку");

            driver.switchTo().defaultContent();
            System.out.println("перекючились");
            if (isElementPresent(By.xpath("//*[contains(text(), 'Запись на подачу документов')]"))) {
                System.out.println("найдена сразу");
                driver.findElement(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")).click();
            }
  /*      else {
            driver.switchTo().frame(0);

            if (isElementPresent(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")))
            {
                System.out.println("найдена во фрейме 0");
                driver.findElement(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")).click();}

             else {
                driver.switchTo().frame(1);
                if (isElementPresent(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")))
                {   System.out.println("найдена во фрейме 1");
                    driver.findElement(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")).click();}

        else {
                    driver.switchTo().defaultContent();
                    if (isElementPresent(By.xpath("//*[contains(text(), 'Запись на подачу документов')]"))) {
                        System.out.println("найдена в дефолт");
                        driver.findElement(By.xpath("//*[contains(text(), 'Запись на подачу документов')]")).click();
                    } else {
                        System.out.println("not found frame");
                    }
                }}}


*/
            //     driver.findElement(By.linkText("Запись на подачу документов")).click();

            //      By.xpath("//*[contains(text(), 'Access denied')]"
            System.out.println("Кнопка записи нажата");
            Thread.sleep(10000);

            checkCenters();

        }
        }



    private void checkCenters() throws Exception {


        for (i = 1; i < 200; i++) {
            System.out.println("цикл проверки: " + i);
            checkErrors();
            checkCenter("option[value=\"633\"]", "option[value=\"1760\"]", "Курьер. Подача документов г. Минск"); // postalMinskD
            checkCenter("option[value=\"115\"]", "option[value=\"302\"]", "Визовый Центр Польши, Минск"); // privMinskD
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Пинск");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Брест");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Гомель");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Гродно");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Лида");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Барановичи");
            checkCenter(" ", "option[value=\"302\"]", "Визовый Центр Польши, Могилёв");

            //       checkCenter("", "option[value=\"115\"]","option[value=\"1767\"]"); // kartaPolakaMinsk

// Визовый Центр Польши, Минск

            //driver.findElement(By.id("btnContinue")).click();

            // class="loaderImg"
            //Server Error
            //Sorry, looks like you were going too fast.
            //Sorry, Something has gone wrong!

        /*    System.out.println("gde eto my???");
            Sound.playSound("c://visa//sound//mario.wav").join();
            driver.findElement(By.id("VisaCategoryId")).click();
            Sound.playSound("c://visa//sound//wow.wav").join();
          */
            System.out.println(i + " цикл закончен");
        }
    }
            private void inLine() throws Exception {

        quick=false;
        String minsToWait = driver.findElement(By.cssSelector("h2")).getText();
        System.out.println(minsToWait);
        String lastUpdated = driver.findElement(By.xpath("//div[@id='wrapper']/main/div/section/section[2]/p[3]/strong")).getText();
        System.out.println("time: "+lastUpdated);
        String[] parts = minsToWait.split(" ");

        //         System.out.println("5=" + parts[5]); // int
        //         System.out.println("6=" + parts[6]); // minutes
        //      System.out.println("7="+parts[7]);
              //  Your estimated wait time is more than 1 hour...
              //  Your estimated wait time is 18 minutes...

          /*      if (parts[5].equals("more"))
                {
                    System.out.println("More tnen hour");
                    Thread.sleep(1000);
                }


           */
     //   int mins = Integer.parseInt(parts[5]);
        String mins = parts[5];
        for (i = 1; i < 100; i++) {

            System.out.println("mins = " + mins);
            if ((mins.equals(3))||(mins.equals(2))||(mins.equals(1))) {
                System.out.println(mins + "<3, ждем, скоро должна появится страница логина");
                //  break;

            }

            System.out.println("ждем минуту");
   /*         if (isElementPresent(By.xpath("//*[contains(text(), 'Access denied')]"))) {
                System.out.println("Access denied");
                driver.close(); // закрывает текущее окно
                break;

            }
     */
                // Error 504
                // Sorry, Something has gone wrong!
                // ждать 3 мин и смена аккаунта
                System.out.println("проверяем что мы не на странице выбора центра, время:");
            Date dateNow = new Date();
            System.out.println(""+ ft.format(dateNow));



            if (isElementPresent(By.id("LocationId"))) {
                    System.out.println("мы на странице выбора центра");
                    checkCenters();
                    break;
                } else
                    System.out.println("проверяем что мы не на странице входа на сайт, время:");
            dateNow = new Date();
            System.out.println(""+ ft.format(dateNow));

            if (isElementPresent(By.id("EmailId"))) {
                    System.out.println("Login Page Found");
                    break;

                } else
                dateNow = new Date();


            System.out.println("проверяем что мы на странице очереди, время:");
            System.out.println(""+ ft.format(dateNow));


                if (isElementPresent(By.xpath("//*[contains(text(), 'You are now in line')]"))) {
                    minsToWait = driver.findElement(By.cssSelector("h2")).getText();
                    System.out.println(minsToWait);
                    lastUpdated = driver.findElement(By.xpath("//div[@id='wrapper']/main/div/section/section[2]/p[3]/strong")).getText();
                    System.out.println("time: " + lastUpdated);
                    parts = minsToWait.split(" ");
                    //         System.out.println("5=" + parts[5]); // int
                    //         System.out.println("6=" + parts[6]); // minutes
                    //mins = Integer.parseInt(parts[5]);
                    mins = parts[5];
                    System.out.println("проверяем..");
                }
                else {
                    System.out.println("dont understand where are we");

            }
        }
        //  Thread.sleep(120000);



    }

    private void checkErrors() throws Exception {
        if (isElementPresent(By.id("LocationId"))) {
            // System.out.println("No Errors");
        } else {
            System.out.println("Found Error");
            if
            (isElementPresent(By.xpath("//*[contains(text(), 'You are now in line')]"))) {
                System.out.println("You are now in line");
                System.out.println("переходим в очередь");
                inLine();
            }
            else if
            (isElementPresent(By.xpath("//*[contains(text(), 'Sorry, looks like you were going too fast')]"))) {
                System.out.println("Sorry, looks like you were going too fast");
                isSpeeding=true;
                speed=g;
                speeding++;

                driver.close();

                // получаем новый набор дескрипторов, включающий уже и новое окно
                Set<String> newWindowsSet = driver.getWindowHandles();
                System.out.println("set new 1 = "+newWindowsSet);
                Thread.sleep(3000);
                // получаем дескриптор нового окна


                String newWindowHandle = newWindowsSet.iterator().next();
                driver.switchTo().window(newWindowHandle);
                Thread.sleep(3000);
                System.out.println("проверяем");
               // driver.get("https://ya.ru");


                pauza++;
                zapisNaPodachu();

            }


            else if
             (isElementPresent(By.id("LocationId"))) {
                 System.out.println("No Errors");
            }
            else if (isElementPresent(By.xpath("//*[contains(text(), 'Access denied')]"))) {
                System.out.println("Access Denied");
                driver.close();
            }
            else if
            (isElementPresent(By.xpath("//*[contains(text(), 'Your estimated wait')]"))) {
                System.out.println("Your estimated wait");
                driver.close();
            } else if
            (isElementPresent(By.xpath("//*[contains(text(), 'Sorry, Something has gone wrong!')]"))) {
                System.out.println("Sorry, Something has gone wrong!");
                driver.close();
            }
            // You are now in line.

            else if
            (isElementPresent(By.xpath("//*[contains(text(), 'Server Error')]"))) {
                System.out.println("Server Error");
                driver.close();
            }
            else if
            (isElementPresent(By.id("LocationId"))) {
                 System.out.println("No Errors");
            }
            else if
            (isElementPresent(By.xpath("//*[contains(text(), 'Не удается получить доступ')]"))) {
                System.out.println("Не удается получить доступ (нету интернета)");
                driver.close();
            }
            else

            {  System.out.println("Непонятная ошибка, делаем скриншот");
                Date dateNow = new Date();
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("c:\\VISA\\LOGS\\ERROR-"+ft.format(dateNow)+".п."+i+g+".png"));
                driver.close();
                System.out.println("закрыли страницу и пробуем снова войти");
                driver.close();

                // получаем новый набор дескрипторов, включающий уже и новое окно
                Set<String> newWindowsSet = driver.getWindowHandles();
                System.out.println("set new 1 = "+newWindowsSet);
                Thread.sleep(3000);
                // получаем дескриптор нового окна


                String newWindowHandle = newWindowsSet.iterator().next();
                driver.switchTo().window(newWindowHandle);
                Thread.sleep(3000);
                System.out.println("проверяем");
                // driver.get("https://ya.ru");
            }

        }


    }


    private void checkCenterQuick(String category, String subcategory, String namecenter) throws Exception {

        if (isElementPresent(By.id("LocationId"))) {//PASS
        } else {
            checkErrors();
        }
        g++;
        System.out.println("выбираем категорию " + g + " " + namecenter);
        Thread.sleep(3500);
        new Select(driver.findElement(By.id("LocationId"))).selectByVisibleText(namecenter);
        Thread.sleep(3500);

    /*    for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (!isElementPresent(By.cssSelector("img.loaderImg"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
*/


        System.out.println("раскрываем сабкатегорию");
        // рекорд 42 города проверено
        // бывает долго его ищет
        if (isElementPresent(By.id("VisaCategoryId")))
        {
            Thread.sleep(2500);
            driver.findElement(By.id("VisaCategoryId")).click();
        }
        Thread.sleep(1500);
        System.out.println("кликаем выбранную сабкатегорию и ждем загрузки");
        driver.findElement(By.cssSelector(subcategory)).click(); //
        //new Select(driver.findElement(By.id("VisaCategoryId"))).selectByVisibleText(namecenter);
        System.out.println("загрузилось, пауза 3 сек");

        Thread.sleep(3000);

  /*      for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (!isElementPresent(By.cssSelector("img.loaderImg"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
*/


        if (isElementPresent(By.xpath("//*[contains(text(), 'Нет доступных мест')]"))) {
            System.out.println("Нет доступных меeeст " + namecenter);
            String errorText = driver.findElement(By.xpath("//*[contains(text(), 'Нет доступных мест')]")).getText();
            System.out.println(errorText);
        }
                 /*
                String errstr = driver.findElement(By.id("LocationError")).getText();
                System.out.println("ошибка");
                System.out.println(errstr);
                if ((errstr.contains("Нет доступных мест"))|(errstr.contains("There are no open seats")))
                // There are no open seats
                {}
                else{
                    System.out.println("похоже пустая ошибка, клиикаем и ждем ");
                    driver.findElement(By.id("VisaCategoryId")).click(); // ошибка если выключена кнопка
                    driver.findElement(By.cssSelector(subcategory)).click();
                    driver.findElement(By.id("btnContinue")).click();
                    Thread.sleep(3000);
                }
                */

        else {
            checkErrors();
            System.out.println("ошибки нету в категории " + namecenter);
            Sound.playSound("c://visa//mario.wav").join();
            Sound.playSound("c://visa//mario.wav").join();
            Sound.playSound("c://visa//mario.wav").join();
            System.out.println("пробуем продолжить , жмем  ");

            driver.findElement(By.id("btnContinue")).click();
            Thread.sleep(3000);
            checkErrors();

            if (isElementPresent(By.xpath("//*[contains(text(), 'Обязательное поле')]"))) {
                System.out.println("Обязательное поле");
                driver.findElement(By.id("btnContinue")).click();
                Thread.sleep(3000);

            }

            else {
                System.out.println("что там сейчас то?? ");
// Обязательное поле

                Sound.playSound("c://visa//mario.wav").join();
                Sound.playSound("c://visa//mario.wav").join();
                Sound.playSound("c://visa//mario.wav").join();
                Sound.playSound("c://visa//mario.wav").join();
                Sound.playSound("c://visa//mario.wav").join();

                Date dateNow = new Date();
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("c:\\VISA\\LOGS\\WTF-"+ft.format(dateNow)+".п."+i+g+".png"));

            }
         //   System.out.println(" надо дописать то делать дальше ");

        }
    }

    private void checkCenter(String category, String subcategory, String namecenter) throws Exception {

      if (isSpeeding)
      {
          System.out.println("speeding="+speeding+"speed"+speed);
      }

        System.out.println("пауза = "+pauza);
        int pauzaInMsec=pauza*1000;
        Thread.sleep(pauzaInMsec);
        if (quick == true) {
            checkCenterQuick(category, subcategory, namecenter);
        } else {
            if (isElementPresent(By.id("LocationId"))) {//PASS
            } else {
                checkErrors();
            }

            // Обязательное поле
            // Нет доступных мест
            Thread.sleep(2500);
            g++;
            System.out.println("выбираем город №" + g + " " + namecenter);
            new Select(driver.findElement(By.id("LocationId"))).selectByVisibleText(namecenter);
            Thread.sleep(3500);

      /*      for (int second = 0;; second++) {
                if (second >= 60) fail("timeout");
                try { if (!isElementPresent(By.cssSelector("img.loaderImg"))) break; } catch (Exception e) {}
                Thread.sleep(1000);
            }
*/

            System.out.println("проверяем ошибки");
            // if (isElementPresent(By.id("LocationError"))) {
            if (isElementPresent(By.xpath("//*[contains(text(), 'Нет доступных мест')]"))) {
                System.out.println("Нет доступных мест " + namecenter);
                String errorText = driver.findElement(By.xpath("//*[contains(text(), 'Нет доступных мест')]")).getText();
                System.out.println(errorText);
                 /*
                String errstr = driver.findElement(By.id("LocationError")).getText();
                System.out.println("ошибка");
                System.out.println(errstr);
                if ((errstr.contains("Нет доступных мест"))|(errstr.contains("There are no open seats")))
                // There are no open seats
                {}
                else{
                    System.out.println("похоже пустая ошибка, клиикаем и ждем ");
                    driver.findElement(By.id("VisaCategoryId")).click(); // ошибка если выключена кнопка
                    driver.findElement(By.cssSelector(subcategory)).click();
                    driver.findElement(By.id("btnContinue")).click();
                    Thread.sleep(3000);
                }
                */

            } else {
                System.out.println("ошибки нету в категории " + namecenter);
                quick = true;
                Sound.playSound("c://visa//mario.wav").join();
         //       Sound.playSound("c://visa//mario.wav").join();
         //       Sound.playSound("c://visa//mario.wav").join();
                System.out.println(" поверяем этот центр еще раз  ");
                checkCenterQuick(category, subcategory, namecenter);
            }
        }
    }

        @After
        public void finish() throws Exception {
           // driver.get("");
            // driver.quit();
            // driver.

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            Sound.playSound("c://visa//sound//err.wav").join();

            Date dateNow = new Date();
            FileUtils.copyFile(scrFile, new File("c:\\VISA\\LOGS\\ERROR-AND-STOP-"+ft.format(dateNow)+".п."+i+".png"));
             FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));

            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }




    public static boolean isElementPresent(By by) {
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

       public void retryForDetachedFrame(final WebDriver driver, final String elementXpath, final int frameId) throws Exception {
        int webDriverExceptionCounter = 0;
        while (webDriverExceptionCounter < 5) {
            try {
                driver.findElement(By.xpath(elementXpath));
                break;
            } catch (final WebDriverException ex) {
                webDriverExceptionCounter++;
                if (webDriverExceptionCounter == 5) {
                    System.out.println("WebDriverException, not trying again: {}");
                    //log.error("WebDriverException, not trying again: {}", webDriverExceptionCounter);
                    throw ex;
                } else {
                   // log.info("WebDriverException, retrying: {}", webDriverExceptionCounter);
                    System.out.println("WebDriverException, retrying: {}"+webDriverExceptionCounter);
                    Thread.sleep(500);
                    final WebDriverWait wait = new WebDriverWait(driver, 15);
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
                }
            } catch (final Exception e) {
               //log.error("Exception: {}", e.getClass());
                System.out.println("Exception: {}"+e.getClass());
                throw e;
            }
        }
    }


    }


