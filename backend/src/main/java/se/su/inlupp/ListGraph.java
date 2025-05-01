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
      adjecencyList.put(node, new ArrayList<>());  //addIFAbssent
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
    if (!adjecencyList.containsKey(node1) || !adjecencyList.containsKey(node2) || (getEdgeBetween(node1, node2) == null)) { //måste man göra den som egen? 
      throw new NoSuchElementException();
    }
  }

  @Override
  public Set<T> getNodes() {
    throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    if(!adjecencyList.containsKey(node)){
      throw new NoSuchElementException();
    }
    List<Edge<T>> result = new ArrayList<>();
    for(Edge<T> edge : adjecencyList.get(node)){
      result.add(edge);
    }
    return Collections.unmodifiableCollection(result);
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
    if (!adjecencyList.containsKey(node1) || !adjecencyList.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    if (getEdgeBetween(node1, node2)== null){
      throw new IllegalStateException("Edge dose not exists between " + node1 + " and " + node2);
    }
    Edge<T> edge1 = getEdgeBetween(node1, node2);
    Edge<T> edge2 = getEdgeBetween(node2, node1);
    
    adjecencyList.get(node1).remove(edge1);
    adjecencyList.get(node2).remove(edge2);
  }

  @Override
  public void remove(T node) {
    if(!adjecencyList.containsKey(node)){
      throw new NoSuchElementException();
    }

    List<Edge<T>> edgesCopy = new ArrayList<>(adjecencyList.get(node));
    for(Edge<T> edge : edgesCopy){  // kan vara ett problem med att vi manuplerar edges medans vi loppar edges
      disconnect(node, edge.getDestination());
    }

    adjecencyList.remove(node);
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
