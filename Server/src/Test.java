import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[]array = new String[]{"ssadasd","sadasds","ewerer"};
        String s = Arrays.toString(array);
        System.out.println(s);
        StringBuilder builder = new StringBuilder();
        for(String r : array) {
            builder.append(r+" ");
        }
        String str = builder.toString();
        System.out.println(str);
    }
}
