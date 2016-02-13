package com.palmiterville.game.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.palmiterville.game.client.camera.gui.CameraControllerPanel;
import com.palmiterville.game.client.camera.gui.CameraControllerPanel.CameraEvent;
import com.palmiterville.game.client.camera.handler.CameraHandler;
import com.palmiterville.game.client.camera.listener.CameraListener;

/**
 * The GameControlPanel is the panel that overlays the grid to and listens for all key and mouse inputs
 * to process events during the game.
 * 
 * @author Dave
 *
 */
public class GameControlPanel extends FocusPanel implements CameraListener {

	private FlowPanel childPanel;
	
	/**
	 * Returns whether the Shift key is depressed or not.
	 */
	private boolean isShiftKeyDown;
	
	/**
	 * Returns whether a mouse drag event is processing.
	 */
	private boolean isDragging;
	
	/**
	 * A list of CameraHandlers attached to the CameControlPanel.
	 */
	private List<CameraHandler> cameraHandlers = new ArrayList<>();
	
	public GameControlPanel() {
		super();
		childPanel = new FlowPanel();
		childPanel.setHeight("100%");
		childPanel.setWidth("100%");
		super.add(childPanel);
		this.setStyleName("control_game_panel");
		this.initKeyModifierHandlers();
		this.setFocus(true);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				GameControlPanel.this.onResize();
			}
			
		});
	}
	
	private void initKeyModifierHandlers() {
		this.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_SHIFT) {
					isShiftKeyDown = true;
				}
			}
			
		});
		
		this.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_SHIFT) {
					isShiftKeyDown = false;
				}
			}
		});
	}
	
	private void onResize() {
		this.setHeight(Window.getClientHeight() + "px");
		this.setWidth(Window.getClientWidth() + "px");
	}
	
	@Override
	public void add(Widget widget) {
		childPanel.add(widget);
	}
	
	public void setCameraControls(final CameraControllerPanel panel) {
		this.add(panel);
		panel.addCameraListener(this);
		this.addCameraHandler(new CameraHandler() {

			@Override
			public void onCameraEvent(CameraEvent event) {
				GameControlPanel.this.setFocus(true);
			}
			
		});
		
		this.addMouseWheelHandler(new MouseWheelHandler() {
			
			@Override
			public void onMouseWheel(MouseWheelEvent event) {

				if (event.isSouth()) {
					if (isShiftKeyDown) {
						event.preventDefault();
						panel.fireCameraEvent(CameraEvent.PAN_RIGHT);
					} else {
						panel.fireCameraEvent(CameraEvent.PAN_DOWN);
					}
				}
				
				if (event.isNorth()) {
					if (isShiftKeyDown) {
						event.preventDefault();
						panel.fireCameraEvent(CameraEvent.PAN_LEFT);
					} else {
						panel.fireCameraEvent(CameraEvent.PAN_UP);
					}
				}
			}
		});
	}

	/**
	 * Adds a CameraHandler to this class.
	 * 
	 * @param handler - the CameraHandler to add.
	 */
	@Override
	public void addCameraHandler(CameraHandler handler) {
		this.cameraHandlers.add(handler);
	}

	/**
	 * Removes a CameraHandler from this class.
	 * 
	 * @param handler - the handler to remove.
	 */
	@Override
	public void removeCameraHandler(CameraHandler handler) {
		this.cameraHandlers.add(handler);
	}

	/**
	 * Fires all CameraHandlers added to this class.
	 * 
	 * @param event - the CameraEvent that caused this method to fire.
	 */
	@Override
	public void fireCameraEvent(CameraEvent event) {
		for (CameraHandler handlers : cameraHandlers) {
			handlers.onCameraEvent(event);
		}
	}
	
}
