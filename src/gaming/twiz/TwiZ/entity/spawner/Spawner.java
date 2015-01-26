package gaming.twiz.TwiZ.entity.spawner;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.level.Level;


/**
 * Created by Johan Segerlund on 2014-06-21.
 */
public abstract class Spawner extends Entity {

    public enum Type {
        MOB, PARTICLE;
    }

    private Type type;


    public Spawner(int x, int y, Type type, int amount, Level level) {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;

    }

}
