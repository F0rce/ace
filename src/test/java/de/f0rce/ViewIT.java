package de.f0rce;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

  @Test
  public void componentWorks() {
    final TestBenchElement litAce = this.$("lit-ace").waitForFirst();
    // Check that lit-ace contains at least one other element, which means that
    // is has been upgraded to a custom element and not just rendered as an empty
    // tag
    Assert.assertTrue(litAce.$(TestBenchElement.class).all().size() > 0);
  }
}
