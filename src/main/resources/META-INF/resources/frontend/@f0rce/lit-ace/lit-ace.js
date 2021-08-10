/**
@license MIT
Copyright 2021 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html, css } from "lit-element";

import "@f0rce/ace-builds/src-noconflict/ace.js";
import "@f0rce/ace-builds/src-noconflict/ext-language_tools.js";
import "@f0rce/ace-builds/src-noconflict/ext-static_highlight.js";
import "@f0rce/ace-builds/src-noconflict/ext-beautify.js";
import "@f0rce/ace-builds/src-noconflict/ext-statusbar.js";

class LitAce extends LitElement {
  static get properties() {
    return {
      theme: { type: String, reflect: true },
      mode: { type: String, reflect: true },
      value: { type: String },
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
      selection: { type: String },
      useWorker: { type: Boolean },
      customAutoCompletion: { type: String },
      marker: { type: String },
      markerList: { type: Array },
      rmMarker: { type: String },
      cursorPosition: { type: String },
      statusbarEnabled: { type: Boolean },
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
    this.selection = "0|0|0|0|-";
    this.useWorker = false;
    this.customAutoCompletion = "||";
    this.marker = "-|-|-|-|-|-";
    this.markerList = { markers: [] };
    this.rmMarker = "";
    this.cursorPosition = "0|0|-";
    this.statusbarEnabled = true;
  }

  static get styles() {
    return css`
      :host {
        display: block;
        width: 100%;
        height: 100%;
      }
      #editor {
        border: 1px solid var(--lumo-contrast-20pct);
        border-radius: var(--lumo-border-radius);
        @apply --ace-widget-editor;
      }
      #editorStatusbar {
        z-index: 9 !important;
        position: absolute !important;
        right: 4px;
        bottom: 4px;
      }
      .ace_status-indicator {
        background-color: #4e4e4e;
        color: white;
        text-align: right;
        border: 1px solid #292929;
        border-radius: 7px;
        padding-right: 3px;
        padding-left: 3px;
        padding-bottom: 1px;
        font-size: small;
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

    this.editorDiv = this.shadowRoot.getElementById("editor");
    this.editorContainerDiv = this.shadowRoot.getElementById("editorContainer");
    this.editorStatusbarDiv = this.shadowRoot.getElementById("editorStatusbar");

    this.editor = ace.edit(this.editorDiv);
    this.editor.langTools = ace.require("ace/ext/language_tools");
    this.editor.staticHighlight = ace.require("ace/ext/static_highlight");
    this.editor.beautify = ace.require("ace/ext/beautify");
    this.statusBar = ace.require("ace/ext/statusbar").StatusBar;

    let self = this;
    this.observer = new ResizeObserver(function (entries) {
      entries.forEach(function (entry) {
        self.resizeEditor();
      });
    });

    this.observer.observe(this.editorContainerDiv);

    this.initializeEditor();
  }

  updated(changedProperties) {
    changedProperties.forEach((oldValue, propName) => {
      let funcToCall = propName + "Changed";
      if (typeof this[funcToCall] == "function") {
        // This line if freaking epic
        this[funcToCall]();
      }
    });
  }

  initializeEditor() {
    let editor = this.editor;
    this.head = document.head;

    this.injectStyle("#ace_editor\\.css");

    ace.config.set("basePath", this.baseUrl);
    ace.config.set("modePath", this.baseUrl);
    ace.config.set("themePath", this.baseUrl);
    ace.config.set("workerPath", this.baseUrl);

    this.editorValue = "";
    this._selection = this.selection;
    this._cursorPosition = this.cursorPosition;

    // blur
    editor.on("blur", () => this.editorBlurChangeAction());

    // selection change (with simple debounce) - 250ms delay
    var selectionTimeoutId = false;
    editor.selection.on("changeSelection", () => {
      clearTimeout(selectionTimeoutId);
      selectionTimeoutId = setTimeout(() => {
        this.updateSelectionAction(true);
      }, 250);
    });

    if (this.initialFocus) {
      this.editor.focus();
    }

    editor.$blockScrolling = Infinity;

    this.themeChanged();
    this.modeChanged();

    editor.setShowPrintMargin(this.showPrintMargin);
    editor.setShowInvisibles(this.showInvisibles);
    editor.getSession().setUseWorker(this.useWorker);

    editor.renderer.setShowGutter(this.showGutter);
    editor.renderer.setOption("displayIndentGuides", this.displayIndentGuides);

    // Setting content

    // Trying to get content as HTML content
    let htmlContent = this.innerHTML.trim();
    // console.debug("[ace-widget] HTML content found", htmlContent);

    // If we have a value in the `value` attribute, we keep it, else we use the HTML content
    if (this.value === undefined) {
      this.value = htmlContent;
      // console.debug("[ace-widget] initializeEditor - using HTML content as value", this.value)
    } else {
      // Forcing a valueChanged() call, because the initial one din't do anything as editor wasn't created yet
      this.valueChanged();
    }

    editor.setOptions({
      autoScrollEditorIntoView: true,
      enableBasicAutocompletion: this.enableAutocompletion,
      enableLiveAutocompletion: this.enableLiveAutocompletion,
      placeholder: this.placeholder,
    });

    this.editor.statusbar = new this.statusBar(
      this.editor,
      this.editorStatusbarDiv
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

    this.dispatchEvent(new Event("editorInitialized"));
  }

  focusEditor() {
    if (this.editor == undefined) {
      this.addEventListener("editorInitialized", (e) => this.editor.focus(), {
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
    if (this.editor == undefined) {
      return;
    }
    this.editor.session.setMode("ace/mode/" + this.mode);
  }

  valueChanged() {
    if (this.editor == undefined) {
      return;
    }
    if (this.editorValue != this.value) {
      this.editorValue = this.value;
      this.editor.clearSelection();
      this.editor.resize();
    }
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
    this._value = value;
    this.editor.setValue(value);
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

  selectionChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.selection == "0|0|0|0|-") {
      return;
    }

    const selection = this.selection.split("|");
    const rowStart = parseInt(selection[0]);
    const from = parseInt(selection[1]);
    const rowEnd = parseInt(selection[2]);
    const to = parseInt(selection[3]);

    const Range = ace.require("ace/range").Range;
    this.editor.selection.setRange(new Range(rowStart, from, rowEnd, to));

    const set = rowFrom + "|" + from + "|" + rowEnd + "|" + to + "|-";
    this._selection = set;
  }

  customAutoCompletionChanged() {
    if (this.editor == undefined) {
      return;
    }
    const rawString = String(this.customAutoCompletion);
    const rawSplit = rawString.split("|");
    if (rawString === "||") {
      this.editor.completers = [
        this.editor.langTools.snippetCompleter,
        this.editor.langTools.keyWordCompleter,
      ];
    } else {
      var keepCurrentCompleters = rawSplit[2] === "true";
      if (!keepCurrentCompleters) {
        var staticWordCompleter = {
          getCompletions: function (editor, session, pos, prefix, callback) {
            const wordList = rawSplit[1].split(",");
            callback(
              null,
              wordList.map(function (word) {
                return {
                  caption: word,
                  value: word,
                  meta: rawSplit[0],
                };
              })
            );
          },
        };
        this.editor.completers = [staticWordCompleter];
      } else {
        var staticWordCompleter = {
          getCompletions: function (editor, session, pos, prefix, callback) {
            const wordList = rawSplit[1].split(",");
            callback(
              null,
              wordList.map(function (word) {
                return {
                  caption: word,
                  value: word,
                  meta: rawSplit[0],
                };
              })
            );
          },
        };
        this.editor.completers = [
          staticWordCompleter,
          this.editor.langTools.snippetCompleter,
          this.editor.langTools.keyWordCompleter,
        ];
      }
    }
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

  editorBlurChangeAction() {
    this.updateSelectionAction(false);
    this.dispatchEvent(
      new CustomEvent("editor-blur", {
        detail: {
          value: this.editorValue,
          selection: this._selection,
          cursorPosition: this._cursorPosition,
          selectedText: this.editor.getSelectedText(),
        },
      })
    );
  }

  updateSelectionAction(sendEvent) {
    const range = this.editor.selection.getRange();
    const rowFrom = String(range.start.row);
    const from = String(range.start.column);
    const rowTo = String(range.end.row);
    const to = String(range.end.column);

    const set = rowFrom + "|" + from + "|" + rowTo + "|" + to + "|-";
    this._selection = set;

    const cursorPosition = this.editor.getCursorPosition();
    const row = String(cursorPosition.row);
    const column = String(cursorPosition.column);

    this._cursorPosition = row + "|" + column + "|-";

    if (sendEvent == true) {
      this.dispatchEvent(
        new CustomEvent("editor-selection", {
          detail: {
            selection: this._selection,
            selectedText: this.editor.getSelectedText(),
            cursorPosition: this._cursorPosition,
          },
        })
      );
    }
  }

  resizeEditor() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.resize();
  }

  forceSync() {
    this.updateSelectionAction(false);
    this.dispatchEvent(
      new CustomEvent("force-sync", {
        detail: {
          value: this.editorValue,
          selection: this._selection,
          cursorPosition: this._cursorPosition,
          selectedText: this.editor.getSelectedText(),
        },
      })
    );
  }

  insertText(row, column, text) {
    let positionObject = { row, column };
    this.editor.session.insert(positionObject, text);
    editorBlurChangeAction();
  }

  calculateCursorPositionFromIndex(index) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editorInitialized",
        (e) => this._calculateCursorPositionFromIndex(index),
        { once: true }
      );
    } else {
      this._calculateCursorPositionFromIndex(index);
    }
  }

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
        "editorInitialized",
        (e) => this._calculateSelectionByIndices(from, to),
        { once: true }
      );
    } else {
      this._calculateSelectionByIndices(from, to);
    }
  }

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
        this.editorBlurChangeAction();
        return;
      }
      let currentLength = rowLengthObject[i].totalLength;
      if (i === 0) {
        if (from <= currentLength && to <= currentLength) {
          this.editor.selection.setRange(new Range(rowFrom, from, rowTo, to));
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
        this.editorBlurChangeAction();
        return;
      }
    }
  }

  replaceTextAtSelection(text) {
    if (this.editor == undefined) {
      this.addEventListener(
        "editorInitialized",
        (e) => this._replaceTextAtSelection(text),
        { once: true }
      );
    } else {
      this._replaceTextAtSelection(text);
    }
  }

  _replaceTextAtSelection(text) {
    this.editor.session.replace(this.editor.selection.getRange(), text);
    this.editorBlurChangeAction();
  }

  generateHTML(raw) {
    if (raw == true) {
      let currentVal = this.editorValue;

      currentVal = currentVal.replace(/[\u00A0-\u9999<>\&]/g, function (i) {
        return "&#" + i.charCodeAt(0) + ";";
      });
      currentVal = currentVal.replace(new RegExp("\r?\n", "g"), "<br/>");
      currentVal = currentVal.replace(/\s/g, "&nbsp;");

      var htmlContent = `<!DOCTYPE html>
<html>
  <head>
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro&display=swap" rel="stylesheet"> 
    <style>
      #aceRaw {
        font-family: 'Source Code Pro', monospace;
        font-size: 12px;
      }     
    </style>
  </head>
  <body>
    <div id="aceRaw">${currentVal}</div>
  </body>
</html>`;

      this.dispatchEvent(
        new CustomEvent("html-generated", {
          detail: {
            html: htmlContent,
          },
        })
      );
    } else {
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
    <style>
      ${result.css} 
    </style>
  </head>
    <body>
      ${result.html}
    </body>
</html>`;

          self.dispatchEvent(
            new CustomEvent("html-generated", {
              detail: {
                html: htmlContent,
              },
            })
          );
        });
    }
  }

  unfold() {
    this.editor.getSession().unfold();
  }

  foldAll() {
    this.editor.getSession().foldAll();
  }

  foldAll(startRow) {
    this.editor.getSession().foldAll(startRow);
  }

  beautify() {
    this.editor.beautify.beautify(this.editor.session);
  }

  _vScrollbarHandler() {
    var vScrollbar = this.shadowRoot.querySelector(".ace_scrollbar-v");
    if (vScrollbar.style.display === "none") {
      this.editorStatusbarDiv.style.right = "4px";
    } else {
      this.editorStatusbarDiv.style.right = "24px";
    }
  }

  _hScrollbarHandler() {
    var hScrollbar = this.shadowRoot.querySelector(".ace_scrollbar-h");
    if (hScrollbar.style.display === "none") {
      this.editorStatusbarDiv.style.bottom = "4px";
    } else {
      this.editorStatusbarDiv.style.bottom = "24px";
    }
  }

  /**
   * Injects a style element into lit-ace's shadow root
   * @param {CSSSelector} selector for an element in the same shadow tree or document as `lit-ace`
   */
  injectStyle(selector) {
    const lightStyle =
      this.getRootNode().querySelector(selector) ||
      document.querySelector(selector);
    this.shadowRoot.appendChild(lightStyle.cloneNode(true));
  }
}

customElements.define("lit-ace", LitAce);
