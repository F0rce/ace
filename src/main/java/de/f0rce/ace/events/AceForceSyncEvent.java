package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.util.AceCursorPosition;
import de.f0rce.ace.util.AceSelection;

/** @author David "F0rce" Dodlek */
public class AceForceSyncEvent extends ComponentEvent<AceEditor> {
  private static final long serialVersionUID = -1096750808791222529L;
  
  private String value;
  private AceSelection selection;
  private AceCursorPosition cursorPosition;

  public AceForceSyncEvent(
      AceEditor source,
      boolean fromClient,
      String value,
      AceSelection selection,
      AceCursorPosition cursorPosition) {
    super(source, fromClient);
    this.value = value;
    this.selection = selection;
    this.cursorPosition = cursorPosition;
  }

  /**
   * Returns the current value of the editor.
   *
   * @return {@link String}
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Returns the current selection of the editor.
   *
   * @return {@link AceSelection}
   */
  public AceSelection getSelection() {
    return this.selection;
  }

  /**
   * Returns the current cursor position of the editor.
   *
   * @return {@link AceCursorPosition}
   */
  public AceCursorPosition getCursorPosition() {
    return this.cursorPosition;
  }
}
