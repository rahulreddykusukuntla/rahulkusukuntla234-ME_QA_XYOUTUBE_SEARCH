package demo;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */
        @Test
        public void testCase01(){
                driver.get("https://www.youtube.com/");
                
                SoftAssert softAssert = new SoftAssert();
                String url=driver.getCurrentUrl();
                
                String actual="https://www.youtube.com/";
                Wrappers.sAssert(url,actual,"URL not matched");
                
                Wrappers.clickAbout(driver);
                // driver.findElement(By.xpath("//a[text()='About']")).click();
                
                url=driver.getCurrentUrl();
                actual="https://about.youtube/";
                Wrappers.sAssert(url,actual,"About URL not matched");
                String aboutText=Wrappers.getAboutContent(driver);
                System.out.println(aboutText);

        }
        @Test
        public void testCase02(){
                driver.get("https://www.youtube.com/");
                Wrappers.clickMovies(driver);
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Next']")));
                WebElement nextButton=driver.findElement(By.xpath("//button[@aria-label='Next']"));
                while(nextButton.isDisplayed()){
                        nextButton.click();
                }
                String rating=Wrappers.getMovieRating(driver);
                Wrappers.sAssert(rating,"U","Rating not matched");
                String category=Wrappers.getMovieCategory(driver);
                Wrappers.sAssertTrue(category.contains("Animation"), "Category not matched");

        }
        @Test
        public void testCase03(){
                driver.get("https://www.youtube.com/");
                Wrappers.clickMusic(driver);
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@aria-label='Next'])[1]")));
                WebElement nextButton=driver.findElement(By.xpath("(//button[@aria-label='Next'])[1]"));
                while(nextButton.isDisplayed()){
                        nextButton.click();
                }
                String playList=Wrappers.getPlaylist(driver);
                Wrappers.sAssert(playList,"Bollywood Dance Hitlist","PlayList not matched");
                String noOfTracks=Wrappers.getNoOfTracks(driver);
                int tracks=Integer.parseInt(noOfTracks.substring(0, 2));
                Wrappers.sAssertTrue(tracks<=50, "Tracks not matched");
                
        }
        @Test
        public void testCase04(){
                driver.get("https://www.youtube.com/");
                Wrappers.clickNews(driver);
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Latest news posts']/ancestor::div[6]//div[@id='contents']/ytd-rich-item-renderer)[1]")));
                java.util.List<WebElement> list=driver.findElements(By.xpath("//span[text()='Latest news posts']/ancestor::div[6]//div[@id='contents']/ytd-rich-item-renderer"));
                String n="";
                long v=0;
                for(int i=0;i<3;i++){
                       String s= list.get(i).findElement(By.xpath(".//div[@id='header']/div[2]")).getText();
                       String q=list.get(i).findElement(By.xpath(".//div[@id='body']//yt-formatted-string[@id='home-content-text']/span[1]")).getText();
                       System.out.println(s);
                       System.out.println(q);
                       n=list.get(i).findElement(By.xpath(".//div[@id='toolbar']//span[@id='vote-count-middle']")).getText();
                       System.out.println(n);
                       v+=Wrappers.getLikes(n);
                        
                }
                System.out.println(v);
                
                
        }
        @Test(dataProvider="excelData")
        public void testCase05(String toBeSearched){
                driver.get("https://www.youtube.com/");
                Wrappers.toBeSearched(driver, toBeSearched);
                java.util.List<WebElement> view=driver.findElements(By.xpath("//div[@class='text-wrapper style-scope ytd-video-renderer']//span[@class='inline-metadata-item style-scope ytd-video-meta-block'][1]"));
                long j=0;
                for(WebElement ele:view){
                        Wrappers.scrollTo(driver, ele);
                        String d=ele.getText();
                        j+=Wrappers.getLikes(d.substring(0, d.length()-6));
                        if(j>100000000){
                                break;
                        }
                }
                
                
        }

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}