package PracticeAfterLearn.Chuong2.Bai6.Controller;

import PracticeAfterLearn.Chuong2.Bai6.Model.Cart;

import java.util.ArrayList;

public class ControllerUtility {
    public static void updateFile(Cart cart, ArrayList<Cart> carts) {
        boolean isUpdate = false;
        for (int i = 0; i < carts.size(); ++i) {
            if (cart.getCustomer().getCustomerID() == carts.get(i).getCustomer().getCustomerID()
                    && cart.getItems().getItemsID() == carts.get(i).getItems().getItemsID()) {
                isUpdate = true;
                carts.set(i, cart);

            }
        }
        if (isUpdate == false) {
            carts.add(cart);
        }
    }

    public void sortByNameCustomer(ArrayList<Cart> carts) {
        for (int i = 0; i < carts.size(); ++i) {
            for (int j = carts.size() - 1; j > i; j--) {
                Cart bj = carts.get(j);
                Cart bbj = carts.get(j - 1);
                if (bbj.getCustomer().getCustomerName().
                        substring(bbj.getCustomer().getCustomerName().lastIndexOf(" "))
                        .compareTo(bj.getCustomer().getCustomerName().
                                substring(bj.getCustomer().getCustomerName().lastIndexOf(" "))) > 0) {
                    carts.set(j, bbj);
                    carts.set(j-1, bj);

                }
            }
        }
    }

    public void sortByNameItems(ArrayList<Cart> carts) {
        for (int i = 0; i < carts.size(); ++i) {
            for (int j = carts.size() - 1; j > i; j--) {
                Cart bj = carts.get(j);
                Cart bbj = carts.get(j - 1);
                if (bbj.getItems().getItemsName().
                        substring(bbj.getItems().getItemsName().lastIndexOf(" "))
                        .compareTo(bj.getItems().getItemsName().
                                substring(bj.getItems().getItemsName().lastIndexOf(" "))) > 0) {
                    carts.set(j, bbj);
                    carts.set(j-1, bj);

                }
            }
        }
    }

    public ArrayList<Cart> searchItems(String key, ArrayList<Cart> carts) {
        var result = new ArrayList<Cart>();
        String regex = ".*" + key.toLowerCase() + ".*";
        for (int i = 0; i<carts.size(); ++i){
            if(carts.get(i).getItems().getItemsName().toLowerCase().matches(regex)){
                result.add(carts.get(i));
            }
        }
        return result;
    }
}
