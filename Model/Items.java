package PracticeAfterLearn.Chuong2.Bai6.Model;

public class Items {
    /**
     * itemsID, itemsName, itemsType,
     * itemsPrice
     */
    private static int id = 10000000;
    private int itemsID;
    private String itemsName;
    private String itemsType;
    private String itemsPrice;

    public Items(int itemsID) {
        this.itemsID = itemsID;
    }

    public Items(int itemsID, String itemsName, String itemsType, String itemsPrice) {
        if(itemsID == 0){
            this.itemsID = id;
        }
        else {
            this.itemsID = itemsID;
        }
        this.itemsName = itemsName;
        this.itemsType = itemsType;
        this.itemsPrice = itemsPrice;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Items.id = id;
    }

    public int getItemsID() {
        return itemsID;
    }

    public void setItemsID(int itemsID) {
        this.itemsID = itemsID;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsType() {
        return itemsType;
    }

    public void setItemsType(String itemsType) {
        this.itemsType = itemsType;
    }

    public String getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(String itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemsID=" + itemsID +
                ", itemsName='" + itemsName + '\'' +
                ", itemsType='" + itemsType + '\'' +
                ", itemsPrice='" + itemsPrice + '\'' +
                '}';
    }
}
