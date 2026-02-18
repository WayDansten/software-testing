package lab.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DFSWalker {

    private Map<Integer, Boolean> visited;

    public DFSWalker(List<Integer> ids) {
        this.visited = new HashMap<>();

        for (Integer id : ids) {
            visited.put(id, false);
        }
    }

    public void walk(Node node) {
        visited.replace(node.getId(), true);
        List<Node> neighbours = node.getNeighbours();
        for (Node neighbour : neighbours) {
            if (!visited.get(neighbour.getId())) {
                walk(neighbour);
            }
        }
    }
}
