package de.f0rce;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@Route("")
public class View extends VerticalLayout {

	public View() {

		Dialog aceDialog = new Dialog();

		VerticalLayout aceLayout = new VerticalLayout();

		HorizontalLayout layoutComboBoxes = new HorizontalLayout();

		ContextMenu contextMenu = new ContextMenu();

		ComboBox<AceTheme> themesComboBox = new ComboBox<>();
		themesComboBox.setItems(AceTheme.values());
		themesComboBox.setLabel("Themes");

		ComboBox<AceMode> modesComboBox = new ComboBox<>();
		modesComboBox.setItems(AceMode.values());
		modesComboBox.setLabel("Modes");

		layoutComboBoxes.add(themesComboBox, modesComboBox);

		aceLayout.setSizeFull();

		AceEditor aceEditor = new AceEditor();
//		aceEditor.setTheme(AceTheme.github);
//		aceEditor.setMode(AceMode.java);
		// aceEditor.setFontSize(20);
		aceEditor.setInitialFocus(true);
		// aceEditor.setHeight("100%");
		// aceEditor.setWidth("100%");

//		aceEditor.setHeight("100%");
//		aceEditor.setReadOnly(false);
//		aceEditor.setBasePath();
//		aceEditor.setHighlightActiveLine(false);
//		aceEditor.setShowInvisibles(true);
//		aceEditor.setShowGutter(false);
//		aceEditor.setShowPrintMargin(false);
//		aceEditor.setDisplayIndentGuides(false);
//		aceEditor.setHighlightSelectedWord(false);
//		aceEditor.setUseWorker(false);
//		aceEditor.addValueChangeListener(e -> {
//			System.out.println(aceEditor.getValue());
//			System.out.println("*****");
//		});
//
//		aceEditor.setSofttabs(false);
//		aceEditor.setTabSize(25);
//		aceEditor.setWrap(false);
//		aceEditor.setMinlines(2);
//		aceEditor.setMaxlines(10);
//		aceEditor.setPlaceholder("DEMO");
		aceEditor.setAutoComplete(true);
		aceEditor.setHighlightActiveLine(false);
		aceEditor.setHighlightSelectedWord(false);
		aceEditor.setPlaceholder("DIES IST EIN TEST OB DER NEUE PLACEHOLDER FUNKTIONIERT");
		aceEditor.setMinHeight("300px");

		aceEditor.addSelectionChangeListener(event -> {
			System.out.println(event.getSelectionRowStart() + " " + event.getSelectionFrom() + " "
					+ event.getSelectionRowEnd() + " " + event.getSelectionTo());
			System.out.println(event.getCursorRow() + " " + event.getCursorColumn());
		});
//
//		aceEditor.addFocusListener(e -> {
//		 System.out.println("Focus");
//		});

		themesComboBox.setValue(aceEditor.getTheme());
		modesComboBox.setValue(aceEditor.getMode());

		Button button = new Button("demo");
		Button button2 = new Button("rm custom");
		Button button3 = new Button("ok");

		aceLayout.add(layoutComboBoxes, aceEditor, button, button2, button3);

		aceLayout.expand(aceEditor);
		contextMenu.setTarget(aceEditor);

		contextMenu.addItem("Test");

		themesComboBox.addValueChangeListener(event -> {
			if (!event.isFromClient())
				return;

			if (event.getValue() != null) {
				aceEditor.setTheme(event.getValue());
			}
		});

		modesComboBox.addValueChangeListener(event -> {
			if (event.getValue() != null) {
				aceEditor.setMode(event.getValue());
			}
		});

		button.addClickListener(event -> {
			aceEditor.foldAll();
		});
		button2.addClickListener(event -> {
			aceEditor.foldAll(3);
		});
		button3.addClickListener(event -> {
			aceEditor.unfold();
		});

		Shortcuts.addShortcutListener(aceEditor, event -> {
			aceEditor.addSyncCompletedListener(evt -> {
				// current cursor position
			});
			aceEditor.sync();

		}, Key.ARROW_RIGHT).listenOn(aceEditor);

		aceEditor.addHTMLGeneratedListener(event -> {
			System.out.println(event.getHTML());
		});

		aceDialog.setHeight("1000px");
		aceDialog.setWidth("2000px");
		aceDialog.setCloseOnEsc(true);
		aceDialog.setResizable(true);
		aceDialog.add(aceLayout);

		List<Apps> appList = new ArrayList<>();
		appList.add(new Apps("Ace Editor", "v1.0.0"));
		appList.add(new Apps("Test", "v1.0"));

		Grid<Apps> grid = new Grid<>(Apps.class);
		grid.setItems(appList);

		grid.setWidth("500px");

		grid.addItemDoubleClickListener(event -> {
			if (event.getItem().getAppname() == "Ace Editor") {
				aceDialog.open();
			}
		});
		add(grid);
	}
}
