package PracticeAfterLearn.Chuong2.Bai6.View;

import PracticeAfterLearn.Chuong2.Bai6.Controller.ControllerUtility;
import PracticeAfterLearn.Chuong2.Bai6.Controller.DataController;
import PracticeAfterLearn.Chuong2.Bai6.Model.Cart;
import PracticeAfterLearn.Chuong2.Bai6.Model.Customer;
import PracticeAfterLearn.Chuong2.Bai6.Model.Items;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {
    public static void main(String[] args) {
        var dataController = new DataController();
        var controllerUtility = new ControllerUtility();
        var itemFileName = "ITEMS.DAT";
        var customerFileName = "CUSTOMER.DAT";
        var cartFileName = "CART.DAT";
        var scanner = new Scanner(System.in);
        var items = new ArrayList<Items>();
        var customers = new ArrayList<Customer>();
        var carts = new ArrayList<Cart>();
        int option;
        do {
            System.out.println("*****************************MENU****************************");
            System.out.println("1.Add Item To File");
            System.out.println("2.Show Information Item In File");
            System.out.println("3.Add Customer To File");
            System.out.println("4.Show Information In File");
            System.out.println("5.Conduct Information");
            System.out.println("6.Show Information In File ");
            System.out.println("7.Sort Information");
            System.out.println("8.create Bill for Customer");
            System.out.println("0.exist");
            System.out.println("you choose");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    System.out.println("exist !");
                    break;
                }
                case 1: {
                    /**
                     * (itemsID, itemsName, itemsType,
                     * itemsPrice) – trong đó itemsType có thể là: Clothes, Electronics, Books, Foods.
                     */
                    checkAscendingItemsID(dataController, itemFileName);
                    String itemsName, itemsType, itemsPrice;
                    int its;
                    System.out.println("Input itemsName");
                    itemsName = scanner.nextLine();
                    String[] itemss = {"Clothes", "Electronics", "Books", "Foods."};
                    do {
                        System.out.println("Input itemsType");
                        System.out.println("1.Clothes\n2.Electronics\n3.Books\n4.Foods.");
                        its = scanner.nextInt();
                        if (its < 1 || its > 4) {
                            System.out.println("please choose option in list");
                        } else {
                            itemsType = itemss[its - 1];
                            break;
                        }
                    } while (true);
                    System.out.println("Input ItemsPrice");
                    scanner.nextLine();
                    itemsPrice = scanner.nextLine();
                    Items items1 = new Items(0, itemsName, itemsType, itemsPrice);
                    dataController.writeItemsToFile(items1, itemFileName);
                    break;
                }
                case 2: {
                    System.out.println("***********************Information Items In File************************");
                    items = dataController.readItemsFromFile(itemFileName);
                    show(items);
                    break;
                }
                case 3: {
                    checkAscendingCustomerID(dataController, customerFileName);
                    String customerName, address, phoneNumber;
                    System.out.println("Input customerName");
                    customerName = scanner.nextLine();
                    System.out.println("Input Address");
                    address = scanner.nextLine();
                    do {
                        String regex = "[0-9]{3}.[0-9]{4}.[0-9]{4}";
                        System.out.println("Input phoneNumber");
                        phoneNumber = scanner.nextLine();
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(phoneNumber);
                        if (matcher.find()) {
                            break;
                        } else {
                            System.out.println("Input phoneNumber with format true");
                        }
                    } while (true);
                    Customer customer = new Customer(0, customerName, address, phoneNumber);
                    dataController.writeCustomerToFile(customer, customerFileName);
                    break;

                }
                case 4: {
                    customers = dataController.readCustomerFromFile(customerFileName);
                    System.out.println("***************************Information Customer****************************");
                    show(customers);
                    break;
                }
                case 5: {
                    /**
                     * cart.getCustomer().getCustomerID() + "|" + cart.getItems().getItemsID() + "|" + cart.getNumOfBuy()
                     */
                    items = dataController.readItemsFromFile(itemFileName);
                    carts = dataController.readCartFromFile(cartFileName);
                    customers = dataController.readCustomerFromFile(customerFileName);
                    int customerId;
                    Integer itemId;
                    int totalOfBuy;
                    boolean checkAllow = false;
                    boolean exist = false;
                    do {
                        System.out.println("*******************Information Customer in File************************");
                        show(customers);
                        System.out.println("Input customerID, input 0 to break");
                        customerId = scanner.nextInt();
                        if (customerId == 0) {
                            break;
                        }
                        checkAllow = getNumOfTypeAllow(customerId, carts);
                        if (checkAllow == false) {
                            System.out.println("you bought over 11 types");
                        } else {
                            break;
                        }
                    } while (true);
                    do {
                        items = dataController.readItemsFromFile(itemFileName);
                        System.out.println("********************Information Items in File***********************");
                        show(items);
                        System.out.println("Input ItemsID, input 0 to break");
                        itemId = scanner.nextInt();
                        if (itemId == 0) {
                            break;
                        }
                        exist = checkExist(itemId, items);
                        if (exist == false) {
                            System.out.println("please check itemID again");
                        } else {
                            break;
                        }
                    } while (true);
                    totalOfBuy = getTotal(customerId, itemId, carts);
                    System.out.println("Input NumOfBuy (bought " + totalOfBuy + ")");
                    int numItems = scanner.nextInt();
                    totalOfBuy += numItems;
                    Customer currentCustomer = getCustomer(customerId, customers);
                    Items currentItems = getItems(itemId, items);
                    Cart cart = new Cart(currentCustomer, currentItems, totalOfBuy);
                    ControllerUtility.updateFile(cart, carts);
                    dataController.updateFile(carts, cartFileName);
                    show(carts);
                    break;

                }
                case 6:{
                    carts = dataController.readCartFromFile(cartFileName);
                    System.out.println("****************Information Cart In File*****************");
                    show(carts);
                    break;
                }
                case 7:{
                    int choose;
                    carts = dataController.readCartFromFile(cartFileName);
                    do {
                        System.out.println("********************menu*****************");
                        System.out.println("1.Sort By Name Customer");
                        System.out.println("2.Sort By Name Items");
                        System.out.println("3.Search Name Items");
                        System.out.println("0.return main menu");
                        System.out.println("you choose!");
                        choose = scanner.nextInt();
                        if(choose == 0){
                            break;
                        }
                        switch (choose){
                            case 1:{
                                controllerUtility.sortByNameCustomer(carts);
                                System.out.println("Information after Sort");
                                show(carts);
                                break;
                            }
                            case  2:{
                                controllerUtility.sortByNameItems(carts);
                                System.out.println("Information after sort");
                                show(carts);
                            }
                            case 3:{
                                System.out.println("Input items to search");
                                String key = "";
                                scanner.nextLine();
                                key = scanner.nextLine();
                                var results = new ArrayList<Cart>();
                                results = controllerUtility.searchItems(key, carts);
                                if(results.size() == 0){
                                    System.out.println("no Items in Store");
                                }
                                else {
                                    show(results);
                                }
                                break;

                            }
                        }
                    }while (choose != 0);
                }
            }
        } while (option != 0);
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

    private static int getTotal(int customerId, Integer itemId, ArrayList<Cart> carts) {
        for (var cart : carts) {
            if (customerId == cart.getCustomer().getCustomerID() && itemId == cart.getItems().getItemsID()) {
                return cart.getNumOfBuy();
            }
        }
        return 0;
    }

    private static boolean checkExist(Integer itemId, ArrayList<Items> items) {
        for (int i = 0; i < items.size(); ++i) {
            if (itemId.equals(items.get(i).getItemsID()) == true) {
                return true;
            }
        }
        return false;
    }

    private static boolean getNumOfTypeAllow(int customerID, ArrayList<Cart> cartFileName) {
        int count = 0;
        for (var cart : cartFileName) {
            if (customerID == cart.getCustomer().getCustomerID()) {
                ++count;
            }
        }
        if (count > 10) {
            return false;
        }
        return true;
    }

    private static void checkAscendingCustomerID(DataController dataController, String customerFileName) {
        var customers = dataController.readCustomerFromFile(customerFileName);
        if (customers.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Customer.setId(customers.get(customers.size() - 1).getCustomerID() + 1);
        }
    }

    private static void checkAscendingItemsID(DataController dataController, String itemFileName) {
        var items = dataController.readItemsFromFile(itemFileName);
        if (items.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Items.setId(items.get(items.size() - 1).getItemsID() + 1);
        }
    }

    public static void show(ArrayList list) {
        for (var x : list) {
            System.out.println(x);
        }
    }

}
