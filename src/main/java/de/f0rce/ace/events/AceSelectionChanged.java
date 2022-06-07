package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.util.AceCursorPosition;
import de.f0rce.ace.util.AceSelection;
import elemental.json.JsonObject;

/** @author David "F0rce" Dodlek */
@SuppressWarnings("serial")
@DomEvent("editor-selection")
public class AceSelectionChanged extends ComponentEvent<AceEditor> {

  private AceSelection selection;
  private AceCursorPosition cursorPosition;

  public AceSelectionChanged(
      AceEditor source,
      boolean fromClient,
      @EventData("event.detail.selection") JsonObject selectionObject,
      @EventData("event.detail.cursorPosition") JsonObject cursorObject) {
    super(source, fromClient);
    this.selection = new AceSelection(selectionObject);
    this.cursorPosition = new AceCursorPosition(cursorObject);
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
