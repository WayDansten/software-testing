package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import lab.dfs.Node;

class NodeTest {
    @Test
    void createNodeTest() {
        Node node = new Node(1);
        assertNotNull(node);
        assertEquals(1, node.getId());
    }

    @Test
    void createNodeWithNeighboursTest() {
        Node node2 = new Node(2);
        Node node1 = new Node(1, List.of(node2));

        assertFalse(node1.getNeighbours().isEmpty());
        assertFalse(node2.getNeighbours().isEmpty());
        assertEquals(node1.getNeighbours().get(0), node2);
        assertEquals(node2.getNeighbours().get(0), node1);
    }

    @Test
    void addNeightbourTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.addNeighbour(node2);

        assertFalse(node1.getNeighbours().isEmpty());
        assertFalse(node2.getNeighbours().isEmpty());
        assertEquals(node1.getNeighbours().get(0), node2);
        assertEquals(node2.getNeighbours().get(0), node1);
    }

    @Test
    void removeNeighbourTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.addNeighbour(node2);
        node2.removeNeightbour(node1);

        assertTrue(node1.getNeighbours().isEmpty());
        assertTrue(node2.getNeighbours().isEmpty());
    }
}
