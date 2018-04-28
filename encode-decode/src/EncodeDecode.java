/**
 * Created by sofia on 4/23/18.
 */

/***
 * Code written during a timed online coding test.
 */

/**
 * Given an encode function, write a corresponding decode function.
 */
public class EncodeDecode {

    static String encode(String text) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            b.append(c += c + i);
        }
        return b.reverse().toString();
    }

    static String decode(String encoded) {
        if (encoded == null) return null;
        if (encoded.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(encoded).reverse();

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            res.append((char)((c-i)/2));
        }

        return res.toString();
    }





    public static void main(String[] args) {
        String s = "ABCDE";
        String e = encode(s);
        System.out.println(e);
        String d = decode(e);
        System.out.println(d);
        System.out.println();

        s = "abcde";
        e = encode(s);
        System.out.println(e);
        d = decode(e);
        System.out.println(d);
        System.out.println();
    }

}
