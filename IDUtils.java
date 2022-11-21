package Utils;


import java.util.Arrays;
import java.util.Random;

public class IDUtils {

    // Characters for the Tag
    private static final String[] tagChars = {"0", "2", "8", "9", "P", "Y", "L", "Q", "G", "R", "J", "C", "U", "V"};
	// Gabriel55Ita shitcoder cause int limit is ~2kkk so after Q in 1 character in tag code breaks :)
    public static String id2Tag(long Id) {
        StringBuilder tag = new StringBuilder();
        while (Id > 0) {
            int charIndex = (int) Math.floor(Id % tagChars.length);
            tag.insert(0, tagChars[charIndex]);
            Id -= charIndex;
            Id /= tagChars.length;
        }
        return "#" + tag;
    }
    public static int randomHighId(){
        Random rand = new Random();
        return rand.nextInt(30000000);
    }
    public static int randomLowId(){
//        int Id = (int) Math.random()*80707213;
        Random rand = new Random();
        return rand.nextInt(30000000);
    }
	// nasral polniy unitaz replace
    public static long[] tag2HLid(String hashtag){
        String[] tagArray = hashtag.replace("#", "").split("");
        long Id = 0;
        long[] highlow = new long[2];
        long charIndex;
        for (int i = 0; i < tagArray.length; i++){
            String character = tagArray[i];
            try {
                charIndex = Arrays.toString(tagChars).replace("[", "").replace("]", "").replace(",", "").replace(" ", "").indexOf(character);
            }
            catch (IllegalArgumentException e){
                highlow[0] = 0;
                highlow[1] = 1;
                return highlow;
            }
            Id *= tagChars.length;
            Id += charIndex;
        }
        highlow[0] = Id % 256;
        highlow[1] = (Id - (Id % 256)) >> 8;
        return highlow;
    }

}
