package dragon;

import org.junit.Test;

import java.util.Random;

/**
 * Created by sofia on 11/3/15.
 */
public class DragonSpeedTest {

    @Test
    public void testSolveDragon_SpeedPerDataSetSize_1() {
        int n = 10;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            a[i] = random.nextInt(10+1);
        }
        long start = System.currentTimeMillis();
        String result = Dragon.solveDragon(a, n);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Input size: " + n);
        System.out.println("Result: " + result);
        System.out.println("Processing speed: " + duration + " (milliseconds)");
        System.out.println();
    }

    @Test
    public void testSolveDragon_SpeedPerDataSetSize_2() {
        int n = 100;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            a[i] = random.nextInt(10+1);
        }
        long start = System.currentTimeMillis();
        String result = Dragon.solveDragon(a, n);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Input size: " + n);
        System.out.println("Result: " + result);
        System.out.println("Processing speed: " + duration + " (milliseconds)");
        System.out.println();
    }

    @Test
    public void testSolveDragon_SpeedPerDataSetSize_3() {
        int n = 1000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            a[i] = random.nextInt(10+1);
        }
        long start = System.currentTimeMillis();
        String result = Dragon.solveDragon(a, n);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Input size: " + n);
        System.out.println("Result: " + result);
        System.out.println("Processing speed: " + duration + " (milliseconds)");
        System.out.println();
    }

    @Test
    public void testSolveDragon_SpeedPerDataSetSize_4() {
        int n = 10000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            a[i] = random.nextInt(10+1);
        }
        long start = System.currentTimeMillis();
        String result = Dragon.solveDragon(a, n);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Input size: " + n);
        System.out.println("Result: " + result);
        System.out.println("Processing speed: " + duration + " (milliseconds)");
        System.out.println();
    }

    @Test
    public void testSolveDragon_SpeedPerDataSetSize_5() {
        int n = 100000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            a[i] = random.nextInt(10+1);
        }
        long start = System.currentTimeMillis();
        String result = Dragon.solveDragon(a, n);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Input size: " + n);
        System.out.println("Result: " + result);
        System.out.println("Processing speed: " + duration + " (milliseconds)");
        System.out.println();
    }

}
