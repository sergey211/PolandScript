package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ProfilePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора меню пользователя
     */
  //  @FindBy(xpath = "//*[contains(@class, 'account__name_hasAccentLetter')]") // incorrect
    @FindBy(xpath = "//*[contains(@class, 'personal-info-login__text personal-info-login__text_decorated')]")

    private WebElement userMenu;
    /**
     * определение локатора кнопки выхода из аккаунта
     */
    // @FindBy(xpath = "//*[contains(@class, 'menu-item_action_exit menu__item menu__item_type_link')]")
    @FindBy(xpath = "//*[contains(@class, 'user-pic user-pic_has-plus_ user-account__pic')]")

    private WebElement logoutBtn;
    /**
     * метод для получения имени пользователя из меню пользователя
     */
    public String getUserName() {
        String userName = userMenu.getText();
        return userName; }
    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void entryMenu() {
        userMenu.click(); }
    /**
     * метод для нажатия кнопки выхода из аккаунта
     */
    public void userLogout() {
        logoutBtn.click(); } }