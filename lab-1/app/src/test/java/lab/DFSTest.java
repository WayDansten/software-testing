package lab;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lab.dfs.DFSWalker;
import lab.dfs.Node;

class DFSTest {
    @Test
    void singleNodeGraphTest() {
        Node node = new Node(1);

        List<Integer> ids = List.of(1);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node);

        Map<Integer, Boolean> results = walker.getVisited();
        assertFalse(results.containsValue(false));
    }

    @Test
    void linearGraphTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.addNeighbour(node2);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node2);

        List<Integer> ids = List.of(1, 2, 3);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node1);

        Map<Integer, Boolean> results = walker.getVisited();
        assertFalse(results.containsValue(false));
    }

    @Test
    void treeGraphTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.addNeighbour(node2);
        node1.addNeighbour(node4);
        node1.addNeighbour(node5);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node2);

        node4.addNeighbour(node1);

        node5.addNeighbour(node1);

        List<Integer> ids = List.of(1, 2, 3, 4, 5);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node1);

        Map<Integer, Boolean> results = walker.getVisited();
        assertFalse(results.containsValue(false));
    }

    @Test
    void loopGraphTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.addNeighbour(node3);
        node1.addNeighbour(node2);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node2);
        node3.addNeighbour(node1);
        
        List<Integer> ids = List.of(1, 2, 3);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node1);

        Map<Integer, Boolean> results = walker.getVisited();
        assertFalse(results.containsValue(false));
    }

    @Test
    void disconnectedGraphTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.addNeighbour(node2);

        node2.addNeighbour(node1);
        
        List<Integer> ids = List.of(1, 2, 3);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node1);

        Map<Integer, Boolean> results = walker.getVisited();
        for (var entry : results.entrySet()) {
            if (entry.getKey() == 3) {
                assertFalse(entry.getValue());
            } else {
                assertTrue(entry.getValue());
            }
        }
    }
}
