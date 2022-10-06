package assn02;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class JavaWarmUp {

    static LinkedList<InventoryItem> iLL = new LinkedList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //Retrieve number of transactions to store
        int numOrders = s.nextInt();
        s.nextLine();
        //Retrieves and logs transactions information
        for (int i = 0; i < numOrders; i++) {
            String line = s.nextLine();
            logTransaction(line);
        }
        //Reports the transaction with the highest unit price
        getMaxPrice();
        //Reports the transaction with the lowest unit price
        getMinPrice();
        //Reports the averages of each category
        getAverage("book");
        getAverage("jewelry");
        getAverage("phone");
    }

    static void logTransaction(String input){
        //Function that logs a transaction into as an element in a LinkedList
        InventoryItem it = new InventoryItem();

        String[] parts = input.split(" ");

        iLL.add(it);
        it._date = parts[0];
        it._time = parts[1];
        it._category = parts[2];
        it._price = Float.parseFloat(parts[3]);
        it._quantity = Integer.parseInt(parts[4]);
        it._rating = Float.parseFloat(parts[5]);
        it._delivery = Integer.parseInt(parts[6]);
    }

    static void getMaxPrice(){
        //Function that finds and reports the transaction with the highest unit price
        Iterator iter = iLL.iterator();
        double maxPrice = 0; int maxIndex = 0; int count = 0;
        while(iter.hasNext()) {
            InventoryItem it = (InventoryItem) iter.next();
            if (it._price >= maxPrice){
                maxPrice = it._price;
                maxIndex = count;
            }
            count++;
        }
        System.out.println("Highest per unit sale:");
        System.out.println("\tWhen: "+iLL.get(maxIndex)._date+" "+iLL.get(maxIndex)._time);
        System.out.println("\tCategory: "+iLL.get(maxIndex)._category);
        System.out.println("\tPrice: "+String.format("%.2f",iLL.get(maxIndex)._price));
        System.out.println("\tRating: "+iLL.get(maxIndex)._rating);
    }

    static void getMinPrice(){
        //Function that finds and reports the transaction with the lowest unit price
        Iterator iter = iLL.iterator();
        double minPrice = iLL.get(0)._price; int minIndex = 0; int count = 0;
        while(iter.hasNext()) {
            InventoryItem it = (InventoryItem) iter.next();
            if (it._price <= minPrice){
                minPrice = it._price;
                minIndex = count;
            }
            count++;
        }
        System.out.println("Lowest per unit sale:");
        System.out.println("\tWhen: "+iLL.get(minIndex)._date+" "+iLL.get(minIndex)._time);
        System.out.println("\tCategory: "+iLL.get(minIndex)._category);
        System.out.println("\tPrice: "+String.format("%.2f",iLL.get(minIndex)._price));
        System.out.println("\tRating: "+iLL.get(minIndex)._rating);
    }

    static void getAverage(String category){
        //Function that calculates and reports the average statistics of a given category of item
        Iterator iter = iLL.iterator();
        double sumOfTransactions = 0; int numberOfUnits = 0;
        double sumOfRatings = 0; double sumOfDuration = 0; int numberOfTransactions = 0;
        while(iter.hasNext()){
            InventoryItem it = (InventoryItem) iter.next();
            if (it._category.equals(category)){
                sumOfTransactions += it._quantity*it._price;
                numberOfUnits += it._quantity;
                sumOfRatings += it._rating;
                sumOfDuration += it._delivery;
                numberOfTransactions++;
            }
        }
        double averagePrice = sumOfTransactions / numberOfUnits;
        double averageRating = sumOfRatings / numberOfTransactions;
        double averageDuration = sumOfDuration /numberOfTransactions;
        System.out.println("Averages by "+category);
        System.out.println("\tQuantity: "+numberOfUnits);
        System.out.println("\tPrice: "+String.format("%.2f",averagePrice));
        System.out.println("\tRating: "+String.format("%.2f",averageRating));
        System.out.println("\tDuration: "+String.format("%.2f",averageDuration));
    }
}