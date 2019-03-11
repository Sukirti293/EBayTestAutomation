package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjects {

    /**
     * PageObject Model to return WebElements find by ID
     */


    @FindBy(id = "com.ebay.mobile:id/edit_text_username")
    public WebElement SignUserName;

    @FindBy(id = "com.ebay.mobile:id/edit_text_password")
    public WebElement SignInPassword;

    @FindBy(id = "com.ebay.mobile:id/button_sign_in")
    public WebElement SignInButton;

    @FindBy(id = "com.ebay.mobile:id/home")
    public WebElement Home;

    @FindBy(id = "com.ebay.mobile:id/textview_sign_out_status")
    public WebElement SignOutStatus;

    @FindBy(id = "com.ebay.mobile:id/button_google_deny")
    public WebElement GoogleDeny;

    @FindBy(id = "com.ebay.mobile:id/search_box")
    public WebElement SearchBox;

    @FindBy(id = "com.ebay.mobile:id/search_src_text")
    public WebElement SearchText;

    @FindBy(id = "com.ebay.mobile:id/textview_item_name")
    public WebElement ProductPageName;

    @FindBy(id = "com.ebay.mobile:id/textview_item_price")
    public WebElement ProductPagePrice;

    @FindBy(id = "com.ebay.mobile:id/button_bin")
    public WebElement BuyNowButton;

    @FindBy(id = "com.ebay.mobile:id/item_title")
    public WebElement CheckOutPageName;

    @FindBy(id = "com.ebay.mobile:id/textview_item_price")
    public WebElement CheckOutPageprice;

    @FindBy(id = "com.ebay.mobile:id/take_action")
    public WebElement ReviewProductDetails;



}
