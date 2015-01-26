package gaming.twiz.TwiZ.level;

import gaming.twiz.TwiZ.util.Vector2i;

/**
 * Episode 99
 * Created by Johan Segerlund on 2014-06-24.
 */
public class Node {

    public Vector2i tile;
    public Node parent; //föregående node
    public double fCost; //fastest way, maybe not the closest, (swim over river or go around it?)
    public double gCost; //keeps track of how long it have taken to get to finish
    public double hCost; //rak väg till start node från slut node

    public Node(Vector2i tile, Node parent, double gCost, double hCost) {
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = gCost;
        this.fCost = this.gCost * this.hCost;
    }
}
