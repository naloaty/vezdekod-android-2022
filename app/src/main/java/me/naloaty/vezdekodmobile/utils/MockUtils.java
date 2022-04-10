package me.naloaty.vezdekodmobile.utils;

import java.util.Arrays;
import java.util.List;

import me.naloaty.vezdekodmobile.model.Order;

public class MockUtils {

    public static List<Order> simpleOrders() {

        return Arrays.asList(
                new Order("Vezdekod Store", "Moscow", RandomUtils.localTimeHM()),
                new Order("Some store 1", "Some loc 1", RandomUtils.localTimeHM()),
                new Order("Some store 2", "Some loc 2", RandomUtils.localTimeHM()),
                new Order("Some store 3", "Some loc 3", RandomUtils.localTimeHM()),
                new Order("Some store 4", "Some loc 4", RandomUtils.localTimeHM()),
                new Order("Some store 5", "Some loc 5", RandomUtils.localTimeHM()),
                new Order("Some store 6", "Some loc 6", RandomUtils.localTimeHM()),
                new Order("Some store 7", "Some loc 7", RandomUtils.localTimeHM()),
                new Order("Some store 8", "Some loc 8", RandomUtils.localTimeHM()),
                new Order("Some store 9", "Some loc 9", RandomUtils.localTimeHM()),
                new Order("Some store 10", "Some loc 10", RandomUtils.localTimeHM())

        );
    }
}
