package de.f0rce.ace.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import de.f0rce.ace.AceEditor;

@SuppressWarnings("serial")
@DomEvent("editor-change")
public class AceChanged extends ComponentEvent<AceEditor> {
	private String value;

	public AceChanged(AceEditor source, boolean fromClient, @EventData("event.detail.value") String value) {
		super(source, fromClient);
		this.value = value;
	}

	/**
	 * Returns the current set value for the editor.
	 * 
	 * @return {@link String}
	 */
	public String getValue() {
		return this.value;
	}
}