import java.math.BigInteger;
// En base a las respuestas de Mike Samuel y wmassingham en:
// https://stackoverflow.com/questions/9246326/convert-hexadecimal-string-hex-to-a-binary-string#comment12442372_9246349
public class hexToBin32 {
    public static String convert(String s){
        String sinPadding = new BigInteger(s, 16).toString(2);
        return String.format("%32s", sinPadding).replace(' ', '0');
    }
}