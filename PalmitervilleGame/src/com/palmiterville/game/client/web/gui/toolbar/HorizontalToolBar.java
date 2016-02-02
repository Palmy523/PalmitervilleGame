package com.palmiterville.game.client.web.gui.toolbar;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HorizontalToolBar extends HorizontalPanel {

	public static final int TOOL_BAR_HEIGHT = 30;
	
	private static HorizontalToolBar instance;
	private HorizontalPanel contentPanel;
	
	public HorizontalToolBar() {
		super();
		contentPanel = new HorizontalPanel();
		this.setStyleName("toolBar");
		super.add(contentPanel);
		super.setCellHorizontalAlignment(contentPanel, HasHorizontalAlignment.ALIGN_CENTER);
		super.setCellVerticalAlignment(contentPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		contentPanel.setStyleName("toolBar_content");
		contentPanel.setSpacing(15);
		add(new HomeToolBarItem());
		add(new AboutToolBarItem());
		setInstance(this);
	}
	
	@Override
	public void add(Widget widget) {
		if (contentPanel.getWidgetCount() > 0) {
			addDivider();
		}
		contentPanel.add(widget);
		style(widget);
		size(widget);
		center(widget);
	}
	
	private void addDivider() {
		Label divider = new Label(" | ");
		contentPanel.add(divider);
		center(divider);
		divider.setStyleName("toolBar_item");
	}
	
	private void center(Widget widget) {
		contentPanel.setCellHorizontalAlignment(widget, HasHorizontalAlignment.ALIGN_CENTER);
		contentPanel.setCellVerticalAlignment(widget, HasVerticalAlignment.ALIGN_MIDDLE);
	}
	
	private void style(Widget widget) {
	}
	
	private void size(Widget widget) {
		contentPanel.setCellWidth(widget, String.valueOf(widget.getOffsetWidth() + "px"));
	}
	
	public static void setInstance(HorizontalToolBar toolbar) {
		instance = toolbar;
	}
	
	public static HorizontalToolBar getInstance() {
		return (instance != null) ? instance : new HorizontalToolBar();
	}
}
