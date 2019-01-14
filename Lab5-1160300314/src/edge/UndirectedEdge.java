package edge;

import exception.IllegalEdgeParamsException;
import exception.IllegalVertexParamsException;
import exception.InputFileAgainException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import vertex.Vertex;

/**
 * Describe the undirected edge in graph.
 *
 * @author Zhu Mingyan
 */
public class UndirectedEdge extends Edge {

  /*
  AF: Represents the undirected edge in graph with two vertices.
  RI: Any vertex of undirected edge is non-null and an instance of Vertex.
  Safety for Rep Exposure:
      All fields except label and weight are modified by key word private and clients can access
      only by these getter function and change them by the addVertices function which is strictly
      limited.
   */
  private Vertex vertex1;
  private Vertex vertex2;

  //  private Set<Vertex> vertices = new HashSet<>();
  public UndirectedEdge(String label, double weight) throws InputFileAgainException {
    super(label, weight);
    super.checkRep();
  }

  @Override
  protected void checkRep() throws InputFileAgainException {
    super.checkRep();
    //    if (vertex1 == null || vertex2 == null) {
    //      throw new IllegalEdgeParamsException("Some vertex in undirected edge is null!");
    //    }
    //    assert vertex1 != null;
    //    assert vertex2 != null;
  }

  @Override
  public boolean addVertices(List<Vertex> vertexList) throws InputFileAgainException {
    //    assert vertices.size() == 1 || vertices.size() == 2;
    // If this edge is a loop the size of vertices will be one.
    if (vertexList == null) {
      throw new IllegalVertexParamsException("Vertices of undirected edge is null");
    }
    if (vertexList.size() != 2 && vertexList.size() != 1) {
      throw new IllegalVertexParamsException("Number of vertices in undirected edge is "
          + " illegal with " + vertexList.size() + " vertices");
    }
    if (vertexList.contains(vertex1) && vertexList.contains(vertex2)) {
      return false;
    }
    //    try {
    if (vertexList.size() == 1) {
      if (vertexList.get(0) == null) {
        throw new IllegalVertexParamsException("Add null in undirected edge!");
      }
      // 为了优化 牺牲安全性
      //        vertex1 = vertex2 = vertices.get(0).clone();
      vertex1 = vertex2 = vertexList.get(0);
    } else {
      if (vertexList.get(0) == null || vertexList.get(1) == null) {
        throw new IllegalVertexParamsException("Add null in undirected edge!");
      }
      vertex1 = vertexList.get(0);
      vertex2 = vertexList.get(1);
      //        vertex1 = vertices.get(0).clone();
      //        vertex2 = vertices.get(1).clone();
    }
    //    }
    //    catch (CloneNotSupportedException e) {
    //      e.printStackTrace();
    //    }
    //    checkRep();
    //    vertices.add(vertex1);
    //    vertices.add(vertex2);
    return true;
  }

  @Override
  public boolean containVertex(Vertex v) {
    return vertex1.equals(v) || vertex2.equals(v);
  }

  @Override
  public Set<Vertex> vertices() {
    Set<Vertex> set = new HashSet<>();
    set.add(vertex1);
    set.add(vertex2);
    return set;
    //    return vertices;
  }

  @Override
  public Set<Vertex> sourceVertices() {
    return vertices();
  }

  @Override
  public Set<Vertex> targetVertices() {
    return vertices();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UndirectedEdge)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    UndirectedEdge that = (UndirectedEdge) o;
    return (vertex1.equals(that.vertex1)
        && vertex2.equals(that.vertex2))
        || (vertex1.equals(that.vertex2)
        && vertex2.equals(that.vertex1));
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), vertex1, vertex2);
  }

  @Override
  public String toString() {
    return "UndirectedEdge{"
        + "label='" + getLabel() + '\'' + ", weight=" + getWeight()
        + ", vertex1=" + vertex1 + ", vertex2=" + vertex2 + '}';
  }
}
