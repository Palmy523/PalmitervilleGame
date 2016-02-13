package com.palmiterville.game.client.camera.listener;

import com.palmiterville.game.client.camera.gui.CameraControllerPanel.CameraEvent;
import com.palmiterville.game.client.camera.handler.CameraHandler;

public interface CameraListener {

	public void addCameraHandler(CameraHandler handler);
	public void removeCameraHandler(CameraHandler handler);
	public void fireCameraEvent(CameraEvent event);
}
