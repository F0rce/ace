package de.f0rce.ace.util;

import de.f0rce.ace.enums.AceMarkerColor;
import java.util.UUID;

/** @author David "F0rce" Dodlek */
public class AceMarker {

  private String id;
  private int rowStart;
  private int from;
  private int rowEnd;
  private int to;
  private AceMarkerColor aceMarkerColor;
  private String alias = "";

  /**
   * @param from int
   * @param to int
   * @param aceMarker {@link AceMarkerColor}
   */
  public AceMarker(int from, int to, AceMarkerColor aceMarker) {
    this.rowStart = 0;
    this.from = from;
    this.rowEnd = 0;
    this.to = to;
    this.aceMarkerColor = aceMarker;
    this.id = UUID.randomUUID().toString();
  }

  /**
   * @param from int
   * @param to int
   * @param aceMarker {@link AceMarkerColor}
   * @param alias {@link String}
   */
  public AceMarker(int from, int to, AceMarkerColor aceMarker, String alias) {
    this.rowStart = 0;
    this.from = from;
    this.rowEnd = 0;
    this.to = to;
    this.aceMarkerColor = aceMarker;
    this.id = UUID.randomUUID().toString();
    this.alias = alias;
  }

  /**
   * @param rowStart int
   * @param from int
   * @param rowEnd int
   * @param to int
   * @param aceMarker {@link AceMarkerColor}
   */
  public AceMarker(int rowStart, int from, int rowEnd, int to, AceMarkerColor aceMarker) {
    this.rowStart = rowStart;
    this.from = from;
    this.rowEnd = rowEnd;
    this.to = to;
    this.aceMarkerColor = aceMarker;
    this.id = UUID.randomUUID().toString();
  }

  /**
   * @param rowStart int
   * @param from int
   * @param rowEnd int
   * @param to int
   * @param aceMarker {@link AceMarkerColor}
   * @param alias {@link String}
   */
  public AceMarker(
      int rowStart, int from, int rowEnd, int to, AceMarkerColor aceMarker, String alias) {
    this.rowStart = rowStart;
    this.from = from;
    this.rowEnd = rowEnd;
    this.to = to;
    this.aceMarkerColor = aceMarker;
    this.id = UUID.randomUUID().toString();
    this.alias = alias;
  }

  /**
   * Returns the random generated UUID of the AceMarker itself.
   *
   * @return {@link String}
   */
  public String getID() {
    return this.id;
  }

  /**
   * Returns the row where the AceMarker starts.
   *
   * @return int
   */
  public int getRowStart() {
    return this.rowStart;
  }

  /**
   * Returns the index in the rowStart {@link #getRowStart()} where the AceMarker starts.
   *
   * @return int
   */
  public int getFrom() {
    return this.from;
  }

  /**
   * Returns the row where the AceMarker ends.
   *
   * @return int
   */
  public int getRowEnd() {
    return this.rowEnd;
  }

  /**
   * Returns the index in the rowEnd {@link #getRowEnd()} where the selection ends.
   *
   * @return int
   */
  public int getTo() {
    return this.to;
  }

  /**
   * Returns the color set for the AceMarker.
   *
   * @return {@link AceMarkerColor}
   */
  public AceMarkerColor getAceMarkerColor() {
    return this.aceMarkerColor;
  }

  /**
   * Returns the current set alias for the AceMarker.
   *
   * @return {@link String}
   */
  public String getAlias() {
    return this.alias;
  }

  /**
   * Sets the alias for the AceMarker.
   *
   * @param alias {@link String}
   */
  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Override
  public String toString() {
    return "AceMarker [id="
        + this.id
        + ", rowStart="
        + this.rowStart
        + ", from="
        + this.from
        + ", rowEnd="
        + this.rowEnd
        + ", to="
        + this.to
        + ", aceMarkerColor="
        + this.aceMarkerColor
        + ", alias="
        + this.alias
        + "]";
  }
}
