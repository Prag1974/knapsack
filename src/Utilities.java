
import java.util.*;

public class Utilities {
    public static List<List<Item>> generateCombinations(List<Item> items) {
        List<List<Item>> combinations = new ArrayList<>();
        generateCombinationsHelper(items, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(List<Item> items, int index, List<Item> currentCombination, List<List<Item>> combinations) {
        combinations.add(new ArrayList<>(currentCombination));

        for (int i = index; i < items.size(); i++) {
            currentCombination.add(items.get(i));
            generateCombinationsHelper(items, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
   public static void printCombinations(List<List<Item>> combinations){
        for(int i = 0; i < combinations.size(); i++){

            //Thus, we can see the situation which has most items last.
            List<Item> list = combinations.stream().sorted(Comparator.comparing(List::size)).toList().get(i);

            //Thus, for each iteration we see the items in descending order of value.
            List<Item> sortedList = list.stream().sorted(Comparator.comparing(Item::getValue).reversed()).toList();

            double totalValue = list.stream().mapToDouble(Item::getValue).sum();
            double totalWeight = list.stream().mapToDouble(Item::getWeight).sum();

            System.out.printf("Combination %d: (Total value: %s, Total Weight: %s kg)%n",i,totalValue,totalWeight);
            System.out.println("[");
            for (Item item : sortedList){
                System.out.printf(" { Name: %s, Value: %f, Weight: %f },\n",item.getName(), item.getValue(),item.getWeight());
            }
            System.out.println("]\n");
        }
    }
    public static void printCombinations(TreeMap<Double, List<Item>> map){
        System.out.println("THE ITEMS IN EACH ITERATION ARE SHOWN IN DESCENDING VALUE ORDER.\n");


        for(double key : map.keySet()){

            List<Item> currentList = map.get(key);

            //Thus, for each iteration we see the items in descending order of value.
            List<Item> sortedList = currentList.stream().sorted(Comparator.comparing(Item::getValue).reversed()).toList();

            double totalValue = sortedList.stream().mapToDouble(Item::getValue).sum();
            double totalWeight = sortedList.stream().mapToDouble(Item::getWeight).sum();

            System.out.printf("Combination: (Total value: %s, Total Weight: %s kg)%n",totalValue,totalWeight);
            System.out.println("[");
            for (Item item : sortedList){
                System.out.printf(" { Name: %s, Value: %f, Weight: %f },\n",item.getName(), item.getValue(),item.getWeight());
            }
            System.out.println("]\n");
        }
    }
    public static Map<Double, List<Item>> getMap(List<List<Item>> combinations) {
        Map<Double, List<Item>> map = new HashMap<>();

        for (List<Item> combination : combinations) {
            double sumValue = combination.stream().map(Item::getValue).mapToDouble(Double::doubleValue).sum();

            map.put(sumValue,combination);

        }
        return map;
    }
}
