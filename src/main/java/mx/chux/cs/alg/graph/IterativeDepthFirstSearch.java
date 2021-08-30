package mx.chux.cs.alg.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.chux.cs.ds.graph.Graph;
import mx.chux.cs.ds.graph.Node;

public class IterativeDepthFirstSearch<T extends Comparable<T>> extends DepthFirstSearch<T> {
    
    public IterativeDepthFirstSearch(final Graph<T> graph) {
        super(graph);
    }
    
    @Override
    protected List<Node<T>> dfs(Node<T> first, Node<T> last) {
        
        final Deque<Node<T>> stack = new ArrayDeque<>();
        
        final Map<Node<T>, Node<T>> parents = new HashMap<>(this.graph.sizeOfNodes());
        
        final T value = last.get();
        
        stack.push(first);
        
        while( !stack.isEmpty() ) {
            
            final Node<T> node = stack.pop();
            
            if( !node.hasBeenVisited() ) {

                node.setVisited(true);
                
                if( node.get().equals(value) ) {
                    return backtrace(parents, first, last);
                }
                
                final Collection<Node<T>> adjecents = this.graph.getAdjacentNodes(node);
                
                for( Node<T> n : adjecents ) {
                    if( !stack.contains(n) ) {
                        parents.put(n, node);
                        stack.push(n);
                    }
                }
                
            }
            
        }
        
        return Collections.emptyList();
    }
    
    private List<Node<T>> backtrace(Map<Node<T>, Node<T>> parents, Node<T> first, Node<T> last) {
        
        final List<Node<T>> path = new ArrayList<>(parents.size());
        
        path.add(last);
        
        Node<T> parent = parents.get(last);
        
        while( !parent.equals(first) ) {
            path.add(parent);
            parent = parents.get(parent);
        }
         
        path.add(parent);
        
        return path;
    }

}
