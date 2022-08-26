package de.f0rce.ace.util;

import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.interfaces.IAceWordCompleter;

/** @author David "F0rce" Dodlek */
public class AceDynamicWordCompleter implements IAceWordCompleter {

  private Map<String, List<String>> dynamicWords;
  private String seperator;
  private String category;
  private boolean keepCompleters = false;

  /**
   * @param dynamicWords {@link Map}
   * @param seperator {@link String}
   */
  public AceDynamicWordCompleter(Map<String, List<String>> dynamicWords, String seperator) {
    this(dynamicWords, seperator, AceEditor.DEFAULT_DYNAMIC_CATEGORY, false);
  }

  /**
   * @param dynamicWords {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   */
  public AceDynamicWordCompleter(
      Map<String, List<String>> dynamicWords, String seperator, String category) {
    this(dynamicWords, seperator, category, false);
  }

  /**
   * @param dynamicWords {@link Map}
   * @param seperator {@link String}
   * @param keepCompleters boolean
   */
  public AceDynamicWordCompleter(
      Map<String, List<String>> dynamicWords, String seperator, boolean keepCompleters) {
    this(dynamicWords, seperator, AceEditor.DEFAULT_DYNAMIC_CATEGORY, keepCompleters);
  }

  /**
   * @param dynamicWords {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   * @param keepCompleters boolean
   */
  public AceDynamicWordCompleter(
      Map<String, List<String>> dynamicWords,
      String seperator,
      String category,
      boolean keepCompleters) {
    this.dynamicWords = dynamicWords;
    this.seperator = seperator;
    this.category = category;
    this.keepCompleters = keepCompleters;
  }

  /** @return {@link Map} */
  public Map<String, List<String>> getDynamicWords() {
    return this.dynamicWords;
  }

  /** @return {@link String} */
  public String getSeperator() {
    return this.seperator;
  }

  /** @return {@link String} */
  public String getCategory() {
    return this.category;
  }

  /** @return boolean */
  public boolean isKeepCompleters() {
    return this.keepCompleters;
  }

  @Override
  public String toJSON() {
    return new Gson().toJson(this);
  }
}
