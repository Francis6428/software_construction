package edge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import exception.InputFileAgainException;
import factory.LoggerFactory;
import java.util.Arrays;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.junit.Test;
import vertex.Word;

public class DirectedEdgeTest {

  /*
  Test strategy
  Because other test units has test other methods except addVertices(List<Vertex> vertices)
  we will only test this method there.
  Partition for inputs of addVertices(vertices)
      vertices: null, list with more than 2 null params,
      2 params but one is non-null, only one param
   */
  private static final Logger LOGGER = LoggerFactory.createLogger(DirectedEdgeTest.class);

  @Test
  public void addVertices() {
    String label = "edge";
    double weight = 1;
    Word word1 = null;
    Word word2 = null;
    try {
      word1 = new Word("Hello");
      word2 = new Word("World");
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge edge = new DirectedEdge(label, weight);
      edge.addVertices(null);

      assertEquals(null, edge.vertices());
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);
      directedEdge.addVertices(Arrays.asList(word1));
      directedEdge.addVertices(Arrays.asList(word1));

      assertEquals(label, directedEdge.getLabel());
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);
      directedEdge.addVertices(Arrays.asList(word1, word2));

      assertTrue(directedEdge.vertices().contains(word1));
      assertTrue(directedEdge.vertices().contains(word2));

      directedEdge.addVertices(Arrays.asList(word1, word2));

    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);

      directedEdge.addVertices(Arrays.asList(word1, word2, word1));
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);

      directedEdge.addVertices(Arrays.asList(null, null));
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);

      directedEdge.addVertices(Collections.singletonList(null));
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }

    try {
      DirectedEdge directedEdge = new DirectedEdge(label, weight);

      UndirectedEdge undirectedEdge = new UndirectedEdge(label, weight);

      Word word = new Word("Hello");
      directedEdge.addVertices(Collections.singletonList(word));
      undirectedEdge.addVertices(Collections.singletonList(word));

      assertFalse(directedEdge.equals(undirectedEdge));
    } catch (InputFileAgainException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }
}