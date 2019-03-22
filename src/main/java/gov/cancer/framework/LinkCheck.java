package gov.cancer.framework;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkCheck {
	public static void verifyLinkActive(String linkUrl)
	{
	
	    try 
	    {
	       URL url = new URL(linkUrl);
	      
	       HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	       httpURLConnect.setConnectTimeout(3000);
	       httpURLConnect.connect();
	       System.out.println(httpURLConnect.getResponseCode());
	       if(httpURLConnect.getResponseCode()==200)
	       {
	           System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	       }
	       else 
	       {
	           System.out.println(linkUrl+" - is Dowm");
	       }
	    } catch (Exception e) 
	    {

	    }
	} 
	
	public static boolean isLinkActive(String linkUrl)
	{
	boolean active = true;
	    try 
	    {
	       URL url = new URL(linkUrl); 
	       HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	       httpURLConnect.setConnectTimeout(3000);
	       httpURLConnect.connect();
	       System.out.println(httpURLConnect.getResponseCode());
	       if(httpURLConnect.getResponseCode()==200)
	       {
	           System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	       }
	       else 
	       {
	           System.out.println(linkUrl+" - is Dowm");
	           active = false;           
	       }
	    } catch (Exception e) 
	    {
	    	active = false;
	    }
	    return active;
	} 

}
