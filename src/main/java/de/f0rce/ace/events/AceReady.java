package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

import de.f0rce.ace.AceEditor;

/** @author David "F0rce" Dodlek */
@SuppressWarnings("serial")
@DomEvent("editor-ready")
public class AceReady extends ComponentEvent<AceEditor> {

  public AceReady(AceEditor source, boolean fromClient) {
    super(source, fromClient);
  }
}
