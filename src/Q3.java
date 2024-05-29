import java.util.*;

public class Q3 {

    public static ArrayList<Item> divisibleKnapsack(int capacity, List<Item> items) {

        // Sort items by their value-to-weight ratio in descending order
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        ArrayList<Item> result = new ArrayList<>();
        double currentWeight = 0;

        for (Item item : items) {

            if (currentWeight + item.weight <= capacity) {
                // Take the whole item
                result.add(item);
                currentWeight += item.weight;

                // Update remaining item in the list
                item.weight = 0;
                item.profit = 0;

            } else {
                // Take the fraction of the item that fits
                double remainingCapacity = capacity-currentWeight;
                double profitFraction = item.profit * remainingCapacity/item.weight;
                Item fractionItem = new Item(remainingCapacity,profitFraction);
                result.add(fractionItem);

                // Update remaining item in the list
                item.weight -= remainingCapacity;
                item.profit -= fractionItem.profit;
            }

            if (capacity == currentWeight) return result;

        }

        return result;
    }

    public static void main(String[] args) {
        int capacity = 50;
        List<Item> items = Arrays.asList(
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        );

        ArrayList<Item> takenItems = divisibleKnapsack(capacity, items);

        System.out.println("Taken items: " + takenItems);

    }
}


class Item {
    double weight;
    double profit;
    double ratio;

    public Item(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
    @Override
    public String toString(){
        return String.format("p: %s; w: %s",profit,weight);
    }
}