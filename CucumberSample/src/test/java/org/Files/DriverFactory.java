package org.Files;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
		private static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
		    
		    // To quit the drivers and browsers at the end only.
		    private static List<WebDriver> storedDrivers = new ArrayList<WebDriver>();
		    
		    static {
		        Runtime.getRuntime().addShutdownHook(new Thread() {
		            public void run() {
		                storedDrivers.forEach(WebDriver::quit);
		            }
		        });
		    }
		    
		    private DriverFactory() {}
		    
		    public static WebDriver getDriver() {
		        return drivers.get();
		    }
		    
		    public static void addDriver(WebDriver driver) {
		        storedDrivers.add(driver);
		        drivers.set(driver);
		    }
		    
		    public static void removeDriver() {
		        storedDrivers.remove(drivers.get());
		        drivers.remove();
		    }


}
