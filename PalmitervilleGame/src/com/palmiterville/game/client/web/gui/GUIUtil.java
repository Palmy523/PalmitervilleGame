package com.palmiterville.game.client.web.gui;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class GUIUtil {

	public static void centerCellContent(CellPanel panel, Widget widget) {
		panel.setCellHorizontalAlignment(widget, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setCellVerticalAlignment(widget,  HasVerticalAlignment.ALIGN_MIDDLE);
	}
	
}
