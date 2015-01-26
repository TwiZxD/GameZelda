package gaming.twiz.TwiZ.level;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.mob.Player;
import gaming.twiz.TwiZ.entity.particle.Particle;
import gaming.twiz.TwiZ.entity.projectile.Projectile;
import gaming.twiz.TwiZ.entity.spawner.Spawner;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.level.tile.Tile;
import gaming.twiz.TwiZ.util.Hitbox;
import gaming.twiz.TwiZ.util.Vector2i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Johan Segerlund on 2014-04-09.
 */
public class Level  {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    protected int tile_size;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();
    private List<Hitbox> hitboxes = new ArrayList<Hitbox>();

    public List<Player> players = new ArrayList<Player>();
 //   public static Level spawn = new SpawnLevel("/levels/spawn.png"); avsnitt 63 kanske? skippad

    private Comparator<Node> nodeSorter = new Comparator<Node>() {
        public int compare(Node n0, Node n1){
            if (n1.fCost < n0.fCost) return +1;
            if (n1.fCost > n0.fCost) return -1;
            return 0;
        }
    };
    public Level(int width, int height){
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
     //   generateLevel(); // finds what tile

    }

    public Level(String path) {
        loadLevel(path);
     //   generateLevel();
    }

    /*protected void generateLevel() { //doesnt get used really...
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
                getTile(x, y);
            }

        }
        tile_size = 16;
    }*/

    protected void loadLevel(String path){
    }

    public void update() {

        for(int i = 0; i < entities.size(); i++){
    /*        System.out.println(entities.get(i));
            System.out.println(entities.get(i));*/
            entities.get(i).update();
        }
        for(int i = 0; i < projectiles.size(); i++){
            projectiles.get(i).update();
        }
        for(int i = 0; i < particles.size(); i++){
            particles.get(i).update();
        }
        for(int i = 0; i < players.size(); i++){
            players.get(i).update();
        }
        for(int i = 0; i < hitboxes.size(); i++){
            hitboxes.get(i).update();
        }
        remove();
        /*System.out.println("entities= " + entities.size());
        System.out.println("projectiles= " + projectiles.size());
        System.out.println("particles= " + particles.size());
        System.out.println("players= " + players.size());*/
    }


