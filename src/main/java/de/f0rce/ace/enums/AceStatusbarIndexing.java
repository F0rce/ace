package de.f0rce.ace.enums;

public enum AceStatusbarIndexing {
  /** StatusBar */
  ZERO_BASED(0),
  ONE_BASED(1);

  private int index;

  private AceStatusbarIndexing(int index) {
    this.index = index;
  }

  public int getIntValue() {
    return this.index;
  }
}
