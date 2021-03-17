package PracticeAfterLearn.Chuong2.Bai6.Model;

public class Customer {
    /**
     * customerID, customerName, address, phone number
     */
    private static int id = 1000000000;
    private int customerID;
    private String customerName;
    private String address;
    private String phoneNumber;

    public Customer(int customerID) {
        this.customerID = customerID;
    }

    public Customer(int customerID, String customerName, String address, String phoneNumber) {
        if(customerID == 0){
            this.customerID = id;
        }
        else {
            this.customerID = customerID;
        }
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Customer.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
