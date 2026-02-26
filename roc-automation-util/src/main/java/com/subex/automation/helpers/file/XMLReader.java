package com.subex.automation.helpers.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class XMLReader extends AcceptanceTest {

	public static Document initializeDoc(String os, String fileNameWithPath) throws Exception {
		try {
			
			String filepath = GenericHelper.getPath(os, fileNameWithPath);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			return doc;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void replaceValue(String os, String fileNameWithPath, String tagName, String value) throws Exception {

	   try {
		Document doc = initializeDoc(os, fileNameWithPath);

		Node tag = doc.getElementsByTagName(tagName).item(0);
		tag.setTextContent(value);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileNameWithPath));
		transformer.transform(source, result);
	   }
	   catch (ParserConfigurationException pce) {
		   FailureHelper.setErrorMessage(pce);
			throw pce;
	   }
	   catch (IOException ioe) {
		   FailureHelper.setErrorMessage(ioe);
		   throw ioe;
	   }
	   catch (SAXException sae) {
		   FailureHelper.setErrorMessage(sae);
		   throw sae;
	   }
	   catch (Exception e) {
		   FailureHelper.setErrorMessage(e);
		   throw e;
	   }
	}
	
	public static void updateAttribute(String os, String fileNameWithPath, String tagName, String tagAttribute, String value) throws Exception {
	   try {
		   Document doc = initializeDoc(os, fileNameWithPath);

			Node staff = doc.getElementsByTagName(tagName).item(0);
			NamedNodeMap attr = staff.getAttributes();
			Node nodeAttr = attr.getNamedItem(tagAttribute);
			nodeAttr.setTextContent(value);
	
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileNameWithPath));
			transformer.transform(source, result);
	   }
	   catch (ParserConfigurationException pce) {
		   FailureHelper.setErrorMessage(pce);
		   throw pce;
	   }
	   catch (TransformerException tfe) {
		   FailureHelper.setErrorMessage(tfe);
		   throw tfe;
	   }
	   catch (IOException ioe) {
		   FailureHelper.setErrorMessage(ioe);
		   throw ioe;
	   }
	   catch (SAXException sae) {
		   FailureHelper.setErrorMessage(sae);
		   throw sae;
	   }
	   catch (Exception e) {
		   FailureHelper.setErrorMessage(e);
		   throw e;
	   }
	}
	
	public static void updateAllAttributes(String os, String fileNameWithPath, String parentTagName, String tagAttribute, String value) throws Exception {
	   try {
		   Document doc = initializeDoc(os, fileNameWithPath);
		   
		   Node parent = doc.getElementsByTagName(parentTagName).item(0);
		   NodeList childNodes = parent.getChildNodes();
		   int length = childNodes.getLength();
		   
		   if (length > 0) {
			   for (int i = 0; i < length; i++) {
				   Node node = childNodes.item(i);
				   NamedNodeMap attr = node.getAttributes();
				   
				   if (attr != null) {
					   Node nodeAttr = attr.getNamedItem(tagAttribute);
					   
					   if (nodeAttr != null)
						   nodeAttr.setTextContent(value);
				   }
			   }
		   }
		   
		   TransformerFactory transformerFactory = TransformerFactory.newInstance();
		   Transformer transformer = transformerFactory.newTransformer();
		   DOMSource source = new DOMSource(doc);
		   StreamResult result = new StreamResult(new File(fileNameWithPath));
		   transformer.transform(source, result);
	   }
	   catch (ParserConfigurationException pce) {
		   FailureHelper.setErrorMessage(pce);
		   throw pce;
	   }
	   catch (TransformerException tfe) {
		   FailureHelper.setErrorMessage(tfe);
		   throw tfe;
	   }
	   catch (IOException ioe) {
		   FailureHelper.setErrorMessage(ioe);
		   throw ioe;
	   }
	   catch (SAXException sae) {
		   FailureHelper.setErrorMessage(sae);
		   throw sae;
	   }
	   catch (Exception e) {
		   FailureHelper.setErrorMessage(e);
		   throw e;
	   }
	}
	
	public static void replaceLine(String fileNameWithPath, String[][] stringToReplace) throws Exception {
		BufferedReader br = null;
		try {
			
			String newline = System.getProperty("line.separator");
			StringBuffer sb = new StringBuffer();
			File xmlFile = new File(fileNameWithPath);
			br = new BufferedReader(new FileReader(xmlFile));
			String line = null;
			while((line = br.readLine())!= null)
			{
				for (int i = 0; i < stringToReplace.length; i++) {
				    if(line.contains(stringToReplace[i][0]))
				    {
				        line = line.replace(stringToReplace[i][0], stringToReplace[i][1]);
				    }
				}
			    sb.append(line).append(newline);                
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile));
			bw.write(sb.toString());
			bw.close();
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		   throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	public static int searchValue(String fileNameWithPath, String stringToSearch) throws Exception {
		BufferedReader br = null;
		try {
			File xmlFile = new File(fileNameWithPath);
			br = new BufferedReader(new FileReader(xmlFile));
			String line = null;
			int count = 0;
			
			while((line = br.readLine())!= null)
			{
				if(line.contains(stringToSearch))
				    count++;
			}
			
			return count;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		   throw e;
		}
		finally {
			if (br != null)
				br.close();
		}
	}
	
	public static String[][] getAttributes(String os, String fileNameWithPath, String tagName, String[] attributeName) throws Exception {
		try {
			Document doc = initializeDoc(os, fileNameWithPath);
			
			NodeList nodes = doc.getElementsByTagName(tagName);
			String[][] attributes = new String[nodes.getLength()][attributeName.length];
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node tag = nodes.item(i);
				
				for (int j = 0; j < attributeName.length; j++) {
					Node sAttribute = tag.getAttributes().getNamedItem(attributeName[j]);
					
					if (sAttribute != null) {
						attributes[i][j] = sAttribute.getNodeValue();
					}
				}
			}
			
			return attributes;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getAttributes(String os, String fileNameWithPath, String tagName, int parentNodeCount, String[] attributeName, String filterAttribute, String filterValue) throws Exception {
		try {
			Document doc = initializeDoc(os, fileNameWithPath);
			
			NodeList nodes = doc.getElementsByTagName(tagName);
			String[][] attributes = new String[nodes.getLength()][attributeName.length];
			int count = 0;
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node tag = nodes.item(i);
				boolean hasAttribute = false;
				
				if (tag != null) {
					Node parentNode = tag.getParentNode();
					for (int x = 0; x < parentNodeCount-1; x++)
						parentNode = parentNode.getParentNode();
					
					if (parentNode != null) {
						NamedNodeMap parentAttributes = parentNode.getAttributes();
						Node fAttribute = parentAttributes.getNamedItem(filterAttribute);
						
						if (fAttribute != null && fAttribute.getNodeValue().equals(filterValue)) {
							NamedNodeMap tagAttributes = tag.getAttributes();
							
							for (int k = 0; k < attributeName.length; k++) {
								Node sAttribute = tagAttributes.getNamedItem(attributeName[k]);
								
								if (sAttribute != null) {
									attributes[count][k] = sAttribute.getNodeValue();
									hasAttribute = true;
								}
							}
							
							if (hasAttribute)
								count++;
						}
					}
				}
			}
			
			attributes = StringHelper.resizeStringArray(attributes);
			return attributes;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] searchValue(String os, String fileNameWithPath, String tagName, String[] attributeNames, String stringToSearch) throws Exception {
		try {
			String[][] attributeValues = getAttributes(os, fileNameWithPath, tagName, attributeNames);
			
			int[] count = new int[attributeNames.length];
			
			for (int i = 0; i < attributeValues.length; i++) {
				
				for (int j = 0; j < attributeValues[i].length; j++) {
					if (attributeValues[i][j] != null && attributeValues[i][j].contains(stringToSearch)) {
						count[j] = count[j] + 1;
					}
				}
			}
			
			return count;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		   throw e;
		}
	}
	
	public static int[] searchValue(String os, String fileNameWithPath, String tagName, int parentNodeCount, String filterAttribute, String filterValue, String[] attributeNames, String stringToSearch) throws Exception {
		try {
			int length = attributeNames.length;
			String[][] attributeValues = getAttributes(os, fileNameWithPath, tagName, parentNodeCount, attributeNames, filterAttribute, filterValue);
			int[] iCount = new int[length];
			
			for (int i = 0; i < attributeValues.length; i++) {
					for (int j = 0; j < attributeValues[i].length; j++) {
						if (attributeValues[i][j] != null && attributeValues[i][j].contains(stringToSearch)) {
							iCount[j] = iCount[j] + 1;
					}
				}
			}
			
			return iCount;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
		   throw e;
		}
	}
	
	public static String getTagValue(String fileNameWithPath, String filterAttributeValue) throws Exception {
		BufferedReader br = null;
		try {
			String tagValue = null;
			File xmlFile = new File(fileNameWithPath);
			br = new BufferedReader(new FileReader(xmlFile));
			String line = null;
			
			while((line = br.readLine())!= null) {
				if (line.contains(filterAttributeValue)) {
					int closeIndex = line.indexOf(">") + 1;
					int lastOpenIndex = line.lastIndexOf("<") - 1;
					tagValue = line.substring(closeIndex, lastOpenIndex);
					break;
				}
			}
			
			return tagValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (br != null)
				br.close();
		}
	}
}