import java.util.*;

/**
 * Created by sofia on 4/16/18.
 */

/**
 * Code written during a phone interview.
 */

/**
 * Question 1:
 *
 * Input:
 * MIA -> BOS
 * JFK -> LAS
 * SFO -> JFK
 * BOS -> SFO
 *
 * Output:
 * MIA -> BOS -> SFO -> JFK -> LAS
 *
 * Question 2:
 *
 * Input:
 * SFO - JFK
 * BOS - SFO
 * MIA - BOS
 * JFK - LAS
 *
 * Output:
 * MIA -> BOS -> SFO -> JFK -> LAS
 * LAS -> JFK -> SFO -> BOS -> MIA
 */
public class FlightPathFinder {

    /**
     * Path finder for directed edges.
     */
    public static class PathFinder {

        static class Route {
            String src;
            String dst;

            public Route(String src, String dst) {
                this.src = src;
                this.dst = dst;
            }
        }

        public List<String> findPath(List<Route> input) {
            List<String> res = new ArrayList<>();

            Set<String> srcSet = new HashSet<>();
            Set<String> dstSet = new HashSet<>();

            Map<String, String> map = new HashMap<>();

            for (Route route : input) {
                srcSet.add(route.src);
                dstSet.add(route.dst);
                map.put(route.src, route.dst);
            }

            String origin = identifyOrigin(srcSet, dstSet);
            String destination = identifyDestination(srcSet, dstSet);

            visit(origin, destination, map, res);

            return res;
        }

        protected void visit(String curr, String dst, Map<String, String> map, List<String> res) {
            res.add(curr);
            if (curr.equals(dst)) return;

            String next = map.get(curr);
            if (next == null) return;

            visit(next, dst, map, res);
        }

        private String identifyOrigin(Set<String> srcSet, Set<String> dstSet) {
            for (String city : srcSet) {
                if (!dstSet.contains(city)) {
                    return city;
                }
            }
            return null;
        }

        private String identifyDestination(Set<String> srcSet, Set<String> dstSet) {
            for (String city : dstSet) {
                if (!srcSet.contains(city)) {
                    return city;
                }
            }
            return null;
        }
    }


    /**
     * Path finder for undirected edges.
     */
    public static class PathFinder2 {

        static class Graph {
            int V;
            List<String> nodes;
            List<String>[] adjLists;

            @SuppressWarnings("rawtypes")
            public Graph(int size) {
                V = size;
                nodes = new ArrayList<>();
                adjLists = new ArrayList[V];
                for (int i = 0; i < V; i++) {
                    adjLists[i] = new ArrayList<>();
                }
            }

            public void addEdge(String v, String w) {
                int vIdx = getNodeIndex(v);
                int wIdx = getNodeIndex(w);

                adjLists[vIdx].add(w);
                adjLists[wIdx].add(v);
            }

            private int getNodeIndex(String v) {
                int idx = nodes.indexOf(v);
                if (idx < 0) {
                    idx = nodes.size();
                    nodes.add(v);
                }
                return idx;
            }

            public void print() {
                System.out.println("Graph:");
                System.out.println("Nodes:");
                System.out.println(nodes);

                System.out.println("Adjacency Lists:");
                for (int i = 0; i < V; i++) {
                    String node = nodes.get(i);
                    List<String> adjs = adjLists[i];
                    System.out.println(node + ": " + adjs);
                }

                System.out.println();
            }
        }

        public List<List<String>> findPath2(List<String[]> input) {
            List<List<String>> res = new ArrayList<>();

            Graph graph = buildGraph(input);
            graph.print();

            Set<String> candidates = identifyCandidates(graph);

            for (String candidate : candidates) {
                boolean[] visited = new boolean[graph.V];
                List<String> path = new ArrayList<>();
                visit2(candidate, graph, visited, path);
                res.add(path);
            }

            return res;
        }

        protected void visit2(String curr, Graph graph, boolean[] visited, List<String> path) {
            path.add(curr);

            int currIdx = graph.nodes.indexOf(curr);
            visited[currIdx] = true;

            List<String> adjs = graph.adjLists[currIdx];

            for (String adj : adjs) {
                int adjIdx = graph.nodes.indexOf(adj);
                if (visited[adjIdx]) continue;
                visit2(adj, graph, visited, path);
            }
        }

        protected Graph buildGraph(List<String[]> input) {
            Graph graph = new Graph(input.size()+1);

            for (String[] route : input) {
                String src = route[0];
                String dst = route[1];
                graph.addEdge(src, dst);
            }

            return graph;
        }

        protected Set<String> identifyCandidates(Graph graph) {
            Set<String> res = new HashSet<>();

            for (int i = 0; i < graph.nodes.size(); i++) {
                if (graph.adjLists[i].size() == 1) {
                    res.add(graph.nodes.get(i));
                }
            }

            return res;
        }
    }





    public static void main(String[] args) {
        PathFinder pathFinder = new PathFinder();

        List<PathFinder.Route> input = new ArrayList<>();
        input.add(new PathFinder.Route("MIA", "BOS"));
        input.add(new PathFinder.Route("JFK", "LAS"));
        input.add(new PathFinder.Route("SFO", "JFK"));
        input.add(new PathFinder.Route("BOS", "SFO"));

        List<String> path = pathFinder.findPath(input);
        System.out.print(path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print("->"+path.get(i));
        }
        System.out.println();

        System.out.println();

        PathFinder2 pathFinder2 = new PathFinder2();

        List<String[]> input2 = new ArrayList<>();
        input2.add(new String[] { "SFO", "JFK" });
        input2.add(new String[] { "BOS", "SFO" });
        input2.add(new String[] { "MIA", "BOS" });
        input2.add(new String[] { "JFK", "LAS" });

        List<List<String>> paths = pathFinder2.findPath2(input2);
        for (List<String> p : paths) {
            System.out.print(p.get(0));
            for (int i = 1; i < p.size(); i++) {
                System.out.print("->"+p.get(i));
            }
            System.out.println();
        }

        System.out.println();
    }

}
