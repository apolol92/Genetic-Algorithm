package genetic_algorithm;

import java.util.ArrayList;

/**
 * Created by apolol92 on 06.02.2016.
 * Represents a node of a graph
 */
public class Node {
    private final int id;
    private static int lastId = -1;
    private ArrayList<Node> children;

    /**
     * Getter X
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Setter X
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter Y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Setter Y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Coordinates of node
     */
    double x,y;

    /**
     * Generate new node id
     * @return new node id
     */
    private static int generateNewId() {
        lastId++;
        return lastId;
    }

    /**
     * Create new node
     * @param x
     * @param y
     */
    public Node(double x, double y) {
        this.id = generateNewId();
        this.children = new ArrayList<Node>();
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate distance to node n
     * @param n node
     * @return distance
     */
    public double distanceTo(Node n) {
        return Math.sqrt((n.x - this.x) * (n.x - this.x) + (n.y - this.y) * (n.y - this.y));
    }





}
