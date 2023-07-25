package org.Files;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends BaseClass {
	 private static boolean initializer = true;
	  private static Properties props = null;
	  private BaseClass base;

	  public Hooks(BaseClass base) {

	    this.base = base;
	  }

	  @Before(order = 1)
	  public void printScenarioName(Scenario scenario) {
	    System.out.println("Executing Scenario: " + scenario.getName());
	  }

	  @Before(order = 2)
	  public void beforeAll() {
	    if (initializer) {
	      try (InputStream input =
	          Hooks.class.getClassLoader().getResourceAsStream("config.properties")) {
	        props = new Properties();
	        props.load(input);
	      } catch (IOException ex) {
	        ex.printStackTrace();
	      }
	      initializer = false;
	    }
	  }

	  @Before(order = 3)
	  public void setupBrowser() {
	    System.out.println("setting up browser");
	    WebDriver driver = null;
	    if (DriverFactory.getDriver() == null) {
	      if (StringUtils.compareIgnoreCase(props.getProperty("browser"), "chrome") == 0) {
	        WebDriverManager.chromedriver().setup();

	        driver = new ChromeDriver();

	        //        base.driver = new ChromeDriver();
	      }
	      if (StringUtils.compareIgnoreCase(props.getProperty("browser"), "edge") == 0) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	      }
	      base.setupDriver(driver);
	      base.maxWindow();
	      DriverFactory.addDriver(driver);
	    }
	  }

	  
	  
	   @After(order = 1)
	  public void closeDriver() {
	    //    BaseClass.driver.close();
	  }

	   @AfterStep()
	   public void addScreenshot(Scenario scenario) {
	     if (scenario.isFailed()) {
	       WebDriver driver = DriverFactory.getDriver();
	       final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	       scenario.attach(screenshot, "image/png", "image");

	   
	     }
	  
	}
}

