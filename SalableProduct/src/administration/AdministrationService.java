package administration;

import storeFront.StoreFront;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;

import salableProduct.SalableProduct;

public class AdministrationService {
    private StoreFront storeFront;
    private List<SalableProduct> productList = new ArrayList<>();

    public AdministrationService(StoreFront storeFront) {
        // Initialize the StoreFront instance and other dependencies.
        this.storeFront = storeFront;
        this.productList = new ArrayList<>();
    }
    public void startService() {
        // Start the Administration Service in the background.
        // Implement any necessary initialization or background tasks.
    }

    public void stopService() {
        // Stop the Administration Service gracefully.
        // Implement any cleanup tasks.
    }

    public void processCommand(String command, String payload) {
        switch (command.toUpperCase()) {
        case "U":
            // Command to update the Store Inventory Management System with new Salable Products.
            // Parse the JSON payload and update the inventory.
            List<SalableProduct> newProducts = parseSalableProductsFromJSON(payload);
            boolean updated = updateStoreInventory(newProducts);

            if (updated) {
                // Save the updated inventory to the 'inventory.json' file
                saveProductsToJsonFile(productList, "inventory.json");
            }
            break;

            case "R":
                // Command to retrieve all Salable Products from the Store Inventory Management System.
                // Retrieve the products and send them as JSON back to the Administration Application.
                String productsJSON = retrieveStoreInventory();
                sendProductsJSONToAdmin(productsJSON);
                break;
            default:
                System.out.println("Invalid command: " + command);
                break;
        }
    }

 // Method to save a list of products to a JSON file
    public void saveProductsToJsonFile(List<SalableProduct> products, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty printing

        try {
            File file = new File(filePath);
            List<SalableProduct> existingProducts = loadProductsFromJsonFile(filePath);

            if (existingProducts == null) {
                existingProducts = new ArrayList<>();
            }

            // Merge the existing products with the new products
            existingProducts.addAll(products);

            // Serialize the merged list of SalableProduct objects to JSON and write to the file
            objectMapper.writeValue(file, existingProducts);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle File I/O errors
        }
    }



    private List<SalableProduct> parseSalableProductsFromJSON(String payload) {
        List<SalableProduct> products = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            products = Arrays.asList(objectMapper.readValue(payload, SalableProduct[].class));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle JSON parsing error here.
        }

        return products;
    }

    private boolean updateStoreInventory(List<SalableProduct> newProducts) {
        try {
            // Add the new products to the InventoryManager's product list
            storeFront.getInventoryManager().getProductList().addAll(newProducts);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to load products from a JSON file
    private List<SalableProduct> loadProductsFromJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SalableProduct> products = new ArrayList<>();

        try {
            File file = new File(filePath);

            // If the file exists, deserialize JSON data from the file
            if (file.exists()) {
                products = Arrays.asList(objectMapper.readValue(file, SalableProduct[].class));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle JSON parsing error here.
        }

        return products;
    }


    // Method to retrieve all Salable Products from the Store Inventory Management System
    private String retrieveStoreInventory() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(storeFront.getInventoryManager().getProductList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle JSON serialization error here.
            return ""; // Return an empty string in case of an error.
        }
    }

    // Method to send the retrieved JSON data back to the Administration Application
    private void sendProductsJSONToAdmin(String productsJSON) {
        // Implement logic to send the JSON data back to the Administration Application.
        // You may use network communication or other mechanisms for this purpose.
    }

    public void retrieveAndDisplayProducts() {
        // Retrieve and display all Salable Products in JSON format
        String productsJSON = retrieveStoreInventory();

        if (!productsJSON.isEmpty()) {
            System.out.println("Salable Products in JSON format:");
            System.out.println(productsJSON);
        } else {
            System.out.println("No products found in inventory.");
        }
    }
}
