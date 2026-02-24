package lab.dfs;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

// make addNeightbour add neighbours to both Node's neighbour lists

@Getter
@Setter
public class Node {

    private int id;

    private final List<Node> neighbours;
    
    public Node(int id) {
        this.id = id;
        this.neighbours = new ArrayList<>();
    }

    public Node (int id, List<Node> neighbours) {
        this.id = id;
        this.neighbours = new ArrayList<>();

        for (Node node : neighbours) {
            node.addNeighbour(this);
        }
    }

    public void addNeighbour(Node node) {
        neighbours.add(node);
        node.getNeighbours().add(this);
    }

    public void removeNeightbour(Node node) {
        neighbours.remove(node);
        node.getNeighbours().remove(this);
    }
}
