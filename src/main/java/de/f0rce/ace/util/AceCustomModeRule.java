package de.f0rce.ace.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import de.f0rce.ace.enums.AceCustomModeTokens;

/**
 * Ace Modes can be created on a "set-what-you-need" basis. Please read the documentation on
 * creating a ace mode and use the provided setters. If something is missing make sure to open an
 * issue over at the GitHub Repository.
 *
 * @see <a href="https://github.com/ajaxorg/ace/wiki/Creating-or-Extending-an-Edit-Mode">Creating or
 *     Extending an Edit Mode</a>
 * @see <a href="https://github.com/F0rce/ace/issues">GitHub Repository</a>
 * @author David "F0rce" Dodlek
 */
public class AceCustomModeRule {

  private Object token;
  private String regex;
  private String next;
  private String defaultToken;

  /** @param token {@link AceCustomModeTokens} */
  public void setToken(AceCustomModeTokens token) {
    this.token = token.getToken();
  }

  /** @return {@link AceCustomModeTokens} */
  public AceCustomModeTokens getToken() {
    if (token instanceof String) {
      return AceCustomModeTokens.findByToken((String) this.token);
    }

    return null;
  }

  /** @param tokens AceCustomModeTokens[] */
  public void setTokens(AceCustomModeTokens... tokens) {
    if (tokens.length == 1) {
      this.setToken(tokens[0]);
      return;
    }

    this.token =
        Arrays.asList(tokens).stream()
            .map(acmt -> acmt.getToken())
            .collect(Collectors.toCollection(ArrayList<String>::new));
  }

  /** @return {@link ArrayList} */
  public ArrayList<AceCustomModeTokens> getTokens() {
    if (this.token instanceof ArrayList<?>) {
      ArrayList<AceCustomModeTokens> tokens = new ArrayList<>();

      for (Object check : (ArrayList<?>) this.token) {
        if (check instanceof String) {
          tokens.add(AceCustomModeTokens.findByToken((String) check));
        }
      }

      return tokens;
    }

    return null;
  }

  /** @param regex {@link String} */
  public void setRegex(String regex) {
    this.regex = regex;
  }

  /** @return {@link String} */
  public String getRegex() {
    return this.regex;
  }

  /** @param next {@link String} */
  public void setNext(String next) {
    this.next = next;
  }

  /** @return {@link String} */
  public String getNext() {
    return this.next;
  }

  /** @param defaultToken {@link AceCustomModeTokens} */
  public void setDefaultToken(AceCustomModeTokens defaultToken) {
    this.defaultToken = defaultToken.getToken();
  }

  /** @return {@link AceCustomModeTokens} */
  public AceCustomModeTokens getDefaultToken() {
    return AceCustomModeTokens.findByToken(this.defaultToken);
  }
}
