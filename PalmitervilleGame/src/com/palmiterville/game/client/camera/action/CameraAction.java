package com.palmiterville.game.client.camera.action;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.palmiterville.game.client.camera.exception.CameraException;

/**
 * Action class used for rotating a panel.
 * 
 * @author Dave
 *
 */
public class CameraAction {
	
	/**
	 * Padding to allow camera to extend past max left, right, up, and down for the pan function.
	 */
	private static final int PAN_PADDING = 0;

	/**
	 * The max amount of rotation degrees for the X rotation.
	 */
	private static final int MAX_X_ROTATION_DEGREES = 70;
	
	/**
	 * The min amount of rotation degrees for the X rotation.
	 */
	private static final int MIN_X_ROTATION_DEGREES = 0;

	/**
	 * The Default sensitivity for the pan function.
	 */
	private static final int DEFAULT_PAN_SENSITIVITY = 300;
	
	/**
	 * The Default sensitivity for rotation along the X axis.
	 */
	private static final int X_ROTATE_SENSITIVITY = 5;
	
	/**
	 * The Default sensitivity for the rotation along the Z axis.
	 */
	private static final int Z_ROTATE_SENSITIVITY = 10;
	
	/**
	 * The default perspective value.
	 */
	private static final int DEFAULT_PERSPECTIVE = 1200;

	/**
	 * The panel to perform camera actions on.
	 */
	private Panel panel;

	/**
	 * The current amount of perspective of the {@link #panel panel}.
	 */
	private int perspective;
	
	/**
	 * The current xCoordinate of the panel. This relates to the "left" style of the panel.
	 */
	private int left;
	
	/**
	 * The current yCoordinate of the panel. This relates to the "top" style of the panel.
	 */
	private int top;
	
	/**
	 * The current rotation amount of the {@link #panel panel};
	 */
	private int xRotation;
	
	/**
	 * The current rotation amount in degrees of the panel.
	 */
	private int zRotation;
	
	/**
	 * The max position left the camera can be panned.
	 */
	int maxLeft;
	
	/**
	 * The max position up the camera can be panned.
	 */
	int maxTop;
	
	/**
	 * The max position right the camera can be panned.
	 */
	int maxRight;
	
	/**
	 * The max position down the camera can be panned.
	 */
	int maxBottom;
	
	/**
	 * The origin of transform along the X axis.
	 */
	int xOrigin;
	
	/**
	 * The origin of transform along the Y axis.
	 */
	int yOrigin;
	
	/**
	 * The sensitivity setting for the pan function.
	 */
	private int sensitivityPan;
	
	/**
	 * The sensitivity setting for the XRotate function.
	 */
	private int sensitivityXRotate;
	
	/**
	 * The sensitivity setting for the YRotate function.
	 */
	private int sensitivityZRotate;
	
	/**
	 * Construct using a panel to perform pan functions on.
	 * 
	 * @param panel - the panel to perform camera actions on.
	 * @throws CameraException
	 */
	public CameraAction(Panel panel) throws CameraException {
		//initialize variables
		this.panel = panel;
		this.left = 0;
		this.top = 0;
		maxLeft = 0 - PAN_PADDING;
		maxRight = 0 - PAN_PADDING;
		autoSetMaxRight();
		autoSetMaxBottom();
		this.xRotation = 0;
		this.zRotation = 0;
		this.perspective = panel.getOffsetWidth();
		
		//Set sensitivities
		setSensitivityPan(DEFAULT_PAN_SENSITIVITY);
		setSensitivityXRotate(X_ROTATE_SENSITIVITY);
		setSensitivityZRotate(Z_ROTATE_SENSITIVITY);

		//Set panel styles
		this.setTop(top);
		this.setLeft(left);
		this.setTransformOrigin(panel.getOffsetWidth() / 2, panel.getOffsetHeight() / 2);
//		this.setTransformOrigin(0,  0);
		this.setTransformProperty(perspective, xRotation, zRotation);
		
		addResizeHandler();
	}
	
	/**
	 * Rotates the camera clockwise along the Z axis based on the current Z sensitivity.
	 */
	public void rotateClockwise() {
		setZRotation(zRotation + sensitivityZRotate);
	}
	
