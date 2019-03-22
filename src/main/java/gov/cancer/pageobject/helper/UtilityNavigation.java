package gov.cancer.pageobject.helper;

import java.util.regex.Pattern;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.WebElement;

public class UtilityNavigation {

	WebElement element;

	public UtilityNavigation(WebElement element) {
		this.element = element;
	}

	public Boolean isLinkElement() {
		if(element != null) {
		
			return element.getTagName().equalsIgnoreCase("a");
			
		}
		else
			System.out.println(element.getTagName().equalsIgnoreCase("a"));
			return false;
	}

	public String getLinkText() {
		if(element != null) {
			return element.getText();
			
		}
		else
			System.out.println(element);
			return "";
	}


	public boolean isLinkTextBlank() {
		 
		if (element.getText().isEmpty()) {
		 
			return false;
		}
		else {
		 
			return true;
			
		}
			
	}
	

	public boolean isLinkHrefBlank() {
 
		if (element.getAttribute("href").isEmpty()) {
			 
			return false;
		}
		else {
			System.out.println(element.getAttribute("href"));
			return true;
		}
			
	}
	
	// ===== is link broken
	public boolean isLinkBroken() {
		if (Pattern.matches("\\s+", element.getAttribute("href")))
			return false;
		else
			 
			return true;
	}
	
	public void isLinkExternal() {
		if(getIsExternal()) {
			//do something if the link is external
		}
		else {
			//do something else if the link is not external
		}
	}

	public String getLinkUrl() {
		throw new NotImplementedException("getLinkUrl");
	}

	public Boolean getIsExternal() {
		// Return true for links to anything other than .gov sites.
		if (!element.getAttribute("href").contains(".gov"))
			return true;
		else
			throw new NotImplementedException("getIsExternal");
	}
}
