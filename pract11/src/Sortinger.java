public abstract class Sortinger<T extends Comparable<T>> {
    public abstract void sort(T[] arr);
}

class Bubbler<T extends Comparable<T>> extends Sortinger<T> {

    @Override
    public void sort(T[] arr) {
        boolean sorted = false;
        T temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
}

class Insertor<T extends Comparable<T>> extends Sortinger<T> {

    @Override
    public void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            while (j >= 0 && (current.compareTo(arr[j]) < 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
}

class Selector<T extends Comparable<T>> extends Sortinger<T> {
    @Override
    public void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            T min = arr[i];
            int minId = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j].compareTo(min) > 0) {
                    min = arr[j];
                    minId = j;
                }
            }
            // замена
            T temp = arr[i];
            arr[i] = min;
            arr[minId] = temp;
        }
    }
}

