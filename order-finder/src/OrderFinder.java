import java.util.*;

/**
 * Created by sofia on 2023-05-08.
 */

/**
 * Sources:
 * Coding interview
 */

/**
 * Problem:
 *
 * Input:
 * - Menu: Map<String, String> (key: item, value: price)
 * - Total Price: String
 *
 * Price Format:
 * "XXXXXX.YY"
 * At least 1 X digit
 * Exactly 2 Y digits
 *
 * Valid Example:
 * 1.00
 * 0.43
 * 10472.23
 *
 * Invalid Example:
 * .45
 * 1
 * 2.3
 *
 * Prices will ALWAYS be valid and NEVER negative.
 * Items in the menu will never be 0.
 *
 * Output:
 * - All orders that total the price EXACTLY: List<List<String>>
 *   Outer list is list of orders.
 *   Inner list represents a single order, where each element is an item from the menu.
 *
 * Example:
 *  "Fruit": "2.15",
 *  "Fries": "2.75",
 *  "Salad": "3.35,
 *  "Wings": 3.55,
 *  "Mozzarella": 4.20,
 *  "Plate": 5.80,
 *
 *  4.30 -> [["Fruit", "Fruit"]]
 *  4.90 -> [["Fruit", "Fries"]] OR [["Fries", "Fruit"]] but NOT [["Fruit", "Fries"], ["Fries", "Fruit"]]
 *
 *  1.00 -> []
 *  0.00 -> [[]]
 *
 *  Clarification:
 *  - Same items can be ordered multiple times
 *  - Can skip price validation
 *  - Prices in menu will never be 0
 */
public class OrderFinder {

    static class Item {
        String name;
        double price;

        public Item(String n, double p) {
            name = n;
            price = p;
        }
    }

    public static List<List<String>> findOrdersThatSumUpToTotalPrice(Map<String, String> menu, String totalPriceStr) {
        List<List<String>> res = new ArrayList<>();

        double totalPrice = Double.parseDouble(totalPriceStr);

        // take care of the case where totalPrice = 0.0
        if (totalPrice == 0.0) {
            res.add(new ArrayList<>());
            return res;
        }

        List<String> order = new ArrayList<>();

        List<Item> items = new ArrayList<>();
        populateItems(menu, items);
        Collections.sort(items, (a,b) -> Double.compare(a.price, b.price));

        int n = items.size();

        backtrack(items, n, 0, 0.0, totalPrice, order, res);

        return res;
    }

    protected static void backtrack(List<Item> items, int n, int pos, double currPrice, double totalPrice,
                                    List<String> order, List<List<String>> res) {

        if (Math.abs(totalPrice-currPrice) <= 0.0001) {
            // we have found a valid order
            // let's add the order to the result
            res.add(new ArrayList<>(order));
            return;
        }

        if (pos == n) return;

        for (int i = pos; i < n; i++) {
            Item item = items.get(i);

            double itemPrice = item.price;
            if (currPrice + itemPrice > totalPrice) {
                break;
            }

            // let's add current item and check if we can make up total price
            order.add(item.name);
            currPrice += itemPrice;

            backtrack(items, n, i, currPrice, totalPrice, order, res);

            order.remove(order.size()-1);
            currPrice -= itemPrice;
        }
    }

    private static void populateItems(Map<String, String> menu, List<Item> items) {
        for (Map.Entry<String, String> entry : menu.entrySet()) {
            String name = entry.getKey();
            String priceStr = entry.getValue();
            double price = Double.parseDouble(priceStr);

            items.add(new Item(name, price));
        }
    }





    public static void main(String[] args) {
        Map<String, String> menu = new HashMap<>();
        menu.put("Fruit", "2.15");
        menu.put("Fries", "2.75");
        menu.put("Salad", "3.35");
        menu.put("Wings", "3.55");
        menu.put("Mozzarella", "4.20");
        menu.put("Plate", "5.80");

        System.out.println(findOrdersThatSumUpToTotalPrice(menu, "4.30"));
        System.out.println(findOrdersThatSumUpToTotalPrice(menu, "4.90"));
        System.out.println(findOrdersThatSumUpToTotalPrice(menu, "6.45"));
        System.out.println(findOrdersThatSumUpToTotalPrice(menu, "11.30"));
        System.out.println(findOrdersThatSumUpToTotalPrice(menu, "15.05"));
    }

}
