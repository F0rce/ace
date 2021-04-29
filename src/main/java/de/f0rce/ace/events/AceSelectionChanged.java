package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import de.f0rce.ace.AceEditor;

/**
 * @author David "F0rce" Dodlek
 */

@SuppressWarnings("serial")
@DomEvent("editor-selection")
public class AceSelectionChanged extends ComponentEvent<AceEditor> {

	private String selectedText;
	private int selectionRowStart;
	private int selectionFrom;
	private int selectionRowEnd;
	private int selectionTo;
	private int cursorRow;
	private int cursorColumn;

	public AceSelectionChanged(AceEditor source, boolean fromClient,
			@EventData("event.detail.selectedText") String selectedText,
			@EventData("event.detail.selection") String selectionValue,
			@EventData("event.detail.cursorPosition") String cursorPosition) {
		super(source, fromClient);
		this.selectedText = selectedText;
		String[] split = selectionValue.split("\\|");
		this.selectionRowStart = Integer.parseInt(split[0]);
		this.selectionFrom = Integer.parseInt(split[1]);
		this.selectionRowEnd = Integer.parseInt(split[2]);
		this.selectionTo = Integer.parseInt(split[3]);

		String[] splitCursor = cursorPosition.split("\\|");
		this.cursorRow = Integer.parseInt(splitCursor[0]);
		this.cursorColumn = Integer.parseInt(splitCursor[1]);
	}

	/**
	 * Returns the Text that is selected.
	 * 
	 * @return {@link String}
	 */
	public String getSelectedText() {
		return selectedText;
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
