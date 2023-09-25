package inventoryManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import salableProduct.SalableProduct;
import storeFront.StoreFront; // Import the StoreFront class

public class InventoryManager {
    private List<SalableProduct> productList = new ArrayList<>();
    private StoreFront storeFront; // Reference to StoreFront

    // Constructor to initialize with a StoreFront object
    public InventoryManager(StoreFront storeFront) {
        this.storeFront = storeFront;
        this.productList = new ArrayList<>();
    }

    public void addProduct(SalableProduct product) {
        productList.add(product);
    }

    public List<SalableProduct> getProductList() {
        return productList;
    }

    public SalableProduct findProductByName(String productName) {
        for (SalableProduct product : productList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null; // Product not found
    }

    // Method to load inventory data from a JSON file
    public void loadInventoryFromJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Deserialize JSON data from the file into a list of SalableProduct objects
            SalableProduct[] loadedProducts = objectMapper.readValue(new File(filePath), SalableProduct[].class);

            // Clear the existing inventory and add the loaded products
            productList.clear();
            Collections.addAll(productList, loadedProducts); // Use Collections.addAll to add elements to the list
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
            // Handle JSON parsing errors
        } catch (IOException e) {
            e.printStackTrace();
            // Handle File I/O errors
        }
    }

    // Method to save inventory data to a JSON file
    public void saveInventoryToJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty printing

        try {
            // Serialize the list of SalableProduct objects to JSON and write to the file
            objectMapper.writeValue(new File(filePath), productList);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle File I/O errors
        }
    }

    // Method to sort products by name in ascending order
    public void sortProductsByNameAscending() {
        productList.sort(Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER));
    }

    // Method to sort products by name in descending order
    public void sortProductsByNameDescending() {
        productList.sort(Collections.reverseOrder(Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER)));
    }

    // Method to sort products by price in ascending order
    public void sortProductsByPriceAscending() {
        productList.sort(Comparator.comparing(SalableProduct::getPrice));
    }

    // Method to sort products by price in descending order
    public void sortProductsByPriceDescending() {
        productList.sort(Collections.reverseOrder(Comparator.comparing(SalableProduct::getPrice)));
    }
}
