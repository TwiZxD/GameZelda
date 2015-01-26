package gaming.twiz.TwiZ.util;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.mob.Player;
import gaming.twiz.TwiZ.entity.projectile.ArrowProjectile;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.level.Level;

import java.util.ArrayList;
import java.util.List;

import static gaming.twiz.TwiZ.util.Debug.drawRect;

/**
 * Created by Johan Segerlund on 2014-07-05.
 */
public class Hitbox{

    //private int x,y;
    private int width,height;
    private Entity source;
    private static int debugColorBlue = 0x0000ff;
    private static int debugColorRed = 0xff0000;
    private int color = debugColorRed;
    private Level level;

    public Hitbox(Entity entity){
        this.source = entity;
    //    this.x = (int)entity.getX();
   //     this.y = (int)entity.getY();
        this.width = 16;
        this.height = 16;
  //      level.add(this,5);
    }

    /**
     * @param entity the entity that this hitbox is referring too
     * @param width The width of the mobs hitbox
     * @param height The height of the mobs hitbox
     */
    public Hitbox(Entity entity, int width, int height ){
        this.source = entity;
    //    this.x = (int)entity.getX();
     //   this.y = (int)entity.getY();
        this.width = width;
        this.height = height;
        //level.add(this,5);
    }

    //Mid Position
    private int getX(){
        return (int)source.getX();
    }
    //Left Wall
    private int getX1(){
        return getX()-width/2;
    }
    //Right Wall
    private int getX2(){
        return getX()+width/2;
    }

    //MidPosition
    private int getY(){
        return (int)source.getY();
    }
    //Top Wall
    private int getY1(){
        return getY()-height/2;
    }
    //Bottom Wall
    private int getY2(){
        return getY()+height/2;
    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void update(){
    }

    public Entity getOwnerOfThisHitbox(){
        return source;
    }

    public Entity collision(Level level){
       // List<Entity> entities = level.getEntities(this.getOwnerOfThisHitbox(),50);
        List<Entity> entities = getCollisionHitList(level);
    //    System.out.println(entities.size());
        for(int i  = 0; i<entities.size(); i++){
            Hitbox other = entities.get(i).getHitbox();
           // System.out.println(other);
            if(other != null){
                if(checkCollision(other)) {
                    return entities.get(i);
                }
            }
        }
        return null;
    }

    public List<Entity> getCollisionHitList(Level level) {
        List<Entity> result = new ArrayList<Entity>();
        List<Entity> entities = level.getEntities(this.getOwnerOfThisHitbox(),50);
        List<Player> players = level.getPlayers(this.getOwnerOfThisHitbox(), 50);
        for(int i = 0; i < entities.size(); i++) {
            if (checkCollision(entities.get(i).getHitbox())) {
                result.add(entities.get(i));
            }
        }
        for(int i = 0; i < players.size(); i++){
            if(checkCollision(players.get(i).getHitbox())){
                result.add(players.get(i));
            }

        }
        return result;
    }


    private boolean checkCollision(Hitbox other){
   //     if(source instanceof ArrowProjectile) {
    //        System.out.print("A(" + getX1() + "," + getY1() + " ," + getX2() + "," + getY2() + " ) " + "B(" + other.getX1() + "," + other.getY1() + " ," + other.getX2() + " ," + other.getY2() + ")");
     //   }
        //Check if Ax2 < Bx1: true : nocollision
        //!(PvQvRvT)
        if(!(other.getOwnerOfThisHitbox().equals(source))) {
            if (!((this.getX2() < other.getX1())
                    || (this.getX1() > other.getX2())
                    || (this.getY1() > other.getY2())
                    || (this.getY2() < other.getY1())
            )) {
                color = debugColorBlue;
                return true;

            } else {
                color = debugColorRed;
                return false;
            }
        }return false;
    }

    public void render(Screen screen){
        drawRect(screen, getX1(),getY1(),width,height, color, true);
    }

}
