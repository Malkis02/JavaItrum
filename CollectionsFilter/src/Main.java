import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Filter filter = new FilterImpl();
        int[] arr = new int[]{5,6,7,1,2,3,4,};
        filter(arr,filter);
    }

    public static int[] filter(int[] arr,Filter filter){
        int[] newArr = arr.clone();
        for(int i = 0;i<= newArr.length;i++){
            filter.apply(i);
        }
        System.out.println(Arrays.toString(newArr));
        return newArr;
    }
}