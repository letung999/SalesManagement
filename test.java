package PracticeAfterLearn.Chuong2.Bai6;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer id;
        boolean exits = false;
        ArrayList list = new ArrayList();
        list.add(10000);
        list.add(10001);
        list.add(10002);
        list.add(10003);
        list.add(10004);
        list.add(10005);
        list.add(10006);
        System.out.println("input id");
        id = scanner.nextInt();
        for (int i = 0; i < list.size(); ++i){
            if(id.equals(list.get(i)) == true){
                exits = true;
            }
        }
        if(exits == false){
            System.out.println("Not exist");
        }
        else {
            System.out.println("exist");
        }
    }
}