	/**
	 * Rotates the camera clockwise along the Z axis based on the specified amount.
	 * @param amount - the amount to rotate the camera by.
	 */
	public void rotateClockwise(int amount) {
		setZRotation(zRotation + amount);
	}
	
	/**
	 * Rotates the camera counterClockwise along the Z axis based on the current Z sensitivity.
	 */
	public void rotateCounterClockwise() {
		setZRotation(zRotation - sensitivityZRotate);
	}
	
	/**
	 * Rotates the camera counterClockwise along the Z axis based on the specified amount.
	 * @param amount - the amount to rotate the camera by.
	 */
	public void rotateCounterClockwise(int amount) {
		setZRotation(zRotation - amount);
	}
	
	public void setXRotation(int xRotationDegs) {
		this.setTransformProperty(perspective, xRotationDegs, zRotation);
	}
	
	/**
	 * Roates the camera tilt along the Z axis
	 * @param zRotationDegs
	 */
	private void setZRotation(int zRotationDegs) {
		this.setTransformProperty(perspective, xRotation, zRotationDegs);
	}
	
	/**
	 * Sets the perspective for the {@link #panel panel} and rotation along the X axis.
	 * 
	 * @param perspectiveValue - the perspective value to set.
	 * @param xRotationDegs - the amount of degrees in X rotation to set.
	 */
	private void setPerspective(int perspectiveValue, int xRotationDegs) {
		this.setTransformProperty(perspectiveValue, xRotationDegs, zRotation);
	}
	
	/**
	 * Sets the transform rotation and perspective properties for the camera.
	 * 
	 * @param perspectiveValue
	 * @param xRotationDegs
	 * @param zRotationDegs
	 */
	public void setTransformProperty(int perspectiveValue, int xRotationDegs, int zRotationDegs) {
		if (xRotationDegs > MAX_X_ROTATION_DEGREES) {
			xRotationDegs = MAX_X_ROTATION_DEGREES;
		}
		if (xRotationDegs < MIN_X_ROTATION_DEGREES) {
			xRotationDegs = MIN_X_ROTATION_DEGREES;
		}
		int yOrigin;
		
//		xOrigin = getXOrigin();
//		yOrigin = getYOrigin();
//		this.setTransformOrigin(xOrigin, yOrigin);
		
		panel.getElement().getStyle().setProperty("transform", 
				"perspective(" + perspectiveValue + "px) "
						+ "rotateX(" + xRotationDegs +"deg) "
						+ "rotateZ(" + zRotationDegs + "deg)");
		this.perspective = perspectiveValue;
		this.xRotation = xRotationDegs;
		this.zRotation = zRotationDegs;
		autoSetMaxRight();
		autoSetMaxBottom();
		autoSetMaxLeft();
		autoSetMaxRight();
//		GWT.log("xOrigin: " + xOrigin);
//		GWT.log("yOrigin: " + yOrigin);
		GWT.log("xRotation: " + xRotation);
		GWT.log("yRotation: " + zRotation);
	}
	
	/**
	 * 
	 * @param xOrigin - the x coordinate to set the transform origin.
	 * @param yOrigin the y coordinate to set the transform origin.
	 */
	public void setTransformOrigin(int xOrigin, int yOrigin) {
		if (xOrigin > panel.getOffsetWidth()) {
			xOrigin = panel.getOffsetWidth();
		} 
		if (xOrigin < 0) {
			xOrigin = 0;
		}
		if (yOrigin > panel.getOffsetHeight()) {
			yOrigin = panel.getOffsetHeight();
		}
		if (yOrigin < 0) {
			yOrigin = 0;
		}
		
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		panel.getElement().getStyle().setProperty("transformOrigin", xOrigin + "px " + yOrigin + "px" );
	}
	
	/**
	 * 
	 * @return - return the xOrigin the transformation should be based from.
	 */
	private int getXOrigin() {
		return  left + (Window.getClientWidth() / 2);
	}
	
	/**
	 * 
	 * @return the yOrigin the transformation should be based from.
	 */
	private int getYOrigin() {
		return top + (Window.getClientHeight() / 2);
	}
	
	/**
	 * Tilts the upper portion of the {@link #panel panel} towards the background by 
	 * {@link #sensitivity sensitivity} amount.
	 */
	public void tiltBack() {
		setPerspective(perspective, xRotation + sensitivityXRotate);
	}
	
