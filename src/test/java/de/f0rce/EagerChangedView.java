package de.f0rce;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Route("eager")
public class EagerChangedView extends VerticalLayout {

	public EagerChangedView() {

		AceEditor aceEditor = new AceEditor();
		aceEditor.setInitialFocus(true);
		aceEditor.setMinHeight("300px");

		aceEditor.addAceChangedListener(event -> {
			Notification.show("Ace-Editor Changed: " + event.getValue());
		});
		add(aceEditor);
	}
}
