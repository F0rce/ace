package de.f0rce.ace.util;

import de.f0rce.ace.AceEditor;

/**
 * Utility Class for generating JSON Objects to be sent to the frontend.
 *
 * @author David "F0rce" Dodlek
 */
public class AceJSON {

  /**
   * {@link AceEditor#setSelection(int, int, int, int)}
   *
   * @param startRow int
   * @param startColumn int
   * @param endRow int
   * @param endColumn int
   * @return {@link String}
   */
  public static String generateSelectionJSON(
      int startRow, int startColumn, int endRow, int endColumn) {
    StringBuilder builder = new StringBuilder();

    builder.append("{");
    builder.append("\"start\": { \"row\": " + startRow + ", \"column\": " + startColumn + " }, ");
    builder.append("\"end\": { \"row\": " + endRow + ", \"column\": " + endColumn + " }");
    builder.append("}");

    return builder.toString();
  }

  /**
   * {@link AceEditor#setSelection(AceSelection)}
   *
   * @param selection {@link AceSelection}
   * @return {@link String}
   */
  public static String generateSelectionJSON(AceSelection selection) {
    StringBuilder builder = new StringBuilder();

    builder.append("{");
    builder.append(
        "\"start\": { \"row\": "
            + selection.getStartRow()
            + ", \"column\": "
            + selection.getStartColumn()
            + " }, ");
    builder.append(
        "\"end\": { \"row\": "
            + selection.getEndRow()
            + ", \"column\": "
            + selection.getEndColumn()
            + " }");
    builder.append("}");

    return builder.toString();
  }

  /**
   * {@link AceEditor#setCursorPosition(int, int)}
   *
   * @param row int
   * @param column int
   * @return {@link String}
   */
  public static String generateCursorPositionJSON(int row, int column) {
    StringBuilder builder = new StringBuilder();

    builder.append("{");
    builder.append("\"row\": " + (row + 1) + ", ");
    builder.append("\"column\": " + column);
    builder.append("}");

    return builder.toString();
  }

  /**
   * {@link AceEditor#setCursorPosition(AceCursorPosition)}
   *
   * @param cursorPosition {@link AceCursorPosition}
   * @return {@link String}
   */
  public static String generateCursorPositionJSON(AceCursorPosition cursorPosition) {
    StringBuilder builder = new StringBuilder();

    builder.append("{");
    builder.append("\"row\": " + (cursorPosition.getRow() + 1) + ", ");
    builder.append("\"column\": " + cursorPosition.getColumn());
    builder.append("}");

    return builder.toString();
  }
}
