package gaming.twiz.TwiZ.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 * Created by Johan Segerlund on 2014-04-08.
 * Episode 19
 */
/**
 * Här skapar/lägger vi till våra spritesheets som innehåller alla grafiska komponenter.
 *
 */
public class SpriteSheet {

    private String path;
    public final int SIZE;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;

    private int width, height;
    public int[] pixels;

    //Players, Mobs
    public static SpriteSheet player_black_Link_moving = new SpriteSheet("/textures/sheets/Player_black_Link_moving_sheet.png",352,128);
    public static SpriteSheet player_black_Link_moving_down =     new SpriteSheet(player_black_Link_moving,0,0,11,1,32);
    public static SpriteSheet player_black_Link_moving_right =    new SpriteSheet(player_black_Link_moving,0,1,11,1,32);
    public static SpriteSheet player_black_Link_moving_up =       new SpriteSheet(player_black_Link_moving,0,2,11,1,32);
    public static SpriteSheet player_black_Link_moving_left =     new SpriteSheet(player_black_Link_moving,0,3,11,1,32);

    public static SpriteSheet player_Link = new SpriteSheet("/textures/sheets/Player_Link_sheet.png",96,128);
    public static SpriteSheet player_Link_down =    new SpriteSheet(player_Link,0,0,3,1,32);
    public static SpriteSheet player_Link_right =   new SpriteSheet(player_Link,0,1,3,1,32);
    public static SpriteSheet player_Link_up =      new SpriteSheet(player_Link,0,2,3,1,32);
    public static SpriteSheet player_Link_left =    new SpriteSheet(player_Link,0,3,3,1,32);

    public static SpriteSheet player_Link_moving = new SpriteSheet("/textures/sheets/Player_Link_moving_sheet.png",352,128);
    public static SpriteSheet player_Link_moving_down =     new SpriteSheet(player_Link_moving,0,0,11,1,32);
    public static SpriteSheet player_Link_moving_right =    new SpriteSheet(player_Link_moving,0,1,11,1,32);
    public static SpriteSheet player_Link_moving_up =       new SpriteSheet(player_Link_moving,0,2,11,1,32);
    public static SpriteSheet player_Link_moving_left =     new SpriteSheet(player_Link_moving,0,3,11,1,32); /*någon bugg med y coordinat*/

    public static SpriteSheet link_Bow = new SpriteSheet("/textures/sheets/Link_Bow.png", 256,256);
    public static SpriteSheet link_Bow_Move_Right = new SpriteSheet(link_Bow,0,0,8,1,32);
    public static SpriteSheet link_Bow_Move_Down =  new SpriteSheet(link_Bow,0,1,8,1,32);
    public static SpriteSheet link_Bow_Move_Up =    new SpriteSheet(link_Bow,0,2,8,1,32);
    public static SpriteSheet link_Bow_Move_Left =  new SpriteSheet(link_Bow,0,3,8,1,32);
    public static SpriteSheet link_Bow_Pull_Right = new SpriteSheet(link_Bow,0,4,3,1,32);
    public static SpriteSheet link_Bow_Pull_Down =  new SpriteSheet(link_Bow,0,5,3,1,32);
    public static SpriteSheet link_Bow_Pull_Up =    new SpriteSheet(link_Bow,0,6,3,1,32);
    public static SpriteSheet link_Bow_Pull_Left =  new SpriteSheet(link_Bow,0,7,3,1,32);
    public static SpriteSheet link_Bow_Release_Right =  new SpriteSheet(link_Bow,5,4,3,1,32);
    public static SpriteSheet link_Bow_Release_Down =   new SpriteSheet(link_Bow,5,5,3,1,32);
    public static SpriteSheet link_Bow_Release_Up =     new SpriteSheet(link_Bow,5,6,3,1,32);
    public static SpriteSheet link_Bow_Release_Left =   new SpriteSheet(link_Bow,5,7,3,1,32);


