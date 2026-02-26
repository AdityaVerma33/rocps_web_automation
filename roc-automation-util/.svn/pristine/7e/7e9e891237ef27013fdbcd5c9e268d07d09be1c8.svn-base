package com.subex.automation.helpers.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class ImageFileHelper extends AcceptanceTest {

	public void convertToJPG(String filename) throws Exception {
		try {
			BufferedImage bufferedImage = ImageIO.read(new File(filename));

			// create a blank, RGB, same width and height, and a white background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = newBufferedImage.createGraphics();
			g2.drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", new File(filename));
			g2.dispose();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public BufferedImage imageToBufferedImage(Image img) throws Exception {
		try {
			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, null, null);
			g2.dispose();
			return bi;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public BufferedImage cropImage(BufferedImage bi) throws Exception {
		try {
			int x = 0;
			int y = 65;
			int w = bi.getWidth();
			int h = bi.getHeight() - 65;
			
			bi = bi.getSubimage(x, y, w, h);
			return bi;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public BufferedImage loadImage(String filename, boolean cropTopPanel) throws Exception {
		try {
			BufferedImage bi = ImageIO.read(new File(filename));
			
			if (cropTopPanel)
				bi = cropImage(bi);
			
			return bi;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void saveImage(BufferedImage srcImage, String destFileName) throws Exception {
		FileOutputStream out = null;
		try {
			BufferedImage bi = imageToBufferedImage(srcImage);
			out = new FileOutputStream(destFileName);
			ImageIO.write(bi, "jpeg", out);
			out.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void combineImages(String firstFile, String secondFile, String finalFile) throws Exception {
		try {
        	BufferedImage image = ImageIO.read(new File(firstFile));
        	BufferedImage overlay = ImageIO.read(new File(secondFile));
        	File output = new File(finalFile);
        	
        	// create the new image, canvas size is the max. of both image sizes
        	int firstImageWidth = image.getWidth();
        	int w = firstImageWidth + overlay.getWidth() + 5;
        	int h = Math.max(image.getHeight(), overlay.getHeight());
        	BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        	// paint both images, preserving the alpha channels
        	Graphics g = combined.getGraphics();
        	g.drawImage(image, 0, 0, null);
        	g.drawImage(overlay, (firstImageWidth + 4), 0, null);
        	g.setColor(Color.BLACK);
        	g.draw3DRect(firstImageWidth, 0, (firstImageWidth+3), h, true);
        	
        	ImageIO.write(combined, "jpg", output);
        	g.dispose();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void combineImages(BufferedImage image1, BufferedImage image2, String finalFile) throws Exception {
		try {
        	File output = new File(finalFile);
        	
        	// create the new image, canvas size is the max. of both image sizes
        	int image1Width = image1.getWidth();
        	int image1Height = image1.getHeight();
        	int image2Width = image1.getWidth();
        	int image2Height = image2.getHeight();
        	
        	int w = image1Width + image2Width + 5;
        	int h = Math.max(image1Height, image2Height);
        	BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        	// paint both images, preserving the alpha channels
        	Graphics g = combined.getGraphics();
        	g.drawImage(image1, 0, 0, null);
        	g.drawImage(image2, (image1Width + 4), 0, null);
        	g.setColor(Color.BLACK);
        	g.draw3DRect(image1Width, 0, (image1Width+3), h, true);
        	
        	ImageIO.write(combined, "jpg", output);
        	g.dispose();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public int[] getPixels(BufferedImage img) throws Exception {
		try {
			int width = img.getWidth();
			int height = img.getHeight();
			int[] data = new int[width * height];
			int count = 0;
            
            for (int y = 0; y < height; y++) {
            	for (int x = 0; x < width; x++) {        			
            		data[count] = img.getRGB(x, y);
            		count++;
            	}
            }
            
            data = GenericHelper.resizeIntArray(data, count);
            return data;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public String convertToBase64(String fileNameWithPath) throws Exception {
		try {
			File file = new File(fileNameWithPath);
			String base64String = Base64.encodeBase64String(FileUtils.readFileToByteArray(file));
			
			return base64String;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void takeScreenShot(String fileNameWithPath) throws Exception {
		try {
			TakesScreenshot newScreen = (TakesScreenshot) driver;
		    File screenShot = newScreen.getScreenshotAs(OutputType.FILE);
		    BufferedImage img = ImageIO.read(screenShot);
		    saveImage(img, fileNameWithPath);
//		    FileUtils.copyFile(screenShot, new File(GenericHelper.getPath(fileNameWithPath)));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void takeScreenShot(WebElement element, String fileNameWithPath) throws Exception {
		try {
			Point p = element.getLocation();
	        int width = element.getSize().getWidth();
	        int height = element.getSize().getHeight();

	        TakesScreenshot newScreen = (TakesScreenshot) driver;
		    File screenShot = newScreen.getScreenshotAs(OutputType.FILE);
		    
		    BufferedImage img = ImageIO.read(screenShot);
	        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, height);
	        saveImage(dest, fileNameWithPath);
//		    FileUtils.copyFile(screenShot, new File(GenericHelper.getPath(fileNameWithPath)));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void resizeImage(String filename, int desiredWidth, int desiredHeight) throws Exception {
		try {
			BufferedImage bufferedImage = ImageIO.read(new File(filename));
			int actualWidth = bufferedImage.getWidth();
			int actualHeight = bufferedImage.getHeight();
			float widthPercentage = ((float) desiredWidth / (float) actualWidth) * 100;
			float heightPercentage = ((float) desiredHeight / (float) actualHeight) * 100;
			
			int scaledWidth = (int) ((bufferedImage.getWidth() * widthPercentage) / 100);
	        int scaledHeight = (int) ((bufferedImage.getHeight() * heightPercentage) / 100);
	        
			// create a blank, RGB, same width and height, and a white background
			BufferedImage newBufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = newBufferedImage.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(bufferedImage, 0, 0, scaledWidth, scaledHeight, null);

			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", new File(filename));
			g2.dispose();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
}