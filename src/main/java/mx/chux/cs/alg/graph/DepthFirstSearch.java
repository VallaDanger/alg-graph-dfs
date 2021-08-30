package mx.chux.cs.alg.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import mx.chux.cs.ds.graph.Graph;
import mx.chux.cs.ds.graph.Node;

public abstract class DepthFirstSearch<T extends Comparable<T>> implements BiFunction<T, T, List<T>> {

    protected final Graph<T> graph;
    
    public DepthFirstSearch(final Graph<T> graph) {
        this.graph = graph;
    }
    
    @Override
    public final List<T> apply(T first, T last) {
        
        final Node<T> firstNode = this.graph.getNode(first);
        final Node<T> lastNode = this.graph.getNode(last);
        
        if( (firstNode == null) || (lastNode == null) ) {
            return Collections.emptyList();
        }
        
        final List<Node<T>> path = dfs(firstNode, lastNode);
        
        Collections.reverse(path);
        
        resetVisitedNodes(this.graph.getNodes());
        
        return extractPath(path);
    }
    
    protected abstract List<Node<T>> dfs(Node<T> first, Node<T> last);
    
    private List<T> extractPath(List<Node<T>> path) {
        final List<T> extractedPath = new ArrayList<>(path.size());
        for(Node<T> node : path) {
            extractedPath.add(node.get());
        }
        return extractedPath;
    }
    
    private void resetVisitedNodes(Collection<Node<T>> nodes) {
        for( Node<T> node : nodes ) {
            if( node.hasBeenVisited() ) {
                node.setVisited(false);
            }
        }
    }

}
