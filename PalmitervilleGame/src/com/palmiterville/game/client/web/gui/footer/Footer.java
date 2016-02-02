package com.palmiterville.game.client.web.gui.footer;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Footer extends HorizontalPanel {
	
	public static final int FOOTER_HEIGHT = 100;
	private static Footer instance;
	
	private CopyrightLabel copyrightLabel = new CopyrightLabel();
	private ContactLabel contactLabel = new ContactLabel();
	
	public Footer() {
		this.setStyleName("footer");
		this.add(copyrightLabel);
		this.add(contactLabel);
		
		this.setCellVerticalAlignment(copyrightLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		this.setCellVerticalAlignment(contactLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		this.setCellHorizontalAlignment(copyrightLabel, HasHorizontalAlignment.ALIGN_LEFT);
		this.setCellHorizontalAlignment(contactLabel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		setInstance(this);
	}
	
	public static void setInstance(Footer instance) {
		Footer.instance = instance;
	}
	
	public static Footer getInstance() {
		return (instance != null) ? instance : new Footer();
	}
}
