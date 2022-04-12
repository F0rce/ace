ace.define("ace/mode/lobster_logs", function (require, exports, module) {
  var oop = require("ace/lib/oop");
  var TextMode = require("ace/mode/text").Mode;
  var LobsterLogsHighlightRules =
    require("ace/mode/lobster_logs_highlight_rules").LobsterLogsHighlightRules;

  var Mode = function () {
    this.HighlightRules = LobsterLogsHighlightRules;
  };
  oop.inherits(Mode, TextMode);

  (function () {
    // Extra logic goes here. (see below)
  }.call(Mode.prototype));

  exports.Mode = Mode;
});

ace.define(
  "ace/mode/lobster_logs_highlight_rules",
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

    var LobsterLogsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "comment",
            regex: "^\\[.*?\\]",
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
            regex: "(\\[[mM]:(.*?)\\])",
          },
          {
            token: "meta.tag",
            regex: "(\\[[pP]os=\\d*\\])",
          },
          {
            token: "constant.language",
            regex: "([iI]nput\\([^)]+\\))",
          },
          {
            token: "constant.numeric",
            regex: "([rR]esult)",
          },
          {
            token: "invalid",
            regex: "(\\[[eE]:(.*?)\\])",
          },
          {
            token: "comment",
            regex: "([nN]ested [eE]xception [iI]s:)",
          },
          {
            token: "constant.language",
            regex: "([fF]ield=(?=(.*?)))",
          },
          {
            token: "constant.language",
            regex: "([rR]ecord=(?=(.*?)))",
          },
          {
            token: "constant.language",
            regex: "([iI]teration=(?=(.*?)))",
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

    oop.inherits(LobsterLogsHighlightRules, TextHighlightRules);

    exports.LobsterLogsHighlightRules = LobsterLogsHighlightRules;
  }
);
