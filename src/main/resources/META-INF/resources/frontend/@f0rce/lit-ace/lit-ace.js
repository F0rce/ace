/**
@license MIT
Copyright 2021-2022 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html, css } from "lit";

import "@f0rce/ace-builds/src-noconflict/ace.js";
import "@f0rce/ace-builds/src-noconflict/ext-language_tools.js";
import "@f0rce/ace-builds/src-noconflict/ext-static_highlight.js";
import "@f0rce/ace-builds/src-noconflict/ext-beautify.js";
import "@f0rce/ace-builds/src-noconflict/ext-statusbar.js";
import "@f0rce/ace-builds/src-noconflict/snippets/snippets.js";

const CUSTOM_KEYWORD = "custom";
class LitAce extends LitElement {
  static get properties() {
    return {
      theme: { type: String, reflect: true },
      mode: { type: String, reflect: true },
      baseUrl: { type: String },
      readonly: { type: Boolean },
      softtabs: { type: Boolean },
      wrap: { type: Boolean },
      fontSize: { type: Number },
      tabSize: { type: Number },
      enableLiveAutocompletion: { type: Boolean },
      enableAutocompletion: { type: Boolean },
      initialFocus: { type: Boolean },
      placeholder: { type: String },
      showPrintMargin: { type: Boolean },
      showInvisibles: { type: Boolean },
      showGutter: { type: Boolean },
      highlightActiveLine: { type: Boolean },
      displayIndentGuides: { type: Boolean },
      highlightSelectedWord: { type: Boolean },
      useWorker: { type: Boolean },
      marker: { type: String }, // TODO: remove this, use markerList instead
      markerList: { type: Array }, // TODO: kepp this, backend should create a json with all markers
      rmMarker: { type: String }, // TODO: use method
      statusbarEnabled: { type: Boolean },
      enableSnippets: { type: Boolean },
    };
  }

  constructor() {
    super();
    this.theme = "eclipse";
    this.mode = "javascript";
    this.baseUrl = "ace-builds/src-min-noconflict/";
    this.readonly = false;
    this.softtabs = true;
    this.wrap = false;
    this.fontSize = "14px";
    this.tabSize = 4;
    this.enableLiveAutocompletion = false;
    this.enableAutocompletion = false;
    this.initialFocus = false;
    this.placeholder = "";
    this.showPrintMargin = false;
    this.showInvisibles = false;
    this.showGutter = true;
    this.highlightActiveLine = true;
    this.displayIndentGuides = false;
    this.highlightSelectedWord = false;
    this.useWorker = false;
    this.marker = "-|-|-|-|-|-";
    this.markerList = { markers: [] };
    this.rmMarker = "";
    this.statusbarEnabled = true;
    this.enableSnippets = false;
  }

  static get styles() {
    return css`
      :host {
        display: block;
        width: 100%;
        height: 100%;
      }
      #editor {
        border: var(--lae-border, 1px solid var(--lumo-contrast-20pct));
        border-radius: var(--lae-border-radius, var(--lumo-border-radius));
        @apply --ace-widget-editor;
      }
      #editorStatusbar {
        z-index: 9 !important;
        position: absolute !important;
        right: 4px;
        bottom: 4px;
      }
      .ace_status-indicator {
        background-color: var(--las-background-color, #777);
        color: var(--las-color, white);
        text-align: center;
        border: none;
        border-radius: var(--las-border-radius, 7px);
        padding-right: 3px;
        padding-left: 3px;
        padding-bottom: 1px;
        font-size: small;
        opacity: 0.9;
      }
      .hide_statusbar {
        display: none;
      }
      .ace_marker-layer .green {
        background-color: var(--lumo-success-color);
        color: var(--lumo-primary-contrast-color);
        position: absolute;
      }
      .ace_marker-layer .darkGrey {
        background-color: var(--lumo-shade-50pct);
        color: var(--lumo-primary-contrast-color);
        position: absolute;
      }
      .ace_marker-layer .red {
        background-color: var(--lumo-error-color);
        color: var(--lumo-primary-contrast-color);
        position: absolute;
      }
      .ace_marker-layer .blue {
        background-color: var(--lumo-primary-color);
        color: var(--lumo-primary-contrast-color);
        position: absolute;
      }
      .ace_marker-layer .orange {
        background-color: #ff9900;
        color: #555;
        position: absolute;
      }
      .ace_placeholder {
        color: #808080 !important;
        font-family: var(--lumo-font-family) !important;
        transform: scale(1) !important;
        opacity: 1 !important;
        font-style: italic !important;
      }
    `;
  }

  render() {
    return html`
      <div
        id="editorContainer"
        style="height: 100%; width: 100%; position: relative;"
      >
        <div
          id="editor"
          style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"
        ></div>
        <div id="editorStatusbar"></div>
      </div>
    `;
  }

  async firstUpdated(changedProperties) {
    if (!ace) {
      await import("@f0rce/ace-builds/src-noconflict/ace");
    }

    if (!ace.require("ace/ext/language_tools")) {
      await import("@f0rce/ace-builds/src-noconflict/ext-language_tools");
    }

    if (!ace.require("ace/ext/static_highlight")) {
      await import("@f0rce/ace-builds/src-noconflict/ext-static_highlight");
    }

    if (!ace.require("ace/ext/beautify")) {
      await import("@f0rce/ace-builds/src-noconflict/ext-beautify");
    }

    if (!ace.require("ace/ext/statusbar")) {
      await import("@f0rce/ace-builds/src-noconflict/ext-statusbar");
    }

    if (!ace.require("ace/snippets")) {
      await import("@f0rce/ace-builds/src-noconflict/snippets/snippets");
    }

    this.initialInit = true;

    this.editorDiv = this.shadowRoot.getElementById("editor");
    this.editorContainerDiv = this.shadowRoot.getElementById("editorContainer");
    this.editorStatusbarDiv = this.shadowRoot.getElementById("editorStatusbar");

    this.editor = ace.edit(this.editorDiv);
    this.editor.renderer.attachToShadowRoot();
    this.editor.langTools = ace.require("ace/ext/language_tools");
    this.editor.staticHighlight = ace.require("ace/ext/static_highlight");
    this.editor.beautify = ace.require("ace/ext/beautify");
    this.statusBar = ace.require("ace/ext/statusbar").StatusBar;
    this.snippetManager = ace.require("ace/snippets").snippetManager;

    this._customModes = new Map();

    let self = this;

    // when the CSS resize Property is added (to a container-div or lit-ace itself) the correct sizing is maintained (after user resize)
    document.addEventListener("mouseup", function (e) {
      self.resizeEditor();
    });

    this.observer = new ResizeObserver(function (entries) {
      entries.forEach(function (entry) {
        self.resizeEditor();
      });
    });

    this.observer.observe(this.editorContainerDiv);

    this.initializeEditor();
  }

  updated(changedProperties) {
    for (let i = 0; i < changedProperties.size; i++) {
      var toUpdate = Array.from(changedProperties.keys())[i];
      var funcToCall = toUpdate + "Changed";
      if (typeof this[funcToCall] == "function") {
        this[funcToCall](); // This line is freaking cool
      }
      // If last initial property update done, send ready event to ensure every next operation has an existing editor
      if (i == changedProperties.size - 1) {
        if (this.initialInit) {
          this.dispatchEvent(
            new CustomEvent("editor-ready", {
              detail: {},
            })
          );
          this.initialInit = false;
        }
      }
    }
  }

  initializeEditor() {
    let editor = this.editor;

    ace.config.set("basePath", this.baseUrl);
    ace.config.set("modePath", this.baseUrl);
    ace.config.set("themePath", this.baseUrl);
    ace.config.set("workerPath", this.baseUrl);

    // blur
    editor.on("blur", () => this.editorBlurChangeAction());

    // change -> possibility to allow saving the value without having to wait for blur
    editor.on("change", () => this.editorChangeAction());

    // selection change (with simple debounce) - 250ms delay
    var selectionTimeoutId = false;
    editor.selection.on("changeSelection", () => {
      clearTimeout(selectionTimeoutId);
      selectionTimeoutId = setTimeout(() => {
        this.updateSelectionAction();
      }, 250);
    });

    editor.$blockScrolling = Infinity;

    this.themeChanged();
    this.modeChanged();

    editor.setShowPrintMargin(this.showPrintMargin);
    editor.setShowInvisibles(this.showInvisibles);
    editor.getSession().setUseWorker(this.useWorker);

    editor.renderer.setShowGutter(this.showGutter);
    editor.renderer.setOption("displayIndentGuides", this.displayIndentGuides);

    editor.setOptions({
      autoScrollEditorIntoView: true,
      enableBasicAutocompletion: this.enableAutocompletion,
      enableLiveAutocompletion: this.enableLiveAutocompletion,
      placeholder: this.placeholder,
      enableSnippets: this.enableSnippets,
    });

    this.editorValue = "";

    if (this.initialFocus) {
      this.editor.focus();
    }

    this._statusbarIndex = 1;
    this.editor.statusbar = new this.statusBar(
      this.editor,
      this.editorStatusbarDiv,
      this._statusbarIndex
    );

    this.vScrollbarObserver = new IntersectionObserver(
      this._vScrollbarHandler.bind(this),
      { root: null }
    );
    this.vScrollbarObserver.observe(
      this.shadowRoot.querySelector(".ace_scrollbar-v")
    );

    this.hScrollbarObserver = new IntersectionObserver(
      this._hScrollbarHandler.bind(this),
      { root: null }
    );
    this.hScrollbarObserver.observe(
      this.shadowRoot.querySelector(".ace_scrollbar-h")
    );
  }

  focusEditor() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", (e) => this.editor.focus(), {
        once: true,
      });
    } else {
      this.editor.focus();
    }
  }

  themeChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.setTheme("ace/theme/" + this.theme);
  }

  modeChanged() {
    if (this.editor == undefined || this.mode == CUSTOM_KEYWORD) {
      return;
    }

    this.editor.session.setMode("ace/mode/" + this.mode);
  }

  get editorValue() {
    if (this.editor == undefined) {
      return "";
    }
    return this.editor.getValue();
  }

  set editorValue(value) {
    if (this.editor == undefined || value === undefined) {
      return;
    }
    this.editor.setValue(value, 1);
  }

  readonlyChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.setReadOnly(this.readonly);
    this.editor.setHighlightActiveLine(!this.readonly);
    this.editor.setHighlightGutterLine(!this.readonly);
    this.editor.renderer.$cursorLayer.element.style.opacity = this.readonly
      ? 0
      : 1;
  }

  softtabsChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.getSession().setUseSoftTabs(this.softtabs);
  }

  wrapChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.getSession().setUseWrapMode(this.wrap);
  }

  fontSizeChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.setFontSize(this.fontSize);
  }

  tabSizeChanged() {
    if (this.editor == undefined) {
      return;
    }
    if (this.tabSize) {
      this.editor.getSession().setTabSize(this.tabSize);
    }
  }

  highlightActiveLineChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setHighlightActiveLine(this.highlightActiveLine);
  }

  highlightSelectedWordChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setHighlightSelectedWord(this.highlightSelectedWord);
  }

  showPrintMarginChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setShowPrintMargin(this.showPrintMargin);
  }

  showInvisiblesChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setShowInvisibles(this.showInvisibles);
  }

  displayIndentGuidesChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.renderer.setOption(
      "displayIndentGuides",
      this.displayIndentGuides
    );
  }

  enableAutocompletionChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setOption(
      "enableBasicAutocompletion",
      this.enableAutocompletion
    );
  }

  enableLiveAutocompletionChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setOption(
      "enableBasicAutocompletion",
      this.enableLiveAutocompletion
    );
  }

  placeholderChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setOption("placeholder", this.placeholder);
  }

  showGutterChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.renderer.setShowGutter(this.showGutter);
  }

  markerChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.marker == "-|-|-|-|-|-") {
      return;
    }

    const markerRaw = this.marker;
    const rawSplit = markerRaw.split("|");
    const markerRowStart = parseInt(rawSplit[0]);
    const markerFrom = parseInt(rawSplit[1]);
    const markerRowEnd = parseInt(rawSplit[2]);
    const markerTo = parseInt(rawSplit[3]);
    const markerColor = String(rawSplit[4]);
    const uuid = String(rawSplit[5]);

    const Range = ace.require("ace/range").Range;
    const _range = this.editor.session.addMarker(
      new Range(markerRowStart, markerFrom, markerRowEnd, markerTo),
      markerColor,
      "text",
      false
    );
    this.markerList.markers.push({ uuid: uuid, rangeid: _range });
  }

  rmMarkerChanged() {
    if (this.rmMarker == "") {
      return;
    }

    if (this.rmMarker.includes("all")) {
      for (var i in this.markerList.markers) {
        const del = this.markerList.markers[i].rangeid;
        this.editor.getSession().removeMarker(del);
        delete this.markerList.markers[i];
      }
    } else {
      for (var i in this.markerList.markers) {
        if (this.markerList.markers[i].uuid == this.rmMarker) {
          const del = this.markerList.markers[i].rangeid;
          this.editor.getSession().removeMarker(del);
          delete this.markerList.markers[i];
        }
      }
    }
  }

  cursorPositionChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.selection == "0|0|-") {
      return;
    }

    const cursorPosition = this.cursorPosition.split("|");
    const row = parseInt(cursorPosition[0]);
    const column = parseInt(cursorPosition[1]);

    this.editor.gotoLine(row, column);
    this.editorBlurChangeAction();
  }

  statusbarEnabledChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.statusbarEnabled) {
      this.editorStatusbarDiv.classList.remove("hide_statusbar");
    } else {
      this.editorStatusbarDiv.classList.add("hide_statusbar");
    }
  }

  enableSnippetsChanged() {
    if (this.editor == undefined) {
      return;
    }

    var snippetManager = this.snippetManager;
    var snippets = snippetManager.files;

    if (!this.enableSnippets) {
      this.editor.setOptions({
        enableSnippets: false,
      });

      if (snippets) {
        for (const [name, props] of Object.entries(snippets)) {
          if (props.snippets) {
            snippetManager.unregister(props.snippets);
          }
        }
      }
    } else {
      this.editor.setOptions({
        enableSnippets: true,
      });

      if (snippets) {
        for (const [name, props] of Object.entries(snippets)) {
          if (props.snippets) {
            snippetManager.register(props.snippets);
          }
        }
      }
    }
  }

  editorBlurChangeAction() {
    this.dispatchEvent(
      new CustomEvent("editor-blur", {
        detail: {
          value: this.editorValue,
          selection: this._createSelectionObject(),
          cursorPosition: this._createCursorObject(),
        },
      })
    );
  }

  editorChangeAction() {
    this.dispatchEvent(
      new CustomEvent("editor-change", {
        detail: {
          value: this.editorValue,
        },
      })
    );
  }

  updateSelectionAction() {
    this.dispatchEvent(
      new CustomEvent("editor-selection", {
        detail: {
          selection: this._createSelectionObject(),
          cursorPosition: this._createCursorObject(),
        },
      })
    );
  }

  forceSync() {
    this.dispatchEvent(
      new CustomEvent("force-sync", {
        detail: {
          value: this.editorValue,
          selection: this._createSelectionObject(),
          cursorPosition: this._createCursorObject(),
        },
      })
    );
  }

  setSelection(json) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._setSelection(json), {
        once: true,
      });
    } else {
      this._setSelection(json);
    }
  }

  /** @private */
  _setSelection(json) {
    const parsed = JSON.parse(json);

    this.editor.selection.setRange(parsed, true);

    let currSelection = this.editor.selection.getRange();
    if (currSelection) {
      this.editor.renderer.scrollCursorIntoView(currSelection.start, 0.5);
    }

    this.editorBlurChangeAction();
  }

  setCursorPosition(json) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._setCursorPosition(json),
        { once: true }
      );
    } else {
      this._setCursorPosition(json);
    }
  }

  /** @private */
  _setCursorPosition(json) {
    const parsed = JSON.parse(json);
    this.editor.gotoLine(parsed.row, parsed.column);
    this.editorBlurChangeAction();
  }

  setValue(value) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._setValue(value), {
        once: true,
      });
    } else {
      this._setValue(value);
    }
  }

  /** @private */
  _setValue(value) {
    if (this.editorValue != value) {
      this.editorValue = value;
      this.editor.clearSelection();
      this.editor.resize();
    }
  }

  resizeEditor() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._resizeEditor(), {
        once: true,
      });
    } else {
      this._resizeEditor();
    }
  }

  /** @private */
  _resizeEditor() {
    this.editor.resize();
  }

  insertText(row, column, text) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._insertText(row, column, rext),
        { once: true }
      );
    } else {
      this._insertText(row, column, text);
    }
  }

  /** @private */
  _insertText(row, column, text) {
    let positionObject = { row, column };
    this.editor.session.insert(positionObject, text);
    this.editorBlurChangeAction();
  }

  calculateCursorPositionFromIndex(index) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._calculateCursorPositionFromIndex(index),
        { once: true }
      );
    } else {
      this._calculateCursorPositionFromIndex(index);
    }
  }

  /** @private */
  _calculateCursorPositionFromIndex(index) {
    var currentValue = this.editorValue;
    var split = currentValue.split("\n");
    var rowLengthObject = [];

    for (let i = 0; i < split.length; i++) {
      let totalLength;
      if (i === 0) {
        totalLength = split[i].length + 1; // +1 for \n
        rowLengthObject.push({
          row: i + 1,
          totalLength: totalLength,
        });
        continue;
      }
      totalLength = split[i].length + 1 + rowLengthObject[i - 1].totalLength;
      if (i === split.length - 1) totalLength--; // last line has no \n

      rowLengthObject.push({
        row: i + 1,
        totalLength: totalLength,
      });
    }

    for (let i = 0; i < rowLengthObject.length; i++) {
      if (rowLengthObject.length === 1) {
        this.editor.gotoLine(rowLengthObject[i].row, split[i].length);
        this.editorBlurChangeAction();
        return;
      }
      let currentLength = rowLengthObject[i].totalLength;
      if (i === 0) {
        if (index <= currentLength) {
          this.editor.gotoLine(rowLengthObject[i].row, index);
          this.editorBlurChangeAction();
          return;
        }
        continue;
      }
      if (i === rowLengthObject.length - 1) {
        this.editor.gotoLine(rowLengthObject[i].row, split[i].length);
        this.editorBlurChangeAction();
        return;
      }
      if (index <= currentLength) {
        this.editor.gotoLine(
          rowLengthObject[i].row,
          index - rowLengthObject[i - 1].totalLength
        );
        this.editorBlurChangeAction();
        return;
      }
    }
  }

  calculateSelectionByIndices(from, to) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._calculateSelectionByIndices(from, to),
        { once: true }
      );
    } else {
      this._calculateSelectionByIndices(from, to);
    }
  }

  /** @private */
  _calculateSelectionByIndices(from, to) {
    var currentValue = this.editorValue;
    var split = currentValue.split("\n");
    var rowLengthObject = [];

    for (let i = 0; i < split.length; i++) {
      let totalLength;
      if (i === 0) {
        totalLength = split[i].length + 1; // +1 for \n
        rowLengthObject.push({
          row: i,
          totalLength: totalLength,
        });
        continue;
      }
      totalLength = split[i].length + 1 + rowLengthObject[i - 1].totalLength;
      if (i === split.length - 1) totalLength--; // last line has no \n

      rowLengthObject.push({
        row: i,
        totalLength: totalLength,
      });
    }

    const Range = ace.require("ace/range").Range;
    var rowFrom = 0,
      fromC = from,
      rowTo = 0,
      toC = to;

    for (let i = 0; i < rowLengthObject.length; i++) {
      if (rowLengthObject.length === 1) {
        this.editor.selection.setRange(new Range(rowFrom, from, rowTo, to));

        let currSelection = this.editor.selection.getRange();
        this.editor.renderer.scrollCursorIntoView(currSelection.start, 0.5);
        this.editorBlurChangeAction();
        return;
      }
      let currentLength = rowLengthObject[i].totalLength;
      if (i === 0) {
        if (from <= currentLength && to <= currentLength) {
          this.editor.selection.setRange(new Range(rowFrom, from, rowTo, to));

          let currSelection = this.editor.selection.getRange();
          this.editor.renderer.scrollCursorIntoView(currSelection.start, 0.5);
          this.editorBlurChangeAction();
          return;
        }
        continue;
      }
      if (from <= currentLength) {
        if (fromC === from) {
          rowFrom = rowLengthObject[i].row;
          fromC = from - rowLengthObject[i - 1].totalLength;
        }
      }
      if (to <= currentLength) {
        if (toC === to) {
          rowTo = rowLengthObject[i].row;
          toC = to - rowLengthObject[i - 1].totalLength;
        }
      }
      if (i === rowLengthObject.length - 1) {
        this.editor.selection.setRange(new Range(rowFrom, fromC, rowTo, toC));

        let currSelection = this.editor.selection.getRange();
        this.editor.renderer.scrollCursorIntoView(currSelection.start, 0.5);
        this.editorBlurChangeAction();
        return;
      }
    }
  }

  replaceTextAtSelection(text) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._replaceTextAtSelection(text),
        { once: true }
      );
    } else {
      this._replaceTextAtSelection(text);
    }
  }

  /** @private */
  _replaceTextAtSelection(text) {
    this.editor.renderer.scrollCursorIntoView(
      this.editor.selection.getRange().start,
      0.5
    );
    this.editor.session.replace(this.editor.selection.getRange(), text);
    this.editorBlurChangeAction();
  }

  generateHTML(exportType) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._generateHTML(exportType),
        {
          once: true,
        }
      );
    } else {
      this._generateHTML(exportType);
    }
  }

  /** @private */
  async _generateHTML(exportType) {
    const content = await this._generateExport(exportType);

    this.dispatchEvent(
      new CustomEvent("html-generated", {
        detail: {
          html: content,
        },
      })
    );
  }

  unfold() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._unfold(), {
        once: true,
      });
    } else {
      this._unfold();
    }
  }

  /** @private */
  _unfold() {
    this.editor.getSession().unfold();
  }

  foldAll() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._foldAll(), {
        once: true,
      });
    } else {
      this._foldAll();
    }
  }

  /** @private */
  _foldAll() {
    this.editor.getSession().foldAll();
  }

  foldAll(startRow) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._foldAll(startRow), {
        once: true,
      });
    } else {
      this._foldAll(startRow);
    }
  }

  /** @private */
  _foldAll(startRow) {
    this.editor.getSession().foldAll(startRow);
  }

  beautify() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._beautify(), {
        once: true,
      });
    } else {
      this._beautify();
    }
  }

  /** @private */
  _beautify() {
    this.editor.beautify.beautify(this.editor.getSession());
    this.editorBlurChangeAction();
  }

  openAutocompletion() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._openAutocompletion(), {
        once: true,
      });
    } else {
      this._openAutocompletion();
    }
  }

  /** @private */
  _openAutocompletion() {
    this.editor.execCommand("startAutocomplete");
  }

  scrollToLine(line) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._scrollToLine(line), {
        once: true,
      });
    } else {
      this._scrollToLine(line);
    }
  }

  /** @private */
  _scrollToLine(line) {
    this.editor.renderer.scrollCursorIntoView({ row: line, column: 1 }, 0.5);
    this.editorBlurChangeAction();
  }

  scrollToEnd() {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._scrollToEnd(), {
        once: true,
      });
    } else {
      this._scrollToEnd();
    }
  }

  /** @private */
  _scrollToEnd() {
    let lastLine = this.editor.getSession().getLength();
    this.editor.scrollToLine(lastLine);
    this.editorBlurChangeAction();
  }

  findAndSelect(text) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._findAndSelect(text), {
        once: true,
      });
    } else {
      this._findAndSelect(text);
    }
  }

  /** @private */
  _findAndSelect(text) {
    let found = this.editor.find(text);
    if (found) {
      this.editor.renderer.scrollCursorIntoView(found.start, 0.5);
      this.editorBlurChangeAction();
    }
  }

  disableCustomAutocompletion(useDefault) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._disableCustomAutocompletion(useDefault),
        { once: true }
      );
    } else {
      this._disableCustomAutocompletion(useDefault);
    }
  }

  /** @private */
  _disableCustomAutocompletion(useDefault) {
    if (useDefault) {
      this.editor.completers = [
        this.editor.langTools.snippetCompleter,
        this.editor.langTools.keyWordCompleter,
      ];
    } else {
      this.editor.completers = [];
    }
  }

  print(exportType) {
    if (this.editor == undefined) {
      this.addEventListener("editor-ready", () => this._print(exportType), {
        once: true,
      });
    } else {
      this._print(exportType);
    }
  }

  /** @private */
  async _print(exportType) {
    const content = await this._generateExport(exportType);
    this._initializePrint(content);
  }

  setStatusbarIndexing(statusbarIndex) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._setStatusbarIndexing(statusbarIndex),
        {
          once: true,
        }
      );
    } else {
      this._setStatusbarIndexing(statusbarIndex);
    }
  }

  /** @private */
  _setStatusbarIndexing(statusbarIndex) {
    if (statusbarIndex == this._statusbarIndex) return;

    this._statusbarIndex = statusbarIndex;

    for (const child of this.editorStatusbarDiv.childNodes) {
      this.editorStatusbarDiv.removeChild(child);
    }

    this.editor.statusbar = new this.statusBar(
      this.editor,
      this.editorStatusbarDiv,
      this._statusbarIndex
    );

    this.editor.statusbar.updateStatus(this.editor, this._statusbarIndex);
  }

  addStaticWordCompleter(json) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._addStaticWordCompleter(json),
        {
          once: true,
        }
      );
    } else {
      this._addStaticWordCompleter(json);
    }
  }

  /** @private */
  _addStaticWordCompleter(json) {
    const parsed = JSON.parse(json);
    const category = parsed.category;
    const words = parsed.words;

    var staticWordCompleter = {
      getCompletions: function (editor, session, pos, prefix, callback) {
        callback(
          null,
          words.map(function (word) {
            return { name: word, value: word, score: 10, meta: category };
          })
        );
      },
    };

    if (parsed.keepCompleters) {
      this.editor.completers.push(staticWordCompleter);
    } else {
      this.editor.completers = [staticWordCompleter];
    }
  }

  addDynamicWordCompleter(json) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._addDynamicWordCompleter(json),
        {
          once: true,
        }
      );
    } else {
      this._addDynamicWordCompleter(json);
    }
  }

  /** @private */
  _addDynamicWordCompleter(json) {
    const parsed = JSON.parse(json);
    const seperator = parsed.seperator;
    const list = parsed.dynamicWords;
    const keys = Object.keys(list);
    const defaultCallback = keys.map(function (word) {
      return {
        name: word,
        value: word,
        score: 50,
        meta: parsed.category,
      };
    });

    var dynamicCompletion = {
      getCompletions: function (editor, session, pos, prefix, callback) {
        var curLine = session.getDocument().getLine(pos.row);
        var curTokens = curLine.slice(0, pos.column).split(/\s+/);
        var curCmd = curTokens[curTokens.length - 1];
        if (!curCmd) {
          callback(null, defaultCallback);
          return;
        }
        const seperatorReplaced = seperator.replace(
          /[-\/\\^$*+?.()|[\]{}]/g,
          "\\$&"
        );
        var canidates = [];
        const match = curCmd.match(
          new RegExp(`(${keys.join("|")}){1}(?=${seperatorReplaced})`, "i")
        );
        if (match) {
          const keyword = match[0];
          for (var option of list[keyword]) {
            canidates.push(keyword + seperator + option);
          }
          callback(
            null,
            canidates.map(function (option) {
              return {
                name: option,
                value: option,
                score: 100,
                meta: keyword,
              };
            })
          );
        } else {
          callback(null, defaultCallback);
        }
      },
    };

    if (parsed.keepCompleters) {
      this.editor.completers.push(dynamicCompletion);
    } else {
      this.editor.completers = [dynamicCompletion];
    }
  }

  addCustomMode(name, json) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._addCustomMode(name, json),
        {
          once: true,
        }
      );
    } else {
      this._addCustomMode(name, json);
    }
  }

  /** @private */
  _addCustomMode(name, json) {
    let parsed;
    try {
      parsed = JSON.parse(json);
    } catch (e) {
      return;
    }

    var customModeFunction = function () {
      this.$rules = parsed.states;

      this.normalizeRules();
    }

    ace.require("ace/lib/oop").inherits(customModeFunction, ace.require("ace/mode/text_highlight_rules").TextHighlightRules);

    var TextMode = ace.require("ace/mode/text").Mode;

    var customMode = new TextMode();
    customMode.HighlightRules = customModeFunction;

    this._customModes.set(name, customMode);
  }

  setCustomMode(mode) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editor-ready",
        () => this._setCustomMode(mode),
        {
          once: true,
        }
      );
    } else {
      this._setCustomMode(mode);
    }
  }

  /** @private */
  _setCustomMode(mode) {
    if (this._customModes.has(mode)) {
      this.editor.session.setMode(this._customModes.get(mode));
    }
  }

  /** @private */
  _createSelectionObject() {
    let editor = this.editor;
    let selectionObject = editor.selection.getRange();
    selectionObject.index = {
      start: editor.getSession().doc.positionToIndex(selectionObject.start),
      end: editor.getSession().doc.positionToIndex(selectionObject.end),
    };
    selectionObject.selectedText = editor.getSelectedText();
    return selectionObject;
  }

  /** @private */
  _createCursorObject() {
    let editor = this.editor;
    let cursorObject = this.editor.getCursorPosition();
    let index = editor.getSession().doc.positionToIndex(cursorObject);
    cursorObject.index = index;
    return cursorObject;
  }

  /** @private */
  _vScrollbarHandler() {
    var vScrollbar = this.shadowRoot.querySelector(".ace_scrollbar-v");
    if (vScrollbar.style.display === "none") {
      this.editorStatusbarDiv.style.right = "4px";
    } else {
      let width = vScrollbar.offsetWidth - vScrollbar.clientWidth;
      if (width === undefined || width === null) {
        width = 20;
      }
      this.editorStatusbarDiv.style.right = width + 4 + "px";
    }
  }

  /** @private */
  _hScrollbarHandler() {
    var hScrollbar = this.shadowRoot.querySelector(".ace_scrollbar-h");
    if (hScrollbar.style.display === "none") {
      this.editorStatusbarDiv.style.bottom = "4px";
    } else {
      let height = hScrollbar.offsetHeight - hScrollbar.clientHeight;
      if (height === undefined || height === null) {
        height = 20;
      }
      this.editorStatusbarDiv.style.bottom = height + 4 + "px";
    }
  }

  /** @private */
  _generateExport(exportType) {
    const printCSS = `@media print {
  @page {
    margin-top: 0;
    margin-bottom: 0;
  }
  body {
    padding-top: 72px;
    padding-bottom: 72px ;
  }
}`;

    return new Promise((resolve) => {
      if (exportType.toLowerCase() == "flat") {
        let currentVal = this.editorValue;

        currentVal = currentVal.replace(/[\u00A0-\u9999<>\&]/g, function (i) {
          return "&#" + i.charCodeAt(0) + ";";
        });
        currentVal = currentVal.replace(new RegExp("\r?\n", "g"), "<br/>");
        currentVal = currentVal.replace(/\s/g, "&nbsp;");

        var htmlContent = `<!DOCTYPE html>
<html>
  <head>
  <title>Ace Export</title>
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro&display=swap" rel="stylesheet">
    <style>
      #aceRaw {
        font-family: 'Source Code Pro', monospace;
        font-size: 12px;
      }
      ${printCSS}
    </style>
  </head>
  <body>
    <div id="aceRaw">${currentVal}</div>
  </body>
</html>`;

        resolve(htmlContent);
      } else if (exportType.toLowerCase() == "rich") {
        let self = this;
        ace
          .require("ace/config")
          .loadModule("ace/ext/static_highlight", function (m) {
            var result = m.renderSync(
              self.editor.getValue(),
              self.editor.session.getMode(),
              self.editor.renderer.theme
            );

            var htmlContent = `<!DOCTYPE html>
<html>
  <head>
    <title>Ace Export</title>
    <style>
      ${result.css}
      ${printCSS}
    </style>
  </head>
    <body>
      ${result.html}
    </body>
</html>`;

            resolve(htmlContent);
          });
      }
    });
  }

  /** @private */
  _initializePrint(content) {
    const popupWidth = 742;
    const popupHeight = 600;

    const dualScreenLeft =
      window.screenLeft !== undefined ? window.screenLeft : window.screenX;
    const dualScreenTop =
      window.screenTop !== undefined ? window.screenTop : window.screenY;

    const width = window.innerWidth
      ? window.innerWidth
      : document.documentElement.clientWidth
        ? document.documentElement.clientWidth
        : screen.width;
    const height = window.innerHeight
      ? window.innerHeight
      : document.documentElement.clientHeight
        ? document.documentElement.clientHeight
        : screen.height;

    const systemZoom = width / window.screen.availWidth;
    const left = (width - popupWidth) / 2 / systemZoom + dualScreenLeft;
    const top = (height - popupHeight) / 2 / systemZoom + dualScreenTop;

    var mywindow = window.open(
      "",
      "_blank",
      `toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,height=${popupHeight / systemZoom
      },width=${popupWidth / systemZoom},top=${top},left=${left}`
    );

    if (!mywindow || mywindow.closed || typeof mywindow.closed == "undefined") {
      return;
    }

    mywindow.document.write(content);
    mywindow.document.close();

    mywindow.onload = function () {
      mywindow.focus();
      mywindow.print();
      mywindow.close();
    };
  }
}

customElements.define("lit-ace", LitAce);