    private void remove() {
        for(int i = 0; i < entities.size(); i++){
            if (entities.get(i).isRemoved()) entities.remove(i);
            else if (entities.get(i) instanceof Spawner) entities.remove(i);
        }
        for(int i = 0; i < projectiles.size(); i++){
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
        }
        for(int i = 0; i < particles.size(); i++){
            if (particles.get(i).isRemoved()) particles.remove(i);
        }
        for(int i = 0; i < players.size(); i++){
            if (players.get(i).isRemoved()) players.remove(i);
        }
        for(int i = 0; i < hitboxes.size(); i++){
            if (hitboxes.get(i).getOwnerOfThisHitbox().isRemoved()) hitboxes.remove(i);
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private void time(){
    }

    /**
     * x,y Position
     * size is size of the projectile width and height
     * x and y offsets are how much space that is left over from the tile for each side... EP 81 around 13 min
     * */
    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset){ //Changed EP 81
        boolean solid = false;
        for ( int c =  0; c < 4; c++) {
            // 2 * arean + pixel avstånd // hur nära får spelaren komma nära objekt
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;
            if(getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;                      // x koordinat längst till vänster om skärmen
        int x1 = (xScroll + screen.width + 16) >> 4;     // x Koordinat längst till höger.
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++){
                //getTile(x,y).render(x,y, screen);
     //           if (x + y * 16 <0 || x + y * 16 >= 256){
                    getTile(x, y).render(x, y, screen);
                }
               //     tiles[x + y * 16].render(x,y,screen);
            for (int i = 0 ; i < entities.size(); i++) {
                entities.get(i).render(screen);
            }
            for (int i = 0 ; i < projectiles.size(); i++) {
                projectiles.get(i).render(screen);
            }
            for (int i = 0 ; i < particles.size(); i++) {
                particles.get(i).render(screen);
            }
            for (int i = 0 ; i < players.size(); i++) {
                players.get(i).render(screen);
            }
            }
        }


    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle){
            particles.add((Particle) e);
        }else if(e instanceof Projectile) {
            projectiles.add((Projectile) e);
        }else if(e instanceof Player) {
            players.add((Player) e);
     //       entities.add(e);

        }else{
            entities.add(e);
        }
    }

    public Player getPlayerAt(int index){
        return players.get(index);
    }

    public Player getClientPlayer(){
        return players.get(0);
    }


    public List<Node> findPath(Vector2i start, Vector2i goal) {    //EP 101?
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();
        Node current = new Node(start,null,0, getDistance(start, goal));
        openList.add(current);
        while(openList.size() > 0) {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);
            if (current.tile.equals(goal)) {
                List<Node> path = new ArrayList<Node>();
                while (current.parent != null){
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9; i++){
                if (i == 4) continue;
                int x = current.tile.getX();
                int y = current.tile.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                Tile at = getTile(x + xi, y + yi);
                if (at == null) continue;
                if (at.solid()) continue;
                Vector2i a = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
                double hCost = getDistance(a, goal);
                Node node = new Node(a,current, gCost, hCost);
                if (vecInList(closedList, a) && gCost >= node.gCost) continue;
                if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
            }
        }
        closedList.clear();
        return null;
    }

    private boolean vecInList(List<Node> list, Vector2i vector){
        for (Node n : list) {
            if(n.tile.equals(vector)) return true;
        }
        return false;
    }

    private double getDistance(Vector2i tile, Vector2i goal){
        double dx = tile.getX() - goal.getX();
        double dy = tile.getY() - goal.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Creates a list with entities around a given entity, excludes itself
     * @param e is the given entity
     * @param radius Search radius
     * @return
     */
    public List <Entity> getEntities(Entity e, int radius){
        List<Entity> result = new ArrayList<Entity>();
        int ex = (int)e.getX();
        int ey = (int)e.getY();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity =  entities.get(i);
            if (entity.equals(e)) continue; //excludes itself
            if (entity.equals(projectiles))continue;
            int x = (int)entity.getX();
            int y = (int)entity.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt(dx*dx + dy*dy);
            if(distance <= radius){
                result.add(entity);
            }
        }
        return result;
    }


    public List<Player> getPlayers(Entity e, int radius) {
        List<Player> result = new ArrayList<Player>();
        int ex = (int)e.getX();
        int ey = (int)e.getY();
        for (int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            int x = (int)player.getX();
            int y = (int)player.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt(dx*dx + dy*dy);
            if(distance <= radius) result.add(player);
        }
        return result;
    }

    // Grass = 0xFF00FF00
    // Flower = 0xFFFFFF00
    // Rock = 0xFF7F7F00
    //Läser in färg kartan och ger en tile för respektive kod.
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidErrorTile;
        if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass_1;
        if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower_1;
        if (tiles[x + y * width] == 0xFF7F7F00) return Tile.rock;
        if (tiles[x + y * width] == 0xFFc88d6d) return Tile.balk_topleft;
        if (tiles[x + y * width] == 0xFFc88d65) return Tile.balk_topright;
        if (tiles[x + y * width] == 0xFFC88D5B) return Tile.balk_BottomRight;
        if (tiles[x + y * width] == 0xFFC88D57) return Tile.balk_BottomLeft;
        if (tiles[x + y * width] == 0xFF000000) return Tile.voidSolidTile;
        if (tiles[x + y * width] == 0xFFe6c061) return Tile.floor_Yellow_6;
        if (tiles[x + y * width] == 0xFFe8c061) return Tile.floor_Yellow_7;
        if (tiles[x + y * width] == 0xFFeac061) return Tile.floor_Yellow_8;
        if (tiles[x + y * width] == 0xFF997363) return Tile.wall_North_Top;
        if (tiles[x + y * width] == 0xFFd0d8c8) return Tile.wall_North_Bot;
        if (tiles[x + y * width] == 0xFF8f7363) return Tile.wall_West_Top;
        if (tiles[x + y * width] == 0xFFd6d8c8) return Tile.wall_West_Bot;
        if (tiles[x + y * width] == 0xFF937363) return Tile.wall_East_Top;
        if (tiles[x + y * width] == 0xFFCED8C8) return Tile.wall_East_Bot;
        if (tiles[x + y * width] == 0xFF917363) return Tile.wall_South_Top;
        if (tiles[x + y * width] == 0xFFCAD8C8) return Tile.wall_South_Bot;
        if (tiles[x + y * width] == 0xFFA11D4D) return Tile.stairs_Up;
        if (tiles[x + y * width] == 0xFFA31C4D) return Tile.stairs_Down;
        if(tiles [x + y * width] == 0xFF998D5B) return Tile.doorway_North;

        return Tile.voidErrorTile;


    }
}
