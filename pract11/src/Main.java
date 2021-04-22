import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sortinger<Integer> s1 = new Bubbler<>();
        Sortinger<Integer> s2 = new Insertor<>();
        Sortinger<Integer> s3 = new Selector<>();


        Integer []arr1 = {0, 3, 6, 1, 2, 5, 4};

        s1.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        Integer []arr2 = {0, 3, 6, 1, 2, 5, 4};

        s2.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        Integer []arr3 = {0, 3, 6, 1, 2, 5, 4};

        s3.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
