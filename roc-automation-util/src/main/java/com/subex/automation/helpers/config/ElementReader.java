package com.subex.automation.helpers.config;

import java.util.Map;
import java.util.TreeMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelWriter;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.JSoupHelper;

public class ElementReader extends AcceptanceTest {
	
	static String[] tag = {"button", "textarea", "a", "img", "input", "div", "table"};
	static String[][] component = {{"button", "Button"}, {"textarea", "TextArea"}, {"a", "Link"}, {"img", "Image"}};
	
	static String[][] inputComponent = {{"text", "TextBox"}, {"password", "TextBox"}, {"checkbox", "CheckBox"}, {"radio", "Radio"}};
	static String[][] classComponent = {{"tabLabel", "Tab"}, {"field-label", "Label"}, {"grid", "Grid"}, {"toolbar-button", "Button"}, {"tree", "Tree"}};
	static Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	
	public static void getAllElements(String sheetName) throws Exception {
		try {
			int length = 0;

			Elements screen = getScreen();
			Elements we;
			
			data.put(length, new Object[] {"Component", "Tag Name", "Label (If any)", "Tag ID", "Tag Text", "Tag Class"});
			length++;
			
			for (int i = 0; i < tag.length; i++) {
				String comp = getComponent(tag[i]);
				we = screen.select(tag[i]);
				
				for (int j = 0; j < we.size(); j++) {
					boolean getDetails = true;
					Element ele = we.get(j);
					
					if ((ele != null)) {
						String text = ele.ownText().trim().replaceAll("\u00A0", "");
						String label = "";
						
						if (tag[i].equalsIgnoreCase("input") || tag[i].equalsIgnoreCase("img") || tag[i].equalsIgnoreCase("div") || tag[i].equalsIgnoreCase("table")) {
							Object[] obj = getDetails(ele);
							getDetails = (boolean) obj[0];
							comp = (String) obj[1];
						}
						
						if (getDetails) {
							Element parentTD = ele.parent().tagName("td");
							Element parentTR = parentTD.parent().tagName("tr");
							Elements labelTRs = parentTR.siblingElements().tagName("tr");
							
							if (ValidationHelper.isNotEmpty(labelTRs)) {
								Element labelTR = labelTRs.get(0);
								Elements labelDivs = labelTR.select("div");
								if (ValidationHelper.isNotEmpty(labelDivs)) {
									Element labelDiv = labelDivs.get(labelDivs.size()-1);
									label = labelDiv.text();
								}
							}
							
							data.put(length, new Object[] {comp, ele.tagName(), label, ele.id(), text, ele.className()});
					        length++;
						}
					}
				}
			}
			
			ExcelWriter.writeToExcel("Application_Elements.xlsx", sheetName, data);
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static Elements getScreen() throws Exception {
		try {
			JSoupHelper jSoup = new JSoupHelper();
			Document document = jSoup.initializeDocument();
			Elements searchScreen = document.select("div#searchPanelContainer");
			Elements detailScreen = document.select("div#bottomScrollablePanel");
			Elements homeScreen = document.select("div#bottomPanel");
			Elements screen;
			
			if (ValidationHelper.isNotEmpty(searchScreen)) {
				screen = searchScreen;
			}
			else if (ValidationHelper.isNotEmpty(detailScreen)) {
				screen = detailScreen;
			}
			else
				screen = homeScreen;
			
			return screen;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static Object[] getDetails(Element ele) throws Exception {
		try {
			boolean getDetails = true;
			String comp = "";
			
			String tagName = ele.tagName();
			String id = ele.id();
			String text = ele.ownText().trim().replaceAll("\u00A0", "");
			String className = ele.className();
			
			if (tagName.equalsIgnoreCase("input")) {
				if (ele.hasAttr("tempid") || ele.attr("role").equalsIgnoreCase("presentation"))
					getDetails = false;
				else
					comp = getInputComponent(ele);
			}
			
			else if (tagName.equalsIgnoreCase("img")) {
				if (ValidationHelper.isEmpty(id)) {
					getDetails = false;
				}
				else {
					if (id.contains("column_field_image"))
						comp = "Grid ComboBox";
				}
			}
			
			else if (tagName.equalsIgnoreCase("div") || tagName.equalsIgnoreCase("table")) {
				if (ValidationHelper.isEmpty(id) && ValidationHelper.isEmpty(text)) {
					getDetails = false;
				}
				else {
					comp = getDivComponent(id, className);
				}
			}
			
			Object[] obj = {getDetails, comp};
			return obj;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getComponent(String tagName) throws Exception {
		try {
			String comp = "";
			
			for (int i = 0; i < component.length; i++) {
				if (component[i][0].equalsIgnoreCase(tagName)) {
					comp = component[i][1];
					break;
				}
			}
			
			return comp;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getInputComponent(Element ele) throws Exception {
		try {
			String comp = "";
			String type = ele.attr("type");
			
			for (int i = 0; i < inputComponent.length; i++) {
				if (inputComponent[i][0].equalsIgnoreCase(type)) {
					comp = inputComponent[i][1];
					break;
				}
			}
			
			return comp;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getDivComponent(String id, String className) throws Exception {
		try {
			String comp = "";
			
			if (id.endsWith("chzn"))
				comp = "ComboBox";
			else {
				comp = getClassComponent(className);
			}
			
			return comp;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getClassComponent(String className) throws Exception {
		try {
			String comp = "";
			
			for (int i = 0; i < classComponent.length; i++) {
				if (className.contains(classComponent[i][0])) {
					comp = classComponent[i][1];
					break;
				}
			}
			
			return comp;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}
