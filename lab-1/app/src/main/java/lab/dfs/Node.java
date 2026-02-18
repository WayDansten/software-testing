package lab.dfs;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

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
        this.neighbours = neighbours;
    }

    public void addNeighbour(Node node) {
        neighbours.add(node);
    }

    public void removeNeightbour(Node node) {
        neighbours.remove(node);
    }
}
