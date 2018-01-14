package particle.movement.test.functional;

import particle.movement.main.Animation;

/**
 * Created by sofia on 9/1/15.
 */
public class AnimationFunctionalTest {

    public static void main(String[] args) {
        Animation animation = new Animation();

        int speed;
        String init;
        String[] result;

        System.out.println("-----------------------------------");

        System.out.println("Case 0:");
        System.out.println();

        speed = 2;
        init = "..R....";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");

        System.out.println("Case 1:");
        System.out.println();

        speed = 3;
        init = "RR..LRL";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");

        System.out.println("Case 2:");
        System.out.println();

        speed = 2;
        init = "LRLR.LRLR";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");

        System.out.println("Case 3:");
        System.out.println();

        speed = 10;
        init = "RLRLRLRLRL";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");

        System.out.println("Case 4:");
        System.out.println();

        speed = 1;
        init = "...";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");

        System.out.println("Case 5:");
        System.out.println();

        speed = 1;
        init = "LRRL.LR.LRR.R.LRRL.";

        System.out.println("Input:");
        System.out.println();
        System.out.println("speed = " + speed);
        System.out.println("init = " + init);
        System.out.println();

        result = animation.animate(speed, init);

        System.out.println("Output:");
        System.out.println();
        for (String state : result) {
            System.out.println(state);
        }
        System.out.println();

        System.out.println("-----------------------------------");
    }

}
