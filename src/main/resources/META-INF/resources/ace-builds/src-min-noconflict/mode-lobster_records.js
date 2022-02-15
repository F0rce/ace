ace.define("ace/mode/lobster_records", function (require, exports, module) {
  var oop = require("ace/lib/oop");
  var TextMode = require("ace/mode/text").Mode;
  var LobsterRecordsHighlightRules =
    require("ace/mode/lobster_records_highlight_rules").LobsterRecordsHighlightRules;

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
  "ace/mode/lobster_records_highlight_rules",
  function (require, exports, module) {
    var oop = require("ace/lib/oop");
    var TextHighlightRules =
      require("ace/mode/text_highlight_rules").TextHighlightRules;

    var LobsterRecordsHighlightRules = function () {
	  this.$rules = {
        "start": [
          {
		    token: "constant",
		    regex: "[Ll]ists:|[Mm]aps:|[Vv]ariables:",
		  },
        ],
      };
    };

    oop.inherits(LobsterRecordsHighlightRules, TextHighlightRules);

    exports.LobsterRecordsHighlightRules = LobsterRecordsHighlightRules;
  }
);
