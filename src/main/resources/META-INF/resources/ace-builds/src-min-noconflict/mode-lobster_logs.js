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

    var LobsterLogsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "keyword.other.unit",
            regex: "(\\[[mM]:(.*?)\\])",
          },
          {
            token: "meta.tag",
            regex: "(\\[[pP]os=\\d*\\])",
          },
          {
            token: "constant.language",
            regex: "([iI]nput)",
          },
          {
            token: "constant.numeric",
            regex: "(\\s[rR]esult)",
          },
        ],
      };
    };

    oop.inherits(LobsterLogsHighlightRules, TextHighlightRules);

    exports.LobsterLogsHighlightRules = LobsterLogsHighlightRules;
  }
);
