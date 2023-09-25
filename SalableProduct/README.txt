# SalableProduct Application Readme

This Java code repository contains a SalableProduct Application for a fictional store called "Bloodbath and Beyond." This system allows users to manage the store's inventory, add and remove products from a shopping cart, view their purchases, and provides administration capabilities for updating and retrieving inventory. Additionally, it offers the capability to load and save inventory data to/from JSON files.

## Table of Contents

1. [Introduction](#introduction)
2. [Usage](#usage)
3. [Code Structure](#code-structure)
4. [Features](#features)
5. [Dependencies](#dependencies)
6. [Contributors](#contributors)
7. [License](#license)

## Introduction

The SalableProduct Application is designed to help store employees and customers interact with the store's inventory and shopping cart. It consists of the following key components:

- **InventoryManager:** Manages the store's inventory, allowing users to add and retrieve products, find products by name, and load/save inventory data to/from JSON files.

- **ShoppingCart:** Represents a customer's shopping cart, allowing them to add and remove products, view cart contents, and calculate the total price.

- **SalableProduct:** Represents a product available for sale, with attributes such as name, description, price, quantity, and quantity purchased.

- **StoreFront:** Serves as the main application that ties everything together. It initializes the store, allows users to interact with the inventory and shopping cart, and provides a command-line interface for customers to make purchases.

- **AdministrationService:** Provides administration capabilities for updating the store's inventory by parsing JSON payloads and saving the inventory to JSON files.

- **AdministrationApplication:** A command-line interface for administrators to interact with the AdministrationService, enabling them to update the inventory and retrieve inventory data.

## Usage

To use the SalableProduct Application, follow these steps:

1. **Compile the Code:** Compile the Java code provided in this repository using a Java compiler.

2. **Run the `StoreFrontApplication` Class:** Execute the `StoreFrontApplication` class to start the application. This class provides a command-line interface for interacting with the store's inventory and shopping cart.

3. **Perform Actions:** In the command-line interface, you can perform various actions, such as purchasing products, canceling purchases, viewing inventory, viewing cart contents, and more. Follow the on-screen instructions to navigate through the menu and perform actions.

4. **Run the `AdministrationApplication` Class:** Execute the `AdministrationApplication` class to start the administration interface. This class allows administrators to update the store's inventory and retrieve inventory data.

5. **Exit the Application:** To exit the application, select the appropriate options from the menus.

6. **View Receipt:** After exiting the application, a receipt will be displayed showing the items purchased, quantities, and the total price.

## Code Structure

The code is organized into several Java classes, each serving a specific purpose:

- `InventoryManager`: Manages the store's inventory, including adding and retrieving products, loading and saving inventory data from/to JSON files.

- `ShoppingCart`: Represents a customer's shopping cart, allowing them to add and remove products, view cart contents, and calculate the total price.

- `SalableProduct`: Represents a product available for sale, with attributes such as name, description, price, quantity, and quantity purchased.

- `StoreFront`: The main application class that provides the user interface for interacting with the inventory and shopping cart.

- `AdministrationService`: Provides administration capabilities for updating the store's inventory by parsing JSON payloads and saving the inventory to JSON files.

- `AdministrationApplication`: A command-line interface for administrators to interact with the AdministrationService, enabling them to update the inventory and retrieve inventory data.

## Features

The SalableProduct Application offers the following features:

- **Add and Remove Products:** Users can add and remove products from their shopping cart, updating the inventory accordingly.

- **View Inventory:** Users can view the store's inventory, including product names and available quantities.

- **View Cart Contents:** Users can view the contents of their shopping cart, including product names, quantities, and the total price.

- **Load and Save Data:** Inventory data can be loaded from and saved to JSON files, allowing for data persistence between sessions.

- **Cancel Cart:** Users can empty their shopping cart.

- **Administration Capabilities:** Administrators can update the store's inventory by providing JSON payloads, and they can retrieve inventory data.

- **Receipt Generation:** After exiting the application, a receipt is displayed showing the items purchased, quantities, and the total price.

## Dependencies

This SalableProduct Application relies on the following dependencies:

- [Jackson Databind](https://github.com/FasterXML/jackson-databind): A library for JSON serialization and deserialization. It is used to load and save inventory data in JSON format.

## Contributors

The code in this repository was developed by an individual contributor.

## License

This project is provided under the MIT License. You are free to use and modify the code for your purposes. See the [LICENSE](LICENSE) file for more details.
