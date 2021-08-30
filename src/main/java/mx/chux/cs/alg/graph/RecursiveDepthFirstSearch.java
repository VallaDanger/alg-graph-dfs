package mx.chux.cs.alg.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mx.chux.cs.ds.graph.Graph;
import mx.chux.cs.ds.graph.Node;

public class RecursiveDepthFirstSearch<T extends Comparable<T>> extends DepthFirstSearch<T> {
    
    public RecursiveDepthFirstSearch(final Graph<T> graph) {
        super(graph);
    }
    
    @Override
    protected List<Node<T>> dfs(Node<T> first, Node<T> last) {
        
        if( first.hasBeenVisited() ) {
            return Collections.emptyList();
        }
        
        if( first.equals(last) ) {
            return Arrays.asList(first);
        }
        
        first.setVisited(true);
        
        final Collection<Node<T>> adjacents = graph.getAdjacentNodes(first);
        List<Node<T>> path = new ArrayList<>();
        
        for( Node<T> adjacent : adjacents ) {
            if( !adjacent.hasBeenVisited() ) {
                path.addAll(dfs(adjacent, last));
            }
        }
        
        if( !path.isEmpty() ) {
            path.add(first);
        }
        
        return path;
    }

}
