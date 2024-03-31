package com.IndependentNo1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Category category;

    public Product(int id, String name, double price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    // Геттери і сеттери
}

class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    // Геттери і сеттери
}

class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}

class Order {
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    // Методи для обробки замовлення
}

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(3, "Carrot", 1.5, "Fresh carrot", new Category("Vegetables")));
        productList.add(new Product(4, "Tomato", 2.0, "Ripe tomato", new Category("Vegetables")));
        productList.add(new Product(5, "Cucumber", 1.0, "Green cucumber", new Category("Vegetables")));
        productList.add(new Product(6, "Potato", 1.8, "Organic potato", new Category("Vegetables")));
        productList.add(new Product(7, "Apple", 1.2, "Red apple", new Category("Fruits")));
        productList.add(new Product(8, "Banana", 1.0, "Yellow banana", new Category("Fruits")));
        productList.add(new Product(9, "Orange", 1.5, "Juicy orange", new Category("Fruits")));
        productList.add(new Product(10, "Grapes", 2.5, "Sweet grapes", new Category("Fruits")));

        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. View products");
            System.out.println("2. Add product to cart");
            System.out.println("3. View cart");
            System.out.println("4. Remove product from cart");
            System.out.println("5. Place order");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts(productList);
                    break;
                case 2:
                    System.out.print("Enter product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    Product productToAdd = getProductById(productList, productId);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Product added to cart successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    viewCart(cart);
                    break;
                case 4:
                    System.out.print("Enter product ID to remove from cart: ");
                    int productIdToRemove = scanner.nextInt();
                    Product productToRemove = getProductById(cart.getProducts(), productIdToRemove);
                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Product removed from cart successfully.");
                    } else {
                        System.out.println("Product not found in cart.");
                    }
                    break;
                case 5:
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(cart.getProducts());
                        // Логіка для створення замовлення
                        System.out.println("Order placed successfully.");
                    } else {
                        System.out.println("Cart is empty. Add some products first.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void viewProducts(List<Product> productList) {
        System.out.println("Available Products:");
        for (Product product : productList) {
            System.out.println(product.getId() + ". " + product.getName() + " - $" + product.getPrice());
        }
    }

    private static void viewCart(Cart cart) {
        List<Product> productsInCart = cart.getProducts();
        if (!productsInCart.isEmpty()) {
            System.out.println("Products in Cart:");
            for (Product product : productsInCart) {
                System.out.println(product.getId() + ". " + product.getName() + " - $" + product.getPrice());
            }
        } else {
            System.out.println("Cart is empty.");
        }
    }

    private static Product getProductById(List<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
