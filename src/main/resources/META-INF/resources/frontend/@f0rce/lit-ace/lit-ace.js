/**
@license MIT
Copyright 2021 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html, css } from "lit-element";

import "ace-builds/src-noconflict/ace.js";
import "ace-builds/src-noconflict/ext-language_tools.js";

var editorFocus = function () {
  var _self = this;
  setTimeout(function () {
    if (!_self.isFocused()) _self.textInput.focus();
  });
  this.textInput.$focusScroll = "browser";
  this.textInput.focus();
};

class LitAce extends LitElement {
  static get properties() {
    return {
      theme: { type: String, reflect: true },
      mode: { type: String, reflect: true },
      value: { type: String },
      readonly: { type: Boolean },
      softtabs: { type: Boolean },
      wrap: { type: Boolean },
      fontSize: { type: Number },
      tabSize: { type: Number },
      enableLiveAutocompletion: { type: Boolean },
      enableAutocompletion: { type: Boolean },
      initialFocus: { type: Boolean },
      placeholder: { type: String },
      baseUrl: { type: String },
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
    };
  }

  constructor() {
    super();
    this.theme = "eclipse";
    this.mode = "javascript";
    this.readonly = false;
    this.softtabs = true;
    this.wrap = false;
    this.fontSize = "14px";
    this.tabSize = 4;
    this.enableLiveAutocompletion = false;
    this.enableAutocompletion = false;
    this.initialFocus = false;
    this.placeholder = "";
    this.baseUrl = "";
    this.showPrintMargin = false;
    this.showInvisibles = false;
    this.showGutter = true;
    this.highlightActiveLine = true;
    this.displayIndentGuides = false;
    this.highlightSelectedWord = false;
    this.selection = "0|0|0|0|-";
    this.useWorker = false;
    this.customAutoCompletion = "|";
    this.marker = "-|-|-|-|-|-";
    this.markerList = { markers: [] };
    this.rmMarker = "";
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
        color: var(--lumo-shade-50pct) !important;
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
      </div>
    `;
  }

  async firstUpdated(changedProperties) {
    let baseUrl =
      this.baseUrl || `${this.importPath}../../ace-builds/src-min-noconflict/`;

    if (!ace) {
      await import(`${baseUrl}ace.js`);
    }

    if (!ace.require("ace/ext/language_tools")) {
      await import(`${baseUrl}ext-language_tools.js`);
    }

    this.editorDiv = this.shadowRoot.getElementById("editor");
    this.editorContainerDiv = this.shadowRoot.getElementById("editorContainer");

    this.editor = ace.edit(this.editorDiv);
    this.editor.focus = editorFocus;
    this.editor.langTools = ace.require("ace/ext/language_tools");

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
        this[funcToCall]();
      }
    });
  }

  initializeEditor() {
    let editor = this.editor;
    this.head = document.head;

    this.injectStyle("#ace_editor\\.css");

    let baseUrl =
      this.baseUrl || `${this.importPath}../../ace-builds/src-min-noconflict/`;

    ace.config.set("basePath", baseUrl);
    ace.config.set("modePath", baseUrl);
    ace.config.set("themePath", baseUrl);
    ace.config.set("workerPath", baseUrl);

    this.editorValue = "";
    this._selection = this.selection;

    editor.on("blur", () => this.editorBlurChangeAction());
    editor.selection.on("changeSelection", () =>
      this.updateSelectionAction(true)
    );
    this.session = editor.getSession();

    if (this.initialFocus) {
      editor.focus();
    }

    editor.$blockScrolling = Infinity;

    this.themeChanged();
    this.modeChanged();

    editor.setShowPrintMargin(this.showPrintMargin);
    editor.setShowInvisibles(this.showInvisibles);
    editor.setDisplayIndentGuides(this.displayIndentGuides);
    editor.getSession().setUseWorker(this.useWorker);

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
      showGutter: this.showGutter,
      enableBasicAutocompletion: this.enableAutocompletion,
      enableLiveAutocompletion: this.enableLiveAutocompletion,
      placeholder: this.placeholder,
    });
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
    this.session.setMode("ace/mode/" + this.mode);
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
  }

  customAutoCompletionChanged() {
    if (this.editor == undefined) {
      return;
    }
    const rawString = String(this.customAutoCompletion);
    const rawSplit = rawString.split("|");
    if (rawSplit[1] == "") {
      this.editor.completers = [
        this.editor.langTools.snippetCompleter,
        this.editor.langTools.keyWordCompleter,
      ];
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
      this.editor.completers = [staticWordCompleter];
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
    const _range = this.session.addMarker(
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

  editorBlurChangeAction() {
    this.updateSelectionAction(false);
    this.dispatchEvent(
      new CustomEvent("editor-blur", {
        detail: {
          value: this.editorValue,
          selection: this._selection,
        },
      })
    );
    this.selection = this._selection;
  }

  updateSelectionAction(sendEvent) {
    const range = this.editor.selection.getRange();
    const rowFrom = String(range.start.row);
    const from = String(range.start.column);
    const rowTo = String(range.end.row);
    const to = String(range.end.column);

    const set = rowFrom + "|" + from + "|" + rowTo + "|" + to + "|-";
    this._selection = set;

    if (sendEvent == true) {
      this.dispatchEvent(
        new CustomEvent("editor-selection", {
          detail: {
            selection: this._selection,
            selectedText: this.editor.getSelectedText(),
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
    this.dispatchEvent(
      new CustomEvent("force-sync", {
        detail: {
          value: this.editorValue,
          selection: this._selection,
          selectedText: this.editor.getSelectedText(),
        },
      })
    );
  }

  /**
   * Injects a style element into ace-widget's shadow root
   * @param {CSSSelector} selector for an element in the same shadow tree or document as `ace-widget`
   */
  injectStyle(selector) {
    const lightStyle =
      this.getRootNode().querySelector(selector) ||
      document.querySelector(selector);
    this.shadowRoot.appendChild(lightStyle.cloneNode(true));
  }
}

customElements.define("lit-ace", LitAce);