	/**
	 * Tilt the upper portion of the {@link #panel panel} towards the background by the specified amount.
	 * @param amount - amount of degrees to tilt
	 */
	public void tiltBack(int amount) {
		setPerspective(perspective, xRotation + amount);
	}
	
	/**
	 * Tilts the bottom portion of the {@link #panel panel} towards the background.
	 */
	public void tiltForward() {
		setPerspective(perspective, xRotation - sensitivityXRotate);
	}
	
	/**
	 * Tilt the bottom portion of the {@link #panel panel} towards the background by the specified amount.
	 * @param amount
	 */
	public void tiltForward(int amount) {
		setPerspective(perspective, xRotation + amount);
	}
	
	/**
	 * Moves the {@link #controller panel} X coordinates left by the sensitivity amount.
	 */
	public void panLeft() {
		int panAmount = left + sensitivityPan;
		if (isMaxLeft(panAmount)) {
			panAmount = maxLeft;
		}
		setPanXCoordinates(panAmount);
	}
	
	/**
	 * Moves the {@link #controller panel} X coordinates left by the amount specified.
	 * 
	 * @param amount the amount to move the {@link #controller panel} X coordinates left by.
	 */
	public void panLeft(int amount) {
		int panAmount = left + amount;
		if (isMaxLeft(panAmount)) {
			panAmount = maxLeft;
		}
		setPanXCoordinates(panAmount);

	}
	
	/**
	 * Moves the {@link #controller panel} X coordinates right by the sensitivity amount. If 
	 * the camera is at it's right most position, the method will not be processed.
	 */
	public void panRight() {
		left -= sensitivityPan;
		if (isMaxRight()) {
			left = maxRight;
		}
		setPanXCoordinates(left);

	}
	
	/**
	 * Moves the {@link #controller panel} X coordinates right by the amount specified.
	 * 
	 * @param amount the amount to move the {@link #controller panel} X coordinates right by.
	 */
	public void panRight(int amount) {
		left -= amount;
		if (isMaxRight()) {
			left = maxRight;
		}
		setPanXCoordinates(left);

	}
	
	/**
	 * Moves the {@link #controller panel} Y coordinates up by the sensitivity amount.
	 */
	public void panUp() {
		top += sensitivityPan;
		if (isMaxUp()) {
			top = maxTop;
		}
		setPanYCoordinates(top);

	}

	/**
	 * Moves the {@link #controller panel} Y coordinate up by the amount specified.
	 * 
	 * @param amount the amount to move the {@link #controller panel} Y coordinates up by.
	 */
	public void panUp(int amount) {
		top += amount;
		if (isMaxUp()) {
			top = maxTop;
		}
		setPanYCoordinates(top);

	}
	
	/**
	 * Moves the {@link #controller panel} Y coordinate down by the sensitivity amount.
	 */
	public void panDown() {
		top -= sensitivityPan;
		if (isMaxDown()) {
			top = maxBottom;
		}
		setPanYCoordinates(top);

	}
	
	/**
	 * Moves the panel Y coordinate down the by specified amount.  
	 * @param amount - the amount to move the panel down by.
	 */
	public void panDown(int amount) {
		top -= amount;
		if (isMaxDown()) {
			top = maxBottom;
		}
		setPanYCoordinates(top);

	}
	
	/**
	 * Sets the {@link #controller panel} {@link #left xCoordinate} to the specified position.
	 * 
	 * @param xCoordinate the position to set the {@link #controller panel} {@link #left xCoordinate} to.
	 */
	public void setPanXCoordinates(int xCoordinate) {
		int oldXOrigin = this.getXOrigin();
		int oldYOrigin = this.getYOrigin();
		this.setTransformOrigin(0, 0);
		this.panel.getElement().getStyle().setLeft(xCoordinate, Unit.PX);
		this.left = xCoordinate;
		this.setTransformOrigin((panel.getOffsetWidth() / 2) + left, this.getYOrigin());
	}

	/**
	 * Sets the {@link #controller panel} {@link #top yCoordinate} to the specified position.
	 * 
	 * @param yCoordinate the position to set the {@link #controller panel} {@link #top yCoordinate} to.
	 */
	public void setPanYCoordinates(int yCoordinate) {
		int oldXOrigin = this.getXOrigin();
		int oldYOrigin = this.getYOrigin();
		this.setTransformOrigin(left, top);
		GWT.log("Top: " + yCoordinate);
		this.panel.getElement().getStyle().setTop(yCoordinate, Unit.PX);
		this.top = yCoordinate;
		this.setTransformOrigin(oldXOrigin, (panel.getOffsetHeight() / 2 + top));

	}

