import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] arr = new String[]{"a","b","c","a","b","c","c","b","d"};
        System.out.println(countOfElements(arr));
    }

    public static Map countOfElements(String[] arr){
        Map<String,Integer> elements = new HashMap<>();
        int count = 0;
        for(int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length; i++) {
                String str = arr[j];
                if (arr[j].equals(arr[i])) {
                    count++;
                    elements.put(str, count);
                }
            }
            count = 0;
        }
        return elements;
    }
}