ace.define(
    "ace/theme/lobster_light",
    ["require", "exports", "module", "ace/lib/dom"],
    function (e, t, n) {
        (t.isDark = !1),
            (t.cssClass = "ace-lobster-light"),
            (t.cssText =
                `.ace-lobster-light .ace_gutter {
            background: #ebebeb;
            color: #333;
            overflow: hidden;
          }
          .ace-lobster-light .ace_print-margin {
            width: 1px;
            background: #e8e8e8;
          }
          .ace-lobster-light {
            background-color: #ffffff;
            color: black;
          }
          .ace-lobster-light .ace_cursor {
            color: black;
          }
          .ace-lobster-light .ace_invisible {
            color: rgb(191, 191, 191);
          }
          .ace-lobster-light .ace_constant.ace_buildin {
            color: rgb(88, 72, 246);
          }
          .ace-lobster-light .ace_constant.ace_language {
            color: rgb(88, 92, 246);
          }
          .ace-lobster-light .ace_constant.ace_library {
            color: rgb(6, 150, 14);
          }
          .ace-lobster-light .ace_invalid {
            background-color: #ee5522;
            color: white;
          }
          .ace-lobster-light .ace_fold {
          }
          .ace-lobster-light .ace_support.ace_function {
            color: rgb(60, 76, 114);
          }
          .ace-lobster-light .ace_support.ace_constant {
            color: #4AB04A;
          }
          .ace-lobster-light .ace_support.ace_type,
          .ace-lobster-light .ace_support.ace_class.ace-lobster-light .ace_support.ace_other {
            color: rgb(109, 121, 222);
          }
          .ace-lobster-light .ace_variable.ace_parameter {
            font-style: italic;
            color: #fd971f;
          }
          .ace-lobster-light .ace_keyword.ace_operator {
            color: rgb(104, 118, 135);
          }
          .ace-lobster-light .ace_comment {
            color: #236e24;
          }
          .ace-lobster-light .ace_comment.ace_doc {
            color: #236e24;
          }
          .ace-lobster-light .ace_comment.ace_doc.ace_tag {
            color: #236e24;
          }
          .ace-lobster-light .ace_constant.ace_numeric {
            color: rgb(0, 0, 205);
          }
          .ace-lobster-light .ace_variable {
            color: rgb(49, 132, 149);
          }
          .ace-lobster-light .ace_xml-pe {
            color: rgb(104, 104, 91);
          }
          .ace-lobster-light .ace_entity.ace_name {
            color: #0094c1;
          }
          .ace-lobster-light .ace_heading {
            color: rgb(12, 7, 255);
          }
          .ace-lobster-light .ace_list {
            color: rgb(185, 6, 144);
          }
          .ace-lobster-light .ace_marker-layer .ace_selection {
            background: rgb(181, 213, 255);
          }
          .ace-lobster-light .ace_marker-layer .ace_step {
            background: rgb(252, 255, 0);
          }
          .ace-lobster-light .ace_marker-layer .ace_stack {
            background: rgb(164, 229, 101);
          }
          .ace-lobster-light .ace_marker-layer .ace_bracket {
            margin: -1px 0 0 -1px;
            border: 1px solid rgb(192, 192, 192);
          }
          .ace-lobster-light .ace_marker-layer .ace_active-line {
            background: rgba(0, 0, 0, 0.07);
          }
          .ace-lobster-light .ace_gutter-active-line {
            background-color: #dcdcdc;
          }
          .ace-lobster-light .ace_marker-layer .ace_selected-word {
            background: rgb(250, 250, 255);
            border: 1px solid rgb(200, 200, 250);
          }
          .ace-lobster-light .ace_storage,
          .ace-lobster-light .ace_keyword,
          .ace-lobster-light .ace_meta.ace_tag {
            color: rgb(147, 15, 128);
          }
          .ace-lobster-light .ace_string.ace_regex {
            color: #c12d00;
          }
          .ace-lobster-light .ace_string {
            color: #1a1aa6;
          }
          .ace-lobster-light .ace_entity.ace_other.ace_attribute-name {
            color: #994409;
          }
          .ace-lobster-light .ace_indent-guide {
            background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAACCAYAAACZgbYnAAAAE0lEQVQImWP4////f4bLly//BwAmVgd1/w11/gAAAABJRU5ErkJggg==")
              right repeat-y;
          }`);
        var r = e("../lib/dom");
        r.importCssString(t.cssText, t.cssClass, !1);
    }
);
(function () {
    ace.require(["ace/theme/lobster_light"], function (m) {
        if (typeof module == "object" && typeof exports == "object" && module) {
            module.exports = m;
        }
    });
})();
