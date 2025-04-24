package se.su.inlupp;

import java.util.*;

public class ListGraph<T> implements Graph<T> {

  //Vi valde att använda List före Set främst för att ha mindre begränsningar och vara mer expanderbar.
  //Om vi skulle behöva introducera multi-graph eller använde oss av någon ordning i framtiden så är List mer lämplig.
  //Set skulle vara marginellt mer effektiv men den skulle samtidigt vara mer strikt att implementera.

  private Map<T, List<Edge<T>>> adjecencyList;

  public ListGraph() {
    adjecencyList = new HashMap<>();
  }

  @Override
  public void add(T node) {
    if (!adjecencyList.containsKey(node)) {
      adjecencyList.put(node, new ArrayList<>());
    }
  }

  @Override
  public void connect(T node1, T node2, String name, int weight) {
    if (!adjecencyList.containsKey(node1) || !adjecencyList.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    if(weight < 0){
      throw new IllegalArgumentException("weight must be positive");
    }
    if(getEdgeBetween(node1, node2) == null){
      throw new IllegalStateException("Edge already exists between " + node1 + " and " + node2);
    }
    Edge<T> edge1 = new EdgeImpl(node2, name, weight);
    Edge<T> edge2 = new EdgeImpl(node1, name, weight);
    
    adjecencyList.get(node1).add(edge1);
    adjecencyList.get(node2).add(edge2);
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
  }

  @Override
  public Set<T> getNodes() {
    throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    throw new UnsupportedOperationException("Unimplemented method 'getEdgesFrom'");
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
    if (!adjecencyList.containsKey(node1) || !adjecencyList.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    for(Edge<T> edge : adjecencyList.get(node1)){
      if(edge.getDestination().equals(node2)){
        return edge;
      }
    }
    return null;
  }

  @Override
  public void disconnect(T node1, T node2) {
    throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
  }

  @Override
  public void remove(T node) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public boolean pathExists(T from, T to) {
    throw new UnsupportedOperationException("Unimplemented method 'pathExists'");
  }

  @Override
  public List<Edge<T>> getPath(T from, T to) {
    throw new UnsupportedOperationException("Unimplemented method 'getPath'");
  }
}
