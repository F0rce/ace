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
        "start": [
          {
		    token: "constant.numeric",
		    regex: "\\d([:.]?(?=\\d))?",
		  },
		  {
		    token: "string",
		    regex: '("(?=.))',
		    next: "handleStringDouble"
		  },
		  {
		    token: "string",
		    regex: "('(?=.))",
		    next: "handleStringSingle"
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
			regex: "([iI]nput\\([^\)]+\\))",
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
        
        "handleStringDouble": [
        {
            "token": "constant.character.escape",
            "regex": "\\\\."
        },
        {
            "token" : "string",
            "regex" : '[^"\\\\]+',
            "merge" : true
        }, 
        {
            "token" : "string",
            "regex" : "\\\\$",
            "next"  : "handleString",
            "merge" : true
        }, 
        {
            "token" : "string",
            "regex" : "\"|$",
            "next"  : "start",
            "merge" : true
        }   
        ],
        
        "handleStringSingle": [
        {
            "token": "constant.character.escape",
            "regex": "\\\\."
        },
        {
            "token" : "string",
            "regex" : "[^'\\\\]+",
            "merge" : true
        }, 
        {
            "token" : "string",
            "regex" : "\\\\$",
            "next"  : "handleString",
            "merge" : true
        }, 
        {
            "token" : "string",
            "regex" : "'|$",
            "next"  : "start",
            "merge" : true
        }   
        ],
      };
    };

    oop.inherits(LobsterLogsHighlightRules, TextHighlightRules);

    exports.LobsterLogsHighlightRules = LobsterLogsHighlightRules;
  }
);
