ace.define("ace/mode/lobster_srv_logs", function (require, exports, module) {
  var oop = require("ace/lib/oop");
  var TextMode = require("ace/mode/text").Mode;
  var LobsterSRVLogsHighlightRules =
    require("ace/mode/lobster_srv_logs_highlight_rules").LobsterSRVLogsHighlightRules;

  var Mode = function () {
    this.HighlightRules = LobsterSRVLogsHighlightRules;
  };
  oop.inherits(Mode, TextMode);

  (function () {
    // Extra logic goes here. (see below)
  }.call(Mode.prototype));

  exports.Mode = Mode;
});

ace.define(
  "ace/mode/lobster_srv_logs_highlight_rules",
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

    var LobsterSRVLogsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "comment",
            regex: "-{3,}",
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
            regex: "^(\\d{2}:\\d{2}:\\d{2}){1}\\s+(?=(([a-zA-Z]+)?|([a-zA-Z]+:.*?)+(?!\\S)))?",
            next: "handleType",
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

        handleType: [
          {
            token: "meta.tag",
            regex: "(([a-zA-Z]+)?|([a-zA-Z]+:.*?)+(?!\\S))\\s+(?=(\\w+:?)+)",
            next: "handleOccurance",
          },
          {
            defaultToken: "text",
          },
        ],

        handleOccurance: [
          {
            token: "constant.language",
            regex: "([a-zA-Z]+:.*?)+(?!\\S)",
            next: "handleError",
          },
          {
            defaultToken: "text",
          },
        ],

        handleError: [
          {
            token: "constant.numeric",
            regex: "(.*)?$",
            next: "start",
          },
          {
            defaultToken: "text",
          },
        ],
      };
    };

    oop.inherits(LobsterSRVLogsHighlightRules, TextHighlightRules);

    exports.LobsterSRVLogsHighlightRules = LobsterSRVLogsHighlightRules;
  }
);
