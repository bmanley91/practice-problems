import java.util.HashMap;
import java.util.Map;

public class FruitStand {

    private Map<String, Integer> fruitQuantity;
    private Map<String, Float> fruitPrice;

    public FruitStand() {
        this.fruitQuantity = new HashMap<String, Integer>();
        this.fruitPrice = new HashMap<String, Float>();
    }

    public boolean addFruit(String fruitName, int quantity, float price) {
        // Don't let someone overwrite a fruit which was already created
        if (fruitPrice.containsKey(fruitName)) {
            return false;
        }

        fruitQuantity.put(fruitName, quantity);
        fruitPrice.put(fruitName, price);
        return true;
    }

    public boolean updateQuantity(String fruitName, int quantity) {
        // Can't update quantity on a fruit that doesn't exist
        if (!fruitQuantity.containsKey(fruitName)) {
            return false;
        }

        fruitQuantity.put(fruitName, quantity);
        return true;
    }

    // This approach assumes that we want adding and updating fruit to be faster
    // We could also keep a running total value, which would slow down updates,
    // but make returning total value much faster
    public double totalValue() {
        double total = 0.0;
        for (String fruitName : fruitQuantity.keySet()) {
            total += 
                fruitQuantity.get(fruitName) *
                fruitPrice.get(fruitName);
        }
        return total;
    }
    
}
