import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Weight limit of our bag. You can assign it whatever you want.
        int limitWeight = 40;

        // This is the item list we are trying to steal which you can freely mutate.
        List<Item> itemList = List.of(
                     new Item("Bag",6 ,9.5)
                    ,new Item("Table",3,15.5)
                    ,new Item("Phone",4,9)
                    ,new Item("Laptop",6,4)
                //  ,new Item("Ring",1901,0.6)
                //  ,new Item("A book",1,9)
                //  ,new Item("A Heavy and Valuable Pen",2280,57)
                //  ,new Item("Gold Made Necklace",26,98)
                //  ,new Item("TV",47,91)
                //  ,new Item("Jewelry",383,8)
                //  ,new Item("An Expensive Sun Glasses",922,38)
                //  ,new Item("Love", Double.POSITIVE_INFINITY ,0)

                // ...
        );

        // Getting all combinations of possible bag situations.
        List<List<Item>> combinations = Utilities.generateCombinations(itemList);

        // You can check the possible combinations by using:
        //Utilities.printCombinations(combinations);

        // Getting all combinations to map.
        Map<Double, List<Item>> map = Utilities.getMap(combinations);

        // Filtering according to the limit of weight we can carry.
        TreeMap<Double, List<Item>> filteredBag = new TreeMap<>(map.entrySet().stream().filter(item -> item.getValue().stream().mapToDouble(Item::getWeight).sum() <= limitWeight).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        // Reversing for the see optimal situation at top.
        TreeMap<Double, List<Item>> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(filteredBag);

        // Printing all possibilities in descending order of the value.
        // You can see the most optimal situation of bag at the top and the other possibilities by using:
        Utilities.printCombinations(sortedMap);

    }
}

