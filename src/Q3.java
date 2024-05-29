import java.util.*;

public class Q3 {

    public static int[][] divisibleKnapsack(int capacity, List<Item> items) {
        // Sort items by their value-to-weight ratio in descending order
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        int[][] result = new int[2][items.size()];
        int currentWeight = 0;
        int index = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Take the whole item
                result[0][index] = index;
                result[1][index] = item.weight;
                currentWeight += item.weight;
            } else {
                // Take the fraction of the item that fits
                int remainingCapacity = capacity - currentWeight;
                result[0][index] = index;
                result[1][index] = remainingCapacity;
                break;
            }
            index++;
        }

        // Resize the result array to remove unused slots
        int[][] resizedResult = new int[2][index + 1];
        for (int i = 0; i <= index; i++) {
            resizedResult[0][i] = result[0][i];
            resizedResult[1][i] = result[1][i];
        }

        return resizedResult;
    }

    public static void main(String[] args) {
        int capacity = 50;
        List<Item> items = Arrays.asList(
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        );

        int[][] takenItems = divisibleKnapsack(capacity, items);

        System.out.println("Items taken:");
        for (int i = 0; i < takenItems[0].length; i++) {
            System.out.println("Item index: " + takenItems[0][i] + ", Weight taken: " + takenItems[1][i]);
        }
    }
}


class Item {
    int weight;
    int profit;
    double ratio;

    public Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}