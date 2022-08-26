package de.f0rce.ace.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AceCustomMode {

  private Map<String, List<AceCustomModeRule>> states = new HashMap<>();

  /**
   * Add the start state to the mode (which is required).
   *
   * @param rules AceCustomModeRule[]
   */
  public void addStartState(AceCustomModeRule... rules) {
    this.states.put("start", new ArrayList<>(Arrays.asList(rules)));
  }

  /**
   * Add a new state to the custom mode which can be accessed by a rule if wanted with {@link
   * AceCustomModeRule#setNext(String)}. Make sure to use {@link
   * #addStartState(AceCustomModeRule...)} or you will run against errors.
   *
   * @param stateName {@link String}
   * @param rules AceCustomModeRule[]
   */
  public void addState(String stateName, AceCustomModeRule... rules) {
    this.states.put(stateName, new ArrayList<>(Arrays.asList(rules)));
  }
}
