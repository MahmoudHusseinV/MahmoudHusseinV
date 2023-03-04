package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class PageBase {
    protected AppiumDriver driver;

    public AndroidTouchAction actions;


    public PageBase(AndroidDriver driver) {
        this.driver = driver;
        AppiumFieldDecorator appfieldDecorator = new AppiumFieldDecorator(this.driver, Duration.ofSeconds(30));
        PageFactory.initElements(appfieldDecorator, this);
    }

    public boolean waitForVisibility(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void click(WebElement element) {

        element.click();
    }

    public void clear(WebElement element) {

        element.clear();
    }

    public void sendText(WebElement element, String Text) {

        element.clear();
        element.sendKeys(Text);
    }
    public void MoveSeekBar(WebElement Seek_bar) {
        //Locating seekbar using resource id
        int start=Seek_bar.getLocation().getX();
        //Get width of seekbar
        int end=Seek_bar.getSize().getWidth();
        //get location of seekbar vertically
        int y=Seek_bar.getLocation().getY();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start, end));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),start, end));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

    }
    protected void Swipe(){
        Point source = new Point(515, 1900);
        Point target = new Point(515, 1080);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),target.x, target.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }


    protected void ScrollToElement(WebElement element, String direction, int ScrollCount) {
        int count = 0;
        boolean isDisplayed = false;

        while (!isDisplayed && count++ < ScrollCount) {
            touchScroll(direction);
//			try {
//				isDisplayed = element.isDisplayed();
//
//			} catch (NoSuchElementException | AssertionError | IndexOutOfBoundsException e) {
//				touchScroll(direction);
//			}
        }


//		Assert.assertTrue(format("Element was not found after [%s] scrolls", count), isDisplayed);
    }

    private void touchScroll(String scrollDirection) {
        Map<String, Integer> map;
        Dimension dimension = driver.manage().window().getSize();

        int centerX = dimension.width / 2;
        int centerY = dimension.height / 2;
        int topScreen = (int) (dimension.height * .30);
        int bottomScreen = (int) (dimension.height * .60);
        int rightScreen = (int) (dimension.width * .90);
        int leftScreen = (int) (dimension.width * .10);

        switch (scrollDirection) {
            case "up":
                map = setMoveToCOORD(centerX, topScreen, centerX, bottomScreen);
                break;
            case "down":
                map = setMoveToCOORD(centerX, bottomScreen, centerX, topScreen);
                break;
            case "right":
                map = setMoveToCOORD(rightScreen, centerY, leftScreen, centerY);
                break;
            case "left":
                map = setMoveToCOORD(leftScreen, centerY, rightScreen, centerY);
                break;
            default:
                throw new IllegalArgumentException(
                        "Incorrect scroll direction given: Direction must be [up], [down], [left], or [right]");
        }

        new TouchAction((PerformsTouchActions) driver)
                .longPress(longPressOptions().withPosition(point(map.get("fromX"), map.get("fromY"))))
                .moveTo(point(map.get("toX"), map.get("toY")))
                .release()
                .perform();
    }

    private Map<String, Integer> setMoveToCOORD(int fromX, int fromY, int toX, int toY) {
        Map<String, Integer> coordinates = new HashMap<>();
        coordinates.put("fromX", fromX);
        coordinates.put("fromY", fromY);
        coordinates.put("toX", toX);
        coordinates.put("toY", toY);
        return coordinates;
    }
}