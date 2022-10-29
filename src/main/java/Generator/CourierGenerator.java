package Generator;

import User.Courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier getRandomNewCourierGenerator() {
        Courier createCourier = new Courier();
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }

    public static Courier getNewCourierWithoutLogin() {
        Courier createCourier = new Courier();
        createCourier.setLogin(null);
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }

    public static Courier getNewCourierWithoutPassword() {
        Courier createCourier = new Courier();
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourier.setPassword(null);
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }
}
