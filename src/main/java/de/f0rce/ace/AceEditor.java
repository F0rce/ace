package de.f0rce.ace;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

import de.f0rce.ace.enums.AceMarkerColor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;
import de.f0rce.ace.events.AceBlurChanged;
import de.f0rce.ace.events.AceForceSyncEvent;
import de.f0rce.ace.events.AceHTMLGeneratedEvent;
import de.f0rce.ace.events.AceSelectionChanged;
import de.f0rce.ace.util.AceMarker;

/**
 * @author David "F0rce" Dodlek
 */

@SuppressWarnings("serial")
@Tag("lit-ace")
@NpmPackage(value = "@f0rce/lit-ace", version = "1.3.0")
@JsModule("./ace-builds/src-min-noconflict/ext-beautify.js")
@JsModule("./ace-builds/src-min-noconflict/ext-code_lens.js")
@JsModule("./ace-builds/src-min-noconflict/ext-elastic_tabstops_lite.js")
@JsModule("./ace-builds/src-min-noconflict/ext-emmet.js")
@JsModule("./ace-builds/src-min-noconflict/ext-error_marker.js")
@JsModule("./ace-builds/src-min-noconflict/ext-keybinding_menu.js")
@JsModule("./ace-builds/src-min-noconflict/ext-language_tools.js")
@JsModule("./ace-builds/src-min-noconflict/ext-linking.js")
@JsModule("./ace-builds/src-min-noconflict/ext-modelist.js")
@JsModule("./ace-builds/src-min-noconflict/ext-options.js")
@JsModule("./ace-builds/src-min-noconflict/ext-prompt.js")
@JsModule("./ace-builds/src-min-noconflict/ext-rtl.js")
@JsModule("./ace-builds/src-min-noconflict/ext-searchbox.js")
@JsModule("./ace-builds/src-min-noconflict/ext-settings_menu.js")
@JsModule("./ace-builds/src-min-noconflict/ext-spellcheck.js")
@JsModule("./ace-builds/src-min-noconflict/ext-split.js")
@JsModule("./ace-builds/src-min-noconflict/ext-static_highlight.js")
@JsModule("./ace-builds/src-min-noconflict/ext-statusbar.js")
@JsModule("./ace-builds/src-min-noconflict/ext-textarea.js")
@JsModule("./ace-builds/src-min-noconflict/ext-themelist.js")
@JsModule("./ace-builds/src-min-noconflict/ext-whitespace.js")
@JsModule("./ace-builds/src-min-noconflict/keybinding-emacs.js")
@JsModule("./ace-builds/src-min-noconflict/keybinding-sublime.js")
@JsModule("./ace-builds/src-min-noconflict/keybinding-vim.js")
@JsModule("./ace-builds/src-min-noconflict/keybinding-vscode.js")
@JsModule("./ace-builds/src-min-noconflict/mode-abap.js")
@JsModule("./ace-builds/src-min-noconflict/mode-abc.js")
@JsModule("./ace-builds/src-min-noconflict/mode-actionscript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ada.js")
@JsModule("./ace-builds/src-min-noconflict/mode-alda.js")
@JsModule("./ace-builds/src-min-noconflict/mode-apache_conf.js")
@JsModule("./ace-builds/src-min-noconflict/mode-apex.js")
@JsModule("./ace-builds/src-min-noconflict/mode-applescript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-aql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-asciidoc.js")
@JsModule("./ace-builds/src-min-noconflict/mode-asl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-assembly_x86.js")
@JsModule("./ace-builds/src-min-noconflict/mode-autohotkey.js")
@JsModule("./ace-builds/src-min-noconflict/mode-batchfile.js")
@JsModule("./ace-builds/src-min-noconflict/mode-c9search.js")
@JsModule("./ace-builds/src-min-noconflict/mode-c_cpp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-cirru.js")
@JsModule("./ace-builds/src-min-noconflict/mode-clojure.js")
@JsModule("./ace-builds/src-min-noconflict/mode-cobol.js")
@JsModule("./ace-builds/src-min-noconflict/mode-coffee.js")
@JsModule("./ace-builds/src-min-noconflict/mode-coldfusion.js")
@JsModule("./ace-builds/src-min-noconflict/mode-crystal.js")
@JsModule("./ace-builds/src-min-noconflict/mode-csharp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-csound_document.js")
@JsModule("./ace-builds/src-min-noconflict/mode-csound_orchestra.js")
@JsModule("./ace-builds/src-min-noconflict/mode-csound_score.js")
@JsModule("./ace-builds/src-min-noconflict/mode-csp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-css.js")
@JsModule("./ace-builds/src-min-noconflict/mode-curly.js")
@JsModule("./ace-builds/src-min-noconflict/mode-dart.js")
@JsModule("./ace-builds/src-min-noconflict/mode-diff.js")
@JsModule("./ace-builds/src-min-noconflict/mode-django.js")
@JsModule("./ace-builds/src-min-noconflict/mode-d.js")
@JsModule("./ace-builds/src-min-noconflict/mode-dockerfile.js")
@JsModule("./ace-builds/src-min-noconflict/mode-dot.js")
@JsModule("./ace-builds/src-min-noconflict/mode-drools.js")
@JsModule("./ace-builds/src-min-noconflict/mode-edifact.js")
@JsModule("./ace-builds/src-min-noconflict/mode-eiffel.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ejs.js")
@JsModule("./ace-builds/src-min-noconflict/mode-elixir.js")
@JsModule("./ace-builds/src-min-noconflict/mode-elm.js")
@JsModule("./ace-builds/src-min-noconflict/mode-erlang.js")
@JsModule("./ace-builds/src-min-noconflict/mode-forth.js")
@JsModule("./ace-builds/src-min-noconflict/mode-fortran.js")
@JsModule("./ace-builds/src-min-noconflict/mode-fsharp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-fsl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ftl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-gcode.js")
@JsModule("./ace-builds/src-min-noconflict/mode-gherkin.js")
@JsModule("./ace-builds/src-min-noconflict/mode-gitignore.js")
@JsModule("./ace-builds/src-min-noconflict/mode-glsl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-gobstones.js")
@JsModule("./ace-builds/src-min-noconflict/mode-golang.js")
@JsModule("./ace-builds/src-min-noconflict/mode-graphqlschema.js")
@JsModule("./ace-builds/src-min-noconflict/mode-groovy.js")
@JsModule("./ace-builds/src-min-noconflict/mode-haml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-handlebars.js")
@JsModule("./ace-builds/src-min-noconflict/mode-haskell_cabal.js")
@JsModule("./ace-builds/src-min-noconflict/mode-haskell.js")
@JsModule("./ace-builds/src-min-noconflict/mode-haxe.js")
@JsModule("./ace-builds/src-min-noconflict/mode-hjson.js")
@JsModule("./ace-builds/src-min-noconflict/mode-html_elixir.js")
@JsModule("./ace-builds/src-min-noconflict/mode-html.js")
@JsModule("./ace-builds/src-min-noconflict/mode-html_ruby.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ini.js")
@JsModule("./ace-builds/src-min-noconflict/mode-io.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jack.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jade.js")
@JsModule("./ace-builds/src-min-noconflict/mode-java.js")
@JsModule("./ace-builds/src-min-noconflict/mode-javascript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-json5.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jsoniq.js")
@JsModule("./ace-builds/src-min-noconflict/mode-json.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jsp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jssm.js")
@JsModule("./ace-builds/src-min-noconflict/mode-jsx.js")
@JsModule("./ace-builds/src-min-noconflict/mode-julia.js")
@JsModule("./ace-builds/src-min-noconflict/mode-kotlin.js")
@JsModule("./ace-builds/src-min-noconflict/mode-latex.js")
@JsModule("./ace-builds/src-min-noconflict/mode-less.js")
@JsModule("./ace-builds/src-min-noconflict/mode-liquid.js")
@JsModule("./ace-builds/src-min-noconflict/mode-lisp.js")
@JsModule("./ace-builds/src-min-noconflict/mode-livescript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-logiql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-logtalk.js")
@JsModule("./ace-builds/src-min-noconflict/mode-lsl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-lua.js")
@JsModule("./ace-builds/src-min-noconflict/mode-luapage.js")
@JsModule("./ace-builds/src-min-noconflict/mode-lucene.js")
@JsModule("./ace-builds/src-min-noconflict/mode-makefile.js")
@JsModule("./ace-builds/src-min-noconflict/mode-markdown.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mask.js")
@JsModule("./ace-builds/src-min-noconflict/mode-matlab.js")
@JsModule("./ace-builds/src-min-noconflict/mode-maze.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mediawiki.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mel.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mixal.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mushcode.js")
@JsModule("./ace-builds/src-min-noconflict/mode-mysql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-nginx.js")
@JsModule("./ace-builds/src-min-noconflict/mode-nim.js")
@JsModule("./ace-builds/src-min-noconflict/mode-nix.js")
@JsModule("./ace-builds/src-min-noconflict/mode-nsis.js")
@JsModule("./ace-builds/src-min-noconflict/mode-nunjucks.js")
@JsModule("./ace-builds/src-min-noconflict/mode-objectivec.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ocaml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-pascal.js")
@JsModule("./ace-builds/src-min-noconflict/mode-perl6.js")
@JsModule("./ace-builds/src-min-noconflict/mode-perl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-pgsql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-php.js")
@JsModule("./ace-builds/src-min-noconflict/mode-php_laravel_blade.js")
@JsModule("./ace-builds/src-min-noconflict/mode-pig.js")
@JsModule("./ace-builds/src-min-noconflict/mode-plain_text.js")
@JsModule("./ace-builds/src-min-noconflict/mode-powershell.js")
@JsModule("./ace-builds/src-min-noconflict/mode-praat.js")
@JsModule("./ace-builds/src-min-noconflict/mode-prisma.js")
@JsModule("./ace-builds/src-min-noconflict/mode-prolog.js")
@JsModule("./ace-builds/src-min-noconflict/mode-properties.js")
@JsModule("./ace-builds/src-min-noconflict/mode-protobuf.js")
@JsModule("./ace-builds/src-min-noconflict/mode-puppet.js")
@JsModule("./ace-builds/src-min-noconflict/mode-python.js")
@JsModule("./ace-builds/src-min-noconflict/mode-qml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-razor.js")
@JsModule("./ace-builds/src-min-noconflict/mode-rdoc.js")
@JsModule("./ace-builds/src-min-noconflict/mode-red.js")
@JsModule("./ace-builds/src-min-noconflict/mode-redshift.js")
@JsModule("./ace-builds/src-min-noconflict/mode-rhtml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-r.js")
@JsModule("./ace-builds/src-min-noconflict/mode-rst.js")
@JsModule("./ace-builds/src-min-noconflict/mode-ruby.js")
@JsModule("./ace-builds/src-min-noconflict/mode-rust.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sass.js")
@JsModule("./ace-builds/src-min-noconflict/mode-scad.js")
@JsModule("./ace-builds/src-min-noconflict/mode-scala.js")
@JsModule("./ace-builds/src-min-noconflict/mode-scheme.js")
@JsModule("./ace-builds/src-min-noconflict/mode-scss.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sh.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sjs.js")
@JsModule("./ace-builds/src-min-noconflict/mode-slim.js")
@JsModule("./ace-builds/src-min-noconflict/mode-smarty.js")
@JsModule("./ace-builds/src-min-noconflict/mode-snippets.js")
@JsModule("./ace-builds/src-min-noconflict/mode-soy_template.js")
@JsModule("./ace-builds/src-min-noconflict/mode-space.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sparql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sql.js")
@JsModule("./ace-builds/src-min-noconflict/mode-sqlserver.js")
@JsModule("./ace-builds/src-min-noconflict/mode-stylus.js")
@JsModule("./ace-builds/src-min-noconflict/mode-svg.js")
@JsModule("./ace-builds/src-min-noconflict/mode-swift.js")
@JsModule("./ace-builds/src-min-noconflict/mode-tcl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-terraform.js")
@JsModule("./ace-builds/src-min-noconflict/mode-tex.js")
@JsModule("./ace-builds/src-min-noconflict/mode-textile.js")
@JsModule("./ace-builds/src-min-noconflict/mode-text.js")
@JsModule("./ace-builds/src-min-noconflict/mode-toml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-tsx.js")
@JsModule("./ace-builds/src-min-noconflict/mode-turtle.js")
@JsModule("./ace-builds/src-min-noconflict/mode-twig.js")
@JsModule("./ace-builds/src-min-noconflict/mode-typescript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-vala.js")
@JsModule("./ace-builds/src-min-noconflict/mode-vbscript.js")
@JsModule("./ace-builds/src-min-noconflict/mode-velocity.js")
@JsModule("./ace-builds/src-min-noconflict/mode-verilog.js")
@JsModule("./ace-builds/src-min-noconflict/mode-vhdl.js")
@JsModule("./ace-builds/src-min-noconflict/mode-visualforce.js")
@JsModule("./ace-builds/src-min-noconflict/mode-wollok.js")
@JsModule("./ace-builds/src-min-noconflict/mode-xml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-xquery.js")
@JsModule("./ace-builds/src-min-noconflict/mode-yaml.js")
@JsModule("./ace-builds/src-min-noconflict/mode-zeek.js")
@JsModule("./ace-builds/src-min-noconflict/theme-ambiance.js")
@JsModule("./ace-builds/src-min-noconflict/theme-chaos.js")
@JsModule("./ace-builds/src-min-noconflict/theme-chrome.js")
@JsModule("./ace-builds/src-min-noconflict/theme-clouds.js")
@JsModule("./ace-builds/src-min-noconflict/theme-clouds_midnight.js")
@JsModule("./ace-builds/src-min-noconflict/theme-cobalt.js")
@JsModule("./ace-builds/src-min-noconflict/theme-crimson_editor.js")
@JsModule("./ace-builds/src-min-noconflict/theme-dawn.js")
@JsModule("./ace-builds/src-min-noconflict/theme-dracula.js")
@JsModule("./ace-builds/src-min-noconflict/theme-dreamweaver.js")
@JsModule("./ace-builds/src-min-noconflict/theme-eclipse.js")
@JsModule("./ace-builds/src-min-noconflict/theme-github.js")
@JsModule("./ace-builds/src-min-noconflict/theme-gob.js")
@JsModule("./ace-builds/src-min-noconflict/theme-gruvbox.js")
@JsModule("./ace-builds/src-min-noconflict/theme-idle_fingers.js")
@JsModule("./ace-builds/src-min-noconflict/theme-iplastic.js")
@JsModule("./ace-builds/src-min-noconflict/theme-katzenmilch.js")
@JsModule("./ace-builds/src-min-noconflict/theme-kr_theme.js")
@JsModule("./ace-builds/src-min-noconflict/theme-kuroir.js")
@JsModule("./ace-builds/src-min-noconflict/theme-merbivore.js")
@JsModule("./ace-builds/src-min-noconflict/theme-merbivore_soft.js")
@JsModule("./ace-builds/src-min-noconflict/theme-mono_industrial.js")
@JsModule("./ace-builds/src-min-noconflict/theme-monokai.js")
@JsModule("./ace-builds/src-min-noconflict/theme-nord_dark.js")
@JsModule("./ace-builds/src-min-noconflict/theme-pastel_on_dark.js")
@JsModule("./ace-builds/src-min-noconflict/theme-solarized_dark.js")
@JsModule("./ace-builds/src-min-noconflict/theme-solarized_light.js")
@JsModule("./ace-builds/src-min-noconflict/theme-sqlserver.js")
@JsModule("./ace-builds/src-min-noconflict/theme-terminal.js")
@JsModule("./ace-builds/src-min-noconflict/theme-textmate.js")
@JsModule("./ace-builds/src-min-noconflict/theme-tomorrow.js")
@JsModule("./ace-builds/src-min-noconflict/theme-tomorrow_night_blue.js")
@JsModule("./ace-builds/src-min-noconflict/theme-tomorrow_night_bright.js")
@JsModule("./ace-builds/src-min-noconflict/theme-tomorrow_night_eighties.js")
@JsModule("./ace-builds/src-min-noconflict/theme-tomorrow_night.js")
@JsModule("./ace-builds/src-min-noconflict/theme-twilight.js")
@JsModule("./ace-builds/src-min-noconflict/theme-vibrant_ink.js")
@JsModule("./ace-builds/src-min-noconflict/theme-xcode.js")
@JsModule("./ace-builds/src-min-noconflict/worker-base.js")
@JsModule("./ace-builds/src-min-noconflict/worker-coffee.js")
@JsModule("./ace-builds/src-min-noconflict/worker-css.js")
@JsModule("./ace-builds/src-min-noconflict/worker-html.js")
@JsModule("./ace-builds/src-min-noconflict/worker-javascript.js")
@JsModule("./ace-builds/src-min-noconflict/worker-json.js")
@JsModule("./ace-builds/src-min-noconflict/worker-lua.js")
@JsModule("./ace-builds/src-min-noconflict/worker-php.js")
@JsModule("./ace-builds/src-min-noconflict/worker-xml.js")
@JsModule("./ace-builds/src-min-noconflict/worker-xquery.js")
@JsModule("./@f0rce/lit-ace/lit-ace.js")
public class AceEditor extends AbstractSinglePropertyField<AceEditor, String>
		implements HasSize, HasStyle, Focusable<AceEditor> {

	private AceTheme editorTheme = AceTheme.eclipse;
	private AceMode editorMode = AceMode.javascript;
	private String baseUrl = "ace-builds/src-min-noconflict/";
	private int fontSize = 14;
	private boolean softTabs = true;
	private int tabSize = 4;
	private boolean wrap = false;
	private boolean autoComplete = false;
	private boolean initialFocus = false;
	private String placeHolder = "";
	private boolean readOnly = false;
	private boolean printMargin = false;
	private boolean showInvisibles = false;
	private boolean showGutter = true;
	private boolean hightlightActiveLine = true;
	private boolean displayIndentGuides = false;
	private boolean highlightSelectedWord = false;
	private int[] selection = new int[] { 0, 0, 0, 0 };
	private int[] cursorPosition = new int[] { 0, 0 };
	private boolean useWorker = false;
	private boolean liveAutocompletion = false;
	private boolean enableSnippets = true;
	private String[] customAutoCompletion = new String[0];
	private List<AceMarker> markers = new ArrayList<AceMarker>();
	private String selectedText = "";
	private boolean statusbarEnabled = true;

	public AceEditor() {
		super("value", "", false);
		super.addListener(AceBlurChanged.class, this::updateEditor);
		super.addListener(AceForceSyncDomEvent.class, this::onForceSyncDomEvent);

		this.setHeight("300px");
		this.setWidth("100%");

	}

	public AceEditor(AceTheme theme, AceMode mode, String height, String width) {
		super("value", "", false);
		super.addListener(AceBlurChanged.class, this::updateEditor);
		super.addListener(AceForceSyncDomEvent.class, this::onForceSyncDomEvent);

		this.setTheme(theme);
		this.setMode(mode);
		this.setHeight(height);
		this.setWidth(width);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
	}

	// Updates the Text and selection after the Blur event has been fired (Keyboard
	// lost focus)
	private void updateEditor(AceBlurChanged event) {
		updateSelection(event);
		this.setValue(event.getValue());
	};

	// Updates the private variables to ensure that client and server are up to date
	private void updateSelection(AceBlurChanged event) {
		this.selectedText = event.getSelectedText();
		this.cursorPosition = new int[] { event.getCursorRow(), event.getCursorColumn() };
		this.selection = new int[] { event.getSelectionRowStart(), event.getSelectionFrom(), event.getSelectionRowEnd(),
				event.getSelectionTo() };
	};

	// Keeps the editor up to date and is backwards compatible
	private void onForceSyncDomEvent(AceForceSyncDomEvent event) {
		this.selectedText = event.getSelectedText();
		this.selection = new int[] { event.getSelectionRowStart(), event.getSelectionFrom(), event.getSelectionRowEnd(),
				event.getSelectionTo() };
		this.cursorPosition = new int[] { event.getCursorRow(), event.getCursorColumn() };
		setValue(event.getValue());

		fireEvent(new AceForceSyncEvent(event.getSource(), event.isFromClient(), event.getValue(),
				event.getSelectionRowStart() + "|" + event.getSelectionFrom() + "|" + event.getSelectionRowEnd() + "|"
						+ event.getSelectionTo() + "|-",
				event.getSelectedText(), event.getCursorRow() + "|" + event.getCursorColumn() + "|-"));
	}

	/**
	 * Sets the mode (language) of the editor.
	 *
	 * @param mode {@link AceMode}
	 */
	public void setMode(AceMode mode) {
		getElement().setProperty("mode", mode.toString());
		this.editorMode = mode;
	};

	/**
	 * Returns the current set mode for the editor.
	 * 
	 * @return {@link AceMode}
	 */
	public AceMode getMode() {
		return this.editorMode;
	};

	/**
	 * Sets the theme (style) of the editor.
	 *
	 * @param theme {@link AceTheme}
	 */
	public void setTheme(AceTheme theme) {
		getElement().setProperty("theme", theme.toString());
		this.editorTheme = theme;
	};

	/**
	 * Returns the current set theme for the editor.
	 * 
	 * @return {@link AceTheme}
	 */
	public AceTheme getTheme() {
		return this.editorTheme;
	};

	/**
	 * Sets the baseUrl for the editor. BaseUrl contains the path to the themes and
	 * modes. If you want to add your own custom modes / themes make sure to place
	 * them under <b>src/main/resources/META-INF/resources</b> and make sure to
	 * start your BaseUrl with ../../yourDirectory/...
	 * 
	 * @param baseUrl {@link String}
	 */
	public void setBaseUrl(String baseUrl) {
		getElement().setProperty("baseUrl", baseUrl);
		this.baseUrl = baseUrl;
	}

	/**
	 * Returns the current set baseUrl.
	 * 
	 * @return {@link String}
	 */
	public String getBaseUrl() {
		return this.baseUrl;
	}

	/**
	 * Clears the value contained in the editor.
	 */
	public void clear() {
		getElement().setProperty("value", "");
	};

	/**
	 * Sets value for the editor.
	 * 
	 * @param value {@link String}
	 */
	public void setValue(String value) {
		getElement().setProperty("value", value);
	};

	/**
	 * Sets font-size for the editor in pixels.
	 * 
	 * @param value int
	 */
	public void setFontSize(int value) {
		getElement().setProperty("fontSize", value);
		this.fontSize = value;
	};

	/**
	 * Returns the current set font-size of the editor in pixels.
	 * 
	 * @return int
	 */
	public int getFontSize() {
		return this.fontSize;
	};

	/**
	 * Sets softtabs for the editor.
	 * 
	 * @param value boolean
	 */
	public void setSofttabs(boolean value) {
		getElement().setProperty("softtabs", value);
		this.softTabs = value;
	};

	/**
	 * Returns if softtabs are currently enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isSofttabs() {
		return this.softTabs;
	};

	/**
	 * Sets tab-size for the editor.
	 * 
	 * @param value int
	 */
	public void setTabSize(int value) {
		getElement().setProperty("tabSize", String.valueOf(value));
		this.tabSize = value;
	};

	/**
	 * Returns the current set tab-size for the editor.
	 * 
	 * @return int
	 */
	public int getTabSize() {
		return this.tabSize;
	};

	/**
	 * Sets wrap for the editor.
	 * 
	 * @param wrap boolean
	 */
	public void setWrap(boolean wrap) {
		getElement().setProperty("wrap", wrap);
		this.wrap = wrap;
	};

	/**
	 * Returns if wrap is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isWrap() {
		return this.wrap;
	};

	/**
	 * Sets AutoComplete for the editor.
	 * 
	 * @param value boolean
	 */
	public void setAutoComplete(boolean value) {
		getElement().setProperty("enableAutocompletion", value);
		this.autoComplete = value;
	};

	/**
	 * Returns if autocomplete is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isAutoComplete() {
		return this.autoComplete;
	};

	/**
	 * Sets initialFocus for the editor.
	 * 
	 * @param value boolean
	 */
	public void setInitialFocus(boolean value) {
		getElement().setProperty("initialFocus", value);
		this.initialFocus = value;
	};

	/**
	 * Returns if initial focus is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isInitialFocus() {
		return this.initialFocus;
	};

	/**
	 * Sets placeholder for the editor.
	 * 
	 * @param value {@link String}
	 */
	public void setPlaceholder(String value) {
		getElement().setProperty("placeholder", value);
		this.placeHolder = value;
	};

	/**
	 * Returns the placeholder set for the editor.
	 * 
	 * @return {@link String}
	 */
	public String getPlaceholder() {
		return this.placeHolder;
	};

	/**
	 * Sets readOnly for the editor.
	 * 
	 * @param value boolean
	 */
	public void setReadOnly(boolean value) {
		getElement().setProperty("readonly", value);
		this.readOnly = value;
	};

	/**
	 * Returns if readOnly is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isReadOnly() {
		return this.readOnly;
	};

	/**
	 * Sets showPrintMargin for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowPrintMargin(boolean value) {
		getElement().setProperty("showPrintMargin", value);
		this.printMargin = value;
	};

	/**
	 * Returns if showPrintMargin is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowPrintMargin() {
		return this.printMargin;
	};

	/**
	 * Sets showInvisibles for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowInvisibles(boolean value) {
		getElement().setProperty("showInvisibles", value);
		this.showInvisibles = value;
	};

	/**
	 * Returns if showInvisibles is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowInvisibles() {
		return this.showInvisibles;
	};

	/**
	 * Sets showGutter for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowGutter(boolean value) {
		getElement().setProperty("showGutter", value);
		this.showGutter = value;
	};

	/**
	 * Returns if showGutter is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowGutter() {
		return this.showGutter;
	};

	/**
	 * Sets highlightActiveLine for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightActiveLine(boolean value) {
		getElement().setProperty("highlightActiveLine", value);
		this.hightlightActiveLine = value;
	};

	/**
	 * Returns if hightlightActiveLine is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isHightlightActiveLine() {
		return this.hightlightActiveLine;
	};

	/**
	 * Sets displayIndentGuides for the editor.
	 * 
	 * @param value boolean
	 */
	public void setDisplayIndentGuides(boolean value) {
		getElement().setProperty("displayIndentGuides", value);
		this.displayIndentGuides = value;
	};

	/**
	 * Returns if displayIndentGuides is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isDisplayIndentGuides() {
		return this.displayIndentGuides;
	};

	/**
	 * Sets highlightSelectedWord for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightSelectedWord(boolean value) {
		getElement().setProperty("highlightSelectedWord", value);
		this.highlightSelectedWord = value;
	};

	/**
	 * Returns if hightlightSelectedWord is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isHightlightSelectedWord() {
		return this.highlightSelectedWord;
	};

	/**
	 * Sets selection for the editor.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 */
	public void setSelection(int rowStart, int from, int rowEnd, int to) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}
		getElement().setProperty("selection",
				rowStart + "|" + from + "|" + rowEnd + "|" + to + "|" + UUID.randomUUID().toString());
		this.selection = new int[] { rowStart, from, rowEnd, to };
		this.cursorPosition = new int[] { rowEnd, to };
	};

	/**
	 * Sets selection for the editor and optionally also sets the focus.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param focus    boolean
	 */
	public void setSelection(int rowStart, int from, int rowEnd, int to, boolean focus) {
		setSelection(rowStart, from, rowEnd, to);
		if (focus) {
			this.focus();
		}
	};

	/**
	 * Sets the selection via two indices and the frontend calculates the rowFrom
	 * and the rowTo from it.
	 * 
	 * @param from int
	 * @param to   int
	 */
	public void setSelection(int from, int to) {
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		getElement().callJsFunction("calculateSelectionByIndices", from, to);
	}

	/**
	 * Returns an int array of the current selection where the first index
	 * represents "rowStart", the second index "from", the third index "rowEnd" and
	 * the forth index "to".
	 * 
	 * @return int[]
	 */
	public int[] getSelection() {
		return this.selection;
	};

	/**
	 * Sets useWorker for the editor.
	 * 
	 * @param value boolean
	 */
	public void setUseWorker(boolean value) {
		getElement().setProperty("useWorker", value);
		this.useWorker = value;
	};

	/**
	 * Returns if useWorker is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isUseWorker() {
		return this.useWorker;
	};

	/**
	 * Sets cursorPosition for the editor.
	 * 
	 * @param row    int
	 * @param column int
	 */
	public void setCursorPosition(int row, int column) {
		row = Math.abs(row);
		column = Math.abs(column);

		getElement().setProperty("cursorPosition", row + "|" + column + "|" + UUID.randomUUID().toString());
		this.cursorPosition = new int[] { row, column };
		this.selection = new int[] { row, column, row, column };
	};

	/**
	 * Sets cursorPosition for the editor and optionally also sets the focus.
	 * 
	 * @param row    int
	 * @param column int
	 * @param focus  boolean
	 */
	public void setCursorPosition(int row, int column, boolean focus) {
		setCursorPosition(row, column);
		if (focus) {
			this.focus();
		}
	};

	/**
	 * Sets the cursor position via index and the frontend calculates the row for
	 * it.
	 * 
	 * @param index int
	 */
	public void setCursorPosition(int index) {
		getElement().callJsFunction("calculateCursorPositionFromIndex", index);
	}

	/**
	 * Sets the cursor position via index and the frontend calculates the row for it
	 * and sets the focus.
	 * 
	 * @param index int
	 * @param focus boolean
	 */
	public void setCursorPosition(int index, boolean focus) {
		this.setCursorPosition(index);
		if (focus) {
			this.focus();
		}
	}

	/**
	 * Sets the cursor position in the editor where the first index represents the
	 * row and the second index represents the index/column.
	 * 
	 * @param cursorPosition int[]
	 */
	public void setCursorPosition(int[] cursorPosition) {
		this.setCursorPosition(cursorPosition[0], cursorPosition[1]);
	}

	/**
	 * Sets the cursor position in the editor where the first index represents the
	 * row, the second index represents the index/column and sets the focus.
	 * 
	 * @param cursorPosition int[]
	 * @param focus          boolean
	 */
	public void setCursorPosition(int[] cursorPosition, boolean focus) {
		this.setCursorPosition(cursorPosition[0], cursorPosition[1]);
		if (focus) {
			this.focus();
		}
	}

	/**
	 * Returns the current set cursor position in the editor where the first index
	 * represents the row and the second index represents the index/column.
	 * 
	 * @return int[]
	 */
	public int[] getCursorPosition() {
		return this.cursorPosition;
	};

	/**
	 * Sets liveAutocompletion for the editor.
	 * 
	 * @param value boolean
	 */
	public void setLiveAutocompletion(boolean value) {
		getElement().setProperty("enableLiveAutocompletion", value);
		this.liveAutocompletion = value;
	};

	/**
	 * Returns if live autocompletion is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isLiveAutocompletion() {
		return this.liveAutocompletion;
	};

	/**
	 * Sets enableSnippets for the editor.
	 * 
	 * @param value boolean
	 */
	public void setEnableSnippets(boolean value) {
		getElement().setProperty("enableSnippets", value);
		this.enableSnippets = value;
	};

	/**
	 * Returns if snippets are enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isEnableSnippets() {
		return this.enableSnippets;
	};

	/**
	 * Sets a custom autocompletion list for the editor.
	 * 
	 * @param wordList String[]
	 */
	public void setCustomAutoCompletion(String[] wordList) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion", "|" + String.join(",", wordList) + "|" + false);
		this.customAutoCompletion = wordList;
	};

	/**
	 * Sets a custom autocompletion list for the editor and optionally keeps the
	 * current completers.
	 * 
	 * @param wordList              String[]
	 * @param keepCurrentCompleters boolean
	 */
	public void setCustomAutoCompletion(String[] wordList, boolean keepCurrentCompleters) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion",
				"|" + String.join(",", wordList) + "|" + keepCurrentCompleters);
		this.customAutoCompletion = wordList;
	}

	/**
	 * Sets a custom autocompletion list for the editor and sets the category aswell
	 * (default: "").
	 * 
	 * @param wordList String[]
	 * @param category {@link String}
	 */
	public void setCustomAutoCompletion(String[] wordList, String category) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion", category + "|" + String.join(",", wordList));
		this.customAutoCompletion = wordList;
	};

	/**
	 * Sets a custom autocompletion list for the editor, sets the category (default:
	 * "") and optionally keeps the current completers.
	 * 
	 * @param wordList              String[]
	 * @param category              {@link String}
	 * @param keepCurrentCompleters boolean
	 */
	public void setCustomAutoCompletion(String[] wordList, String category, boolean keepCurrentCompleters) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion",
				category + "|" + String.join(",", wordList) + "|" + keepCurrentCompleters);
		this.customAutoCompletion = wordList;
	};

	/**
	 * Returns a String array of the current custom autocompletion for the editor.
	 * 
	 * @return String[]
	 */
	public String[] getCustomAutoCompletion() {
		return this.customAutoCompletion;
	};

	/**
	 * Removes the custom autocompletion list set with setCustomAutoCompletiton()
	 * and replaces it with the default one.
	 */
	public void disableCustomAutoCompletion() {
		getElement().setProperty("customAutoCompletion", "||");
	};

	/**
	 * Adds text to a specific position in the editor.
	 * 
	 * @param row    int
	 * @param column int
	 * @param text   {@link String}
	 */
	public void addTextAtPosition(int row, int column, String text) {
		getElement().callJsFunction("insertText", row, column, text);
	};

	/**
	 * Adds text at the current cursor position in the editor.
	 * 
	 * @param text {@link String}
	 */
	public void addTextAtCurrentPosition(String text) {
		this.addTextAtPosition(this.cursorPosition[0], this.cursorPosition[1], text);
	};

	/**
	 * Adds a marker to the editor at the current selection. If the returned
	 * {@link AceMarker} is null, there is no current selection. Use
	 * {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor)} or
	 * {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor, String)}
	 * instead. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean)} and
	 * {@link #setHighlightSelectedWord(boolean)} are set to false.
	 * 
	 * @param color {@link AceMarkerColor}
	 * 
	 * @return {@link AceMarker}
	 */
	public AceMarker addMarkerAtCurrentSelection(AceMarkerColor color) {
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			int[] currentSelection = this.selection;

			AceMarker marker = new AceMarker(currentSelection[0], currentSelection[1], currentSelection[2],
					currentSelection[3], color);

			getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
					+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
			this.markers.add(marker);
			return marker;
		}
		return null;
	};

	/**
	 * Adds a marker to the editor at the current selection. If the returned
	 * {@link AceMarker} is null, there is no current selection. Use
	 * {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor)} or
	 * {@link #addMarkerAtSelection(int, int, int, int, AceMarkerColor, String)}
	 * instead. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean)} and
	 * {@link #setHighlightSelectedWord(boolean)} are set to false.
	 * 
	 * @param color {@link AceMarkerColor}
	 * @param alias {@link String}
	 * 
	 * @return {@link AceMarker}
	 */
	public AceMarker addMarkerAtCurrentSelection(AceMarkerColor color, String alias) {
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			int[] currentSelection = this.selection;

			AceMarker marker = new AceMarker(currentSelection[0], currentSelection[1], currentSelection[2],
					currentSelection[3], color, alias);

			getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
					+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
			this.markers.add(marker);
			return marker;
		}
		return null;
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean)} and
	 * {@link #setHighlightSelectedWord(boolean)} are set to false.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param color    {@link AceMarkerColor}
	 * 
	 * @return {@link AceMarker}
	 */
	public AceMarker addMarkerAtSelection(int rowStart, int from, int rowEnd, int to, AceMarkerColor color) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}

		AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color);

		getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
				+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker;
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean)} and
	 * {@link #setHighlightSelectedWord(boolean)} are set to false.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param color    {@link AceMarkerColor}
	 * @param alias    {@link String}
	 * 
	 * @return {@link AceMarker}
	 */
	public AceMarker addMarkerAtSelection(int rowStart, int from, int rowEnd, int to, AceMarkerColor color,
			String alias) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}

		AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color, alias);

		getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
				+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker;
	};

	/**
	 * Adds a {@link AceMarker} to the editor.
	 * 
	 * @param marker {@link AceMarker}
	 */

	public void addMarker(AceMarker marker) {
		getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
				+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
	};

	/**
	 * Returns a list of all current active markers. If the list is empty, no marker
	 * is set at the moment.
	 * 
	 * @return {@link List}
	 */
	public List<AceMarker> getAllMarkers() {
		return this.markers;
	};

	/**
	 * Removes a specific marker from the editor.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()}
	 * 
	 * @param marker {@link AceMarker}
	 */
	public void removeMarker(AceMarker marker) {
		for (AceMarker mar : markers) {
			if (mar.getID().equals(marker.getID())) {
				getElement().setProperty("rmMarker", marker.getID());
				markers.remove(marker);
			}
		}
	};

	/**
	 * Removes a specific marker from the editor by id.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()}.
	 * 
	 * @param id {@link String}
	 */
	public void removeMarkerByID(String id) {
		for (AceMarker mar : markers) {
			if (mar.getID().equals(id)) {
				getElement().setProperty("rmMarker", id);
				markers.remove(mar);
			}
		}
	};

	/**
	 * Removes a specific marker from the editor by alias.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()}.
	 * 
	 * @param alias {@link String}
	 */
	public void removeMarkerByAlias(String alias) {
		for (AceMarker mar : markers) {
			if (mar.getAlias().equals(alias)) {
				getElement().setProperty("rmMarker", mar.getID());
				markers.remove(mar);
			}
		}
	};

	/**
	 * Removes every marker from the editor.
	 */
	public void removeAllMarkers() {
		getElement().setProperty("rmMarker", "all" + UUID.randomUUID().toString());
		this.markers = new ArrayList<AceMarker>();
	}

	/**
	 * Adds a listener to the editor, which listens to when selection in the editor
	 * changes. Check {@link AceSelectionChanged} for all returend values.
	 * 
	 * @param listener {@link ComponentEventListener}
	 * @return {@link Registration}
	 */
	public Registration addSelectionChangeListener(ComponentEventListener<AceSelectionChanged> listener) {
		return addListener(AceSelectionChanged.class, listener);
	}

	/**
	 * Forces a sync between client and server without blurring. Use
	 * {@link #addSyncCompletedListener(ComponentEventListener)} to get the values
	 * after the sync completed.
	 */
	public void sync() {
		getElement().callJsFunction("forceSync");
	}

	/**
	 * Add a listener to the editor, which listens to when the sync event completed,
	 * which is triggered by the {@link #sync()} method. Check
	 * {@link AceForceSyncEvent} for all availible returned values.
	 * 
	 * @param listener {@link ComponentEventListener}
	 * @return {@link Registration}
	 */
	public Registration addSyncCompletedListener(ComponentEventListener<AceForceSyncEvent> listener) {
		return addListener(AceForceSyncEvent.class, listener);
	}

	/**
	 * Generates a fully functional static HTML-String with the current value of the
	 * editor, which can be used to print for example. Use
	 * {@link #addHTMLGeneratedListener(ComponentEventListener)} to get the HTML as
	 * soon as the generate is done.
	 * 
	 * @param raw boolean
	 */
	public void generateHTML(boolean raw) {
		getElement().callJsFunction("generateHTML", raw);
	}

	/**
	 * Add a listener to the editor, which listens to when the HTML generate
	 * completed, which is triggered by the {@link #generateHTML(boolean)} method.
	 * Check {@link AceHTMLGeneratedEvent} for all availible returned values.
	 * 
	 * @param listener {@link ComponentEventListener}
	 * @return {@link Registration}
	 */
	public Registration addHTMLGeneratedListener(ComponentEventListener<AceHTMLGeneratedEvent> listener) {
		return addListener(AceHTMLGeneratedEvent.class, listener);
	}

	/**
	 * Replaces the current selection with given text.
	 * 
	 * @param text {@link String}
	 */
	public void replaceTextAtCurrentSelection(String text) {
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			if (text == null) {
				text = "";
			}
			getElement().callJsFunction("replaceTextAtSelection", text);
		}
	}

	/**
	 * Focuses the textarea of the ace editor.
	 */
	@Override
	public void focus() {
		getElement().callJsFunction("focusEditor");
	}

	/**
	 * Run an action after the sync has completed to ensure the values you want to
	 * use are up to date with the server.
	 * 
	 * @param action {@link Runnable}
	 */
	public void runAfterSync(Runnable action) {
		Objects.requireNonNull(action);
		addListener(AceForceSyncDomEvent.class, event -> runAfterSync(event, action));
		sync();
	}

	// unregister event and run action (private)
	private void runAfterSync(AceForceSyncDomEvent event, Runnable action) {
		event.unregisterListener();
		action.run();
	}

	/**
	 * Returns the selected text (if text is selected).
	 * 
	 * @return {@link String}
	 */
	public String getSelectedText() {
		return this.selectedText;
	}

	/**
	 * Folds all fold marker in the editor. (Created automatically when an if is
	 * added for example).
	 */
	public void foldAll() {
		getElement().callJsFunction("foldAll");
	}

	/**
	 * Folds all fold marker in the editor from a specific line downwards. (Created
	 * automatically when an if is added for example).
	 * 
	 * @param startRow int
	 */
	public void foldAll(int startRow) {
		getElement().callJsFunction("foldAll", startRow);
	}

	/**
	 * Unfolds all fold marker in the editor.
	 */
	public void unfold() {
		getElement().callJsFunction("unfold");
	}

	/**
	 * Beautifies the current code.
	 */
	public void beautify() {
		getElement().callJsFunction("beautify");
	}

	/**
	 * Enables the statusbar which is displayed at the bottom right of the ace
	 * editor to let the user see current cursor position and selection.
	 * 
	 * @param statusbarEnabled boolean
	 */
	public void setStatusbarEnabled(boolean statusbarEnabled) {
		getElement().setProperty("statusbarEnabled", statusbarEnabled);
		this.statusbarEnabled = statusbarEnabled;
	}

	/**
	 * Returns if the statusbar is enabled or not.
	 * 
	 * @return boolean
	 */
	public boolean isStatusbarEnabled() {
		return statusbarEnabled;
	}
}
