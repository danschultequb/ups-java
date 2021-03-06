package qub;

public interface UPSTrackResponsePackageDeliveryDateTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSTrackResponsePackageDeliveryDate.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create();
                test.assertNotNull(deliveryDate, "deliveryDate");
                test.assertNull(deliveryDate.getType());
                test.assertNull(deliveryDate.getDate());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSTrackResponsePackageDeliveryDate.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with empty", (Test test) ->
                {
                    final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create(JSONObject.create());
                    test.assertNotNull(deliveryDate, "deliveryDate");
                    test.assertNull(deliveryDate.getType());
                    test.assertNull(deliveryDate.getDate());
                });

                runner.test("with full", (Test test) ->
                {
                    final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create(JSONObject.create()
                        .setString("type", "a")
                        .setString("date", "b"));
                    test.assertNotNull(deliveryDate, "deliveryDate");
                    test.assertEqual("a", deliveryDate.getType());
                    test.assertEqual("b", deliveryDate.getDate());
                });
            });

            runner.testGroup("setType(String)", () ->
            {
                final Action2<String,Throwable> setTypeErrorTest = (String type, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create();
                        test.assertThrows(() -> deliveryDate.setType(type), expected);
                        test.assertNull(deliveryDate.getType());
                    });
                };

                setTypeErrorTest.run(null, new PreConditionFailure("type cannot be null."));
                setTypeErrorTest.run("", new PreConditionFailure("type cannot be empty."));

                final Action1<String> setTypeTest = (String type) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create();
                        final UPSTrackResponsePackageDeliveryDate setTypeResult = deliveryDate.setType(type);
                        test.assertSame(deliveryDate, setTypeResult);
                        test.assertEqual(type, deliveryDate.getType());
                    });
                };

                setTypeTest.run("a");
                setTypeTest.run("TW0001");
            });

            runner.testGroup("setDate(String)", () ->
            {
                final Action2<String,Throwable> setDateErrorTest = (String date, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(date), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create();
                        test.assertThrows(() -> deliveryDate.setDate(date), expected);
                        test.assertNull(deliveryDate.getDate());
                    });
                };

                setDateErrorTest.run(null, new PreConditionFailure("date cannot be null."));
                setDateErrorTest.run("", new PreConditionFailure("date cannot be empty."));

                final Action1<String> setDateTest = (String date) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(date), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryDate deliveryDate = UPSTrackResponsePackageDeliveryDate.create();
                        final UPSTrackResponsePackageDeliveryDate setDateResult = deliveryDate.setDate(date);
                        test.assertSame(deliveryDate, setDateResult);
                        test.assertEqual(date, deliveryDate.getDate());
                    });
                };

                setDateTest.run("a");
                setDateTest.run("Tracking Information Not Found");
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<UPSTrackResponsePackageDeliveryDate,String> toStringTest = (UPSTrackResponsePackageDeliveryDate deliveryDate, String expected) ->
                {
                    runner.test("with " + deliveryDate, (Test test) ->
                    {
                        test.assertEqual(expected, deliveryDate.toString());
                    });
                };

                toStringTest.run(
                    UPSTrackResponsePackageDeliveryDate.create(),
                    "{}");
                toStringTest.run(
                    UPSTrackResponsePackageDeliveryDate.create()
                        .setType("a")
                        .setDate("b"),
                    "{\"type\":\"a\",\"date\":\"b\"}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSTrackResponsePackageDeliveryDate,Object,Boolean> equalsTest = (UPSTrackResponsePackageDeliveryDate deliveryDate, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(deliveryDate, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, deliveryDate.equals(rhs));
                    });
                };

                equalsTest.run(
                    UPSTrackResponsePackageDeliveryDate.create(),
                    null,
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryDate.create(),
                    "hello",
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryDate.create(),
                    JSONObject.create(),
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryDate.create(),
                    UPSTrackResponsePackageDeliveryDate.create(),
                    true);
            });
        });
    }
}
