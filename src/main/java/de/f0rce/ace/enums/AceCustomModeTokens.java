package de.f0rce.ace.enums;

/**
 * The following are the common tokens to {@link AceTheme} taken from the TextMate manual. Note that
 * not all of these may have styling associated with them, depending on the {@link AceTheme} used.
 * <br>
 * <br>
 * Ace Documentation:<br>
 * <a>https://github.com/ajaxorg/ace/wiki/Creating-or-Extending-an-Edit-Mode#common-tokens</a>
 *
 * @author David "F0rce" Dodlek
 */
public enum AceCustomModeTokens {
  /** for comments */
  COMMENT("comment"),

  /**
   * line comments, we specialize further so that the type of comment start character(s) can be
   * extracted from the scope.
   */
  COMMENT_LINE("comment.line"),

  /** <code>// comment</code> */
  COMMENT_LINE_DOUBLE_SLASH("comment.line.double-slash"),

  /** <code>-- comment</code> */
  COMMENT_LINE_DOUBLE_DASH("comment.line.double-dash"),

  /** <code># comment</code> */
  COMMENT_LINE_NUMBER_SIGN("comment.line.number-sign"),

  /** <code>% comment</code> */
  COMMENT_LINE_PERCENTAGE("comment.line.percentage"),

  /** other types of line comments */
  COMMENT_LINE_CHARACTER("comment.line.character"),

  /**
   * multi-line comments like <code>&#47;* ... *&#47;</code> and <code>&#60;!-- ... --&#62;</code>
   */
  COMMENT_BLOCK("comment.block"),

  /** embedded documentation */
  COMMENT_BLOCK_DOCUMENTATION("comment.block.documentation"),

  /** various forms of constants */
  CONSTANT("constant"),

  /**
   * those which represent numbers, e.g. <code>42</code>, <code>1.3f</code>, <code>0x4AB1U</code>
   */
  CONSTANT_NUMERIC("constant.numeric"),

  /**
   * those which represent characters, e.g. <code>&lt;</code>, <code>\e</code>, <code>
   * \031</code>
   */
  CONSTANT_CHARACTER("constant.character"),

  /** escape sequences like <code>\e</code> */
  CONSTANT_CHARACTER_ESCAPE("constant.character.escape"),

  /**
   * constants (generally) provided by the language which are "special" like <code>true</code>,
   * <code>false</code>, <code>nil</code>, <code>YES</code>, <code>NO</code>, etc.
   */
  CONSTANT_LANGUAGE("constant.language"),

  /** other constants, e.g. colors in CSS */
  CONSTANT_OTHER("constant.other"),

  /**
   * an entity refers to a larger part of the document, for example a chapter, class, function, or
   * tag. We do not scope the entire entity as <code>ENTITY.*</code> (we use <code>META.*</code> for
   * that). But we do use <code>entity.*</code> for the "placeholders" in the larger entity, e.g.
   * if the entity is a chapter, we would use {@link #ENTITY_NAME_SECTION} for the chapter title.
   */
  ENTITY("entity"),

  /** we are naming the larger entity */
  ENTITY_NAME("entity.name"),

  /** the name of a function */
  ENTITY_NAME_FUNCTION("entity.name.function"),

  /** the name of a type declaration or class */
  ENTITY_NAME_TYPE("entity.name.type"),

  /** a tag name */
  ENTITY_NAME_TAG("entity.name.tag"),

  /** the name is the name of a section/heading */
  ENTITY_NAME_SECTION("entity.name.section"),

  /** other entities */
  ENTITY_OTHER("entity.other"),

  /** the superclass/baseclass name */
  ENTITY_OTHER_INHERITED_CLASS("entity.other.inherited-class"),

  /** the name of an attribute (mainly in tags) */
  ENTITY_OTHER_ATTRIBUTE_NAME("entity.other.attribute-name"),

  /** stuff which is "invalid" */
  INVALID("invalid"),

  /**
   * illegal, e.g. an ampersand or lower-than character in HTML (which is not part of an entity/tag)
   */
  INVALID_ILLEGAL("invalid.illegal"),

  /**
   * for deprecated stuff e.g. using an API function which is deprecated or using styling with
   * strict HTML
   */
  INVALID_DEPRECATED("invalid.deprecated"),

  /** keywords (when these do not fall into the other groups) */
  KEYWORD("keyword"),

  /**
   * mainly related to flow control like <code>continue</code>, <code>while</code>, <code>return
   * </code>, etc.
   */
  KEYWORD_CONTROL("keyword.control"),

  /** operators can either be textual (e.g. <code>or</code>) or be characters */
  KEYWORD_OPERATOR("keyword.operator"),

  /** other keywords */
  KEYWORD_OTHER("keyword.other"),

