package com.palmiterville.game.client.grid.gui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.component.Grid;
import com.palmiterville.game.client.grid.exception.GridBuilderException;
import com.palmiterville.game.client.grid.exception.GridCreationException;
import com.palmiterville.game.client.grid.section.gui.EmptySection;
import com.palmiterville.game.client.grid.section.gui.MoveableSection;
import com.palmiterville.game.client.grid.section.gui.MoveableSection.SectionShape;
import com.palmiterville.game.client.grid.section.gui.Section;

public class GridPanel extends FlowPanel {
	
	public static enum SectionType {OCTAGON, SQUARE}
	private Section selectedSection;
	private HorizontalPanel rowPanel = new HorizontalPanel();
	private Grid grid;
	private Cursor cursor;
	private boolean isGridLinesShow = false;
	
	/**
	 * A mapping of Coordinates and the appropriate GridSection for
	 * that coordinate.
	 */
	private Map<Coordinates, Section> sectionMap;
	public GridPanel(int numberRows, int numberColumns) throws GridCreationException {
		this(new Grid(numberRows, numberColumns));
	}
	
	public GridPanel(Grid grid) {
		this.grid = grid;
		this.setStyleName("grid_panel");
		this.getElement().getStyle().setPosition(Style.Position.RELATIVE);
		this.add(rowPanel);
		this.cursor = new Cursor(this);
		sectionMap = new HashMap<>();
		rowPanel.getElement().getStyle().setPadding(0, Unit.PX);
		rowPanel.getElement().getStyle().setMargin(0, Unit.PX);
		rowPanel.setStyleName("grid_row");
	}
	
	public void createTemplate() {
		int columns = grid.getWidth();
		int rows = grid.getHeight();
		for (int i = 0; i < columns; i++) {
			VerticalPanel panel = new VerticalPanel();
			panel.getElement().setAttribute("table-layout", "fixed");
			panel.getElement().getStyle().setPadding(0, Unit.PX);
			panel.getElement().getStyle().setMargin(0, Unit.PX);
			rowPanel.add(panel);
			rowPanel.setCellHorizontalAlignment(panel, HasHorizontalAlignment.ALIGN_CENTER);
			rowPanel.setCellVerticalAlignment(panel, HasVerticalAlignment.ALIGN_MIDDLE);
			for (int j = 0; j < rows; j++) {
				Section section = new EmptySection(j, i);
				panel.add(section);
				panel.setCellHorizontalAlignment(section, HasHorizontalAlignment.ALIGN_CENTER);
				panel.setCellVerticalAlignment(section, HasVerticalAlignment.ALIGN_MIDDLE);
				panel.setStyleName("grid_column");
				this.sectionMap.put(section.getGridCoordinates(), section);
			}
		}
	}
	
	public void addSection(Section section) throws GridBuilderException {
		int row = section.getGridCoordinates().getRow();
		int column = section.getGridCoordinates().getColumn();
		
		if (row > grid.getHeight()) {
			throw new GridBuilderException("Row of Section " + row + " exceeds grid number of rows " 
					+ grid.getHeight());
			
		}
		
		VerticalPanel panel = (VerticalPanel) rowPanel.getWidget(column);
		if (section instanceof MoveableSection) {
			MoveableSection moveableSection = (MoveableSection) section;
			assertSectionShape(moveableSection, row, column);
		}
		
		panel.insert(section, row);
		panel.remove(row + 1);
		sectionMap.remove(section.getGridCoordinates());
		sectionMap.put(section.getGridCoordinates(), section);
		
		if (this.isGridLinesShow) {
			section.enableGridLines(isGridLinesShow);
		}
	}
	
	private void assertSectionShape(MoveableSection section, int row, int column) {
		SectionShape shape = getShapeForCoordinates(row, column);
		section.setShape(shape);
	}
	
	/**
	 * Gets the SectionShape for the specified row and column indices. The shape is determined
	 * by the predefined layout of the grid. Octagon shapes are always even row and even column, 
	 * or odd row, and odd column. Squares are always even row, odd column or odd row odd column.
	 * 
	 * @param row - the row index for the SectionShape.
	 * @param column - the column index for the SectionShape.
	 * @return - The SectionShape for the specified row and column indices.
	 */
	private SectionShape getShapeForCoordinates(int row, int column) {
		boolean rowEven = (row % 2 == 0);
		boolean columnEven = (column % 2 == 0);
		if((rowEven && columnEven) || (!rowEven && !columnEven)) {
			return SectionShape.OCTAGON;
		} else {
			return SectionShape.SQUARE;
		}
	}
	
	public boolean isGridLinesShowing() {
		return isGridLinesShow;
	}
	
	public Section getSectionAt(int row, int column) {
		return sectionMap.get(new Coordinates(row, column));
	}
	
	public void enableGridLines(boolean show) {
		for (Section section : sectionMap.values()) {
			section.enableGridLines(show);
		}
		isGridLinesShow = show;
	}
	
	public void setSelectedSection(Section section) {
		this.selectedSection = section;
	}
	
	public void setSelectedSection(Coordinates coordinates) {
		this.selectedSection = sectionMap.get(coordinates);
	}
	
	public Section getSelectedSection() {
		return selectedSection;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public HorizontalPanel getRowPanel() {
		return rowPanel;
	}
	
	public Cursor getCursor() {
		return cursor;
	}
	
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
	
	public Map<Coordinates, Section> getSectionMap() {
		return sectionMap;
	}
}
