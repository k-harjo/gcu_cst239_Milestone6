package storeFront;

import inventoryManager.InventoryManager;
import shoppingCart.ShoppingCart;
import salableProduct.SalableProduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreFront {
    private InventoryManager inventoryManager;
    private List<ShoppingCart<SalableProduct>> shoppingCarts = new ArrayList<>();

    public StoreFront() {
        inventoryManager = new InventoryManager(null);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void purchaseProduct(SalableProduct product, ShoppingCart<SalableProduct> cart, int quantity) {
        if (inventoryManager.getProductList().contains(product)) {
            if (product.getQuantity() >= quantity) {
                cart.addProduct(product, quantity);
                product.setQuantity(product.getQuantity() - quantity);
                System.out.println("Product purchased: " + product.getName());
            } else {
                System.out.println("Insufficient quantity in inventory for: " + product.getName());
            }
        } else {
            System.out.println("Product not found in inventory: " + product.getName());
        }
    }

    public void cancelPurchase(SalableProduct product, ShoppingCart<SalableProduct> cart, int quantity) {
        if (cart.getCartItems().contains(product)) {
            int cartQuantity = cart.getQuantityInCart(product);
            if (cartQuantity >= quantity) {
                cart.removeProduct(product, quantity);
                product.setQuantity(product.getQuantity() + quantity);
                System.out.println("Purchase canceled, product returned to inventory: " + product.getName());
            } else {
                System.out.println("Insufficient quantity in cart for: " + product.getName());
            }
        } else {
            System.out.println("Product not found in cart: " + product.getName());
        }
    }

    public void sortProductsByNameAscending() {
        Collections.sort(inventoryManager.getProductList());
    }

    public void sortProductsByNameDescending() {
        Comparator<SalableProduct> nameComparator = Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER).reversed();
        inventoryManager.getProductList().sort(nameComparator);
    }

    public void sortProductsByPriceAscending() {
        Comparator<SalableProduct> priceComparator = Comparator.comparing(SalableProduct::getPrice);
        inventoryManager.getProductList().sort(priceComparator);
    }

    public void sortProductsByPriceDescending() {
        Comparator<SalableProduct> priceComparator = Comparator.comparing(SalableProduct::getPrice).reversed();
        inventoryManager.getProductList().sort(priceComparator);
    }
}
