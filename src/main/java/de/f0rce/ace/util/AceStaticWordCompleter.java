package de.f0rce.ace.util;

import java.io.Serializable;
import java.util.List;
import com.google.gson.Gson;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.interfaces.IAceWordCompleter;

/** @author David "F0rce" Dodlek */
public class AceStaticWordCompleter implements IAceWordCompleter, Serializable {
  private static final long serialVersionUID = -1311138752178496479L;
  
  private List<String> words;
  private String category;
  private boolean keepCompleters = false;

  /** @param words {@link List} */
  public AceStaticWordCompleter(List<String> words) {
    this(words, AceEditor.DEFAULT_STATIC_CATEGORY, false);
  }

  /**
   * @param words {@link List}
   * @param keepCompleters boolean
   */
  public AceStaticWordCompleter(List<String> words, boolean keepCompleters) {
    this(words, AceEditor.DEFAULT_STATIC_CATEGORY, keepCompleters);
  }

  /**
   * @param words {@link List}
   * @param category {@link String}
   */
  public AceStaticWordCompleter(List<String> words, String category) {
    this(words, AceEditor.DEFAULT_STATIC_CATEGORY, false);
  }

  /**
   * @param words {@link List}
   * @param category {@link String}
   * @param keepCompleters boolean
   */
  public AceStaticWordCompleter(List<String> words, String category, boolean keepCompleters) {
    this.keepCompleters = keepCompleters;
    this.category = category;
    this.words = words;
  }

  /** @return boolean */
  public boolean isKeepCompleters() {
    return this.keepCompleters;
  }

  /** @return {@link String} */
  public String getCategory() {
    return this.category;
  }

  /** @return {@link List} */
  public List<String> getWords() {
    return this.words;
  }

  @Override
  public String toJSON() {
    return new Gson().toJson(this);
  }
}
