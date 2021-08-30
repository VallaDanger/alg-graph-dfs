package mx.chux.cs.alg.graph;

import org.junit.Before;
import org.junit.Test;

import mx.chux.cs.ds.graph.Graph;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RecursiveDepthFirstSearchTest {

    Graph<String> graph;
    DepthFirstSearch<String> dfs;
    
    @Before
    public void initializeGraph() {
        Map<String, Collection<String>> data = new HashMap<>();
        
        data.put("a", Arrays.asList("b", "c", "e"));
        data.put("b", Arrays.asList("c"));
        data.put("c", Arrays.asList("d"));
        data.put("o", Collections.emptyList());
        
        this.graph = new Graph<>(data);
        this.dfs = new RecursiveDepthFirstSearch<>(this.graph);
    }
    
    @Test
    public void pathMustExistsTest() {
        Collection<String> path = this.dfs.apply("a", "d");
        assertThat(path).isNotEmpty()
        .isEqualTo(Arrays.asList("a", "b", "c", "d"));
    }
    
    @Test
    public void pathMustNotExistTest() {
        assertThat(this.dfs.apply("b", "o")).isEmpty();
    }
    
    @Test
    public void transitivePathExistTest() {
        assertThat(this.dfs.apply("b", "e")).isNotEmpty()
        .isEqualTo(Arrays.asList("b", "a", "e"));
    }
    
    @Test
    public void illegalLastNodeTest() {
        assertThat(this.dfs.apply("a", "x")).isEmpty();
    }
    
    @Test
    public void illegalFirstNodeTest() {
        assertThat(this.dfs.apply("x", "a")).isEmpty();
    }
    
    @Test
    public void illegalFirstAndLastNodesTest() {
        assertThat(this.dfs.apply("x", "x")).isEmpty();
    }
    
}
