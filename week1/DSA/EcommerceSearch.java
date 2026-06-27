package week1.DSA;

import java.util.Arrays;
import java.util.Comparator;

class Product {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String toString() {
        return "ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

public class EcommerceSearch {

    static Product linearSearch(Product[] products, int id) {
        for (Product p : products) {
            if (p.productId == id)
                return p;
        }
        return null;
    }

    static Product binarySearch(Product[] products, int id) {
        int low = 0, high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (products[mid].productId == id)
                return products[mid];
            else if (products[mid].productId < id)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
            new Product(104, "Monitor", "Electronics"),
            new Product(101, "Shirt", "Fashion"),
            new Product(103, "Wallet", "Accessories"),
            new Product(102, "Television", "Electronics")
        };

        int searchId = 103;

        Product linearResult = linearSearch(products, searchId);
        System.out.println("Linear Search Result:");
        System.out.println(linearResult);

        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));

        Product binaryResult = binarySearch(products, searchId);
        System.out.println("\nBinary Search Result:");
        System.out.println(binaryResult);
    }
}