package subway.domain;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.List;
import java.util.Set;

public class Graph {
    private static final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
    private static final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);

    public static WeightedMultigraph<String, DefaultWeightedEdge> getDistanceGraph() {
        return distanceGraph;
    }

    public static WeightedMultigraph<String, DefaultWeightedEdge> getTimeGraph() {
        return timeGraph;
    }

    public static Set<String> vertexs() {
        return distanceGraph.vertexSet();
    }

    public static void addVertex(String vertex) {
        distanceGraph.addVertex(vertex);
        timeGraph.addVertex(vertex);
    }

    public static void setEdgeWeight(String sourceVertex, String targetVertex, int distanceWeight, int timeWeight) {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(sourceVertex, targetVertex), distanceWeight);
        timeGraph.setEdgeWeight(timeGraph.addEdge(sourceVertex, targetVertex), timeWeight);
    }

    public static List<String> getDijkstraShortestPath(String sourceVertex, String targetVertex) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(distanceGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(sourceVertex, targetVertex).getVertexList();
        return shortestPath;
    }
}
