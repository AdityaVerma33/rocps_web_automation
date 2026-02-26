package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.CanvasElementHelper;
import com.subex.automation.helpers.componentHelpers.DNAElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CanvasHelper extends ComponentHelper {
	
	private final static int xOffset = 60;
	private final static int yOffset = 0;
	
	/**
	 * This method is used to check if the Canvas is present in GUI.
	 * If Canvas is not present, test case will fail.
	 * @param canvasId - id of the canvas.
	 * @throws Exception
	 */
	public static boolean isPresent( String canvasId ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = CanvasElementHelper.getElement( canvasId );
			
			if ( element == null )
				return false;
			else
				return true;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Canvas is present in GUI.
	 * If Canvas is not present, test case will fail.
	 * @param canvasWrapper - Div or Table id within which the Canvas is present.
	 * @param canvasId - id of the canvas.
	 * @throws Exception
	 */
	public static boolean isPresent( String canvasWrapper, String canvasId ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = CanvasElementHelper.getElement( canvasWrapper, canvasId );
			
			if ( element == null )
				return false;
			else
				return true;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to click a Node in the Canvas
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void click( String canvasId, String nodeText ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = null;
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				int index = Integer.parseInt( nodeText );
				element = CanvasElementHelper.getNodeElement( canvasId, index );
			}
			else {
				element = CanvasElementHelper.getNodeElement( canvasId, nodeText );
			}
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.click( element );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to click a Node in the Canvas.
	 * This method can be used in three ways with below combination of arguments
	 * 1. canvas wrapper, canvas id, node text e.g., (popupWindow, vizBusnesHrchy, Postpaid) - Postpaid that appears first in the html tag will be clicked.
	 * 2. canvas wrapper, canvas id, node position e.g., (popupWindow, vizBusnesHrchy, 4) - 4th node in the html tag will be clicked.
	 * 3. canvas id, parent node text, node text to be clicked e.g., (vizBusnesHrchy, LTE, Postpaid) - Postpaid below LTE will be clicked
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text or node position
	 * @throws Exception
	 */
	public static void click( String canvasWrapper, String canvasId, String nodeText ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = null;
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				int index = Integer.parseInt( nodeText );
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, index );
			}
			else {
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText );
			}
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.click(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a Node in the Canvas based on its position or based on parent node text.
	 * This method can be used in three ways with below combination of arguments
	 * 1. canvas wrapper, canvas id, node text, node position e.g., (popupWindow, vizBusnesHrchy, Postpaid, 4) - 4th Postpaid will be clicked.
	 * 2. canvas wrapper, canvas id, parent node text, node text to be clicked e.g., (popupWindow, vizBusnesHrchy, LTE, Postpaid) - Postpaid below LTE will be clicked
	 * 3. canvas id, first level parent node text, second level parent node text, node text to be clicked e.g., (vizBusnesHrchy, Wireless, LTE, Postpaid) - Postpaid below LTE will be clicked
	 * @param canvasWrapperOrId - Div or Table id within which the canvas is present. Or canvas Id.
	 * @param canvasId - id of the Canvas. Or first level Parent Node Text e.g., Wireless
	 * @param nodeText - Node text. Or second level Parent Node text, e.g., LTE
	 * @param position - Position of the node. e.g., 1, 2, etc or child node text e.g., Postpaid
	 * @throws Exception
	 */
	public static void click( String canvasWrapper, String canvasId, String nodeText, String position ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = null;
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				int index = Integer.parseInt( position );
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText, index );
			}
			else {
				element = DNAElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText, position );
			}
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.click( element );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to right click a Node in the Canvas
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void rightClick( String canvasId, String nodeText ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = CanvasElementHelper.getNodeElement( canvasId, nodeText );
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.rightClick( element );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to right click a Node in the Canvas
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void rightClick( String canvasWrapper, String canvasId, String nodeText ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = null;
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				int index = Integer.parseInt( nodeText );
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, index );
			}
			else {
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText );
			}
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.rightClick( element );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to right click on a Node in the Canvas based on its position or based on parent node text.
	 * This method can be used in three ways with below combination of arguments
	 * 1. canvas wrapper, canvas id, node text, node position e.g., (popupWindow, vizBusnesHrchy, Postpaid, 4) - 4th Postpaid will be right clicked.
	 * 2. canvas wrapper, canvas id, parent node text, node text to be clicked e.g., (popupWindow, vizBusnesHrchy, LTE, Postpaid) - Postpaid below LTE will be right clicked
	 * 3. canvas id, first level parent node text, second level parent node text, node text to be clicked e.g., (vizBusnesHrchy, Wireless, LTE, Postpaid) - Postpaid below LTE will be right clicked
	 * @param canvasWrapperOrId - Div or Table id within which the canvas is present. Or canvas Id.
	 * @param canvasId - id of the Canvas. Or first level Parent Node Text e.g., Wireless
	 * @param nodeText - Node text. Or second level Parent Node text, e.g., LTE
	 * @param position - Position of the node. e.g., 1, 2, etc or child node text e.g., Postpaid
	 * @throws Exception
	 */
	public static void rightClick( String canvasWrapper, String canvasId, String nodeText, String position ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			WebElement element = null;
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				int index = Integer.parseInt( position );
				element = CanvasElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText, index );
			}
			else {
				element = DNAElementHelper.getNodeElement( canvasWrapper, canvasId, nodeText, position );
			}
			
			if ( element == null )
				FailureHelper.failTest( "Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'" );
			else
				MouseHelper.rightClick( element );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to resize a Node in the Canvas
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void resizeNode( String canvasId, String nodeText ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			
			CanvasElementHelper.resizeNode( canvasId, nodeText, xOffset, yOffset );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to resize a Node in the Canvas
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void resizeNode( String canvasWrapper, String canvasId, String nodeText ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			
			if ( ValidationHelper.isInteger( nodeText ) ) {
				CanvasElementHelper.resizeNode( canvasWrapper, canvasId, Integer.parseInt( nodeText ), yOffset );
			}
			else {
				String canvasLocator = CanvasElementHelper.getLocator( canvasWrapper, canvasId );
				CanvasElementHelper.resizeNode( canvasLocator, nodeText, xOffset, yOffset );
			}
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to resize a Node in the Canvas
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @param xOffset - X resize Width
	 * @param yOffset - Y resize Height
	 * @throws Exception
	 */
	public static void resizeNode( String canvasId, String nodeText, String xOffset, String yOffset ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			CanvasElementHelper.resizeNode( canvasId, nodeText, Integer.parseInt( xOffset ), Integer.parseInt( yOffset ) );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to resize a Node in the Canvas
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @param xOffset - X resize Width
	 * @param yOffset - Y resize Height
	 * @throws Exception
	 */
	public static void resizeNode( String canvasWrapper, String canvasId, String nodeText, String xOffset, String yOffset ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			String canvasLocator = CanvasElementHelper.getLocator( canvasWrapper, canvasId );
			CanvasElementHelper.resizeNode( canvasLocator, nodeText, Integer.parseInt( xOffset ), Integer.parseInt( yOffset ) );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to drag a Node in the Canvas to the right by 30 pixels.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void drageAndDrop( String canvasId, String nodeText ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			CanvasElementHelper.dragAndDrop( canvasId, nodeText, "Right" );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to drag a Node in the Canvas to the right by 30 pixels.
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @throws Exception
	 */
	public static void drageAndDrop( String canvasWrapper, String canvasId, String nodeText ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			
			if ( CanvasElementHelper.dragToPositions( nodeText ) ) {
				CanvasElementHelper.dragAndDrop( canvasWrapper, canvasId, nodeText );
			}
			else {
				String canvasLocator = CanvasElementHelper.getLocator( canvasWrapper, canvasId );
				CanvasElementHelper.dragAndDrop( canvasLocator, canvasId, nodeText );
			}
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public static void drageAndDrop( String canvasWrapper, String canvasId, String nodeText, String dragTo ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			String canvasLocator = CanvasElementHelper.getLocator( canvasWrapper, canvasId );
			CanvasElementHelper.dragAndDrop( canvasLocator, nodeText, dragTo );
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to check if a Node with specified text is present in the Canvas
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @return 
	 * @throws Exception
	 */
	public static boolean isTextPresent( String canvasId, String nodeText ) throws Exception {
		try {
			canvasId = GenericHelper.getORProperty(canvasId);
			String locator = CanvasElementHelper.getNodeLocator( canvasId, nodeText );
			
			if ( locator == null )
				return false;
			else
				return true;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	/**
	 * This method is used to check if a Node with specified text is present in the Canvas
	 * @param canvasWrapper - Div or Table id within which the canvas is present.
	 * @param canvasId - id of the Canvas.
	 * @param nodeText - Node text
	 * @return 
	 * @throws Exception
	 */
	public static boolean isTextPresent( String canvasWrapper, String canvasId, String nodeText ) throws Exception {
		try {
			canvasWrapper = GenericHelper.getORProperty(canvasWrapper);
			canvasId = GenericHelper.getORProperty(canvasId);
			String locator = CanvasElementHelper.getNodeLocator( canvasWrapper, canvasId, nodeText );
			
			if ( locator == null )
				return false;
			else
				return true;
		} catch ( Exception e ) {
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}