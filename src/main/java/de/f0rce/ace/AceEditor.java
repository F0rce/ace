package de.f0rce.ace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

import de.f0rce.ace.enums.AceMarkerColor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;
import de.f0rce.ace.events.AceBlurChanged;
import de.f0rce.ace.events.AceChanged;
import de.f0rce.ace.events.AceForceSyncDomEvent;
import de.f0rce.ace.events.AceForceSyncEvent;
import de.f0rce.ace.events.AceHTMLGeneratedEvent;
import de.f0rce.ace.events.AceReady;
import de.f0rce.ace.events.AceSelectionChanged;
import de.f0rce.ace.util.AceCursorPosition;
import de.f0rce.ace.util.AceJSON;
import de.f0rce.ace.util.AceMarker;
import de.f0rce.ace.util.AceSelection;

/** @author David "F0rce" Dodlek */
@SuppressWarnings("serial")
@Tag("lit-ace")
@NpmPackage(value = "@f0rce/lit-ace", version = "1.4.0")
@JsModule("./@f0rce/lit-ace/lit-ace.js")
public class AceEditor extends AbstractSinglePropertyField<AceEditor, String>
    implements HasSize, HasStyle, Focusable<AceEditor> {

  private AceTheme theme = AceTheme.eclipse;
  private AceMode mode = AceMode.javascript;
  private String baseUrl = "ace-builds/src-min-noconflict/";
  private int fontSize = 14;
  private boolean softTabs = true;
  private int tabSize = 4;
  private boolean wrap = false;
  private boolean autoComplete = false;
  private boolean initialFocus = false;
  private String placeHolder = "";
  private boolean readOnly = false;
  private boolean printMargin = false;
  private boolean showInvisibles = false;
  private boolean showGutter = true;
  private boolean hightlightActiveLine = true;
  private boolean displayIndentGuides = false;
  private boolean highlightSelectedWord = false;
  private AceSelection selection = new AceSelection();
  private AceCursorPosition cursorPosition = new AceCursorPosition();
  private boolean useWorker = false;
  private boolean liveAutocompletion = false;
  private boolean enableSnippets = false;
  private String[] customAutoCompletion = new String[0];
  private List<AceMarker> markers = new ArrayList<AceMarker>();
  private boolean statusbarEnabled = true;

  public AceEditor() {
    super("value", "", false);
    super.addListener(AceBlurChanged.class, this::updateEditor);
    super.addListener(AceForceSyncDomEvent.class, this::onForceSyncDomEvent);

    this.setHeight("300px");
    this.setWidth("100%");
  }

  public AceEditor(String height, String width) {
    super("value", "", false);
    super.addListener(AceBlurChanged.class, this::updateEditor);
    super.addListener(AceForceSyncDomEvent.class, this::onForceSyncDomEvent);

    this.setHeight(height);
    this.setWidth(width);
  }

  public AceEditor(AceTheme theme, AceMode mode, String height, String width) {
    super("value", "", false);
    super.addListener(AceBlurChanged.class, this::updateEditor);
    super.addListener(AceForceSyncDomEvent.class, this::onForceSyncDomEvent);

    this.setTheme(theme);
    this.setMode(mode);
    this.setHeight(height);
    this.setWidth(width);
  }

  // Updates the Text and selection after the Blur event has been fired (Keyboard
  // lost focus)
  private void updateEditor(AceBlurChanged event) {
    this.selection = event.getSelection();
    this.cursorPosition = event.getCursorPosition();
    this.setValue(event.getValue());
  }

  // Keeps the editor up to date and is backwards compatible
  private void onForceSyncDomEvent(AceForceSyncDomEvent event) {
    this.selection = event.getSelection();
    this.cursorPosition = event.getCursorPosition();
    this.setValue(event.getValue());

    this.fireEvent(
        new AceForceSyncEvent(
            event.getSource(),
            event.isFromClient(),
            event.getValue(),
            event.getSelection(),
            event.getCursorPosition()));
  }

  /**
   * Sets the mode (language) of the editor.
   *
   * @param mode {@link AceMode}
   */
  public void setMode(AceMode mode) {
    this.getElement().setProperty("mode", mode.toString());
    this.mode = mode;
  }

  /**
   * Returns the current mode of the editor.
   *
   * @return {@link AceMode}
   */
  public AceMode getMode() {
    return this.mode;
  }

  /**
   * Sets the theme (style) of the editor.
   *
   * @param theme {@link AceTheme}
   */
  public void setTheme(AceTheme theme) {
    this.getElement().setProperty("theme", theme.toString());
    this.theme = theme;
  }

  /**
   * Returns the current theme of the editor.
   *
   * @return {@link AceTheme}
   */
  public AceTheme getTheme() {
    return this.theme;
  }

  /**
   * Sets the baseUrl for the editor. BaseUrl contains the path to the themes and modes. If you want
   * to add your own custom modes/themes make sure to place them under
   * <b>src/main/resources/META-INF/resources</b> and make sure to start your BaseUrl with
   * ace-builds/src-min-noconflict/
   *
   * @param baseUrl {@link String}
   */
  public void setBaseUrl(String baseUrl) {
    this.getElement().setProperty("baseUrl", baseUrl);
    this.baseUrl = baseUrl;
  }

  /**
   * Returns the current set baseUrl.
   *
   * @return {@link String}
   */
  public String getBaseUrl() {
    return this.baseUrl;
  }

  /** Clears the value of the editor. */
  @Override
  public void clear() {
    this.getElement().setProperty("value", "");
  }

  /**
   * Sets the value of the editor.
   *
   * @param value {@link String}
   */
  @Override
  public void setValue(String value) {
    this.getElement().setProperty("value", value);
  }

  /**
   * Sets font-size for the editor in pixels.
   *
   * @param value int
   */
  public void setFontSize(int value) {
    this.getElement().setProperty("fontSize", value);
    this.fontSize = value;
  }

  /**
   * Returns the current set font-size of the editor in pixels.
   *
   * @return int
   */
  public int getFontSize() {
    return this.fontSize;
  }

  /**
   * Sets softtabs for the editor.
   *
   * @param value boolean
   */
  public void setSofttabs(boolean value) {
    this.getElement().setProperty("softtabs", value);
    this.softTabs = value;
  }

  /**
   * Returns if softtabs are currently enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isSofttabs() {
    return this.softTabs;
  }

  /**
   * Sets tab-size for the editor.
   *
   * @param value int
   */
  public void setTabSize(int value) {
    this.getElement().setProperty("tabSize", String.valueOf(value));
    this.tabSize = value;
  }

  /**
   * Returns the current set tab-size for the editor.
   *
   * @return int
   */
  public int getTabSize() {
    return this.tabSize;
  }

  /**
   * Sets wrap for the editor.
   *
   * @param wrap boolean
   */
  public void setWrap(boolean wrap) {
    this.getElement().setProperty("wrap", wrap);
    this.wrap = wrap;
  }

  /**
   * Returns if wrap is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isWrap() {
    return this.wrap;
  }

  /**
   * Sets AutoComplete for the editor.
   *
   * @param value boolean
   */
  public void setAutoComplete(boolean value) {
    this.getElement().setProperty("enableAutocompletion", value);
    this.autoComplete = value;
  }

  /**
   * Returns if autocomplete is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isAutoComplete() {
    return this.autoComplete;
  }

  /**
   * Sets initialFocus for the editor.
   *
   * @param value boolean
   */
  public void setInitialFocus(boolean value) {
    this.getElement().setProperty("initialFocus", value);
    this.initialFocus = value;
  }

  /**
   * Returns if initial focus is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isInitialFocus() {
    return this.initialFocus;
  }

  /**
   * Sets placeholder for the editor.
   *
   * @param value {@link String}
   */
  public void setPlaceholder(String value) {
    this.getElement().setProperty("placeholder", value);
    this.placeHolder = value;
  }

  /**
   * Returns the placeholder set for the editor.
   *
   * @return {@link String}
   */
  public String getPlaceholder() {
    return this.placeHolder;
  }

  /**
   * Sets readOnly for the editor.
   *
   * @param value boolean
   */
  @Override
  public void setReadOnly(boolean value) {
    this.getElement().setProperty("readonly", value);
    this.readOnly = value;
  }

  /**
   * Returns if readOnly is enabled/disabled for the editor.
   *
   * @return boolean
   */
  @Override
  public boolean isReadOnly() {
    return this.readOnly;
  }

  /**
   * Sets showPrintMargin for the editor.
   *
   * @param value boolean
   */
  public void setShowPrintMargin(boolean value) {
    this.getElement().setProperty("showPrintMargin", value);
    this.printMargin = value;
  }

  /**
   * Returns if showPrintMargin is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isShowPrintMargin() {
    return this.printMargin;
  }

  /**
   * Sets showInvisibles for the editor.
   *
   * @param value boolean
   */
  public void setShowInvisibles(boolean value) {
    this.getElement().setProperty("showInvisibles", value);
    this.showInvisibles = value;
  }

  /**
   * Returns if showInvisibles is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isShowInvisibles() {
    return this.showInvisibles;
  }

  /**
   * Sets showGutter for the editor.
   *
   * @param value boolean
   */
  public void setShowGutter(boolean value) {
    this.getElement().setProperty("showGutter", value);
    this.showGutter = value;
  }

  /**
   * Returns if showGutter is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isShowGutter() {
    return this.showGutter;
  }

  /**
   * Sets highlightActiveLine for the editor.
   *
   * @param value boolean
   */
  public void setHighlightActiveLine(boolean value) {
    this.getElement().setProperty("highlightActiveLine", value);
    this.hightlightActiveLine = value;
  }

  /**
   * Returns if hightlightActiveLine is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isHightlightActiveLine() {
    return this.hightlightActiveLine;
  }

  /**
   * Sets displayIndentGuides for the editor.
   *
   * @param value boolean
   */
  public void setDisplayIndentGuides(boolean value) {
    this.getElement().setProperty("displayIndentGuides", value);
    this.displayIndentGuides = value;
  }

  /**
   * Returns if displayIndentGuides is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isDisplayIndentGuides() {
    return this.displayIndentGuides;
  }

  /**
   * Sets highlightSelectedWord for the editor.
   *
   * @param value boolean
   */
  public void setHighlightSelectedWord(boolean value) {
    this.getElement().setProperty("highlightSelectedWord", value);
    this.highlightSelectedWord = value;
  }

  /**
   * Returns if hightlightSelectedWord is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isHightlightSelectedWord() {
    return this.highlightSelectedWord;
  }

  /**
   * Sets the selection of the editor.
   *
   * @param startRow int
   * @param startColumn int
   * @param endRow int
   * @param endColumn int
   */
  public void setSelection(int startRow, int startColumn, int endRow, int endColumn) {
    startRow = Math.abs(startRow);
    startColumn = Math.abs(startColumn);
    endRow = Math.abs(endRow);
    endColumn = Math.abs(endColumn);
    if (endColumn < startColumn) {
      int tmp = startColumn;
      startColumn = endColumn;
      endColumn = tmp;
    }
    if (endRow < startRow) {
      int tmp = startRow;
      startRow = endRow;
      endRow = tmp;
    }

    String json = AceJSON.generateSelectionJSON(startRow, startColumn, endRow, endColumn);
    this.getElement().callJsFunction("setSelection", json);
  }

  /**
   * Sets the selection of the editor, it can be focused on demand.
   *
   * @param startRow int
   * @param startColumn int
   * @param endRow int
   * @param endColumn int
   * @param focus boolean
   */
  public void setSelection(
      int startRow, int startColumn, int endRow, int endColumn, boolean focus) {
    this.setSelection(startRow, startColumn, endRow, endColumn);
    if (focus) {
      this.focus();
    }
  }

  /**
   * Sets the selection of the editor under use of two indices.
   *
   * @param startIndex int
   * @param endIndex int
   */
  public void setSelection(int startIndex, int endIndex) {
    if (endIndex < startIndex) {
      int tmp = startIndex;
      startIndex = endIndex;
      endIndex = tmp;
    }
    this.getElement().callJsFunction("calculateSelectionByIndices", startIndex, endIndex);
  }

  /**
   * Returns an {@link AceSelection} which contains the current selection values.
   *
   * @return {@link AceSelection}
   */
  public AceSelection getSelection() {
    return this.selection;
  }

  /**
   * Sets useWorker for the editor.
   *
   * @param value boolean
   */
  public void setUseWorker(boolean value) {
    this.getElement().setProperty("useWorker", value);
    this.useWorker = value;
  }

  /**
   * Returns if useWorker is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isUseWorker() {
    return this.useWorker;
  }

  /**
   * Sets cursorPosition for the editor.
   *
   * @param row int
   * @param column int
   */
  public void setCursorPosition(int row, int column) {
    row = Math.abs(row);
    column = Math.abs(column);

    String json = AceJSON.generateCursorPositionJSON(row, column);
    this.getElement().callJsFunction("setCursorPosition", json);
  }

  /**
   * Sets cursorPosition for the editor and optionally also sets the focus.
   *
   * @param row int
   * @param column int
   * @param focus boolean
   */
  public void setCursorPosition(int row, int column, boolean focus) {
    this.setCursorPosition(row, column);
    if (focus) {
      this.focus();
    }
  }

  /**
   * Sets the cursor position via index and the frontend calculates the row for it.
   *
   * @param index int
   */
  public void setCursorPosition(int index) {
    this.getElement().callJsFunction("calculateCursorPositionFromIndex", index);
  }

  /**
   * Sets the cursor position via index and the frontend calculates the row for it and sets the
   * focus.
   *
   * @param index int
   * @param focus boolean
   */
  public void setCursorPosition(int index, boolean focus) {
    this.setCursorPosition(index);
    if (focus) {
      this.focus();
    }
  }

  /**
   * Returns an {@link AceCursorPosition} which contains the current cursor position values.
   *
   * @return {@link AceCursorPosition}
   */
  public AceCursorPosition getCursorPosition() {
    return this.cursorPosition;
  }

  /**
   * Sets liveAutocompletion for the editor.
   *
   * @param value boolean
   */
  public void setLiveAutocompletion(boolean value) {
    this.getElement().setProperty("enableLiveAutocompletion", value);
    this.liveAutocompletion = value;
  }

  /**
   * Returns if live autocompletion is enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isLiveAutocompletion() {
    return this.liveAutocompletion;
  }

  /**
   * Sets enableSnippets for the editor.
   *
   * @param value boolean
   */
  public void setEnableSnippets(boolean value) {
    this.getElement().setProperty("enableSnippets", value);
    this.enableSnippets = value;
  }

  /**
   * Returns if snippets are enabled/disabled for the editor.
   *
   * @return boolean
   */
  public boolean isEnableSnippets() {
    return this.enableSnippets;
  }

  /**
   * Sets a custom autocompletion list for the editor.
   *
   * @param wordList String[]
   */
  public void setCustomAutoCompletion(String[] wordList) {
    if (wordList.length == 0) {
      this.customAutoCompletion = new String[0];
      return;
    }
    this.getElement()
        .setProperty("customAutoCompletion", "|" + String.join(",", wordList) + "|" + false);
    this.customAutoCompletion = wordList;
  }

  /**
   * Sets a custom autocompletion list for the editor and optionally keeps the current completers.
   *
   * @param wordList String[]
   * @param keepCurrentCompleters boolean
   */
  public void setCustomAutoCompletion(String[] wordList, boolean keepCurrentCompleters) {
    if (wordList.length == 0) {
      this.customAutoCompletion = new String[0];
      return;
    }
    this.getElement()
        .setProperty(
            "customAutoCompletion", "|" + String.join(",", wordList) + "|" + keepCurrentCompleters);
    this.customAutoCompletion = wordList;
  }

  /**
   * Sets a custom autocompletion list for the editor and sets the category aswell (default: "").
   *
   * @param wordList String[]
   * @param category {@link String}
   */
  public void setCustomAutoCompletion(String[] wordList, String category) {
    if (wordList.length == 0) {
      this.customAutoCompletion = new String[0];
      return;
    }
    this.getElement()
        .setProperty("customAutoCompletion", category + "|" + String.join(",", wordList));
    this.customAutoCompletion = wordList;
  }

  /**
   * Sets a custom autocompletion list for the editor, sets the category (default: "") and
   * optionally keeps the current completers.
   *
   * @param wordList String[]
   * @param category {@link String}
   * @param keepCurrentCompleters boolean
   */
  public void setCustomAutoCompletion(
      String[] wordList, String category, boolean keepCurrentCompleters) {
    if (wordList.length == 0) {
      this.customAutoCompletion = new String[0];
      return;
    }
    this.getElement()
        .setProperty(
            "customAutoCompletion",
            category + "|" + String.join(",", wordList) + "|" + keepCurrentCompleters);
    this.customAutoCompletion = wordList;
  }

  /**
   * Returns a String array of the current custom autocompletion for the editor.
   *
   * @return String[]
   */
  public String[] getCustomAutoCompletion() {
    return this.customAutoCompletion;
  }

  /**
   * Removes the custom autocompletion list set with setCustomAutoCompletiton() and replaces it with
   * the default one.
   */
  public void disableCustomAutoCompletion() {
    this.getElement().setProperty("customAutoCompletion", "||");
  }

  /**
   * Adds text to a specific position in the editor.
   *
   * @param row int
   * @param column int
   * @param text {@link String}
   */
  public void addTextAtPosition(int row, int column, String text) {
    this.getElement().callJsFunction("insertText", row, column, text);
  }

  /**
   * Adds text at the current cursor position in the editor.
   *
   * @param text {@link String}
   */
  public void addTextAtCurrentPosition(String text) {
    this.addTextAtPosition(this.cursorPosition.getRow(), this.cursorPosition.getColumn(), text);
  }

  /**
   * Adds a marker to the editor at the current selection. If the returned {@link AceMarker} is
   * null, there is no current selection. Use {@link #addMarkerAtSelection(int, int, int, int,
   * AceMarkerColor)} or {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor, String)}
   * instead. If the marker is not visible make sure that {@link #setHighlightActiveLine(boolean)}
   * and {@link #setHighlightSelectedWord(boolean)} are set to false.
   *
   * @param color {@link AceMarkerColor}
   * @return {@link AceMarker}
   */
  public AceMarker addMarkerAtCurrentSelection(AceMarkerColor color) {
    if (!this.selection.compareTo(new AceSelection())) {
      AceSelection currentSelection = this.selection;

      AceMarker marker =
          new AceMarker(
              currentSelection.getStartRow(),
              currentSelection.getStartColumn(),
              currentSelection.getEndRow(),
              currentSelection.getEndColumn(),
              color);

      this.getElement()
          .setProperty(
              "marker",
              marker.getRowStart()
                  + "|"
                  + marker.getFrom()
                  + "|"
                  + marker.getRowEnd()
                  + "|"
                  + marker.getTo()
                  + "|"
                  + marker.getAceMarkerColor().toString()
                  + "|"
                  + marker.getID());
      this.markers.add(marker);
      return marker;
    }
    return null;
  }

  /**
   * Adds a marker to the editor at the current selection. If the returned {@link AceMarker} is
   * null, there is no current selection. Use {@link #addMarkerAtSelection(int, int, int, int,
   * AceMarkerColor)} or {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor, String)}
   * instead. If the marker is not visible make sure that {@link #setHighlightActiveLine(boolean)}
   * and {@link #setHighlightSelectedWord(boolean)} are set to false.
   *
   * @param color {@link AceMarkerColor}
   * @param alias {@link String}
   * @return {@link AceMarker}
   */
  public AceMarker addMarkerAtCurrentSelection(AceMarkerColor color, String alias) {
    if (!this.selection.compareTo(new AceSelection())) {
      AceSelection currentSelection = this.selection;

      AceMarker marker =
          new AceMarker(
              currentSelection.getStartRow(),
              currentSelection.getStartColumn(),
              currentSelection.getEndRow(),
              currentSelection.getEndColumn(),
              color,
              alias);

      this.getElement()
          .setProperty(
              "marker",
              marker.getRowStart()
                  + "|"
                  + marker.getFrom()
                  + "|"
                  + marker.getRowEnd()
                  + "|"
                  + marker.getTo()
                  + "|"
                  + marker.getAceMarkerColor().toString()
                  + "|"
                  + marker.getID());
      this.markers.add(marker);
      return marker;
    }
    return null;
  }

  /**
   * Adds a marker to the editor. If the marker is not visible make sure that {@link
   * #setHighlightActiveLine(boolean)} and {@link #setHighlightSelectedWord(boolean)} are set to
   * false.
   *
   * @param rowStart int
   * @param from int
   * @param rowEnd int
   * @param to int
   * @param color {@link AceMarkerColor}
   * @return {@link AceMarker}
   */
  public AceMarker addMarkerAtSelection(
      int rowStart, int from, int rowEnd, int to, AceMarkerColor color) {
    rowStart = Math.abs(rowStart);
    from = Math.abs(from);
    rowEnd = Math.abs(rowEnd);
    to = Math.abs(to);
    if (to < from) {
      int tmp = from;
      from = to;
      to = tmp;
    }
    if (rowEnd < rowStart) {
      int tmp = rowStart;
      rowStart = rowEnd;
      rowEnd = tmp;
    }

    AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color);

    this.getElement()
        .setProperty(
            "marker",
            marker.getRowStart()
                + "|"
                + marker.getFrom()
                + "|"
                + marker.getRowEnd()
                + "|"
                + marker.getTo()
                + "|"
                + marker.getAceMarkerColor().toString()
                + "|"
                + marker.getID());
    this.markers.add(marker);
    return marker;
  }

  /**
   * Adds a marker to the editor. If the marker is not visible make sure that {@link
   * #setHighlightActiveLine(boolean)} and {@link #setHighlightSelectedWord(boolean)} are set to
   * false.
   *
   * @param rowStart int
   * @param from int
   * @param rowEnd int
   * @param to int
   * @param color {@link AceMarkerColor}
   * @param alias {@link String}
   * @return {@link AceMarker}
   */
  public AceMarker addMarkerAtSelection(
      int rowStart, int from, int rowEnd, int to, AceMarkerColor color, String alias) {
    rowStart = Math.abs(rowStart);
    from = Math.abs(from);
    rowEnd = Math.abs(rowEnd);
    to = Math.abs(to);
    if (to < from) {
      int tmp = from;
      from = to;
      to = tmp;
    }
    if (rowEnd < rowStart) {
      int tmp = rowStart;
      rowStart = rowEnd;
      rowEnd = tmp;
    }

    AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color, alias);

    this.getElement()
        .setProperty(
            "marker",
            marker.getRowStart()
                + "|"
                + marker.getFrom()
                + "|"
                + marker.getRowEnd()
                + "|"
                + marker.getTo()
                + "|"
                + marker.getAceMarkerColor().toString()
                + "|"
                + marker.getID());
    this.markers.add(marker);
    return marker;
  }

  /**
   * Adds a {@link AceMarker} to the editor.
   *
   * @param marker {@link AceMarker}
   */
  public void addMarker(AceMarker marker) {
    this.getElement()
        .setProperty(
            "marker",
            marker.getRowStart()
                + "|"
                + marker.getFrom()
                + "|"
                + marker.getRowEnd()
                + "|"
                + marker.getTo()
                + "|"
                + marker.getAceMarkerColor().toString()
                + "|"
                + marker.getID());
    this.markers.add(marker);
  }

  /**
   * Returns a list of all current active markers. If the list is empty, no marker is set at the
   * moment.
   *
   * @return {@link List}
   */
  public List<AceMarker> getAllMarkers() {
    return this.markers;
  }

  /**
   * Removes a specific marker from the editor.<br>
   * <br>
   * You can get all the active markers with {@link #getAllMarkers()}
   *
   * @param marker {@link AceMarker}
   */
  public void removeMarker(AceMarker marker) {
    for (AceMarker mar : this.markers) {
      if (mar.getID().equals(marker.getID())) {
        this.getElement().setProperty("rmMarker", marker.getID());
        this.markers.remove(marker);
      }
    }
  }

  /**
   * Removes a specific marker from the editor by id.<br>
   * <br>
   * You can get all the active markers with {@link #getAllMarkers()}.
   *
   * @param id {@link String}
   */
  public void removeMarkerByID(String id) {
    for (AceMarker mar : this.markers) {
      if (mar.getID().equals(id)) {
        this.getElement().setProperty("rmMarker", id);
        this.markers.remove(mar);
      }
    }
  }

  /**
   * Removes a specific marker from the editor by alias.<br>
   * <br>
   * You can get all the active markers with {@link #getAllMarkers()}.
   *
   * @param alias {@link String}
   */
  public void removeMarkerByAlias(String alias) {
    for (AceMarker mar : this.markers) {
      if (mar.getAlias().equals(alias)) {
        this.getElement().setProperty("rmMarker", mar.getID());
        this.markers.remove(mar);
      }
    }
  }

  /** Removes every marker from the editor. */
  public void removeAllMarkers() {
    this.getElement().setProperty("rmMarker", "all" + UUID.randomUUID().toString());
    this.markers = new ArrayList<AceMarker>();
  }

  /**
   * Adds a listener to the editor, which listens to when selection in the editor changes. Check
   * {@link AceSelectionChanged} for all returend values.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addSelectionChangeListener(
      ComponentEventListener<AceSelectionChanged> listener) {
    return this.addListener(AceSelectionChanged.class, listener);
  }

  /**
   * Forces a sync between client and server without blurring. Use {@link
   * #addSyncCompletedListener(ComponentEventListener)} to get the values after the sync completed.
   */
  public void sync() {
    this.getElement().callJsFunction("forceSync");
  }

  /**
   * Add a listener to the editor, which listens to when the sync event completed, which is
   * triggered by the {@link #sync()} method. Check {@link AceForceSyncEvent} for all availible
   * returned values.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addSyncCompletedListener(ComponentEventListener<AceForceSyncEvent> listener) {
    return this.addListener(AceForceSyncEvent.class, listener);
  }

  /**
   * Generates a fully functional static HTML-String with the current value of the editor, which can
   * be used to print for example. Use {@link #addHTMLGeneratedListener(ComponentEventListener)} to
   * get the HTML as soon as the generate is done.
   *
   * @param raw boolean
   */
  public void generateHTML(boolean raw) {
    this.getElement().callJsFunction("generateHTML", raw);
  }

  /**
   * Add a listener to the editor, which listens to when the HTML generate completed, which is
   * triggered by the {@link #generateHTML(boolean)} method. Check {@link AceHTMLGeneratedEvent} for
   * all availible returned values.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addHTMLGeneratedListener(
      ComponentEventListener<AceHTMLGeneratedEvent> listener) {
    return this.addListener(AceHTMLGeneratedEvent.class, listener);
  }

  /**
   * Add a listener to the editor, which listens to when the value is changed The event is sent for
   * every change in the editor, it could send a lot of data.
   *
   * <p>Check {@link AceChanged} for all available returned values.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addAceChangedListener(ComponentEventListener<AceChanged> listener) {
    return this.addListener(AceChanged.class, listener);
  }

  /**
   * Replaces the current selection with given text.
   *
   * @param text {@link String}
   */
  public void replaceTextAtCurrentSelection(String text) {
    if (!this.selection.compareTo(new AceSelection())) {
      if (text == null) {
        text = "";
      }
      this.getElement().callJsFunction("replaceTextAtSelection", text);
    }
  }

  /** Focuses the textarea of the ace editor. */
  @Override
  public void focus() {
    this.getElement().callJsFunction("focusEditor");
  }

  /**
   * Run an action after the sync has completed to ensure the values you want to use are up to date
   * with the server.
   *
   * @param action {@link Runnable}
   */
  public void runAfterSync(Runnable action) {
    Objects.requireNonNull(action);
    this.addListener(AceForceSyncDomEvent.class, event -> this.runAfterSync(event, action));
    this.sync();
  }

  // unregister event and run action (private)
  private void runAfterSync(AceForceSyncDomEvent event, Runnable action) {
    event.unregisterListener();
    action.run();
  }

  /**
   * @deprecated As of release 2.0.0, please use {@link #getSelection()} and {@link
   *     AceSelection#getSelectedText()}
   * @return {@link String}
   */
  @Deprecated
  public String getSelectedText() {
    return this.getSelection().getSelectedText();
  }

  /**
   * Folds all fold marker in the editor. (Created automatically when an if is added for example).
   */
  public void foldAll() {
    this.getElement().callJsFunction("foldAll");
  }

  /**
   * Folds all fold marker in the editor from a specific line downwards. (Created automatically when
   * an if is added for example).
   *
   * @param startRow int
   */
  public void foldAll(int startRow) {
    this.getElement().callJsFunction("foldAll", startRow);
  }

  /** Unfolds all fold marker in the editor. */
  public void unfold() {
    this.getElement().callJsFunction("unfold");
  }

  /** Beautifies the current code. */
  public void beautify() {
    this.getElement().callJsFunction("beautify");
  }

  /**
   * Enables the statusbar which is displayed at the bottom right of the ace editor to let the user
   * see current cursor position and selection.
   *
   * @param statusbarEnabled boolean
   */
  public void setStatusbarEnabled(boolean statusbarEnabled) {
    this.getElement().setProperty("statusbarEnabled", statusbarEnabled);
    this.statusbarEnabled = statusbarEnabled;
  }

  /**
   * Returns if the statusbar is enabled or not.
   *
   * @return boolean
   */
  public boolean isStatusbarEnabled() {
    return this.statusbarEnabled;
  }

  /**
   * Set the css text color of the statusbar.
   *
   * @param textColor {@link String}
   */
  public void setStatusbarTextColor(String textColor) {
    this.getElement().getStyle().set("--las-color", textColor);
  }

  /**
   * Set the css background color of the statusbar.
   *
   * @param backgroundColor {@link String}
   */
  public void setStatusbarBackgroundColor(String backgroundColor) {
    this.getElement().getStyle().set("--las-background-color", backgroundColor);
  }

  /**
   * Set the css border radius of the statusbar.
   *
   * @param borderRadius double
   */
  public void setStatusbarBorderRadius(String borderRadius) {
    this.getElement().getStyle().set("--las-border-radius", borderRadius);
  }

  /**
   * Add a listener to the editor, which listens to when the editor is fully initialized, so every
   * operation you try to do has an existing editor.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addAceReadyListener(ComponentEventListener<AceReady> listener) {
    return this.addListener(AceReady.class, listener);
  }

  /**
   * Adds a dynamic autocompletion to the editor's autocompletion.
   *
   * @param map {@link Map}
   * @param seperator {@link String}
   */
  public void addDynamicAutocompletion(Map<String, List<String>> map, String seperator) {
    String json = AceJSON.generateDynamicAutocompletionJSON(map, seperator);
    this.getElement().setProperty("dynamicAutocompletion", json);
  }

  /**
   * Adds a dynamic autocompletion to the editor's autocompletion.
   *
   * @param map {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   */
  public void addDynamicAutocompletion(
      Map<String, List<String>> map, String seperator, String category) {
    String json = AceJSON.generateDynamicAutocompletionJSON(map, seperator, category);
    this.getElement().setProperty("dynamicAutocompletion", json);
  }

  /**
   * Adds a dynamic autocompletion to the editor's autocompletion.
   *
   * @param map {@link Map}
   * @param seperator {@link String}
   * @param keepCompleters boolean
   */
  public void addDynamicAutocompletion(
      Map<String, List<String>> map, String seperator, boolean keepCompleters) {
    String json = AceJSON.generateDynamicAutocompletionJSON(map, seperator, keepCompleters);
    this.getElement().setProperty("dynamicAutocompletion", json);
  }

  /**
   * Adds a dynamic autocompletion to the editor's autocompletion.
   *
   * @param map {@link Map}
   * @param seperator {@link String}
   * @param category {@link String}
   * @param keepCompleters boolean
   */
  public void addDynamicAutocompletion(
      Map<String, List<String>> map, String seperator, String category, boolean keepCompleters) {
    String json =
        AceJSON.generateDynamicAutocompletionJSON(map, seperator, category, keepCompleters);
    this.getElement().setProperty("dynamicAutocompletion", json);
  }

  /** Open the autocompletion dialog programatically. */
  public void openAutocompletion() {
    this.getElement().callJsFunction("openAutocompletion");
  }
}
