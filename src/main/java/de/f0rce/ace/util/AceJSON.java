package de.f0rce.ace.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.f0rce.ace.AceEditor;

/**
 * Utility Class for generating JSON Objects to be sent to the frontend.
 *
 * @author David "F0rce" Dodlek
 */
public class AceJSON {

  private static final String DEFAULT_CATEGORY = "dynamic";

  /**
   * {@link AceEditor#addDynamicAutocompletion(Map, String, String, boolean)}
   *
   * @param toConvert {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   * @param keepCompleters boolean
   * @return {@link String}
   */
  public static String generateDynamicAutocompletionJSON(
      Map<String, List<String>> toConvert,
      String seperator,
      String category,
      boolean keepCompleters) {
    Set<String> keys = toConvert.keySet();

    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"seperator\": " + "\"" + seperator + "\", ");
    builder.append("\"category\": " + "\"" + category + "\", ");
    builder.append("\"keepcompleters\": " + keepCompleters + ", ");

    builder.append("\"list\": " + "{");
    int counter = 0;
    for (String key : keys) {
      builder.append("\"" + key + "\": ");
      builder.append("[");
      List<String> currentList = toConvert.get(key);
      for (String word : currentList) {
        builder.append("\"" + word + "\"");
        if (currentList.indexOf(word) != currentList.size() - 1) {
          builder.append(", ");
        }
      }
      builder.append("]");
      if (counter != keys.size() - 1) {
        builder.append(", ");
      }
      counter++;
    }
    builder.append("}");
    builder.append("}");

    return builder.toString();
  }

  /**
   * {@link AceEditor#addDynamicAutocompletion(Map, String)}
   *
   * @param toConvert {@link Map}
   * @param seperator {@link String}
   * @return {@link String}
   */
  public static String generateDynamicAutocompletionJSON(
      Map<String, List<String>> toConvert, String seperator) {
    return AceJSON.generateDynamicAutocompletionJSON(toConvert, seperator, DEFAULT_CATEGORY, false);
  }

  /**
   * {@link AceEditor#addDynamicAutocompletion(Map, String, String)}
   *
   * @param toConvert {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   * @return {@link String}
   */
  public static String generateDynamicAutocompletionJSON(
      Map<String, List<String>> toConvert, String seperator, String category) {
    return AceJSON.generateDynamicAutocompletionJSON(toConvert, seperator, category, false);
  }

  /**
   * {@link AceEditor#addDynamicAutocompletion(Map, String, boolean)}
   *
   * @param toConvert {@link Map}
   * @param seperator {@link String}
   * @param keepCompleters boolean
   * @return {@link String}
   */
  public static String generateDynamicAutocompletionJSON(
      Map<String, List<String>> toConvert, String seperator, boolean keepCompleters) {
    return AceJSON.generateDynamicAutocompletionJSON(
        toConvert, seperator, DEFAULT_CATEGORY, keepCompleters);
  }

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
}