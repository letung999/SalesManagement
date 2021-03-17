package PracticeAfterLearn.Chuong2.Bai6.Model;

public class Cart {
    private Customer customer;
    private Items items;
    private int numOfBuy;

    public Cart(Customer customer, Items items, int numOfBuy) {
        this.customer = customer;
        this.items = items;
        this.numOfBuy = numOfBuy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getNumOfBuy() {
        return numOfBuy;
    }

    public void setNumOfBuy(int numOfBuy) {
        this.numOfBuy = numOfBuy;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customerID=" + customer.getCustomerID() +
                ", customerName=" + customer.getCustomerName() +
                ", itemsID=" + items.getItemsID() +
                ", itemsName=" + items.getItemsName() +
                ", numOfBuy=" + numOfBuy +
                '}';
    }
}
