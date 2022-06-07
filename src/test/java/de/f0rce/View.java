package de.f0rce;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceExportType;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
    aceEditor.setLiveAutocompletion(true);
    aceEditor.setHighlightActiveLine(false);
    aceEditor.setHighlightSelectedWord(false);
    aceEditor.setStatusbarEnabled(true);
    aceEditor.setPlaceholder("DIES IST EIN TEST OB DER NEUE PLACEHOLDER FUNKTIONIERT");
    aceEditor.setMinHeight("300px");
    aceEditor.setMode(AceMode.java);
    aceEditor.setStatusbarTextColor("red");

    // aceEditor.setCustomAutoCompletion(new String[] {"search(", "test"}, "methods", false);
    ArrayList<String> list = new ArrayList<>();
    list.add("encoding");
    list.add("working");
    ArrayList<String> l2 = new ArrayList<>();
    l2.add("data");
    l2.add("json");
    ArrayList<String> l3 = new ArrayList<>();
    l3.add("drinking");
    l3.add("eating");
    l3.add("walking");
    l3.add("driving");

    LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
    map.put("basic", list);
    map.put("asic", l2);
    map.put("tree", l2);
    map.put("going", l3);

    aceEditor.setValue(
        "\n\n\n\n\n\n\n"
            + "awdawdawdawdawdawd\n\n"
            + "awdawdawd\n"
            + "awdawd\n\n"
            + "af\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            + "test");

    ArrayList<String> custom = new ArrayList<String>();
    custom.add("JSON");
    custom.add("ist");
    custom.add("eine");
    custom.add("coole");
    custom.add("Sache");

    aceEditor.setCustomAutocompletion(custom, "JSONTest", true);

    // aceEditor.addCustomAutocompletion(l3, "food", true);
    aceEditor.addDynamicAutocompletion(map, ".", "not dynmaic", true);

    aceEditor.addFocusListener(
        evt -> {
          // aceEditor.openAutocompletion();
        });

    // aceEditor.setCustomAutoCompletion(str);

    aceEditor.addSelectionChangeListener(
        event -> {
          System.out.println(
              event.getSelection().getStartColumn()
                  + " "
                  + event.getSelection().getEndColumn()
                  + " "
                  + event.getSelection().getStartRow()
                  + " "
                  + event.getSelection().getEndRow());
          System.out.println(
              event.getCursorPosition().getColumn() + " " + event.getCursorPosition().getRow());
        });
    //
    //		aceEditor.addFocusListener(e -> {
    //		 System.out.println("Focus");
    //		});

    themesComboBox.setValue(aceEditor.getTheme());
    modesComboBox.setValue(aceEditor.getMode());

    aceEditor.setEnableSnippets(true);

    Button button = new Button("demo");
    Button button2 = new Button("rm custom");
    Button button3 = new Button("ok");
    Checkbox cb = new Checkbox();
    cb.setLabel("Enable Snippets");

    aceLayout.add(layoutComboBoxes, aceEditor, button, button2, button3, cb);

    aceLayout.expand(aceEditor);
    contextMenu.setTarget(aceEditor);

    Label lbl = new Label("Version 1.3.1");

    contextMenu.addItem("Version 1.3.1");

    themesComboBox.addValueChangeListener(
        event -> {
          if (!event.isFromClient()) {
            return;
          }

          if (event.getValue() != null) {
            aceEditor.setTheme(event.getValue());
          }
        });

    modesComboBox.addValueChangeListener(
        event -> {
          if (event.getValue() != null) {
            aceEditor.setMode(event.getValue());
          }
        });

    cb.addValueChangeListener(
        evt -> {
          aceEditor.setEnableSnippets(evt.getValue());
        });

    button.addClickListener(event -> {});

    button2.addClickListener(
        event -> {
          aceEditor.disableCustomAutocompletion(false);
        });
    button3.addClickListener(
        event -> {
          aceEditor.generateHTML(AceExportType.RICH);
        });

    aceEditor.addHTMLGeneratedListener(
        event -> {
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

    grid.addItemDoubleClickListener(
        event -> {
          if (event.getItem().getAppname() == "Ace Editor") {
            aceDialog.open();
          }
        });
    this.add(grid);
  }
}
