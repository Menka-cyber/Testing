
package Sample.Testing;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.helper.HttpConnection.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.After;

public class stepdefinition {
	protected static final stepdefinition AdditionalConditions = null;
	WebDriver driver =null;
	WebDriverWait wait=null;
	
	
	@Before
	public void browser()
	{
		
	System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");// for setting chrome browser
	driver= new ChromeDriver();
    wait = new WebDriverWait(driver,30);
	
	}
  @Given("^User open the URL '(.*)'$")
  public void logged(String url) throws IOException
  {
	  
    given(url);
 }
  
  public void given(String url) throws IOException // for displaying the documentation link 
  {
	  
		driver.get(url);
		String S= driver.getTitle();
		System.out.println(S);
		getResponseCode(url);
		
  }
  public  void getResponseCode(String URL) throws IOException
  {
	  URL url = new URL("https://developer.here.com/documentation");
   HttpURLConnection http = (HttpURLConnection)url.openConnection();
      int statusCode = http.getResponseCode();
      System.out.println(statusCode);
       if (statusCode == 200)
       {
    	   System.out.println("Satus is verified as"+statusCode);}
    	   else
    	   {
    		   System.out.println("Satus is not verified as"+statusCode);
       }
      
  }
  @And("I navigate to documentation link$")
  public void navigate()  //navigate to documentation link
  {
	  
	  
	  //Thread.sleep(5);
	  String doc_x="//span[@data-title='Documentation']";
	  driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(doc_x)));
	  WebElement document = driver.findElement(By.xpath(doc_x));
	  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(doc_x)));
		 document.click();
		
	  
  }
  
       
  /*@And("user prints all the links$")
  public void printlink()
  {
	  List<WebElement> allLinks = driver.findElements(By.tagName("a"));


	   System.out.println("All links found on web page are: " + allLinks.size() + " links");

	   for (WebElement link : allLinks)
	   {

	   //print the links 
	   System.out.println(link.getAttribute("href"));

	   //print the links text
	   System.out.println(link.getText());
	   
	   }
	
  }
  */
  
   	@And("verify and display all the links which are 200$")
       public void links() throws IOException
       {
    	   List<WebElement> allLinks = driver.findElements(By.xpath("//body//following::a"));
          System.out.println("All links found on web page are: " + allLinks.size() + " links");

    	   for (int i=0;i<allLinks.size();i++)
    	   {
    		  
			String p = allLinks.get(i).getAttribute("href");
    		   try
    		   {
    		   URL url = new URL(p);
    	   HttpURLConnection http = (HttpURLConnection)url.openConnection();
    	      int statusCode = http.getResponseCode();
    		if((statusCode == 200))
    	   {
    		   System.out.println("count"+i+"/nheader"+(allLinks.get(i).getText())+"is a valid link");
    		   
    		   
    	   }
    		   else{
    			   System.out.println("response is not 200"+(allLinks.get(i).getText()));
    		   }
    		   }
    	   
    	  catch(Exception e)
    		   {
    		  System.out.print("Inavlid URL"+p);
    	   }
    		   }
    	   
      
    	 }
   	
   	@And("verify angular js is present in the web page$")
   	public void angularHasFinishedProcessing() throws InterruptedException, IOException {
   	 driver.get("https://developer.here.com/documentation");
     List<WebElement> links=driver.findElements(By.xpath("//body//following::a"));
   	 

     System.out.println("no of links:" +links.size());

     for(int i=0;i<links.size();i++)
     {
    	 String urlfromlink =links.get(i).getAttribute("href");
         if(!(links.get(i).getText().isEmpty()))
         {
        	 try
         
		   {
        	 URL url = new URL(urlfromlink);
      	   HttpURLConnection http = (HttpURLConnection)url.openConnection();
      	      int statusCode = http.getResponseCode();
      		if((statusCode == 200))
      		{
        driver.navigate().to(url);
       Thread.sleep(2000);
        apply(driver);
        links.remove(i);
      		}else{
             links.remove(i);
             }  
		   }
        	 catch(Exception e){
        		 System.out.print("inavlid link");
        	 }
        	 }
         else{
         links.remove(i);
         }       
     }
      
        }
 protected static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}
 public boolean apply(WebDriver driver) {
    boolean s= Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
   
     while(s==true){
System.out.println("Has angular Js");
    	 
     }
	return s;
	
     
     
     
 }
 	
 public  void navi(String[] args) ///navigating all the links in the web page
 {
     
    
}
 
@After
 public void af()
 {
	 driver.quit();
 }
 }
 
 

    	   
       
