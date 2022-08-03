ace.define(
    "ace/theme/lobster_dark",
    ["require", "exports", "module", "ace/lib/dom"],
    function (e, t, n) {
        (t.isDark = !0),
            (t.cssClass = "ace-lobster-dark"),
            (t.cssText =
                `.ace-lobster-dark .ace_gutter {
                    background: #3b3b3b;
                    color: rgb(153, 153, 153);
                  }
                  .ace-lobster-dark .ace_print-margin {
                    width: 1px;
                    background: #3b3b3b;
                  }
                  .ace-lobster-dark {
                    background-color: #323232;
                    color: #ffffff;
                  }
                  .ace-lobster-dark .ace_cursor {
                    color: #91ff00;
                  }
                  .ace-lobster-dark .ace_marker-layer .ace_selection {
                    background: rgba(90, 100, 126, 0.88);
                  }
                  .ace-lobster-dark.ace_multiselect .ace_selection.ace_start {
                    box-shadow: 0 0 3px 0px #323232;
                  }
                  .ace-lobster-dark .ace_marker-layer .ace_step {
                    background: rgb(102, 82, 0);
                  }
                  .ace-lobster-dark .ace_marker-layer .ace_bracket {
                    margin: -1px 0 0 -1px;
                    border: 1px solid #404040;
                  }
                  .ace-lobster-dark .ace_marker-layer .ace_active-line {
                    background: #353637;
                  }
                  .ace-lobster-dark .ace_gutter-active-line {
                    background-color: #353637;
                  }
                  .ace-lobster-dark .ace_marker-layer .ace_selected-word {
                    border: 1px solid rgba(90, 100, 126, 0.88);
                  }
                  .ace-lobster-dark .ace_invisible {
                    color: #404040;
                  }
                  .ace-lobster-dark .ace_keyword,
                  .ace-lobster-dark .ace_meta {
                    color: #cc7833;
                  }
                  .ace-lobster-dark .ace_constant,
                  .ace-lobster-dark .ace_constant.ace_character,
                  .ace-lobster-dark .ace_constant.ace_character.ace_escape,
                  .ace-lobster-dark .ace_constant.ace_other,
                  .ace-lobster-dark .ace_support.ace_constant {
                    color: #0094c1;
                  }
                  .ace-lobster-dark .ace_invalid {
                    color: #ffffff;
                    background-color: #ee5522;
                  }
                  .ace-lobster-dark .ace_fold {
                    background-color: #cc7833;
                    border-color: #ffffff;
                  }
                  .ace-lobster-dark .ace_support.ace_function {
                    color: #ee5522;
                  }
                  .ace-lobster-dark .ace_variable.ace_parameter {
                    font-style: italic;
                  }
                  .ace-lobster-dark .ace_string {
                    color: #a5c261;
                  }
                  .ace-lobster-dark .ace_string.ace_regexp {
                    color: #cccc33;
                  }
                  .ace-lobster-dark .ace_comment {
                    font-style: italic;
                    color: #bc9458;
                  }
                  .ace-lobster-dark .ace_meta.ace_tag {
                    color: #ffe5bb;
                  }
                  .ace-lobster-dark .ace_entity.ace_name {
                    color: #ffc66d;
                  }
                  .ace-lobster-dark .ace_collab.ace_user1 {
                    color: #323232;
                    background-color: #fff980;
                  }
                  .ace-lobster-dark .ace_indent-guide {
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAACCAYAAACZgbYnAAAAEklEQVQImWMwMjLyZYiPj/8PAAreAwAI1+g0AAAAAElFTkSuQmCC)
                      right repeat-y;
                  }`);
        var r = e("../lib/dom");
        r.importCssString(t.cssText, t.cssClass, !1);
    }
);
(function () {
    ace.require(["ace/theme/lobster_dark"], function (m) {
        if (typeof module == "object" && typeof exports == "object" && module) {
            module.exports = m;
        }
    });
})();
