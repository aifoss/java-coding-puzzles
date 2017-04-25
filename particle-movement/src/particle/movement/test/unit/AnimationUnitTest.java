package particle.movement.test.unit;

import org.junit.Assert;
import org.junit.Test;
import particle.movement.main.Animation;

import java.util.Arrays;

/**
 * Created by sofia on 4/25/17.
 */
public class AnimationUnitTest {

    @Test
    public void testAnimate0() {
        int speed = 2;
        String init = "..R....";
        String[] expected = {
                "..X....",
                "....X..",
                "......X",
                "......." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testAnimate1() {
        int speed = 3;
        String init = "RR..LRL";
        String[] expected = {
                "XX..XXX",
                ".X.XX..",
                "X.....X",
                "......." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testAnimate2() {
        int speed = 2;
        String init = "LRLR.LRLR";
        String[] expected = {
                "XXXX.XXXX",
                "X..X.X..X",
                ".X.X.X.X.",
                ".X.....X.",
                "........." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testAnimate3() {
        int speed = 10;
        String init = "RLRLRLRLRL";
        String[] expected = {
                "XXXXXXXXXX",
                ".........." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testAnimate4() {
        int speed = 1;
        String init = "...";
        String[] expected = { "..." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testAnimate5() {
        int speed = 1;
        String init = "LRRL.LR.LRR.R.LRRL.";
        String[] expected = {
                "XXXX.XX.XXX.X.XXXX.",
                "..XXX..X..XX.X..XX.",
                ".X.XX.X.X..XX.XX.XX",
                "X.X.XX...X.XXXXX..X",
                ".X..XXX...X..XX.X..",
                "X..X..XX.X.XX.XX.X.",
                "..X....XX..XX..XX.X",
                ".X.....XXXX..X..XX.",
                "X.....X..XX...X..XX",
                ".....X..X.XX...X..X",
                "....X..X...XX...X..",
                "...X..X.....XX...X.",
                "..X..X.......XX...X",
                ".X..X.........XX...",
                "X..X...........XX..",
                "..X.............XX.",
                ".X...............XX",
                "X.................X",
                "..................." };
        Animation animation = new Animation();
        String[] actual = animation.animate(speed, init);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

}
