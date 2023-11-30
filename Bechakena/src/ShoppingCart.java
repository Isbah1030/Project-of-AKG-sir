import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addItem(Product product) {
        cartItems.add(product);
    }

    public void removeItem(Product product) {
        cartItems.remove(product);
    }

    public List<Product> getItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}
