package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import de.f0rce.ace.AceEditor;

/** @author David "F0rce" Dodlek */
public class AceValueChanged extends ComponentEvent<AceEditor> {
  private static final long serialVersionUID = -6041775847653361784L;
  
  private String value;

  public AceValueChanged(AceEditor source, boolean fromClient, String value) {
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
