import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static List<Product> availableProducts = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        availableProducts.add(new Product("Product 1", 25.0));
        availableProducts.add(new Product("Product 2", 35.0));
        availableProducts.add(new Product("Product 3", 45.0));

        boolean exit = false;

        while (!exit) {
            if (currentUser == null || !currentUser.isLoggedIn()) {
                displayMenu();
            }
            else {
            displayManu1();
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    postProduct();
                    break;
                case 4:
                    addToCart();
                    break;
                case 5:
                    viewCart();
                    break;
                case 6:
                    checkout();
                    break;
                case 7:
                    logout();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting the application. Thanks for using!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to the E-Commerce System!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Post a Product");
        System.out.println("4. Add Product to Cart");
        System.out.println("5. View Cart");
        System.out.println("6. Checkout");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
    private static void displayManu1(){
        System.out.println("Logged in");
        System.out.println("3. Post a Product");
        System.out.println("4. Add Product to Cart");
        System.out.println("5. View Cart");
        System.out.println("6. Checkout");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = new User(username, password);
        System.out.println("Registration successful!");
    }

    private static void login() {
        if (currentUser != null && currentUser.isLoggedIn()) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (currentUser != null && currentUser.getUsername().equals(username) && currentUser.authenticate(password)) {
            currentUser.login();
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void postProduct() {
        if (currentUser == null || !currentUser.isLoggedIn()) {
            System.out.println("Please login to post a product.");
            return;
        }

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        scanner.nextLine();

        Product newProduct = new Product(productName, productPrice);
        availableProducts.add(newProduct);
        System.out.println("Product posted successfully!");
    }

    private static void addToCart() {
        if (currentUser == null || !currentUser.isLoggedIn()) {
            System.out.println("Please login to add items to the cart.");
            return;
        }

        displayAvailableProducts();

        System.out.print("Enter the index of the product to add to cart: ");
        int productIndex = scanner.nextInt();
        scanner.nextLine();

        if (productIndex >= 0 && productIndex < availableProducts.size()) {
            Product selectedProduct = availableProducts.get(productIndex);
            cart.addItem(selectedProduct);
            System.out.println(selectedProduct.getName() + " added to cart.");
        } else {
            System.out.println("Invalid product index.");
        }
    }

    private static void viewCart() {
        if (currentUser == null || !currentUser.isLoggedIn()) {
            System.out.println("Please login to view your cart.");
            return;
        }

        List<Product> items = cart.getItems();

        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName()+": " + items.get(i).getPrice() + " Taka");
            }
            System.out.println("Total: " + cart.calculateTotal() +" Taka");
        }
    }

    private static void checkout() {
        if (currentUser == null || !currentUser.isLoggedIn()) {
            System.out.println("Please login to checkout.");
            return;
        }

        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout...");
            return;
        }

        System.out.println("Total amount to pay: " + cart.calculateTotal()+" Taka");
        System.out.println("Simulating payment gateway... Payment successful!");
        cart = new ShoppingCart();
    }

    private static void logout() {
        if (currentUser != null && currentUser.isLoggedIn()) {
            currentUser.logout();
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("No user logged in.");
        }
    }

    private static void displayAvailableProducts() {
        if (availableProducts.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available Products:");
            for (int i = 0; i < availableProducts.size(); i++) {
                System.out.println((i) + ". " + availableProducts.get(i).getName()+":-" + availableProducts.get(i).getPrice() + " Taka");
            }
        }
    }
}