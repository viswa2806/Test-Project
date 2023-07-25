package org.Files;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
	public static WebDriver driver;
     public static String baseURL="";
     public List<String> itemsToBeDeleted = new ArrayList<String>();
     public static Actions a;
	 public static Select s;
	 public static Alert al;
	 public static Robot r;
	 
	 public static TakesScreenshot ts;
	 public static JavascriptExecutor jse;
	 public static File f;
	 public static WebDriverWait wait;
	 public void setupDriver(WebDriver d){
	 	drivers.set(d);
	 	driver=drivers.get();
	 	jse=(JavascriptExecutor)driver;
	 	
	 }
	 
	
      public void maximumPageLoadTime() {
    	  try {
    		  
    	  driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    	  }
    	  catch (Exception e) {
		   e.printStackTrace();
		}

       } 
      //sh
      public  void switchToChildwindow(int index) {  //switch to window based on index of child window
	    	 try {
	    		Set<String> allId = driver.getWindowHandles();
	    		List<String>li =new ArrayList<String>();
	    		li.addAll(allId);
	    		driver.switchTo().window(li.get(index));
	    	 }
	    		
	    	 catch (Exception e) {
			   e.printStackTrace();
			 }
				
	    }
	   public void switchToParentWindow() {        //switch to parent window
			 try {
				
			     String parentId = driver.getWindowHandle();
				 driver.switchTo().window(parentId);
				
			} catch (Exception e) {
			      e.printStackTrace();
			}

		}
	   public void refreshApp(String url) {        //refresh web application
		    try {
		    	   driver.get(driver.getCurrentUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		       

	}
	   public boolean switchToLatestChildWindow() {    //switch to latest child window (3rd child window onwards)
			boolean flag =false;
			try {
				int timer=20;
				int window=0;
				while(flag!=true&&timer >0) {
					Set<String> allWindows = driver.getWindowHandles();
					for (String winHAndle : allWindows) {
						driver.switchTo().window(winHAndle);
						window=window+1;
						if (window > 2) {
							flag=true;
						}
				}
					timer--;
				}
			  return flag;
			} catch (Exception e) {
				 e.printStackTrace();
				 return flag;
			}

		}
	
	   public boolean switchToFirstChildWindow() {    //Always switch to 2nd child window
				boolean flag =false;
				try {
					int timer=20;
					int window=0;
					while(flag!=true&&timer >0) {
						Set<String> allWindows = driver.getWindowHandles();
						for (String winHAndle : allWindows) {
							driver.switchTo().window(winHAndle);
							window=window+1;
							if (window== 2) {
								flag=true;
								break;
							}
					}
						timer--;
					}
				  return flag;
				} catch (Exception e) {
					 e.printStackTrace();
					 return flag;
				}

			}
	     public void close() {              //close a tab of browser
	     try {
	    	 driver.close();
		     } catch (Exception e) {
			e.printStackTrace();
		    }
      }
	       public void switchToFrame(String frames) {     //switch to frame
	    	   try {
	    		   driver.switchTo().frame(frames);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	    }
       public void switchToParentFrame() {          //switch to parent frame
      	 try {
      		 driver.switchTo().parentFrame();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
       public void switchToDefaultcontent() {     //to come out of frame
			try {
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

       public void maxWindow() {          //maximise window
      	 try {
      		 driver.manage().window().maximize();
      		 
			} catch (Exception e) {
			     e.printStackTrace();
			}
	     
	    }
        public void quit() {        //quit the browser
      	 try {
			driver.quit();}
      	 catch (Exception e) {
			 e.printStackTrace();
			}
      }
      public void navigate(String url) {   //navigate to url also contains cookies and backup
      	try {
      		driver.navigate().to(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
      }
      public void navigateback() {         //navigate backwards in browser
			try {
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
      public void navigateforward() {       //navigate forward in browser
			try {
				driver.navigate().forward();
			} catch (Exception e) {
				e.printStackTrace();
			}
      }
      public  void navigaterefresh() {        //refresh the navigating page
           try {
          	 driver.navigate().refresh();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
      //robot class
     public  void robotClass() {      //creating object for robot class
		try {
			r=new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
      public  void keyup(int keycode) {  //releasing key using robot class
         try {
        	 r=new Robot();
      	   r.keyPress(keycode);
		   } catch (Exception e) {
			e.printStackTrace();
		}
  }
      public void keydown(int keycode) {   //keypress using robot class
      	try {
      		r=new Robot();
      		 r.keyRelease(keycode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
      //jse
			
      
      public void scrollToElement(WebElement element) {  //scroll to particular element using java script executor
      	try {
      		jse.executeScript("arguments[0].scrollIntoView();", element);
			} catch (Exception e) {
				e.printStackTrace();
			}
      }
      public void highlightElement(WebElement element) {    //highlighting element
   	   jse.executeScript("arguments[0].style.border='3px solid red'", element);
		
		}
      public void javaScriptClick(WebElement element) {
   	   try {
   		   implicitwait();
			jse.executeScript("arguments[0].click();",element);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }
      //screenshot
      
      //screenshot
      
     
    
  //webelement methods
	public void click(WebElement element) {     //click particular element
		try {
			implicitwait();
			element.click();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	  }
	public void waitAndClick(WebElement element) {  //wait and click
		try {
			WaitUntil(element);
			element.click();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	  }
	public void clearAndSendKeys(WebElement element,String keysToSend) {     //clear and sendkeys to text box of application
		try {
			WaitUntilVisible(element);
			element.clear();
			element.sendKeys(keysToSend);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	  }
	public void waitAndSendKeys(WebElement element,String keysToSend) {   //wait and sendkeys to text box of application
		try {
			WaitUntilVisible(element);
			element.sendKeys(keysToSend);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	  }
   public void sendkeys(WebElement element,String value) {   //sendkeys to text box of application
		try {
			implicitwait();
			element.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fontsizecheck(WebElement element,String expectedfs) {  //check font size in UI
		try {
		String fs = element.getCssValue("font-size");
		if (fs.equalsIgnoreCase(expectedfs)) {
			System.out.println("valid font size");
			
		}
		else {
			System.out.println("not valid fontsize");
		}}
		catch (Exception e) {
			e.printStackTrace();
		}
      
	}
	public void fontcolour(WebElement element,String expectedcolour) {   //check font colour in UI
		try {
		String cssValue = element.getCssValue("color");
		if (cssValue.equalsIgnoreCase(expectedcolour)) {
			System.out.println("valid colour");
		}else {
			System.out.println("not valid colour");
		}}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	//action class
	    public static void actionClass() {          //declaring action class and create object
		  a=new Actions(driver);

		}
	    public void actionmte(WebElement actionmte) {   //mousehover to particular element using action class
	    	try {
	    		a=new Actions(driver);
	    		a.moveToElement(actionmte).build().perform();
			} catch (Exception e) {
				e.printStackTrace();
			}
		

	}
	public void actiondandd(WebElement from,WebElement to){    //drag and drop using action class
		try {
			a=new Actions(driver);
			a.dragAndDrop(from, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//select class
	public void selectClass(WebElement element) {    //creaating object for select class
		try {
			s=new Select(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectByIndex(int index) {            //select option in drop down using index of the options
		try {
			s.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
	public void selectByValue(String value) {          //select option in drop down using values
		try {
			s.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
	public void selectByVisibleText(String text) {             //select option ion drop down using visible text
		try {
			s.selectByVisibleText(text);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
	
	public void deselectByValue(String value) {       //deselect option in drop down using value
		try {
			s.deselectByValue(value);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void deselectBYVisibleText(String text) {   //deselect options in dropdown using visible text 
		try {
			 s.deselectByVisibleText(text);
		} catch (Exception e) {
			 e.printStackTrace();
		}
   }
	
	public void getalloptions() {                     //get all options in dropdown
	   try {
		   List<WebElement> options = s.getOptions();
		    System.out.println(options);
	 } catch (Exception e) {
		e.printStackTrace();
	 }
	}
	public void multipe() {                        //check whether mulitiple options present in dropdown or not
		try {
			boolean multiple = s.isMultiple();
			System.out.println(multiple);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//wait
	
	public  void implicitwait() {    //slowing driver 
		try {
			 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public void WaitUntilVisible(WebElement element) {
		try {
			wait=new WebDriverWait(driver, (long) 2);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void WaitUntil(WebElement element) {
		try {
		wait=new WebDriverWait(driver, (long) 5);
//		wait.until(ExpectedConditions.elementToBeClickable(element),)
		wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void explicitwait() {
		try {
			wait=new WebDriverWait(driver, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//alertmethods
	public void alert() {         //switch to alert box
		try {
			implicitwait();
			al = driver.switchTo().alert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void alertaccept() {    //accepting alert
	      try {
	    	  alert();
	    	  al.accept();
		} catch (Exception e) {
			 e.printStackTrace();
		}
   }
	public void alertDismiss() {    //dismissing alert
		  try {
			  alert();
			  al.dismiss();
		} catch (Exception e) {
			  e.printStackTrace();
		}

	}
	public void alertKey(String alertkey) {  //sending keys in alert box
		try {
			alert();
			al.sendKeys(alertkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alertgettext() {       //get text from the textbox in alert
		try {
			alert();
			String alertText = al.getText();
			System.out.println(alertText);
		} catch (Exception e) {
			e.printStackTrace();
		}
   }

	
   public void broken(String Url) {          //check broken links in a url to find the stability of the page
	   List<WebElement> links = driver.findElements(By.tagName("a"));
     
     Iterator<WebElement> it = links.iterator();
     HttpURLConnection huc = null;
	    int respCode = 200;
	    String url="";
     
     while(it.hasNext()){
         
          url= it.next().getAttribute("href");
         
         System.out.println(url);
     
         if(url == null || url.isEmpty()){
         System.out.println("URL is either not configured for anchor tag or it is empty");
             continue;
         }
         
         if(!url.startsWith(Url)){
             System.out.println("URL belongs to another domain, skipping it.");
             continue;
         }
         
         try {
             huc = (HttpURLConnection)(new URL(url).openConnection());
             
             huc.setRequestMethod("HEAD");
             
             huc.connect();
             
             respCode = huc.getResponseCode();
             
             if(respCode >= 400){
                 System.out.println(url+" is a broken link");
             }
             else{
                 System.out.println(url+" is a valid link");
             }
                 
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
	 
	  
    
    public void brokenImage() {      //broken image
     try {
	   int brokenimg=0;
	   List<WebElement> image = driver.findElements(By.tagName("img"));
	   for (WebElement images : image) {
			  if (images.getAttribute("naturalWidth").equals("0")) {
				  brokenimg++;
				  System.out.println("no.ofbroken"+brokenimg);
				  System.out.println(images.getAttribute("naturalwidth").equals("0"));
			}
		}}
     catch (Exception e) {
		e.printStackTrace();
	     }
     }
    public void databaseWaitTime() {
		try {
			Thread.sleep(3000);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
