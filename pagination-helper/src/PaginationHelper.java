import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sofia on 4/30/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * For this exercise you will be strengthening your page-fu mastery.
 * You will complete the PaginationHelper class,
 * which is a utility class helpful for querying paging information related to an array.
 *
 * The class is designed to take in an array of values and an integer indicating how many items will be allowed per each page.
 * The types of values contained within the collection/array are not relevant.
 *
 * The following are some examples of how this class is used:
 *
 * PaginationHelper<Character> helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
 * helper.pageCount(); //should == 2
 * helper.itemCount(); //should == 6
 * helper.pageItemCount(0); //should == 4
 * helper.pageItemCount(1); // last page - should == 2
 * helper.pageItemCount(2); // should == -1 since the page is invalid
 * helper.pageIndex(5); //should == 1 (zero based index)
 * helper.pageIndex(2); //should == 0
 * helper.pageIndex(20); //should == -1
 * helper.pageIndex(-10); //should == -1
 */
public class PaginationHelper<T> {

    int numItems;
    int numPages;

    Map<Integer, Integer> itemIndexToPageIndexMap;
    Map<Integer, List<T>> pageIndexToItemListMap;

    /**
     * The constructor takes in an array of items and an integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<T> collection, int itemsPerPage) {
        itemIndexToPageIndexMap = new HashMap<>();
        pageIndexToItemListMap = new HashMap<>();

        if (collection == null || collection.isEmpty() || itemsPerPage <= 0) {
            numItems = 0;
            numPages = 0;
            return;
        }

        numItems = collection.size();
        numPages = (numItems % itemsPerPage == 0) ? (numItems / itemsPerPage) : ((numItems / itemsPerPage) + 1);

        int pageIdx = 0;
        int itemIdx = 0;

        while (itemIdx < numItems) {
            int start = itemIdx;
            int end = Math.min(itemIdx+itemsPerPage-1, numItems-1);

            for (int j = start; j <= end; j++) {
                itemIndexToPageIndexMap.put(j, pageIdx);
            }

            pageIndexToItemListMap.put(pageIdx++, collection.subList(start, end+1));

            itemIdx = end+1;
        }
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return numItems;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return numPages;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        return pageIndexToItemListMap.containsKey(pageIndex) ? pageIndexToItemListMap.get(pageIndex).size() : -1;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        return itemIndexToPageIndexMap.containsKey(itemIndex) ? itemIndexToPageIndexMap.get(itemIndex) : -1;
    }





    public static void main(String[] args) {
        PaginationHelper<Character> helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
        int ans;

        ans = helper.pageCount(); //should == 2
        System.out.println(ans+" (2)");

        ans = helper.itemCount(); //should == 6
        System.out.println(ans+" (6)");

        ans = helper.pageItemCount(0); //should == 4
        System.out.println(ans+ " (4)");

        ans = helper.pageItemCount(1); // last page - should == 2
        System.out.println(ans+" (2)");

        ans = helper.pageItemCount(2); // should == -1 since the page is invalid
        System.out.println(ans+" (-1)");

        ans = helper.pageIndex(5); //should == 1 (zero based index)
        System.out.println(ans+" (1)");

        ans = helper.pageIndex(2); //should == 0
        System.out.println(ans + " (0)");

        ans = helper.pageIndex(20); //should == -1
        System.out.println(ans + " (-1)");

        ans = helper.pageIndex(-10); //should == -1
        System.out.println(ans + " (-1)");

        System.out.println();

        helper = new PaginationHelper<>(Arrays.asList(), 3);

        ans = helper.pageCount();
        System.out.println(ans+" (0)");

        ans = helper.itemCount();
        System.out.println(ans+" (0)");

        ans = helper.pageIndex(1);
        System.out.println(ans+" (-1)");

        ans = helper.pageItemCount(1);
        System.out.println(ans+" (-1)");

        System.out.println();

        helper = new PaginationHelper<>(Arrays.asList('a', 'b', 'c'), 0);

        ans = helper.pageCount();
        System.out.println(ans+" (0)");

        ans = helper.itemCount();
        System.out.println(ans+" (0)");

        ans = helper.pageIndex(1);
        System.out.println(ans+" (-1)");

        ans = helper.pageItemCount(1);
        System.out.println(ans+" (-1)");
    }

}
