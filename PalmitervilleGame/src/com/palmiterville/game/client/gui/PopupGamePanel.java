package com.palmiterville.game.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.item.component.GridItem;

/**
 * A Closeable PopupPanel that serves as a holder for a widget in the game. 
 * 
 * @author Dave
 *
 */
public class PopupGamePanel extends PopupPanel {

	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 50;
	private boolean hasContent = false;
	
	/**
	 * Position Enumerator.
	 * 
	 * @author Dave
	 *
	 */
	public static enum PopupPosition {BACKDROP_UPPER_LEFT, BACKDROP_UPPER_RIGHT, 
		BACKDROP_UPPER_MIDDLE, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM, CENTER, 
		GRID_ITEM_LABEL}; 
		
	/**
	 * The contentPanel holds the content for the PopupPanel and is the only child of
	 * the popup. Content can be added by accessing this panel and adding to it.
	 */
	public VerticalPanel contentPanel;
	
	public PopupPosition currentPosition;

		/**
		 * An empty PopupPanel constructor.
		 */
		public PopupGamePanel() {
			super();
			contentPanel = new VerticalPanel();
			contentPanel.add(new CloseButton(this));
			super.add(contentPanel);
			this.setStyleName("popupGamePanel");
			this.setSize(DEFAULT_WIDTH + "px", DEFAULT_HEIGHT + "px");
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					try {
						PopupGamePanel.this.setPopupPosition(currentPosition, 
								PopupGamePanel.this.isShowing());
					} catch (GridException e) {
						GWT.log(e.getMessage(), e);
					}
				}
			});
		}

		/**
		 * Displays the PopupGamePanel in the specified PopupPosition.
		 * 
		 * @param position - the PopupPosition to display the panel.
		 * @throws GridException - thrown if the popupPanel is larger than the 
		 * specified container to be displayed in.
		 */
		@SuppressWarnings("incomplete-switch")
		public void setPopupPosition(PopupPosition position, boolean display) throws GridException {
			
			Backdrop backdrop = Backdrop.getInstance();
			int thisWidth = this.getOffsetWidth();
			int thisHeight = this.getOffsetHeight();
			
			//If the backdrop is null, skip this set of statements as we cannot display
			//the panel relative to a null Backdrop.
			if (backdrop != null) {
				int backdropWidth = backdrop.getOffsetWidth();
				int backdropHeight = backdrop.getOffsetHeight();

				//throw exception if the width of the panel is greater than the Backdrop width as offset
				if (thisWidth > backdropWidth) {
					throw new GridException("Cannot display " + this.getClass() + " with width " + thisWidth
							+ "backdrop with smaller width." + "Backdrop Width: " + backdropWidth);
				}

				//throw exception if the height of the panel is greater than the Backdrop height
				if (thisHeight > backdropHeight) {
					throw new GridException("Cannot display " + this.getClass() + " with height " + thisHeight
							+ "backdrop with smaller height." + "Backdrop Height: " + backdropHeight);
				}
				
				int top = backdrop.getAbsoluteTop();
				int left = backdrop.getAbsoluteLeft();
				switch(position) {
					case BACKDROP_UPPER_LEFT : 
						this.setPopupPosition(left, top);
						currentPosition = PopupPosition.BACKDROP_UPPER_LEFT;
						break;
					case BACKDROP_UPPER_RIGHT:
						int absoluteRight = backdropWidth - thisWidth + left;
						this.setPopupPosition(absoluteRight, top);
						currentPosition = PopupPosition.BACKDROP_UPPER_RIGHT;
						break;
					case BACKDROP_UPPER_MIDDLE:
						int middle = left + (backdropWidth/2) - (thisWidth / 2);
						this.setPopupPosition(middle, top);
						currentPosition = PopupPosition.BACKDROP_UPPER_MIDDLE;
						break;
				}
			}
			
			switch(position) {
				case PAGE_LEFT :
					currentPosition = PopupPosition.PAGE_LEFT;
					throw new UnsupportedOperationException("Not supported yet.");
				case PAGE_RIGHT :
					currentPosition = PopupPosition.PAGE_RIGHT;
					throw new UnsupportedOperationException("Not supported yet.");
				case PAGE_TOP :
					currentPosition = PopupPosition.PAGE_TOP;
					throw new UnsupportedOperationException("Not supported yet.");
				case PAGE_BOTTOM :
					currentPosition = PopupPosition.PAGE_BOTTOM;
					throw new UnsupportedOperationException("Not supported yet.");
				case CENTER : 
					currentPosition = PopupPosition.CENTER;
					this.center();
					return;
			}
			if (display) {
				this.show();
			}
		}
		
		public void enableClose(boolean enable) {
			contentPanel.getWidget(0).setVisible(enable);
		}
		
		
		

		
}
