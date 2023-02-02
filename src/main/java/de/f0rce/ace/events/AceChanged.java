package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import de.f0rce.ace.AceEditor;

/** @author Jean-Christophe "jcgueriaud1" Gueriaud */
@DomEvent("editor-change")
public class AceChanged extends ComponentEvent<AceEditor> {
  private static final long serialVersionUID = -7037867260485774128L;
  
  private String value;

  public AceChanged(
      AceEditor source, boolean fromClient, @EventData("event.detail.value") String value) {
    super(source, fromClient);
    this.value = value;
  }

  /**
   * Returns the current value of the editor.
   *
   * @return {@link String}
   */
  public String getValue() {
    return this.value;
  }
}
