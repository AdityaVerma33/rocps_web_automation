package com.subex.automation.helpers.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.subex.automation.helpers.selenium.AcceptanceTest;

public class JSoupHelper extends AcceptanceTest {

	private static Document document = null;

	public Document initializeDocument() throws Exception {
		try {
			document = Jsoup.parse( driver.getPageSource());
			
			return document;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Elements getElements(String cssQuery) throws Exception {
		try {
			initializeDocument();
			Elements elements = document.select(cssQuery);
			
			if (elements != null && elements.size() > 0)
				return elements;
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Elements getElements(String parentCSSQuery, String cssQuery) throws Exception {
		try {
			Elements parentElements = getElements(parentCSSQuery);
			
			if (parentElements != null && parentElements.size() > 0) {
				Elements elements = parentElements.select(cssQuery);
				
				if (elements != null && elements.size() > 0)
					return elements;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Elements getElements(String wrapperCSSQuery, String parentCSSQuery, String cssQuery) throws Exception {
		try {
			Elements parentElements = getElements(wrapperCSSQuery, parentCSSQuery);
			
			if (parentElements != null && parentElements.size() > 0) {
				Elements elements = parentElements.select(cssQuery);
				
				if (elements != null && elements.size() > 0)
					return elements;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Elements getElements(Elements parentElements, String cssQuery) throws Exception {
		try {
			if (parentElements != null && parentElements.size() > 0) {
				Elements elements = parentElements.select(cssQuery);
				
				if (elements != null && elements.size() > 0)
					return elements;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Elements getElements(Elements wrapperElement, String parentCSSQuery, String cssQuery) throws Exception {
		try {
			Elements parentElements = getElements(wrapperElement, parentCSSQuery);
			
			if (parentElements != null && parentElements.size() > 0) {
				Elements elements = parentElements.select(cssQuery);
				
				if (elements != null && elements.size() > 0)
					return elements;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getTexts(Elements elements) throws Exception {
		try {
			if (elements != null && elements.size() > 0) {
				String[] values = new String[elements.size()];
				
				for (int i = 0; i < elements.size(); i++) {
					Element element = elements.get(i);
					if (element != null)
						values[i] = element.text();
					else
						values[i] = "";
				}
				
				return values;
			}

			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getTexts(String parentCSSQuery, String cssQuery) throws Exception {
		try {
			Elements parentElements = getElements(parentCSSQuery);
			
			if (parentElements != null && parentElements.size() > 0) {
				Element parentElement = parentElements.get(0);
				if (parentElement != null) {
					Elements elements = parentElement.select(cssQuery);
					
					if (elements != null && elements.size() > 0) {
						String[] values = new String[elements.size()];
						
						for (int i = 0; i < elements.size(); i++) {
							Element element = elements.get(i);
							if (element != null)
								values[i] = element.text();
							else
								values[i] = "";
						}
						
						return values;
					}
				}
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getTexts(String wrapperCSSQuery, String parentCSSQuery, String cssQuery) throws Exception {
		try {
			Elements wrapperElements = getElements(wrapperCSSQuery);
			
			if (wrapperElements != null && wrapperElements.size() > 0) {
				Element wrapperElement = wrapperElements.get(0);
				
				if (wrapperElement != null) {
					Elements parentElements = wrapperElement.select(parentCSSQuery);
					
					if (parentElements != null && parentElements.size() > 0) {
						Element parentElement = parentElements.get(0);
						
						if (parentElement != null) {
							Elements elements = parentElement.select(cssQuery);
							
							if (elements != null && elements.size() > 0) {
								String[] values = new String[elements.size()];
								
								for (int i = 0; i < elements.size(); i++) {
									Element element = elements.get(i);
									if (element != null)
										values[i] = element.text();
									else
										values[i] = "";
								}
								
								return values;
							}
						}
					}
				}
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public Element getLastElement(String cssQuery) throws Exception {
		try {
			initializeDocument();
			Elements elements = document.select(cssQuery);
			
			if (elements != null && elements.size() > 0)
				return elements.last();
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}