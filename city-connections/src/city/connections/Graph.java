package city.connections;

import java.util.*;

/**
 * Created by sofia on 4/27/17.
 */

/**
 * Data structure to represent graph where a node represents a city
 * and an edge represents a direct path (connection) between 2 cities.
 */
public class Graph {

    private Set<String> nodes;
    private Map<String, Set<String>> adjListMap;

    public Graph() {
        nodes = new HashSet<>();
        adjListMap = new HashMap<>();
    }

    /**
     * Method to add an edge between 2 city nodes in the graph.
     *
     * @param v name of the first city
     * @param w name of the second city
     */
    public void addEdge(String v, String w) {
        nodes.add(v);
        nodes.add(w);

        adjListMap.computeIfAbsent(v, k -> new HashSet<>()).add(w);
        adjListMap.computeIfAbsent(w, k -> new HashSet<>()).add(v);
    }

    /**
     * Method to check if there is a path (connection) between 2 city nodes in the graph.
     *
     * @param src name of the first city
     * @param dst name of the second city
     * @return true if there is a path; false otherwise
     */
    public boolean containsPathBetween(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return false;
        }
        if (src.equals(dst)) {
            return true;
        }
        if (adjListMap.get(src).contains(dst)) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        visited.add(src);
        q.add(src);

        while (!q.isEmpty()) {
            String node = q.remove();

            for (String adj : adjListMap.get(node)) {
                if (adj.equals(dst)) {
                    return true;
                }

                if (!visited.contains(adj)) {
                    visited.add(adj);
                    q.add(adj);
                }
            }
        }

        return false;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public void setNodes(Set<String> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Set<String>> getAdjListMap() {
        return adjListMap;
    }

    public void setAdjListMap(Map<String, Set<String>> adjListMap) {
        this.adjListMap = adjListMap;
    }

}
