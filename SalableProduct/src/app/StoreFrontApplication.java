package app;

import java.util.Scanner;
import storeFront.StoreFront;
import shoppingCart.ShoppingCart;
import salableProduct.SalableProduct;
import inventoryManager.InventoryManager;

public class StoreFrontApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a store front instance
        StoreFront store = new StoreFront();

        // Initialize the store with the initial inventory from the JSON file
        store.getInventoryManager().loadInventoryFromJsonFile("inventory.json");

        // Create a shopping cart instance
        ShoppingCart<SalableProduct> cart = new ShoppingCart<>();

        System.out.println("Welcome to Bloodbath and Beyond,");
        System.out.println("where you can prep for your next battle or bath!\n");
        System.out.println("Available Actions:");
        System.out.println("1. Purchase a product");
        System.out.println("2. Cancel a purchase");
        System.out.println("3. View final inventory");
        System.out.println("4. View cart contents");
        System.out.println("5. Cancel cart");
        System.out.println("0. Checkout/Exit");

        boolean running = true;
        while (running) {
            System.out.print("Enter the action number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
	            case 1:
	                System.out.println("Available products:");
	                for (SalableProduct product : store.getInventoryManager().getProductList()) {
	                    System.out.println(product.getName());
	                }
	                System.out.print("Enter the name of the product you want to purchase: ");
	                String productName = scanner.nextLine();
	                System.out.print("Enter the quantity: ");
	                int quantity = scanner.nextInt();
	                SalableProduct selectedProduct = store.getInventoryManager().findProductByName(productName);
	                if (selectedProduct != null) {
	                    store.purchaseProduct(selectedProduct, cart, quantity);
	                } else {
	                    System.out.println("Product not found.");
	                }
	                break;

                case 2:
                    System.out.println("Your cart:");
                    for (SalableProduct product : cart.getCartItems()) {
                        System.out.println(product.getName());
                    }
                    System.out.print("Enter the name of the product you want to cancel: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    quantity = scanner.nextInt();
                    selectedProduct = cart.findProductByName(productName);
                    if (selectedProduct != null) {
                        store.cancelPurchase(selectedProduct, cart, quantity);
                    } else {
                        System.out.println("Product not found in cart.");
                    }
                    break;

                case 3:
                    System.out.println("Final Inventory:");
                    for (SalableProduct product : store.getInventoryManager().getProductList()) {
                        System.out.println(product.getName() + " - " + product.getQuantity() + " available");
                    }
                    break;

                case 4:
                    System.out.println("Cart Contents:");
                    for (SalableProduct product : cart.getCartItems()) {
                        System.out.println(product.getName() + " - " + product.getQuantityPurchased() + " items");
                    }
                    System.out.println("Total Price: $" + cart.getTotalPrice());
                    break;
                    
                case 5:
                    cart.clearCart(); // Call the clearCart method
                    System.out.println("Shopping Cart has been emptied.");
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid action number.");
                    break;
            }

            // After each action, save the updated inventory to a new JSON file
            store.getInventoryManager().saveInventoryToJsonFile("updated_inventory.json");
        }

        System.out.println("Thank you for shopping at Bloodbath and Beyond!\n");
        System.out.println("Item\tQuantity\tPrice");

        for (SalableProduct product : cart.getCartItems()) {
            System.out.printf("%-10s\t%-10d\t$%.2f%n",
                    product.getName(), product.getQuantityPurchased(), product.getPrice());
        }

        System.out.println("\nYour Total is:\t\t\t$" + cart.getTotalPrice());
        System.out.println("\nYour products will arrive to you by the next full moon!");
    }
}
