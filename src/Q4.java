import java.util.*;

public class Q4 {

    public static void main(String[] args) {
        int[][] arrays = {
                {1, 3, 5},
                {2, 4, 6},
                {0, 9, 10, 11},
                {7, 8}
        };

        int[] mergedArray = mergeKSortedArrays(arrays);
        System.out.println("Merged array: " + Arrays.toString(mergedArray));
    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        List<int[]> arrayList = new ArrayList<>(Arrays.asList(arrays));
        arrayList.sort(Comparator.comparingInt(a -> a.length));

        while (arrayList.size() > 1) {
            // Extract the two smallest arrays
            int[] first = arrayList.remove(0);
            int[] second = arrayList.remove(0);

            // Merge them
            int[] merged = merge(first, second);

            // Insert the merged array back into the sorted list
            int index = Collections.binarySearch(arrayList, merged, Comparator.comparingInt(a -> a.length));
            if (index < 0) index = -index - 1;
            arrayList.add(index, merged);
        }

        // The final array left in the list is the fully merged array
        return arrayList.get(0);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }
}
