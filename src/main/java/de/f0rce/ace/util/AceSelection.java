package de.f0rce.ace.util;

import de.f0rce.ace.events.AceBlurChanged;
import de.f0rce.ace.events.AceForceSyncDomEvent;
import de.f0rce.ace.events.AceSelectionChanged;
import elemental.json.JsonObject;

public class AceSelection {

  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private int startIndex;
  private int endIndex;
  private String selectedText;

  /** Default Constructor to have valid values on initializing. */
  public AceSelection() {
    this.startRow = 0;
    this.startColumn = 0;
    this.endRow = 0;
    this.endColumn = 0;
    this.startIndex = 0;
    this.endIndex = 0;
    this.selectedText = "";
  }

  /**
   * Constructor for initializing the values directly from the frontend (only used in {@link
   * AceBlurChanged}, {@link AceSelectionChanged}, {@link AceForceSyncDomEvent}).
   *
   * @param selectionObject {@link JsonObject}
   */
  public AceSelection(JsonObject selectionObject) {
    JsonObject startObject = selectionObject.getObject("start");
    this.startRow = (int) startObject.getNumber("row");
    this.startColumn = (int) startObject.getNumber("column");

    JsonObject endObject = selectionObject.getObject("end");
    this.endRow = (int) endObject.getNumber("row");
    this.endColumn = (int) endObject.getNumber("column");

    JsonObject indexObject = selectionObject.getObject("index");
    this.startIndex = (int) indexObject.getNumber("start");
    this.endIndex = (int) indexObject.getNumber("end");

    this.selectedText = selectionObject.getString("selectedText");
  }

  /**
   * Returns the row where the selection starts.
   *
   * @return int
   */
  public int getStartRow() {
    return this.startRow;
  }

  /**
   * Returns the column where the selection starts.
   *
   * @return int
   */
  public int getStartColumn() {
    return this.startColumn;
  }

  /**
   * Returns the index where the selection starts.
   *
   * @return int
   */
  public int getStartIndex() {
    return this.startIndex;
  }

  /**
   * Returns the row where the selection ends.
   *
   * @return int
   */
  public int getEndRow() {
    return this.endRow;
  }

  /**
   * Returns the column where the selection ends.
   *
   * @return int
   */
  public int getEndColumn() {
    return this.endColumn;
  }

  /**
   * Returns the index where the selections ends.
   *
   * @return int
   */
  public int getEndIndex() {
    return this.endIndex;
  }

  /**
   * Returns the text which is selected.
   *
   * @return {@link String}
   */
  public String getSelectedText() {
    return this.selectedText;
  }

  /**
   * Check if one instance of the {@link AceSelection} is equal to another.
   *
   * @param a {@link AceSelection}
   * @return boolean
   */
  public boolean compareTo(AceSelection a) {
    if (a.getStartRow() == this.startRow
        && a.getStartColumn() == this.startColumn
        && a.getStartIndex() == this.startIndex
        && a.getEndRow() == this.endRow
        && a.getEndColumn() == this.endColumn
        && a.getEndIndex() == this.endIndex
        && a.getSelectedText().equals(this.selectedText)) {
      return true;
    } else {
      return false;
    }
  }
}