  /** this is for markup languages and generally applies to larger subsets of the text */
  MARKUP("markup"),

  /** underlined text */
  MARKUP_UNDERLINE("markup.underline"),

  /**
   * this is for links, as a convenience this is derived from {@link #MARKUP_UNDERLINE} so that if
   * there is no theme rule which specifically targets {@link #MARKUP_UNDERLINE_LINK} then it will
   * inherit the underline style
   */
  MARKUP_UNDERLINE_LINK("markup.underline.link"),

  /** bold text (text which is strong and similar should preferably be derived from this name) */
  MARKUP_BOLD("markup.bold"),

  /** a section header */
  MARKUP_HEADING("markup.heading"),

  /**
   * italic text (text which is emphasized and similar should preferably be derived from this name)
   */
  MARKUP_ITALIC("markup.italic"),

  /** list items */
  MARKUP_LIST("markup.list"),

  /** numbered list items */
  MARKUP_LIST_NUMBERED("markup.list.numbered"),

  /** unnumbered list items */
  MARKUP_LIST_UNNUMBERED("markup.list.unnumbered"),

  /** quoted (sometimes block quoted) text */
  MARKUP_QUOTE("markup.quote"),

  /**
   * text which is verbatim, e.g. code listings. Normally spell checking is disabled for {@link
   * #MARKUP_RAW}
   */
  MARKUP_RAW("markup.raw"),

  /** other markup constructs */
  MARKUP_OTHER("markup.other"),

  /** things relating to "storage" */
  STORAGE("storage"),

  /**
   * the type of something, <code>class</code>, <code>function</code>, <code>int</code>, <code>var
   * </code>, etc.
   */
  STORAGE_TYPE("storage.type"),

  /**
   * a storage modifier like <code>static</code>, <code>final</code>, <code>abstract</code>, etc.
   */
  STORAGE_MODIFIER("storage.modifier"),

  /** strings */
  STRING("string"),

  /** quoted strings */
  STRING_QUOTED("string.quoted"),

  /** single quoted strings: <code>'foo'</code> */
  STRING_SINGLE("string.single"),

  /** double quoted strings: <code>"foo"</code> */
  STRING_DOUBLE("string.double"),

  /** triple quoted strings: <code>"""Python"""</code> */
  STRING_TRIPLE("string.triple"),

  /** for things like here-docs and here-strings */
  STRING_UNQUOTED("string.unquoted"),

  /** strings which are "evaluated": <code>`date`</code>, <code>$(pwd)</code> */
  STRING_INTERPOLATED("string.interpolated"),

  /** regular expressions: <code>/(\w+)/</code> */
  STRING_REGEXP("string.regexp"),

  /** other types of quoting: <code>$'shell'</code>, <code>%s{...}</code> */
  STRING_OTHER("string.other"),

  /** things provided by a framework or library */
  SUPPORT("support"),

  /** functions provided by the framework/library. For example <code>NSLog</code> in Objective-C */
  SUPPORT_FUNCTION("support.function"),

  /** when the framework/library provides classes */
  SUPPORT_CLASS("support.class"),

  /**
   * types provided by the framework/library, this is probably only used for languages derived from
   * C, which has <code>typedef</code> (and <code>struct</code>). Most other languages would
   * introduce new types as classes
   */
  SUPPORT_TYPE("support.type"),

  /** constants (magic values) provided by the framework/library */
  SUPPORT_CONSTANT("support.constant"),

  /** variables provided by the framework/library. For example <code>NSApp</code> in AppKit */
  SUPPORT_VARIABLE("support.variable"),

  /** the above should be exhaustive, but for everything else */
  SUPPORT_OTHER("support.other"),

  /** variables. Not all languages allow easy identification (and thus markup) of these */
  VARIABLE("variable"),

  /** when the variable is declared as the parameter */
  VARIABLE_PARAMETER("variable.parameter"),

  /**
   * reserved language variables like <code>this</code>, <code>super</code>, <code>self</code>, etc
   */
  VARIABLE_LANGUAGE("variable.language"),

  /** other variables, like <code>$some_variables</code> */
  VARIABLE_OTHER("variable.other");

  private String token;

  private AceCustomModeTokens(String token) {
    this.token = token;
  }

  /**
   * Returns the {@link String} representation of a token.
   *
   * @return {@link String}
   */
  public String getToken() {
    return this.token;
  }

  /**
   * Find the enum by it's {@link String} representation.
   *
   * @param token {@link String}
   * @return {@link AceCustomModeTokens}
   */
  public static AceCustomModeTokens findByToken(String token) {
    for (AceCustomModeTokens acmt : values()) {
      if (acmt.getToken().equals(token)) {
        return acmt;
      }
    }
    return null;
  }
}
