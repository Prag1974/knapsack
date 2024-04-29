public class Item {
    String name;
    double value,weight;

    public Item(String name, double value, double weight){
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }


    public String getName() {
        return name;
    }


    public double getWeight() {
        return weight;
    }

}
