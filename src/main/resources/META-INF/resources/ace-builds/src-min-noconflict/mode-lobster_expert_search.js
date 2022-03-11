ace.define(
  "ace/mode/lobster_expert_search",
  function (require, exports, module) {
    var oop = require("ace/lib/oop");
    var TextMode = require("ace/mode/text").Mode;
    var Behaviour = require("ace/mode/behaviour/cstyle").CstyleBehaviour;
    var LobsterExpertSearchsHighlightRules =
      require("ace/mode/lobster_expert_search_highlight_rules").LobsterExpertSearchsHighlightRules;

    var Mode = function () {
      (this.HighlightRules = LobsterExpertSearchsHighlightRules),
        (this.$behaviour = new Behaviour());
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

    var escapedRe =
      "\\\\(?:x[0-9a-fA-F]{2}|" + // hex
      "u[0-9a-fA-F]{4}|" + // unicode
      "u{[0-9a-fA-F]{1,6}}|" + // es6 unicode
      "[0-2][0-7]{0,2}|" + // oct
      "3[0-7][0-7]?|" + // oct
      "[4-7][0-7]?|" + //oct
      ".)";
    var LobsterExpertSearchsHighlightRules = function () {
      this.$rules = {
        start: [
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

    oop.inherits(LobsterExpertSearchsHighlightRules, TextHighlightRules);

    exports.LobsterExpertSearchsHighlightRules =
      LobsterExpertSearchsHighlightRules;
  }
);
