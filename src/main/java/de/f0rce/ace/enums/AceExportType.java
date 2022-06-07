package de.f0rce.ace.enums;

public enum AceExportType {
  /**
   * Exports the editor's current value as text (indentation will remain but styling won't be
   * applied)
   */
  FLAT("flat"),
  /**
   * Exports the current view of the editor (including indentation, styling and text will be
   * formatted)
   */
  RICH("rich");

  private String type;

  private AceExportType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
