package com.subex.automation.helpers.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.GrayFilter;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;

public class ImageComparer extends AcceptanceTest {

//	private static String path = null;
	private static String imageFileName2 = null;
	private static BufferedImage image1 = null;
	private static BufferedImage image2 = null;
	private static boolean markDifference = false;
	private static BufferedImage highlightedImage = null;
	private static int height = 0;
	private static int width = 0;
	private static int comparex = 10;
	private static int comparey = 10;
	private static boolean cropTopPanel = false;
	private static ImageFileHelper imageFileHelper = null;
	
	public ImageComparer(String firstFile, String secondFile, boolean markDiff, boolean cropPanel) throws Exception {
		try {
			String imageFileName1 = new File(firstFile).getAbsolutePath();
			imageFileName2 = new File(secondFile).getAbsolutePath();
//			path = new File(imageFileName2).getParent();
			markDifference = markDiff;
			ImageComparer.cropTopPanel = cropPanel;
			
			imageFileHelper = new ImageFileHelper();
			image1 = imageFileHelper.loadImage(imageFileName1, cropTopPanel);
			image2 = imageFileHelper.loadImage(imageFileName2, cropTopPanel);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public boolean pixelBasedCompare() throws Exception {
		try {
			int width1 = image1.getWidth();
            int height1 = image1.getHeight();
            width = image2.getWidth();
            height = image2.getHeight();
            
            highlightedImage = imageFileHelper.imageToBufferedImage(image2);
            boolean sameImage = true;
            
            if (imageFileName2.contains("Error_report")) {
            	highlightFullImage();
            	sameImage = false;
            }
            else {
	            if (width1 == width && height1 == height) {
	            	if (markDifference) {
						sameImage = compare();
						imageFileHelper.saveImage(highlightedImage, imageFileName2);
					}
					else {
						int[] file1Pixels = imageFileHelper.getPixels(image1);
						int[] file2Pixels = imageFileHelper.getPixels(image2);
						sameImage = checkDifference(file1Pixels, file2Pixels);
					}
	            }
		        else {
		        	highlightFullImage();
	            	sameImage = false;
				}
            }
            
            imageFileHelper.combineImages(image1, highlightedImage, imageFileName2);
            highlightedImage.flush();
            return sameImage;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private void highlightFullImage() throws Exception {
		try {
			if (markDifference) {
            	drawRectangle(1, 1, height, width, 5);
            	ImageFileHelper imageFile = new ImageFileHelper();
            	imageFile.saveImage(highlightedImage, imageFileName2);
			}
			
			ReportHelper.addWarning("Images are of different size");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	protected void drawRectangle(int x, int y, int height, int width, int rectWidth) throws Exception {
		try {
			Graphics2D gc = highlightedImage.createGraphics();
			gc.setStroke(new BasicStroke(rectWidth));
			gc.setColor(Color.RED);
			gc.drawRect(x, y, (width-rectWidth), (height-rectWidth));
			gc.dispose();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private boolean compare() throws Exception {
		try {
			boolean sameImage = true;
			int xBlocks = width / comparex;
			int yBlocks = height / comparey;
			int[] xCoordinate = new int[comparex * comparey];
			int[] yCoordinate = new int[comparex * comparey];
			int count = 0;
			
			BufferedImage bImage1 = imageFileHelper.imageToBufferedImage(GrayFilter.createDisabledImage(image1));
			BufferedImage bImage2 = imageFileHelper.imageToBufferedImage(GrayFilter.createDisabledImage(image2));
			
			for (int y = 0; y < comparey; y++) {
				int yCoordinates = y * yBlocks;
				
				for (int x = 0; x < comparex; x++) {
					int xCoordinates = x * xBlocks;
					int[] file1Pixels = imageFileHelper.getPixels(bImage1.getSubimage(xCoordinates, yCoordinates, xBlocks-1, yBlocks-1));
					int[] file2Pixels = imageFileHelper.getPixels(bImage2.getSubimage(xCoordinates, yCoordinates, xBlocks-1, yBlocks-1));
					boolean isPixelSame = checkDifference(file1Pixels, file2Pixels);
					
					if (!isPixelSame) {
						xCoordinate[count] = xCoordinates;
						yCoordinate[count] = yCoordinates;
						count++;
					}
				}
			}
			
			xCoordinate = GenericHelper.resizeIntArray(xCoordinate, count);
			yCoordinate = GenericHelper.resizeIntArray(yCoordinate, count);
			
			int[][] coordinates = getCoordinates(xCoordinate, yCoordinate, (xBlocks-1), (yBlocks-1));
			int[] xCoord = coordinates[0];
			int[] yCoord = coordinates[1];
			int[] yHeight = coordinates[2];
			int[] xWidth = coordinates[3];
					
			for (int i = 0; i < xCoord.length; i++) {
				drawRectangle(xCoord[i], yCoord[i], yHeight[i], xWidth[i], 3);
				sameImage = false;
			}
			
			if (xCoord.length > 0)
				sameImage = false;
			
			return sameImage;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private int[][] getCoordinates(int[] xCoordinate, int[] yCoordinate, int xBlocks, int yBlocks) throws Exception {
		try {
			int[][] combinedCoordinates = combineXCoordinates(xCoordinate, yCoordinate, xBlocks, yBlocks);
			int[] xCoord = combinedCoordinates[0];
			int[] yCoord = combinedCoordinates[1];
			int[] yHeight = combinedCoordinates[2];
			int[] xWidth = combinedCoordinates[3];
			
			combinedCoordinates = combineYCoordinates(xCoord, yCoord, xWidth, yHeight, xBlocks, yBlocks);
			return combinedCoordinates;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private int[][] combineXCoordinates(int[] xCoordinate, int[] yCoordinate, int xBlocks, int yBlocks) throws Exception {
		try {
			int length = xCoordinate.length;
			int[] xCoord = new int[length];
			int[] yCoord = new int[length];
			int[] yHeight = new int[length];
			int[] xWidth = new int[length];
			int count = 0;
			int startCoordinate = -1;
			int xDimension = -1;
			
			for (int i = 0; i < length; i++) {
				if (startCoordinate == -1) {
					startCoordinate = i;
					xCoord[count] = xCoordinate[i];
					yCoord[count] = yCoordinate[i];
					xWidth[count] = xBlocks;
					yHeight[count] = yBlocks;
					xDimension = xCoordinate[i] + xBlocks + 1;
				}

				if (length > (i+1) && xCoordinate[i+1] == xDimension && yCoordinate[i+1] == yCoord[count]) {
					xWidth[count] = xWidth[count] + xBlocks;
					xDimension = xCoordinate[i+1] + xBlocks + 1;
					if (i == (length-1))
						count++;
				}
				else {
					count++;
					startCoordinate = -1;
				}
			}
			
			xCoord = GenericHelper.resizeIntArray(xCoord, count);
			yCoord = GenericHelper.resizeIntArray(yCoord, count);
			yHeight = GenericHelper.resizeIntArray(yHeight, count);
			xWidth = GenericHelper.resizeIntArray(xWidth, count);
			return new int[][] {xCoord, yCoord, yHeight, xWidth};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private int[][] combineYCoordinates(int[] xCoordinate, int[] yCoordinate, int[] xWidth, int[] yHeight, int xBlocks, int yBlocks) throws Exception {
		try {
			int xLength = xCoordinate.length;
			int[] xCoord = new int[xLength];
			int[] yCoord = new int[xLength];
			int[] height = new int[xLength];
			int[] width = new int[xLength];
			int startCoordinate = -1;
			int yDimension = -1;
			int yLength = yCoordinate.length;
			int count = 0;
			
			for (int i = 0; i < xLength; i++) {
				if (startCoordinate == -1) {
					startCoordinate = i;
					xCoord[count] = xCoordinate[i];
					yCoord[count] = yCoordinate[i];
					width[count] = xWidth[i];
					height[count] = yHeight[i];
					yDimension = yCoordinate[i] + yBlocks + 1;
				}
				
				if (yLength > (i+1) && yCoordinate[i+1] == yDimension && xCoordinate[i+1] == xCoord[count]) {
					height[count] = height[count] + yBlocks;
					yDimension = yCoordinate[i+1] + yBlocks + 1;
					if (i == (xLength-1))
						count++;
				}
				else {
					count++;
					startCoordinate = -1;
				}
			}
			
			xCoord = GenericHelper.resizeIntArray(xCoord, count);
			yCoord = GenericHelper.resizeIntArray(yCoord, count);
			height = GenericHelper.resizeIntArray(height, count);
			width = GenericHelper.resizeIntArray(width, count);
			return new int[][] {xCoord, yCoord, height, width};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	private boolean checkDifference(int[] file1Pixels, int[] file2Pixels) throws Exception {
		try {
			for (int i = 0; i < file1Pixels.length; i++) {
				int file1Red = (file1Pixels[i] >> 16) & 0xFF;
				int file1Green = (file1Pixels[i] >> 8) & 0xFF;
				int file1Blue = (file1Pixels[i] >> 0) & 0xFF;

				int file2Red = (file2Pixels[i] >> 16) & 0xFF;
				int file2Green = (file2Pixels[i] >> 8) & 0xFF;
				int file2Blue = (file2Pixels[i] >> 0) & 0xFF;
				
				int diff = Math.abs(file2Red - file1Red) + Math.abs(file2Green - file1Green) + Math.abs(file2Blue - file1Blue);
				if (diff > 10)
					return false;
			}
			
			return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
}