 /*   public static SpriteSheet player_Link_shooting_arrow = new SpriteSheet("/textures/sheets/Link_Shooting_Arrow.png",512,128);
    public static SpriteSheet Link_shooting_arrow_right =   new SpriteSheet(player_Link_shooting_arrow, 0,0,16,1,32);
    public static SpriteSheet Link_shooting_arrow_up =      new SpriteSheet(player_Link_shooting_arrow, 0,1,16,1,32);
    public static SpriteSheet Link_shooting_arrow_down =    new SpriteSheet(player_Link_shooting_arrow, 0,2,16,1,32);
    public static SpriteSheet Link_shooting_arrow_left =    new SpriteSheet(player_Link_shooting_arrow, 0,3,16,1,32);
*/
    public static SpriteSheet Link_attack_sword = new SpriteSheet("/textures/sheets/Link_Sword_Attack.png",320,128);
    public static SpriteSheet Link_attack_sword_down =  new SpriteSheet(Link_attack_sword,0,0,10,1,32);
    public static SpriteSheet Link_attack_sword_right = new SpriteSheet(Link_attack_sword,0,1,10,1,32);
    public static SpriteSheet Link_attack_sword_up =    new SpriteSheet(Link_attack_sword,0,2,10,1,32);
    public static SpriteSheet Link_attack_sword_left =  new SpriteSheet(Link_attack_sword,0,3,10,1,32);


    public static SpriteSheet Link_sword = new SpriteSheet(Link_attack_sword,0,1,8,1,32);

    public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 64,48);
    public static SpriteSheet player_down = new SpriteSheet(player,0,0,1,3,16);
    public static SpriteSheet player_up = new SpriteSheet(player,1,0,1,3,16);
    public static SpriteSheet player_left = new SpriteSheet(player,2,0,1,3,16);
    public static SpriteSheet player_right = new SpriteSheet(player,3,0,1,3,16);

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256); //location, size in pixel

    //Projectiles
    public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizards.png", 48);

    public static SpriteSheet projectile_arrow = new SpriteSheet("/textures/sheets/projectiles/Arrow.png", 64);
    public static SpriteSheet arrow_Down =  new SpriteSheet(projectile_arrow, 0,0,4,1,16);
    public static SpriteSheet arrow_Right = new SpriteSheet(projectile_arrow, 0,1,4,1,16);
    public static SpriteSheet arrow_Up =    new SpriteSheet(projectile_arrow, 0,2,4,1,16);
    public static SpriteSheet arrow_Left =  new SpriteSheet(projectile_arrow, 0,3,4,1,16);

    public static SpriteSheet projectile_Bomb = new SpriteSheet("/textures/sheets/Bomb.png",256,64);
    public static SpriteSheet bomb_Tick = new SpriteSheet(projectile_Bomb, 0,0,4,1,32);
    public static SpriteSheet bomb_Explosion = new SpriteSheet(projectile_Bomb,0,1,8,1,32);

    //OtherStuff
    public static SpriteSheet life_Hearts_small = new SpriteSheet("/textures/sheets/Life_Hearts_small.png",40,8);

    //Map
    public static SpriteSheet inside_House_Link = new SpriteSheet("/textures/sheets/house_sprites.png", 480,480);

    private Sprite[] sprites;


    /**
     * Use this to select and use a specific area within the spritesheet
     * @param sheet selected Spritesheet
     * @param x start coordinate
     * @param y start coordinate
     * @param width the amount of sprite you want to use in the x axis
     * @param height the amount of sprites you want to use in y axis
     * @param spriteSize the size of EACH SPRITE. if its 16x16 you type 16
     */
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        if(width == height)
            SIZE = width;
        else SIZE = -1;
        SPRITE_WIDTH = w; //skapar spritesheet
        SPRITE_HEIGHT = h;
        pixels = new int[w * h];
        for (int y0 = 0; y0 < h; ++y0){
            int yp = yy + y0;
            for (int x0 = 0 ; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
            }
        }

        int frame = 0;
        sprites = new Sprite[width  * height];
        for (int ya = 0; ya < height; ya++){
            for (int xa = 0; xa < width; xa++){
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++){
                    for (int x0 = 0; x0 < spriteSize; x0++){
          //              System.err.println(spritePixels.length + ", " + pixels.length);
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize , spriteSize);
                sprites[frame] = sprite;
                frame++;
            }
        }

    }
    /**
     * @param path the location where the spritesheet is stored in our
     * @param size The size of the Sprites we want to take out from the spritesheet
     */
    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        SPRITE_WIDTH = size;
        SPRITE_HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     *
     * @param path Location of the spritesheet
     * @param width The width of the spritesheet in pixels
     * @param height The height of the spritesheet in pixels
     */
    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1; // -1 to see that something is wrong here...
        SPRITE_WIDTH = width;
        SPRITE_HEIGHT = height;
        pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    private void load() {


        try {
            System.out.print("Trying to load: "+ path + " ' ... '");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            System.out.println(" succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0,0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.err.println(" failed!");
        }
    }


}
