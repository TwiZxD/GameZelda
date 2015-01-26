package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-04-09.
 */
public class Tile { //Used in the level class
    public Sprite sprite;



    public static Tile grass_1 = new GrassTile(Sprite.grass_1);
    public static Tile grass_2 = new GrassTile(Sprite.grass_2);
    public static Tile flower_1 = new FlowerTile(Sprite.Flower_1);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidErrorTile = new VoidErrorTile(Sprite.voidTile);
    public static Tile voidSolidTile = new NullSolidTile();
 //   public static Tile voidTile = new NormalTile(Sprite.voidTile);

    public static Tile IceShard = new GrassTile(Sprite.iceProjectile);

    public static Tile balk_topleft = new SolidTile(Sprite.balk_topleft);
    public static Tile balk_topright = new SolidTile(Sprite.balk_topright);
    public static Tile balk_BottomRight = new SolidTile(Sprite.balk_BottomRight);
    public static Tile balk_BottomLeft = new SolidTile(Sprite.balk_BottomLeft);

    public static Tile floor_Yellow_6 = new NormalTile(Sprite.floor_Yellow_6);
    public static Tile floor_Yellow_7 = new NormalTile(Sprite.floor_Yellow_7);
    public static Tile floor_Yellow_8 = new NormalTile(Sprite.floor_Yellow_8);

    public static Tile wall_North_Top = new SolidTile(Sprite.wall_North_Top);
    public static Tile wall_North_Bot = new SolidTile(Sprite.wall_North_Bot);
    public static Tile wall_West_Top = new SolidTile(Sprite.wall_West_Top);
    public static Tile wall_West_Bot = new SolidTile(Sprite.wall_West_Bot);
    public static Tile wall_East_Top = new SolidTile(Sprite.wall_East_Top);
    public static Tile wall_East_Bot = new SolidTile(Sprite.wall_East_Bot);
    public static Tile wall_South_Top = new SolidTile(Sprite.wall_South_Top);
    public static Tile wall_South_Bot = new SolidTile(Sprite.wall_South_Bot);

    public static Tile doorway_North = new SolidTile(Sprite.doorway_North);

    public static Tile stairs_Up = new NormalTile(Sprite.stairs_Up);
    public static Tile stairs_Down = new NormalTile(Sprite.stairs_Down);




    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, sprite);

    }

    public boolean solid() { //Om tilen ska ha någon kollition, kan spelaren gå över objektet
        return false;
    }

}
