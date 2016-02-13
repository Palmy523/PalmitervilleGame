package com.palmiterville.game.client.camera.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.palmiterville.game.client.camera.action.CameraAction;
import com.palmiterville.game.client.camera.exception.CameraException;
import com.palmiterville.game.client.camera.listener.CameraListener;

/**
 * 
 * @author Dave
 *
 */
public class CameraControllerPanel extends FlowPanel {
	
	public static enum CameraEvent{PAN_LEFT, PAN_RIGHT, PAN_UP, PAN_DOWN, TILT_BACK, TILT_FORWARD,
		ROTATE_CLOCKWISE, ROTATE_CCW};
	private static List<CameraListener> cameraListeners = new ArrayList<>();
	
	private Panel cameraPanel;
	private CameraAction cameraAction;
	private Label cameraLabel = new Label("Camera Control");
	private Button panLeft = new Button("<");
	private Button panRight = new Button(">");
	private Button panUp = new Button("^");
	private Button panDown = new Button("v");
	private Button tiltBack = new Button("TiltBack");
	private Button tiltForward = new Button("TiltFoward");
	private Button rotateClockwise = new Button("Rotate Clockwise");
	private Button rotateCounterClockwise = new Button ("Rotate CounterClockwise");
	
	/**
	 * Initiates the controller with the panel to perform camera actions on.
	 * 
	 * @param panel
	 * @throws CameraException
	 */
	public CameraControllerPanel(Panel panel) throws CameraException {
		super();
		this.setStyleName("camera_panel");
		cameraLabel.setStyleName("camera_label");
		cameraListeners = new ArrayList<>();
		this.cameraPanel = panel;
		initCameraButtons();
		this.add(cameraLabel);
		this.add(panLeft);
		this.add(panRight);
		this.add(panUp);
		this.add(panDown);
		this.add(tiltBack);
		this.add(tiltForward);
		this.add(rotateClockwise);
		this.add(rotateCounterClockwise);
		
		
	}
	
	private void initCameraButtons() throws CameraException {
		
		cameraAction = new CameraAction(cameraPanel);
		
//**************************Pan Left Button******************************//
		panLeft.setStyleName("camera_pan_left");
		panLeft.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
					fireCameraEvent(CameraEvent.PAN_LEFT);
			}
			
		});

//**************************Pan Right Button******************************//
		panRight.setStyleName("camera_pan_right");
		panRight.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
					fireCameraEvent(CameraEvent.PAN_RIGHT);
			}
			
		});
		
//**************************Pan Up Button******************************//
		panUp.setStyleName("camera_pan_up");
		panUp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.PAN_UP);
			}
			
		});

//**************************Pan Down Button******************************//
		panDown.setStyleName("camera_pan_down");
		panDown.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.PAN_DOWN);
			}
			
			
		});
		
		tiltForward.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.TILT_FORWARD);
			}
			
		});
		
		tiltBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.TILT_BACK);
			}
			
		});
	
		
		this.rotateClockwise.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.ROTATE_CLOCKWISE);
			}
			
		});
		
		this.rotateCounterClockwise.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireCameraEvent(CameraEvent.ROTATE_CCW);
			}
			
		});
	}
	
	public void fireCameraEvent(CameraEvent event) {
		switch(event) {
			case PAN_LEFT : cameraAction.panLeft(); break;
			case PAN_RIGHT : cameraAction.panRight(); break;
			case PAN_UP : cameraAction.panUp(); break;
			case PAN_DOWN : cameraAction.panDown(); break;
			case TILT_FORWARD : cameraAction.tiltForward(); break;
			case TILT_BACK : cameraAction.tiltBack(); break;
			case ROTATE_CLOCKWISE : cameraAction.rotateClockwise(); break;
			case ROTATE_CCW : cameraAction.rotateCounterClockwise(); break;
		}
		
		/**
		 * Notify all listeners an event has fired.
		 */
		for (CameraListener listener : cameraListeners) {
			listener.fireCameraEvent(event);
		}
	}
	
	public Panel getPanel() {
		return cameraPanel;
	}
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	public static boolean addCameraListener(CameraListener listener) {
		return cameraListeners.add(listener);
	}
	
	public static boolean removeCameraListener(CameraListener listener) {
		return cameraListeners.remove(listener);
	}
	
	
}
