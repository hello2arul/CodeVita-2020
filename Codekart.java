import java.util.*;
import java.io.*;

// 12. Codekart
class Codekart {
    Map<String, Double> costs;
    Map<String, Integer> items;
    Map<String, Integer> cart;
    double orderAmount;
    public Codekart() {
        costs = new HashMap<>();
        items = new HashMap<>();
        cart = new HashMap<>();
        orderAmount = 0;
    }
    public void execute(String query) {
        String[] queries = query.split(" ");
        switch(queries[1]) {
            case "SM":
                switch(queries[2]) {
                    case "ADD":
                        int quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || items.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            items.put(queries[3], quantity);
                            System.out.println(quantity);
                        }
                        break;
                    case "REMOVE":
                        if(!items.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            items.remove(queries[3]);
                            System.out.println(1);
                        }
                        break;
                    case "GET_QTY":
                        if(!items.containsKey(queries[3])) {
                            System.out.println(0);
                        } else {
         
                            System.out.println(items.get(queries[3]));
                        }
                        break;
                    case "INCR":
                        quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || !items.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            items.put(queries[3], items.get(queries[3]) + quantity);
                            System.out.println(quantity);
                        }
                        break;
                    case "DCR":
                        quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || !items.containsKey(queries[3]) || items.get(queries[3]) - quantity < 0) {
                            System.out.println(-1);
                        } else {
                            if(items.get(queries[3]) - quantity == 0) {
                                items.remove(queries[3]);
                            } else {
                                items.put(queries[3], items.get(queries[3]) - quantity);
                            }
                            System.out.println(quantity);
                        }
                        break;
                    case "SET_COST":
                        double cost = 0;
                        try {
                            cost = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        cost = Double.valueOf(queries[4]);
                        costs.put(queries[3], cost);
                        System.out.printf("%.1f\n", cost);
                        break;
                }
                break;
            case "S":
                switch(queries[2]) {
                    case "ADD":
                        int quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || !costs.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            orderAmount += (costs.get(queries[3]) * quantity);
                            cart.put(queries[3], quantity);
                            System.out.println(quantity);
                        }
                        break;
                    case "REMOVE":
                        if(!cart.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            orderAmount -= (costs.get(queries[3]) * cart.get(queries[3]));
                            cart.remove(queries[3]);
                            System.out.println(1);
                        }
                        break;
                    case "INCR":
                        quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || !cart.containsKey(queries[3])) {
                            System.out.println(-1);
                        } else {
                            orderAmount += (costs.get(queries[3]) * quantity);
                            items.put(queries[3], cart.get(queries[3]) + quantity);
                            System.out.println(quantity);
                        }
                        break;
                    case "DCR":
                        quantity = 0;
                        try {
                            quantity = Integer.parseInt(queries[4]);
                        } catch(Exception e) {
                            System.out.println(-1);
                            return;
                        }
                        if(quantity <= 0 || !cart.containsKey(queries[3]) || cart.get(queries[3]) - quantity < 0) {
                            System.out.println(-1);
                        } else {
                            if(cart.get(queries[3]) - quantity == 0) {
                                cart.remove(queries[3]);
                            } else {
                                cart.put(queries[3], cart.get(queries[3]) - quantity);
                            }
                            orderAmount -= (costs.get(queries[3]) * quantity);
                            System.out.println(quantity);
                        }
                        break;
                    case "GET_ORDER_AMOUNT":
                        if(cart.isEmpty()) {
                            System.out.println(-1);
                        }
                        System.out.printf("%.2f\n", orderAmount);
                        break;
                }
                break;
        }
    }
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();
        // sc.nextLine();
        // while(t-- > 0) {
        //     Codekart obj = new Codekart();
        //     String current = sc.nextLine();
        //     while(current.length() != 3) {
        //         obj.execute(current);
        //         current = sc.nextLine();
        //         sc.nextLine();
        //     }
        // }
        // sc.close();
        Codekart obj = new Codekart();
        try (Scanner sc = new Scanner(new File("Codekart.txt")).useDelimiter("\n")) {
            while (sc.hasNext()) {
                obj.execute(sc.next().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}