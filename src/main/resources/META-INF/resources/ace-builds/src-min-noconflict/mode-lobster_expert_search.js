ace.define(
  "ace/mode/lobster_expert_search",
  function (require, exports, module) {
    var oop = require("ace/lib/oop");
    var TextMode = require("ace/mode/text").Mode;
    var Behaviour = require("ace/mode/behaviour/cstyle").CstyleBehaviour;
    var LobsterExpertSearchsHighlightRules =
      require("ace/mode/lobster_expert_search_highlight_rules").LobsterExpertSearchsHighlightRules;

    var Mode = function () {
      this.HighlightRules = LobsterExpertSearchsHighlightRules,
      this.$behaviour = new Behaviour()
    };
    oop.inherits(Mode, TextMode);

    (function () {
      // Extra logic goes here. (see below)
    }.call(Mode.prototype));

    exports.Mode = Mode;
  }
);

ace.define(
  "ace/mode/lobster_expert_search_highlight_rules",
  function (require, exports, module) {
    var oop = require("ace/lib/oop");
    var lang = require("ace/lib/lang");
    var TextHighlightRules =
      require("ace/mode/text_highlight_rules").TextHighlightRules;

    var keywordsIC = lang.arrayToMap(
      "search|equals|contains|startswith|endswith|regex".split("|")
    );
    var keywords = lang.arrayToMap("AND|OR".split("|"));
    var LobsterExpertSearchsHighlightRules = function () {
      this.$rules = {
        start: [
          {
            token: "string",
            regex: '("(?=.))',
            next: "handleStringDouble",
          },
          {
            token: "string",
            regex: "('(?=.))",
            next: "handleStringSingle",
          },
          {
            token: function (value) {
              if (keywordsIC.hasOwnProperty(value.toLowerCase())) {
                return "keyword";
              } else if (keywords.hasOwnProperty(value)) {
                return "keyword";
              } else {
                return "text";
              }
            },
            regex: "\\-?[a-zA-Z_][a-zA-Z0-9_\\-]*",
          },
          {
            token: "keyword.operator",
            regex: "(\\&\\&|\\|\\||\\(|\\)|\\!)",
          },
          {
            defaultToken: "text",
          },
        ],

        handleStringDouble: [
          {
            token: "constant.character.escape",
            regex: "\\\\.",
          },
          {
            token: "string",
            regex: '[^"\\\\]+',
            merge: true,
          },
          {
            token: "string",
            regex: "\\\\$",
            next: "handleStringDouble",
            merge: true,
          },
          {
            token: "string",
            regex: '"|$',
            next: "start",
            merge: true,
          },
        ],

        handleStringSingle: [
          {
            token: "constant.character.escape",
            regex: "\\\\.",
          },
          {
            token: "string",
            regex: "[^'\\\\]+",
            merge: true,
          },
          {
            token: "string",
            regex: "\\\\$",
            next: "handleStringSingle",
            merge: true,
          },
          {
            token: "string",
            regex: "'|$",
            next: "start",
            merge: true,
          },
        ],
      };
    };

    oop.inherits(LobsterExpertSearchsHighlightRules, TextHighlightRules);

    exports.LobsterExpertSearchsHighlightRules =
      LobsterExpertSearchsHighlightRules;
  }
);
