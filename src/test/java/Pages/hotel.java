package Pages;

import Base.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class hotel extends PageBase {

    public hotel(AppiumDriver driver) {
        super((AndroidDriver) driver);
    }
    @AndroidFindBy(xpath = "//android.widget.ImageView[2]")
    public WebElement Image;

    @AndroidFindBy(accessibility = "Filter")
    public WebElement filter;

    @AndroidFindBy(accessibility = "Free wifi")
    public WebElement FreeWifi;

    @AndroidFindBy(accessibility = "50%")
    public WebElement SeekBar;

    @AndroidFindBy(accessibility = "Apply")
    public WebElement ApplyBtn;

    @AndroidFindBy(accessibility = "Grand Royal Hotel Wembley, London 2.0 km to city 80 Reviews $180 /per night")
    public WebElement hotelResult;

    public void ClickOnImage() {

        waitForVisibility(Image);
        click(Image);

    }
    public void HotelFilter(){

        waitForVisibility(filter);
        click(filter);
        waitForVisibility(FreeWifi);
        click(FreeWifi);
        MoveSeekBar(SeekBar);
        click(ApplyBtn);

    }
    public void ShowHotelResults(){
        waitForVisibility(hotelResult);
        System.out.println(hotelResult);
    }



}
