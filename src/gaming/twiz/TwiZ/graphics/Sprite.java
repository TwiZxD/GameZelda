package gaming.twiz.TwiZ.graphics;

/**
 * Created by Johan Segerlund on 2014-04-08.
 * Episode 20
 */
//Den här klassen skapar tiles som vi kan använda, grass sand etc mobs, players,.
public class Sprite {

    public final int SIZE;
    private int x, y;
    private int width, height;


    public int[] pixels;
    protected SpriteSheet sheet;

    public static Sprite iceProjectile = new Sprite(16,0,1, SpriteSheet.tiles);

    public static Sprite voidTile = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite grass_1 = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite grass_2 = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite Flower_1= new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 4, 0, SpriteSheet.tiles);
    public static Sprite sand = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidErrorSprite = new Sprite(16, 0xf0f0ff); //16 size, 0 = black 0xffffff = white.
    public static Sprite nullSprite = new Sprite(16, 0xff00ff);
    //   public static Sprite black_Link_down = new Sprite(32,0,1,SpriteSheet.player_black_Link_moving);
    public static Sprite dummy = new Sprite(32,0,0, SpriteSheet.player_Link_moving);

    //Player Sprites here:
    public static Sprite player_Down_1 = new Sprite(16,0,2,SpriteSheet.tiles);
    public static Sprite player_Left_1 = new Sprite(16,1,2,SpriteSheet.tiles);
    public static Sprite player_Up_1 = new Sprite(16,2,2,SpriteSheet.tiles);
    public static Sprite player_Right_1 = new Sprite(16,3,2,SpriteSheet.tiles);

    public static Sprite player_Down_2 = new Sprite(16,0,3,SpriteSheet.tiles);
    public static Sprite player_Left_2 = new Sprite(16,1,3,SpriteSheet.tiles);
    public static Sprite player_Up_2 = new Sprite(16,2,3,SpriteSheet.tiles);
    public static Sprite player_Right_2 = new Sprite(16,3,3,SpriteSheet.tiles);

    public static Sprite spikeplayer_Down_1 = new Sprite(16,0,4,SpriteSheet.tiles);
    public static Sprite spikeplayer_Down_2 = new Sprite(16,0,5,SpriteSheet.tiles);
    public static Sprite spikeplayer_Down_3 = new Sprite(16,0,6,SpriteSheet.tiles);

    public static Sprite spikeplayer_Left_1 = new Sprite(16,1,4,SpriteSheet.tiles);
    public static Sprite spikeplayer_Left_2 = new Sprite(16,1,5,SpriteSheet.tiles);
    public static Sprite spikeplayer_Left_3= new Sprite(16,1,6,SpriteSheet.tiles);

    public static Sprite spikeplayer_Right_1 = new Sprite(16,2,4,SpriteSheet.tiles);
    public static Sprite spikeplayer_Right_2 = new Sprite(16,2,5,SpriteSheet.tiles);
    public static Sprite spikeplayer_Right_3 = new Sprite(16,2,6,SpriteSheet.tiles);
    //public static Sprite player = new Sprite(32, 0, 1, SpriteSheet.tiles); //om vi har 32 stor player

    //Projectile Sprites here:
    public static Sprite projectile_wizard = new Sprite(16,0,0,SpriteSheet.projectile_wizard);
    public static Sprite projectile_arrow_default = new Sprite(16,0,0,SpriteSheet.projectile_arrow);

    //Particles (Size, färg)
    public static Sprite particle_normal = new Sprite(2,0xAAAAAA);
    //Other
    public static Sprite Life_0 = new Sprite(8,0,0,SpriteSheet.life_Hearts_small);
    public static Sprite Life_1 = new Sprite(8,1,0,SpriteSheet.life_Hearts_small);
    public static Sprite Life_2 = new Sprite(8,2,0,SpriteSheet.life_Hearts_small);
    public static Sprite Life_3 = new Sprite(8,3,0,SpriteSheet.life_Hearts_small);
    public static Sprite Life_4 = new Sprite(8,4,0,SpriteSheet.life_Hearts_small);

    //Map
    public static Sprite balk_topleft = new Sprite(32,0,0,SpriteSheet.inside_House_Link);
    public static Sprite balk_topright = new Sprite(32,7,0,SpriteSheet.inside_House_Link);
    public static Sprite balk_BottomRight = new Sprite(32,5,10,SpriteSheet.inside_House_Link);
    public static Sprite balk_BottomLeft = new Sprite(32,0,6,SpriteSheet.inside_House_Link);



    public static Sprite floor_Yellow_1 = new Sprite(16,15,7,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_2 = new Sprite(16,16,7,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_3 = new Sprite(16,17,7,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_4 = new Sprite(16,18,7,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_5 = new Sprite(16,19,7,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_6 = new Sprite(16,15,8,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_7 = new Sprite(16,16,8,SpriteSheet.inside_House_Link);
    public static Sprite floor_Yellow_8 = new Sprite(16,17,8,SpriteSheet.inside_House_Link);

    public static Sprite wall_North_Top = new Sprite(16,3,0,SpriteSheet.inside_House_Link);
    public static Sprite wall_North_Bot = new Sprite(16,3,1,SpriteSheet.inside_House_Link);
    public static Sprite wall_West_Top = new Sprite(16,0,5,SpriteSheet.inside_House_Link);
    public static Sprite wall_West_Bot = new Sprite(16,1,5,SpriteSheet.inside_House_Link);
    public static Sprite wall_East_Top = new Sprite(16,12,10,SpriteSheet.inside_House_Link);
    public static Sprite wall_East_Bot = new Sprite(16,11,10,SpriteSheet.inside_House_Link);
    public static Sprite wall_South_Top = new Sprite(16,8,20,SpriteSheet.inside_House_Link);
    public static Sprite wall_South_Bot = new Sprite(16,5,19,SpriteSheet.inside_House_Link);

    public static Sprite doorway_North = new Sprite(48,32,5,0,16,SpriteSheet.inside_House_Link);
//    public static Sprite doorway_North = new Sprite(32,2,0,SpriteSheet.inside_House_Link);

    public static Sprite stairs_Down = new Sprite(16,6,1,SpriteSheet.inside_House_Link);
    public static Sprite stairs_Up = new Sprite(16,4,1,SpriteSheet.inside_House_Link);



    protected Sprite(SpriteSheet sheet,int width, int height){
        SIZE = (width == height) ? width : -1; //if width == heigh is true then SIZE is width, else -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;

    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load(); //lägger in bilden i pixel[]
    }


    public Sprite(int width, int height, int x, int y,int sizeOfSpritesInSheet, SpriteSheet sheet) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        this.x = x * sizeOfSpritesInSheet;
        this.y = y * sizeOfSpritesInSheet;
        this.sheet = sheet;
        load(); //lägger in bilden i pixel[]
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE*SIZE];
        setColour(colour);
    }

    public Sprite(int width, int height, int colour){
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        setColour(colour);
    }


    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        System.arraycopy(pixels, 0, this.pixels, 0, pixels.length); //Method 1. important to copy EP 109
/*        for (int i = 0; i < pixels.length; i++) {                 //Copy method 2
            this.pixels[i] = pixels[i];
        }*/


    }


    public static Sprite[] split(SpriteSheet sheet) {
        int amount = ( (sheet.getWidth() * sheet.getHeight() ) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT) );
        Sprite[] sprites = new Sprite[amount];
        int current = 0;
        int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
        for (int yp = 0; yp <  sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
            for (int xp = 0; xp <  sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {

                for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
                    for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
                        int x0 = (x + xp * sheet.SPRITE_WIDTH);
                        int y0 = (y + yp * sheet.SPRITE_HEIGHT);
                        pixels[x+y*sheet.SPRITE_WIDTH] = sheet.getPixels()[x0 + y0 * sheet.getWidth()];
                    }
                }
                sprites[current++] = new Sprite(pixels,sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
                //current++; gör detta redan
            }
        }
        return sprites;
    }

    private void setColour(int colour){
        for (int i = 0; i < width*height; i++){
            pixels[i] = colour;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
      return height;
    }

    public int getSIZE(){
        return SIZE;

    }

    private void load(){
        for (int y = 0; y< height; y++){
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y)*sheet.SPRITE_WIDTH];
            }
        }

    }

}
