package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import de.f0rce.ace.AceEditor;

/** @author David "F0rce" Dodlek */
@SuppressWarnings("serial")
@DomEvent("html-generated")
public class AceHTMLGeneratedEvent extends ComponentEvent<AceEditor> {

  private String html;

  public AceHTMLGeneratedEvent(
      AceEditor source, boolean fromClient, @EventData("event.detail.html") String html) {
    super(source, fromClient);
    this.html = html;
  }

  /**
   * Returns a fully functional static HTML-String with the current value of the editor, which can
   * be used to print for example.
   *
   * @return {@link String}
   */
  public String getHTML() {
    return this.html;
  }
}
