ace.define("ace/mode/lobster_dc_logs", function (require, exports, module) {
  var oop = require("ace/lib/oop");
  var TextMode = require("ace/mode/text").Mode;
  var LobsterDCLogsHighlightRules =
    require("ace/mode/lobster_dc_logs_highlight_rules").LobsterDCLogsHighlightRules;

  var Mode = function () {
    this.HighlightRules = LobsterDCLogsHighlightRules;
  };
  oop.inherits(Mode, TextMode);

  (function () {
    // Extra logic goes here. (see below)
  }.call(Mode.prototype));

  exports.Mode = Mode;
});

ace.define(
  "ace/mode/lobster_dc_logs_highlight_rules",
  function (require, exports, module) {
    var oop = require("ace/lib/oop");
    var TextHighlightRules =
      require("ace/mode/text_highlight_rules").TextHighlightRules;

    var escapedRe =
      "\\\\(?:x[0-9a-fA-F]{2}|" + // hex
      "u[0-9a-fA-F]{4}|" + // unicode
      "u{[0-9a-fA-F]{1,6}}|" + // es6 unicode
      "[0-2][0-7]{0,2}|" + // oct
      "3[0-7][0-7]?|" + // oct
      "[4-7][0-7]?|" + //oct
      ".)";

    var LobsterDCLogsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "comment",
            regex: "^\\d*\.\\d*\\.\\d*\\s\\d*:\\d*:\\d*\\.\\d*(?!\\d)",
          },
          {
            token: "string",
            regex: "'(?=.)",
            next: "qstring",
          },
          {
            token: "string",
            regex: '"(?=.)',
            next: "qqstring",
          },
          {
            token: "keyword",
            regex: "(\\[.*?\\])",
          },
          {
            token: "constant.language",
            regex: "([nN]ested [eE]xception [iI]s:)",
          },
        ],

        qqstring: [
          {
            token: "constant.language.escape",
            regex: escapedRe,
          },
          {
            token: "string",
            regex: "\\\\$",
            consumeLineEnd: true,
          },
          {
            token: "string",
            regex: '"|$',
            next: "start",
          },
          {
            defaultToken: "string",
          },
        ],

        qstring: [
          {
            token: "constant.language.escape",
            regex: escapedRe,
          },
          {
            token: "string",
            regex: "\\\\$",
            consumeLineEnd: true,
          },
          {
            token: "string",
            regex: "'|$",
            next: "start",
          },
          {
            defaultToken: "string",
          },
        ],
      };
    };

    oop.inherits(LobsterDCLogsHighlightRules, TextHighlightRules);

    exports.LobsterDCLogsHighlightRules = LobsterDCLogsHighlightRules;
  }
);
