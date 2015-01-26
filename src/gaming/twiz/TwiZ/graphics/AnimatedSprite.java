package gaming.twiz.TwiZ.graphics;

/**
 * Created by Johan Segerlund on 2014-06-22.
 */
public class AnimatedSprite extends Sprite{

    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int time = 0;
    private int length = -1; // how many frames(sprites) of animation we have
    private int startPosition;

    /**
     *
     * @param sheet
     * @param width width of the sprite pixels?
     * @param height height of the sprite pixels?
     * @param length how many animation sprites we use
     */

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length){
        super(sheet,width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        startPosition = 0;
        if(length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!" );
    }

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int start, int endPosition){
        super(sheet,width, height);
        this.length = endPosition - start;
        this.startPosition = start;
        sprite = sheet.getSprites()[0];
        if(length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!" );
    }

    public void update(){
        time++;
        if(time % rate == 0){
            if((frame - startPosition)>= length - 1) frame = startPosition;
            else frame++;
            sprite = sheet.getSprites()[frame];
        //    System.out.println(sprite);
        }
    //    System.out.println(sprite + ": Frame:" + frame);
    }

    public boolean update(int endFrame){
        time++;
        if(time % rate == 0){
            if((frame - startPosition)>= length - 1) return true;
            else frame++;
            sprite = sheet.getSprites()[frame];
            //    System.out.println(sprite);
        }
        return false;
        //    System.out.println(sprite + ": Frame:" + frame);
    }

    /**
     * Animates a specific area of the AnimSpriteArray
     * @param startFrame
     * @param endFrame
     */
    public void update(int startFrame, int endFrame){
        time++;
        if(time % rate == 0){
            if(frame -startFrame>= ((endFrame - startFrame) - 1)) {
                frame = startFrame;
            }
            else frame++;
            sprite = sheet.getSprites()[frame];
        }
      //         System.out.println(sprite + ": Frame:" + frame);
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }

    public void setFrame(int index) {
        if (index > sheet.getSprites().length -1){
            System.err.println("Index out of bounds in " + this);
            return;
        }
        sprite = sheet.getSprites()[index];
    }
}
