package gaming.twiz.TwiZ.graphics;

/**
 * Created by Johan Segerlund on 2014-06-27.
 */
public class Font {

    private static SpriteSheet font = new SpriteSheet("/textures/fonts/8bit.png",8);
    private static Sprite[] characters = Sprite.split(font);

    private static String charIndex =   "ABCDEFG" + //
                                        "HIJKLMN" + //
                                        "OPQRSTU" + //
                                        "VWXYZöö" + //
                                        "abcdefg" + //
                                        "hijklmn" + //
                                        "opqrstu" +//
                                        "vwxyz!ö" +//
                                        "ö\"#$%&'" +//
                                        "()*+,-." +//
                                        "/012345" +//
                                        "6789:;<" +//
                                        "=>?@[/]"; //går inte att skriva \

    public Font(){

    }
    public void render(int x, int y, String text, Screen screen){
        render(x,y,text, 0x000000,screen);
    }

    public void render(int x, int y, String text,int color, Screen screen){

        int size = 8;
        int xOffset = 0;
        int line = 0;
        int xLetterOffset = 0;
        for (int i = 0; i < text.length(); i++){
            xOffset += 1;
            int yOffset = 0;
            char currentChar = text.charAt(i);
            if (currentChar == 'g' || currentChar == 'j' || currentChar == 'y' || currentChar == 'q' || currentChar == 'p' || currentChar == ',') yOffset = 2;


            if (currentChar == 'I'|| currentChar == 'T'||currentChar == 'Y'||currentChar == 'k'||currentChar == 'n'||currentChar == '%'||currentChar == '+'||currentChar == '/'||currentChar == '0'||currentChar == '1'||currentChar == '?') xLetterOffset -=1;
            if (currentChar == 'f'|| currentChar == 'r'|| currentChar == 't'|| currentChar == '"'|| currentChar == '&'|| currentChar == '-'|| currentChar == '=') xLetterOffset -= 2;
            if (currentChar == '$'|| currentChar == '('|| currentChar == ')'|| currentChar == '<'|| currentChar == '>') xLetterOffset -= 3;
            if (currentChar == 'j'||currentChar == '*'||  currentChar == '['|| currentChar == ']') xLetterOffset -= 4;
            if (currentChar == '!'||currentChar == 'i'|| currentChar == 'l'|| currentChar == ','|| currentChar == '\''|| currentChar == '.'|| currentChar == ':'|| currentChar == ';') xLetterOffset -=5;
            if ( currentChar == '\n'){
                line++; //ny rad
                xOffset = 0;
                xLetterOffset = 0;
            }
            int index = charIndex.indexOf(currentChar);
            //System.out.print(currentChar + " ");
            if (index == -1){
                xLetterOffset -=4; //space between words
                continue;
            } // if the character doesn't exist type nothing at that position
            screen.renderTextCharacter(x + xLetterOffset + size * xOffset,y + (line * (size+2)) + yOffset,characters[index],color ,true);
        }


    }

}

