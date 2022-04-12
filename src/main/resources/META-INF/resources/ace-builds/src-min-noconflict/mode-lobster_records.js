ace.define("ace/mode/lobster_records", function (require, exports, module) {
  var oop = require("ace/lib/oop");
  var TextMode = require("ace/mode/text").Mode;
  var LobsterRecordsHighlightRules =
    require("ace/mode/lobster_record_highlight_rules").LobsterRecordsHighlightRules;

  var Mode = function () {
    this.HighlightRules = LobsterRecordsHighlightRules;
  };
  oop.inherits(Mode, TextMode);

  (function () {
    // Extra logic goes here. (see below)
  }.call(Mode.prototype));

  exports.Mode = Mode;
});

ace.define(
  "ace/mode/lobster_record_highlight_rules",
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

    var LobsterRecordsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "keyword.operator",
            regex: "[rR]ecord\\s\\d+",
          },
          {
            token: "text",
            regex: "(\\((?=.+\\)))",
            next: "handleOpeningBrace",
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
            token: "constant",
            regex: "[Ll]ists:|[Mm]aps:|[Vv]ariables:",
          },
          {
            token: "support.constant",
            regex: "(MSG_CALL_VAR_SYS_)",
          },
          {
            token: "support.constant",
            regex: "(MSG_CALL_)",
          },
          {
            token: "support.constant",
            regex: "(VAR_SYS_)",
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

        handleOpeningBrace: [
          {
            // Timestamp
            token: "constant.numeric",
            regex:
              "((?<=\\()[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}\\.{0,1}[0-9]+(?=\\)))",
            next: "handleClosingBrace",
          },
          {
            token: "constant.numeric",
            regex: "((?<=\\()-?[0-9]\\d*(?=\\)))",
            next: "handleClosingBrace",
          },
          {
            token: "constant.language",
            regex: "((?<=\\()[Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee](?=\\)))",
            next: "handleClosingBrace",
          },
          {
            token: "constant.language",
            regex: "(.*(?=\\)))",
            next: "handleClosingBrace",
          },
        ],

        handleClosingBrace: [
          {
            token: "text",
            regex: "(\\))",
            next: "start",
          },
        ],
      };
    };

    oop.inherits(LobsterRecordsHighlightRules, TextHighlightRules);

    exports.LobsterRecordsHighlightRules = LobsterRecordsHighlightRules;
  }
);
