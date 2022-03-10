ace.define("ace/mode/lobster_records", function (require, exports, module) {
  "use strict";

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
          regex: "(\\((?=.+\\)))",
          next: "handleOpeningBrace",
        },
        {
          token: "constant.numeric",
          regex: "\\d",
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
          next: "no_regex",
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
          token: "constant.numeric",
          regex:
            "([0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}\\.{0,1}[0-9]*)",
          next: "handleClosingBrace",
        },
        {
          token: "constant.numeric",
          regex: "(-[0-9]+|$)",
          next: "handleClosingBrace",
        },
        {
          token: "constant.numeric",
          regex: "([0-9]+|$)",
          next: "handleClosingBrace",
        },
        {
          token: "constant.language",
          regex: "((false|true)|$)",
          next: "handleClosingBrace",
        },
        {
          defaultToken: "text",
        },
      ],

      handleClosingBrace: [
        {
          token: "keyword.operator",
          regex: "(\\))",
          next: "start",
        },
      ],
    };
  };

  oop.inherits(LobsterRecordsHighlightRules, TextHighlightRules);

  exports.LobsterRecordsHighlightRules = LobsterRecordsHighlightRules;
});
