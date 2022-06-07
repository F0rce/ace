package de.f0rce.ace.util;

import de.f0rce.ace.AceEditor;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility Class for generating JSON Objects to be sent to the frontend.
 *
 * @author David "F0rce" Dodlek
 */
public class AceJSON {

  private static final String DEFAULT_DYNAMIC_CATEGORY = "dynamic";
  private static final String DEFAULT_CUSTOM_CATEGORY = "keyword";

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
    return AceJSON.generateDynamicAutocompletionJSON(
        toConvert, seperator, DEFAULT_DYNAMIC_CATEGORY, false);
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
        toConvert, seperator, DEFAULT_DYNAMIC_CATEGORY, keepCompleters);
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

  /**
   * {@link AceEditor#setCustomAutocompletion(List, String, boolean)}
   *
   * @param wordList {@link List}
   * @param category {@link String}
   * @param keepCurrentCompleters boolean
   * @return {@link String}
   */
  public static String generateCustomAutocompletionJSON(
      List<String> wordList, String category, boolean keepCurrentCompleters) {
    StringBuilder builder = new StringBuilder();

    builder.append("{");
    builder.append("\"category\": " + "\"" + category + "\", ");
    builder.append("\"keepcompleters\": " + keepCurrentCompleters + ", ");

    builder.append("\"wordlist\": " + "[");
    int counter = 0;
    for (String word : wordList) {
      builder.append("\"" + word + "\"");
      if (counter != wordList.size() - 1) {
        builder.append(", ");
      }
      if (counter == wordList.size() - 1) {
        builder.append("]");
      }
      counter++;
    }
    builder.append("}");

    return builder.toString();
  }

  /**
   * {@link AceEditor#setCustomAutocompletion(List)}
   *
   * @param wordList {@link Map}
   * @return {@link String}
   */
  public static String generateCustomAutocompletionJSON(List<String> wordList) {
    return AceJSON.generateCustomAutocompletionJSON(wordList, DEFAULT_CUSTOM_CATEGORY, false);
  }

  /**
   * {@link AceEditor#setCustomAutocompletion(List, boolean)}
   *
   * @param wordList {@link List}
   * @param keepCurrentCompleters boolean
   * @return {@link String}
   */
  public static String generateCustomAutocompletionJSON(
      List<String> wordList, boolean keepCurrentCompleters) {
    return AceJSON.generateCustomAutocompletionJSON(
        wordList, DEFAULT_CUSTOM_CATEGORY, keepCurrentCompleters);
  }

  /**
   * {@link AceEditor#setCustomAutocompletion(List, String)}
   *
   * @param wordList {@link List}
   * @param category {@link String}
   * @return {@link String}
   */
  public static String generateCustomAutocompletionJSON(List<String> wordList, String category) {
    return AceJSON.generateCustomAutocompletionJSON(wordList, category, false);
  }
}
