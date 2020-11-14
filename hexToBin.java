import java.math.BigInteger;

public class hexToBin {
    public static String convert(String s){
        return new BigInteger(s, 16).toString(2);
    }
}