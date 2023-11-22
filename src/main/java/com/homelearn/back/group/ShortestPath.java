package com.homelearn.back.group;

import com.homelearn.back.house.dto.ApartOutputSpec;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {

    static class Point {
        double x, y;
        ApartOutputSpec apart;

        Point(ApartOutputSpec apart) {
            this.x = Double.parseDouble(apart.getLat());
            this.y = Double.parseDouble(apart.getLng());
            this.apart = apart;
        }

        double distance(Point p) {
            return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
        }
    }

    static class Node implements Comparable<Node> {
        int level;
        double bound;
        List<Integer> path;

        Node(int level, double bound, List<Integer> path) {
            this.level = level;
            this.bound = bound;
            this.path = new ArrayList<>(path);
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.bound, other.bound);
        }
    }

    private static double[][] distanceMatrix;
    private static double bestCost = Double.MAX_VALUE;
    private static List<Integer> bestPath;

    public static List<ApartOutputSpec> findOptimalPath(List<ApartOutputSpec> aparts) {
        List<Point> points = aparts.stream()
                .map(apart-> new Point(apart)).collect(Collectors.toList());

        int n = points.size();
        distanceMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = points.get(i).distance(points.get(j));
            }
        }

        branchAndBound(n);

        List<ApartOutputSpec> optimalPath = new ArrayList<>();
        for (int index : bestPath) {
            optimalPath.add(aparts.get(index));
        }
        return optimalPath;
    }

    private static void branchAndBound(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, Arrays.asList(0)));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.bound < bestCost) {
                for (int i = 0; i < n; i++) {
                    if (!node.path.contains(i)) {
                        List<Integer> newPath = new ArrayList<>(node.path);
                        newPath.add(i);

                        if (newPath.size() == n) {
                            newPath.add(newPath.get(0)); // Complete the cycle
                            double cost = calculateTotalCost(newPath);
                            if (cost < bestCost) {
                                bestCost = cost;
                                bestPath = newPath;
                            }
                        } else {
                            double bound = calculateBound(newPath, n);
                            pq.add(new Node(node.level + 1, bound, newPath));
                        }
                    }
                }
            }
        }
    }

    private static double calculateTotalCost(List<Integer> path) {
        double cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += distanceMatrix[path.get(i)][path.get(i + 1)];
        }
        return cost;
    }

    private static double calculateBound(List<Integer> path, int n) {
        double bound = calculateTotalCost(path);
        int last = path.get(path.size() - 1);

        double minEdge = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!path.contains(i)) {
                minEdge = Math.min(minEdge, distanceMatrix[last][i]);
            }
        }
        return bound + minEdge;
    }
}