	/**
	 * Sets both the {@link #left xCoordinate} and {@link #top yCoordinate} position of the {@link #controller panel}.
	 * @param xCoordinate new {@link #left xCoordinate} position of the {@link #controller panel}.
	 * @param yCoordinate new {@link #top yCoordinate} position of the {@link #controller panel}.
	 */
	public void setPanCoordinates(int xCoordinate, int yCoordinate) {
		setPanXCoordinates(xCoordinate);
		setPanYCoordinates(yCoordinate);
	}
	
	/**
	 * 
	 * @return true if the camera is at it's maximum left position. Determined by 
	 * {@link #PAN_PADDING camera padding}.
	 */
	private boolean isMaxLeft(int amount) {
		return PAN_PADDING <= amount;
	}
	
	/**
	 * 
	 * @return true if the camera is at it's maximum right position. Determined by 
	 * (-({@link #controller panel} width - Window Width + {@link #PAN_PADDING camera padding})).
	 */
	private boolean isMaxRight() {
		return (-(panel.getOffsetWidth() - Window.getClientWidth() + PAN_PADDING) + xOrigin) >= left;
	}
	
	/**
	 * 
	 * @return true if the camera is at it's maximum up position. Determined by 
	 * {@link #PAN_PADDING camera padding}.
	 */
	private boolean isMaxUp() {
		return PAN_PADDING <= top + yOrigin;
	}
	
	/**
	 * 
	 * @return true if the camera is at it's maximum down position. Determined by 
	 * (-({@link #controller panel} height - Window height + {@link #PAN_PADDING camera padding})).
	 */
	private boolean isMaxDown() {
		return (-(panel.getOffsetHeight() - Window.getClientHeight() + PAN_PADDING) + yOrigin) >= top;
	}
	
	/**
	 * Adjusts the panel appropriately when a Window resize in sensed.
	 */
	private void addResizeHandler() {
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				autoSetMaxRight();
				autoSetMaxBottom();
				autoSetMaxTop();
				autoSetMaxLeft();
			}
			
		});
	}
	
	private void autoSetMaxLeft() {
		this.maxLeft = 0;
	}
	
	private void autoSetMaxTop() {
		this.maxTop = 0;
	}
	
	/**
	 * Automatically resets the max right by the current panel and window traits.
	 */
	private void autoSetMaxRight() {
		this.maxRight = (-(panel.getOffsetWidth() - Window.getClientWidth() + PAN_PADDING + xOrigin));
	}
	
	/**
	 * Automatically resets the max bottom by the current panel and window traits.
	 */
	private void autoSetMaxBottom() {
		this.maxBottom = (-(panel.getOffsetHeight() - Window.getClientHeight() + PAN_PADDING + yOrigin));
	}
	
	/**
	 * Sets the top style setting of the panel.
	 * @param amount the amount of shift from the top to set the panel.
	 */
	private void setTop(int amount) {
		this.top = amount;
		panel.getElement().getStyle().setTop(amount, Unit.PX);
	}
	
	/**
	 * Sets the left style setting of the panel.
	 * @param amount the amount of shift from the left to set the panel.
	 */
	private void setLeft(int amount) {
		this.left = amount;
		panel.getElement().getStyle().setLeft(amount, Unit.PX);
	}
	
	/**
	 * Sets the pan sensitivity amount.
	 * 
	 * @param amount the amount to pan by from each call to a pan function.
	 */
	public void setSensitivityPan(int amount) {
		this.sensitivityPan = amount;
	}
	
	/**
	 * Sets the rotate sensitivity amount along the X axis.
	 * 
	 * @param amount the amount to rotate for each call to a xRotate function.
	 */
	public void setSensitivityXRotate(int amount) {
		this.sensitivityXRotate = amount;
	}
	
	/**
	 * Sets the rotate sensitivity amount along the Z axis.
	 * 
	 * @param amount the amount to rotate for each call to a yRotate function.
	 */
	public void setSensitivityZRotate(int amount) {
		this.sensitivityZRotate = amount;
	}
}
