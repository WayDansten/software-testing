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
        Node node4 = new Node(4);

        node1.addNeighbour(node2);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node2);
        node3.addNeighbour(node4);

        node4.addNeighbour(node3);

        List<Integer> ids = List.of(1, 2, 3, 4);
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
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        node1.addNeighbour(node2);
        node1.addNeighbour(node5);
        node1.addNeighbour(node10);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node2);
        node3.addNeighbour(node4);

        node4.addNeighbour(node3);

        node5.addNeighbour(node1);
        node5.addNeighbour(node6);
        node5.addNeighbour(node9);

        node6.addNeighbour(node5);
        node6.addNeighbour(node7);
        node6.addNeighbour(node8);

        node7.addNeighbour(node6);

        node8.addNeighbour(node6);

        node9.addNeighbour(node5);

        node10.addNeighbour(node1);

        List<Integer> ids = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
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
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.addNeighbour(node2);
        node1.addNeighbour(node8);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);
        node2.addNeighbour(node4);

        node3.addNeighbour(node2);
        node3.addNeighbour(node4);

        node4.addNeighbour(node2);
        node4.addNeighbour(node3);
        node4.addNeighbour(node5);

        node5.addNeighbour(node4);
        node5.addNeighbour(node6);
        node5.addNeighbour(node8);

        node6.addNeighbour(node5);
        node6.addNeighbour(node7);

        node7.addNeighbour(node6);
        node7.addNeighbour(node8);

        node8.addNeighbour(node1);
        node8.addNeighbour(node5);
        node8.addNeighbour(node7);
        
        List<Integer> ids = List.of(1, 2, 3, 4, 5, 6, 7, 8);
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
        node1.addNeighbour(node3);

        node2.addNeighbour(node1);
        node2.addNeighbour(node3);

        node3.addNeighbour(node1);
        node3.addNeighbour(node2);
        
        List<Integer> ids = List.of(1, 2, 3, 4);
        DFSWalker walker = new DFSWalker(ids);
        walker.walk(node1);

        Map<Integer, Boolean> results = walker.getVisited();
        for (var entry : results.entrySet()) {
            if (entry.getKey() == 4) {
                assertFalse(entry.getValue());
            } else {
                assertTrue(entry.getValue());
            }
        }
    }
}
