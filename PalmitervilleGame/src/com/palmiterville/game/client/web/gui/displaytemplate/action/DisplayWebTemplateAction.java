package com.palmiterville.game.client.web.gui.displaytemplate.action;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.gui.TemplateLoader;
import com.palmiterville.game.client.web.gui.ContentPanel;
import com.palmiterville.game.client.web.gui.PageHeader;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;
import com.palmiterville.game.client.web.gui.footer.Footer;
import com.palmiterville.game.client.web.gui.toolbar.HorizontalToolBar;

public class DisplayWebTemplateAction implements PalmitervilleAction {

	public static boolean displayed = false;
	
	public DisplayWebTemplateAction() {
		
	}
	
	@Override
	public void preProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void processAction(GwtEvent e) {
		PageHeader header = PageHeader.getInstance();
		HorizontalToolBar toolbar = HorizontalToolBar.getInstance();
		Footer footer = Footer.getInstance();
		if (displayed) {
			RootPanel.get().insert(header, 1);
			RootPanel.get().insert(toolbar, 2);
			RootPanel.get().add(footer);
			ContentPanel.getInstance().setSize("100%", "100%");
			displayed = false;
		} else {
			TemplateLoader.packForGame(true);
			displayed = true;
		}
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

}
