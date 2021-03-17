package PracticeAfterLearn.Chuong2.Bai6.Controller;

import PracticeAfterLearn.Chuong2.Bai6.Model.Cart;
import PracticeAfterLearn.Chuong2.Bai6.Model.Customer;
import PracticeAfterLearn.Chuong2.Bai6.Model.Items;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    /**
     * write:
     */
    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCustomerToFile(Customer customer, String fileName) {
        /**
         * int customerID, String customerName, String address, String phoneNumber
         */
        openFileToWrite(fileName);
        printWriter.println(customer.getCustomerID() + "|" + customer.getCustomerName()
                + "|" + customer.getAddress() + "|" + customer.getPhoneNumber());
        closeFileAfterWrite(fileName);

    }

    public void writeItemsToFile(Items items, String fileName) {
        /**
         * int itemsID, String itemsName, String itemsType, String itemsPrice
         */
        openFileToWrite(fileName);
        printWriter.println(items.getItemsID() + "|" + items.getItemsName()
                + "|" + items.getItemsType() + "|" + items.getItemsPrice());
        closeFileAfterWrite(fileName);
    }

    public void writeCartToFile(Cart cart, String fileName) {
        /**
         * Customer customer, Items items, int numOfBuy
         */
        openFileToWrite(fileName);
        printWriter.println(cart.getCustomer().getCustomerID() + "|" + cart.getItems().getItemsID() + "|" + cart.getNumOfBuy());
        closeFileAfterWrite(fileName);
    }

    /**
     * read
     */
    public void openFileToRead(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead(String fileName) {
        scanner.close();
    }

    public ArrayList<Customer> readCustomerFromFile(String fileName) {
        var customers = new ArrayList<Customer>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Customer customer = createDataFromCustomer(data);
            customers.add(customer);
        }
        closeFileAfterRead(fileName);
        return customers;
    }

    private Customer createDataFromCustomer(String data) {
        /**
         * int customerID, String customerName, String address, String phoneNumber
         */
        String[] datas = data.split("\\|");
        Customer customer = new Customer(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
        ;
        return customer;
    }

    public ArrayList<Items> readItemsFromFile(String fileName) {
        var items = new ArrayList<Items>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Items item = createDataFromItems(data);
            items.add(item);
        }
        closeFileAfterRead(fileName);
        return items;
    }

    private Items createDataFromItems(String data) {
        /**
         * int itemsID, String itemsName, String itemsType, String itemsPrice
         */
        String[] datas = data.split("\\|");
        Items items = new Items(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
        return items;
    }

    public ArrayList<Cart> readCartFromFile(String fileName) {
        var carts = new ArrayList<Cart>();
        var items = readItemsFromFile("ITEMS.DAT");
        var customers = readCustomerFromFile("CUSTOMER.DAT");
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            /**
             * Customer customer, Items items, int numOfBuy
             */
            Cart cart = createDataFromCart(data, items, customers);
            carts.add(cart);
        }
        closeFileAfterRead(fileName);
        return carts;
    }

    private Cart createDataFromCart(String data, ArrayList<Items> items, ArrayList<Customer> customers) {
        /**
         * Customer customer, Items items, int numOfBuy
         */
        String[] datas = data.split("\\|");
        Cart cart = new Cart(getCustomer(Integer.parseInt(datas[0]), customers),
                getItems(Integer.parseInt(datas[1]), items), Integer.parseInt(datas[2]));
        return cart;

    }

    public void updateFile(ArrayList<Cart> carts, String cartFileName) {
        File file = new File(cartFileName);
        if (file.exists()) {
            file.delete();
        }
        for (var cart : carts) {
            openFileToWrite(cartFileName);
            printWriter.println(cart.getCustomer().getCustomerID() + "|" +
                    cart.getItems().getItemsID() + "|" + cart.getNumOfBuy());
            closeFileAfterWrite(cartFileName);
        }
    }
    private static Items getItems(Integer itemId, ArrayList<Items> items) {
        for (int i = 0; i < items.size(); ++i) {
            if (itemId == items.get(i).getItemsID()) {
                return items.get(i);
            }
        }
        return null;
    }

    private static Customer getCustomer(int customerId, ArrayList<Customer> customers) {
        for (int i = 0; i < customers.size(); ++i) {
            if (customerId == customers.get(i).getCustomerID()) {
                return customers.get(i);
            }
        }
        return null;
    }
}
