package me.naloaty.vezdekodmobile.utils;

import java.time.LocalTime;
import java.util.Random;

public class RandomUtils {

    private static int range(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static LocalTime localTimeHM() {
        return LocalTime.of(range(0, 24), range(0, 60));
    }
}
