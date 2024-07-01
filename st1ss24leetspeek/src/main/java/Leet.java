
public class Leet {

    public static String toLeet(String normal) {
        return normal.replaceAll("elite", "1337").replaceAll("cool", "k3wl").replaceAll("!", "!!!11").replaceAll("ck", "xx").replaceAll("ers", "0rz").replaceAll("er", "0rz").replaceAll("en", "n").replaceAll("e", "3").replaceAll("t", "7").replaceAll("o", "0").replaceAll("a", "@");
    }

    public static String[] allToLeet(String[] normals) {
        String[] newLeet = new String[normals.length];
        for (int i = 0; i < normals.length; i++) {
            newLeet[i] = toLeet(normals[i]);
        }
        return newLeet;
    }
}
