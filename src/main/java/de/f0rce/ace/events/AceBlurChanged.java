package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import de.f0rce.ace.AceEditor;

/**
 * @author David "F0rce" Dodlek
 */

@SuppressWarnings("serial")
@DomEvent("editor-blur")
public class AceBlurChanged extends ComponentEvent<AceEditor> {

	private String value;
	private int selectionRowStart;
	private int selectionFrom;
	private int selectionRowEnd;
	private int selectionTo;
	private int cursorRow;
	private int cursorColumn;

	public AceBlurChanged(AceEditor source, boolean fromClient, @EventData("event.detail.value") String value,
			@EventData("event.detail.selection") String selectionValue,
			@EventData("event.detail.cursorPosition") String cursorPosition) {
		super(source, fromClient);
		this.value = value;
		String[] splitSelection = selectionValue.split("\\|");
		this.selectionRowStart = Integer.parseInt(splitSelection[0]);
		this.selectionFrom = Integer.parseInt(splitSelection[1]);
		this.selectionRowEnd = Integer.parseInt(splitSelection[2]);
		this.selectionTo = Integer.parseInt(splitSelection[3]);

		String[] splitCursor = cursorPosition.split("\\|");
		this.cursorRow = Integer.parseInt(splitCursor[0]);
		this.cursorColumn = Integer.parseInt(splitCursor[1]);
	}

	/**
	 * Returns the current set value for the editor.
	 * 
	 * @return {@link String}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns the row where the selection starts.
	 * 
	 * @return int
	 */
	public int getSelectionRowStart() {
		return selectionRowStart;
	}

	/**
	 * Returns the index where the selection starts.
	 * 
	 * @return int
	 */
	public int getSelectionFrom() {
		return selectionFrom;
	}

	/**
	 * Returns the row where the selection ends.
	 * 
	 * @return int
	 */
	public int getSelectionRowEnd() {
		return selectionRowEnd;
	}

	/**
	 * Returns the index where the selection ends.
	 * 
	 * @return int
	 */
	public int getSelectionTo() {
		return selectionTo;
	}

	/**
	 * Returns the row where the cursor is located.
	 * 
	 * @return int
	 */
	public int getCursorRow() {
		return cursorRow;
	}

	/**
	 * Returns the index/column where the cursor is located.
	 * 
	 * @return int
	 */
	public int getCursorColumn() {
		return cursorColumn;
	}

}
