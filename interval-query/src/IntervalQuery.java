import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by sofia on 4/16/18.
 */

/**
 * Code written during a phone interview.
 */

/**
 * Problem:
 *
 * Input:
 * List of time intervals
 * Integer x
 *
 * Output:
 * Whether or not x falls within any interval
 */
public class IntervalQuery {

    public static class IntervalQueryAnswerer {

        List<Interval> intervals;
        TreeMap<Integer, Integer> map;

        public IntervalQueryAnswerer(List<Interval> intervals) {
            this.intervals = mergeIntervals(intervals);
            this.map = putIntervalsToMap();
        }

        public boolean inRange(int x) {
            Integer startTime = map.floorKey(x);
            if (startTime == null) return false;
            int endTime = map.get(startTime);
            return x >= startTime && x <= endTime;
        }

        private List<Interval> mergeIntervals(List<Interval> intervals) {
            List<Interval> res = new ArrayList<>();
            if (intervals == null || intervals.size() == 0) return res;

            Collections.sort(intervals, (a, b) -> (a.start != b.start) ? (a.start - b.start) : (a.end - b.end));

            Interval prev = intervals.get(0);

            for (int i = 1; i < intervals.size(); i++) {
                Interval curr = intervals.get(i);

                if (prev.end >= curr.start) {
                    prev = new Interval(Math.min(prev.start, curr.start), Math.max(prev.end, curr.end));
                } else {
                    res.add(prev);
                    prev = curr;
                }
            }

            res.add(prev);

            return res;
        }

        private TreeMap<Integer, Integer> putIntervalsToMap() {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (Interval interval : this.intervals) {
                map.put(interval.start, interval.end);
            }
            return map;
        }
    }


    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }





    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(30, 40));
        intervals.add(new Interval(50, 70));

        IntervalQueryAnswerer intervalQueryAnswerer = new IntervalQueryAnswerer(intervals);

        int x;
        boolean ans;

        x = 5;
        ans = intervalQueryAnswerer.inRange(x);
        System.out.println(x+": "+ans);
        x = 20;
        ans = intervalQueryAnswerer.inRange(x);
        System.out.println(x+": "+ans);
        x = 100;
        ans = intervalQueryAnswerer.inRange(x);
        System.out.println(x+": "+ans);
    }

}
