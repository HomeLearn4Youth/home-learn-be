package com.homelearn.back.group;

import com.homelearn.back.house.dto.ApartOutputSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ApproximateShortestPath {

    static class Point {
        double x, y;
        ApartOutputSpec aprt;

        Point(double x, double y, ApartOutputSpec aprt) {
            this.x = x;
            this.y = y;
            this.aprt = aprt;
        }

        double distance(Point p) {
            return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
        }
    }

    static class Edge implements Comparable<Edge> {
        int src, dest;
        double weight;

        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge compareEdge) {
            return Double.compare(this.weight, compareEdge.weight);
        }
    }

    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    static int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    static void Union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    static List<Edge> KruskalMST(List<Point> points) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int V = points.size();
        Subset subsets[] = new Subset[V];
        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset(v, 0);
        }

        for (int i = 0; i < V; ++i)
            for (int j = i + 1; j < V; ++j)
                pq.add(new Edge(i, j, points.get(i).distance(points.get(j))));

        List<Edge> mst = new ArrayList<>();

        while (mst.size() < V - 1) {
            Edge edge = pq.remove();
            int x = find(subsets, edge.src);
            int y = find(subsets, edge.dest);

            if (x != y) {
                mst.add(edge);
                Union(subsets, x, y);
            }
        }

        return mst;
    }

    static void dfsUtil(int v, boolean visited[], List<List<Integer>> adjList, List<Integer> path) {
        visited[v] = true;
        path.add(v);

        for (int i : adjList.get(v)) {
            if (!visited[i])
                dfsUtil(i, visited, adjList, path);
        }
    }

    public static List<ApartOutputSpec> findApproximateShortestPath(List<ApartOutputSpec> aparts) {
        List<Point> points = aparts.stream().map(apart->{
            return new Point(Double.parseDouble(apart.getLat()), Double.parseDouble(apart.getLng()), apart);
        }).collect(Collectors.toList());

        List<Edge> mst = KruskalMST(points);
        List<List<Integer>> adjList = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge edge : mst) {
            adjList.get(edge.src).add(edge.dest);
            adjList.get(edge.dest).add(edge.src);
        }

        List<Integer> pathIndex = new ArrayList<>();
        boolean[] visited = new boolean[points.size()];
        dfsUtil(0, visited, adjList, pathIndex);

        List<ApartOutputSpec> path = new ArrayList<>();
        for (int index : pathIndex) {
            path.add(points.get(index).aprt);
        }
        return path;
    }

}
