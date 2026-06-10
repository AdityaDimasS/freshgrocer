public class OrderHandlerTest {

    OrderHandler handler;

    public static void main(String[] args) {

        OrderHandlerTest test = new OrderHandlerTest();
        test.handler = new OrderHandler();

        // Discount
        test.testGoldGroupDiscount();
        test.testGoldNonGroupDiscount();
        test.testGoldDurationDiscount();
        test.testGoldDefaultDiscount();

        test.testSilverDuration12();
        test.testSilverDurationLess12();
        test.testSilverDefaultDiscount();

        test.testBronzeDiscount();
        test.testBronzeNoDiscount();

        test.testRegularLargeGroup();
        test.testRegularSmallGroup();
        test.testRegularNonGroup();

        // Delivery Fee
        test.testGoldDeliveryFee();
        test.testSilverDeliveryFee();
        test.testSilverNormalDelivery();
        test.testRegularDeliveryFee();

        System.out.println("All tests passed.");
    }

    // ======================
    // GOLD
    // ======================

    void testGoldGroupDiscount() {
        assertDiscount(
                handler.calculateDiscount("GOLD", 15, 2, true),
                0.25
        );
    }

    void testGoldNonGroupDiscount() {
        assertDiscount(
                handler.calculateDiscount("GOLD", 15, 2, false),
                0.20
        );
    }

    void testGoldDurationDiscount() {
        assertDiscount(
                handler.calculateDiscount("GOLD", 5, 6, false),
                0.15
        );
    }

    void testGoldDefaultDiscount() {
        assertDiscount(
                handler.calculateDiscount("GOLD", 5, 2, false),
                0.10
        );
    }

    // ======================
    // SILVER
    // ======================

    void testSilverDuration12() {
        assertDiscount(
                handler.calculateDiscount("SILVER", 6, 12, false),
                0.15
        );
    }

    void testSilverDurationLess12() {
        assertDiscount(
                handler.calculateDiscount("SILVER", 6, 3, false),
                0.10
        );
    }

    void testSilverDefaultDiscount() {
        assertDiscount(
                handler.calculateDiscount("SILVER", 3, 2, false),
                0.05
        );
    }

    // ======================
    // BRONZE
    // ======================

    void testBronzeDiscount() {
        assertDiscount(
                handler.calculateDiscount("BRONZE", 4, 3, false),
                0.05
        );
    }

    void testBronzeNoDiscount() {
        assertDiscount(
                handler.calculateDiscount("BRONZE", 2, 1, false),
                0.0
        );
    }

    // ======================
    // REGULAR
    // ======================

    void testRegularLargeGroup() {
        assertDiscount(
                handler.calculateDiscount("REGULAR", 25, 1, true),
                0.08
        );
    }

    void testRegularSmallGroup() {
        assertDiscount(
                handler.calculateDiscount("REGULAR", 10, 1, true),
                0.02
        );
    }

    void testRegularNonGroup() {
        assertDiscount(
                handler.calculateDiscount("REGULAR", 10, 1, false),
                0.0
        );
    }

    // ======================
    // DELIVERY
    // ======================

    void testGoldDeliveryFee() {
        assertDelivery(
                handler.calculateDeliveryFee("GOLD", 1),
                0.0
        );
    }

    void testSilverDeliveryFee() {
        assertDelivery(
                handler.calculateDeliveryFee("SILVER", 6),
                5000.0
        );
    }

    void testSilverNormalDelivery() {
        assertDelivery(
                handler.calculateDeliveryFee("SILVER", 2),
                15000.0
        );
    }

    void testRegularDeliveryFee() {
        assertDelivery(
                handler.calculateDeliveryFee("REGULAR", 1),
                15000.0
        );
    }

    // ======================
    // HELPER
    // ======================

    void assertDiscount(double actual, double expected) {

        if (Math.abs(actual - expected) > 1e-9) {

            throw new AssertionError(
                    "Expected "
                            + expected
                            + ", got "
                            + actual
            );
        }
    }

    void assertDelivery(double actual, double expected) {

        if (Math.abs(actual - expected) > 1e-9) {

            throw new AssertionError(
                    "Expected "
                            + expected
                            + ", got "
                            + actual
            );
        }
    }
}