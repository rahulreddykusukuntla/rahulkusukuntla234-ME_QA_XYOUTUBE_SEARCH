package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Wrappers {
    
    /*
     * Write your selenium wrappers here
     */
    // static ChromeDriver driver;
    
    
    public static void sAssert(String actual,String expected,String message){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,expected,message);
        softAssert.assertAll();
    }
    public static void sAssertTrue(boolean actual,String message){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actual,message);
        softAssert.assertAll();
    }

    public static void clickAbout(ChromeDriver driver){
        
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='About']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element=driver.findElement(By.xpath("//a[text()='About']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public static String getAboutContent(ChromeDriver driver){
        WebElement element=driver.findElement(By.xpath("//section[@class='ytabout__content']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        String text=element.getText();
        return text;
    } 
    public static void clickMovies(ChromeDriver driver){
        
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@id='endpoint'])[9]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element=driver.findElement(By.xpath("(//a[@id='endpoint'])[9]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public static String getMovieRating(ChromeDriver driver){
        WebElement element=driver.findElement(By.xpath("(//div[@class='badge  badge-style-type-simple style-scope ytd-badge-supported-renderer style-scope ytd-badge-supported-renderer'])[16]/p"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        String text=element.getText();
        return text;
    }
    public static String getMovieCategory(ChromeDriver driver){
        WebElement element=driver.findElement(By.xpath("(//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer'])[16]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        String text=element.getText();
        return text;
    }
    public static void clickMusic(ChromeDriver driver){
        
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Music']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element=driver.findElement(By.xpath("//a[@title='Music']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public static String getPlaylist(ChromeDriver driver){
        WebElement element=driver.findElement(By.xpath("(//a[@class='yt-simple-endpoint style-scope ytd-compact-station-renderer'])[11]/h3"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        String text=element.getText();
        return text;
    }
    public static String getNoOfTracks(ChromeDriver driver){
        WebElement element=driver.findElement(By.xpath("(//a[@class='yt-simple-endpoint style-scope ytd-compact-station-renderer'])[11]/p"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        String text=element.getText();
        return text;
    }
    public static void clickNews(ChromeDriver driver){
        
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='News']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element=driver.findElement(By.xpath("//a[@title='News']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public static void scrollTo(ChromeDriver driver,WebElement element){
        
       
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        js.executeScript("arguments[0].scrollIntoView();", element);
        
    }

    public static long getLikes(String str){
        if(!str.isEmpty()){
            if(str.charAt(str.length()-1)=='K'){
                float f=Float.parseFloat(str.substring(0, str.length()-1));
                return (long) (f*1000);
            }
            else if(str.charAt(str.length()-1)=='M'){
                float f=Float.parseFloat(str.substring(0, str.length()-1));
                return (long) (f*1000000);
            }
            else{
                return Long.parseLong(str);
            }
        }
        return 0;
        
    }
    public static void toBeSearched(ChromeDriver driver,String key){
        
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));
        
        WebElement element=driver.findElement(By.xpath("//input[@id='search']"));
        
        element.sendKeys(key);
        WebElement search=driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
        search.click();
    }



}
