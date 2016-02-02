package com.palmiterville.game.client.gui;

import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.web.gui.ContentPanel;
import com.palmiterville.game.client.web.gui.PageHeader;
import com.palmiterville.game.client.web.gui.displaytemplate.MenuDisplayToolbarWidget;
import com.palmiterville.game.client.web.gui.footer.Footer;
import com.palmiterville.game.client.web.gui.toolbar.HorizontalToolBar;

public class TemplateLoader {

	private static ContentPanel contentPanelInstance;
	private static PageHeader pageHeaderInstance;
	private static HorizontalToolBar toolBarInstance;
	private static Footer footerInstance;
	
	private TemplateLoader() {
		throw new UnsupportedOperationException("Cannot instantiate this helper class.");
	}
	
	/**
	 * Loads the default Palmiterville Site template into the supplied RootPanel.
	 * 
	 * @param panel - the panel to load the site template into.
	 */
	public static ContentPanel loadTemplate(RootPanel panel) {
		ContentPanel contentPanel = ContentPanel.getInstance();
		PageHeader header = PageHeader.getInstance();
		HorizontalToolBar toolBar = HorizontalToolBar.getInstance();
		Footer footer = Footer.getInstance();
		panel.add(header);
		panel.add(toolBar);
		panel.add(contentPanel);
		panel.add(footer);
		
		RootPanel.getBodyElement().setId("rootPanel");
		panel.setStyleName("rootPanel", true);
		panel.getElement().setId("rootPanel");
		
		return contentPanel;
	}
	
	public static void packForGame(boolean prep) {
		RootPanel.get().remove(PageHeader.getInstance());
		RootPanel.get().remove(HorizontalToolBar.getInstance());
		RootPanel.get().remove(Footer.getInstance());
		ContentPanel.getInstance().setSize("100%", "100%");
		if (prep) {
			RootPanel.get().insert(MenuDisplayToolbarWidget.getInstance(), 0);
		} else {
			RootPanel.get().remove(MenuDisplayToolbarWidget.getInstance());
		}
	}

	/**
	 * @return the contentPanelInstance
	 */
	public static ContentPanel getContentPanelInstance() {
		return contentPanelInstance;
	}

	/**
	 * @param contentPanelInstance the contentPanelInstance to set
	 */
	public static void setContentPanelInstance(ContentPanel contentPanelInstance) {
		TemplateLoader.contentPanelInstance = contentPanelInstance;
	}

	/**
	 * @return the pageHeaderInstance
	 */
	public static PageHeader getPageHeaderInstance() {
		return pageHeaderInstance;
	}

	/**
	 * @param pageHeaderInstance the pageHeaderInstance to set
	 */
	public static void setPageHeaderInstance(PageHeader pageHeaderInstance) {
		TemplateLoader.pageHeaderInstance = pageHeaderInstance;
	}

	/**
	 * @return the toolBarInstance
	 */
	public static HorizontalToolBar getToolBarInstance() {
		return toolBarInstance;
	}

	/**
	 * @param toolBarInstance the toolBarInstance to set
	 */
	public static void setToolBarInstance(HorizontalToolBar toolBarInstance) {
		TemplateLoader.toolBarInstance = toolBarInstance;
	}

	/**
	 * @return the footerInstance
	 */
	public static Footer getFooterInstance() {
		return footerInstance;
	}

	/**
	 * @param footerInstance the footerInstance to set
	 */
	public static void setFooterInstance(Footer footerInstance) {
		TemplateLoader.footerInstance = footerInstance;
	}

	
	
	
}
