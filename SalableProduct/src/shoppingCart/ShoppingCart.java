package shoppingCart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import salableProduct.SalableProduct;

public class ShoppingCart<T extends SalableProduct> {
    private Map<T, Integer> cartItems = new HashMap<>();
    private double totalPrice = 0.0; // Running total of item prices

    public void addProduct(T product, int quantity) {
        int currentQuantity = cartItems.getOrDefault(product, 0);
        cartItems.put(product, currentQuantity + quantity);
        totalPrice += product.getPrice() * quantity; // Add the price to the running total
        product.setQuantityPurchased(product.getQuantityPurchased() + quantity); // Update quantity purchased
        System.out.println("Added " + quantity + " " + product.getName() + " to cart. Total price: $" + String.format("%.2f", totalPrice));
    }

    public void removeProduct(T product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (currentQuantity > quantity) {
                cartItems.put(product, currentQuantity - quantity);
                totalPrice -= product.getPrice() * quantity; // Subtract the price from the running total
                System.out.println("Removed " + quantity + " " + product.getName() + " from cart. Total price: $" + String.format("%.2f", totalPrice));
            } else if (currentQuantity == quantity) {
                cartItems.remove(product);
                totalPrice -= product.getPrice() * quantity; // Subtract the price from the running total
                System.out.println("Removed " + quantity + " " + product.getName() + " from cart. Total price: $" + String.format("%.2f", totalPrice));
            } else {
                System.out.println("Invalid quantity to remove.");
            }
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantityInCart(T product) {
        return cartItems.getOrDefault(product, 0);
    }
    
    public T findProductByName(String productName) {
        for (T product : cartItems.keySet()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null; // Product not found
    }
    
    public List<T> getCartItems() {
        List<T> items = new ArrayList<>(cartItems.keySet());
        
        // Sort the items based on some criteria, such as name or price
        items.sort(Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER));
        
        return items;
    }

    public void clearCart() {
        cartItems.clear(); // Clear the items in the cart
        totalPrice = 0.0;   // Reset the total price to zero
        System.out.println("Shopping Cart has been emptied.");
    }
}
