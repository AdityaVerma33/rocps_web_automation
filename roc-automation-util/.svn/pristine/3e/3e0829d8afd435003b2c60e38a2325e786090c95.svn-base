package com.subex.automation.helpers.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class ImageResizer extends AcceptanceTest {

	public void resizeImage(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws Exception {
		try {
			File inputFile = new File(inputImagePath);
		    BufferedImage inputImage = ImageIO.read(inputFile);
		    
			ResampleOp resizeOp = new ResampleOp(scaledWidth, scaledHeight);
			resizeOp.setFilter(ResampleFilters.getLanczos3Filter());
			BufferedImage scaledImage = resizeOp.filter(inputImage, null);
			
			String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
			ImageIO.write(scaledImage, formatName, new File(outputImagePath));
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	/*
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws Exception {
        try {
	       File inputFile = new File(inputImagePath);
	       BufferedImage inputImage = ImageIO.read(inputFile);
	        
	       BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
	 
	       // scales the input image to the output image
	       Graphics2D g2d = outputImage.createGraphics();
	       g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
	       g2d.dispose();
	 
	        // extracts extension of output file
	       String formatName =outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
	 
	       ImageIO.write(outputImage, formatName, new File(outputImagePath));
        }
        catch (Exception e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
        }
    }
 
    /*
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws Exception 
     */
    public void resize(String inputImagePath, String outputImagePath, double percent) throws Exception {
        try {
        	ImageFileHelper imageFileHelper = new ImageFileHelper();
        	imageFileHelper.convertToJPG(inputImagePath);
        	inputImagePath = GenericHelper.getPath(automationOS, inputImagePath);
        	outputImagePath = GenericHelper.getPath(automationOS, outputImagePath);
        	
	    	File inputFile = new File(inputImagePath);
	        BufferedImage inputImage = ImageIO.read(inputFile);
	        int width = inputImage.getWidth();
	        int height = inputImage.getHeight();
	        int scaledWidth = (int) (width * percent / 100);
	        int scaledHeight = (int) (height * percent / 100);
	        
	        resizeImage(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
	        imageFileHelper.convertToJPG(outputImagePath);
        }
        catch (Exception e) {
        	FailureHelper.setErrorMessage(e);
        	throw e;
        }
    }
}