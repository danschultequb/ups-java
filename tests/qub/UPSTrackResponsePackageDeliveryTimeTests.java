package qub;

public interface UPSTrackResponsePackageDeliveryTimeTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSTrackResponsePackageDeliveryTime.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                test.assertNotNull(deliveryTime);
                test.assertNull(deliveryTime.getStartTime());
                test.assertNull(deliveryTime.getEndTime());
                test.assertNull(deliveryTime.getType());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSTrackResponsePackageDeliveryTime.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with empty", (Test test) ->
                {
                    final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create(JSONObject.create());
                    test.assertNotNull(deliveryTime);
                test.assertNull(deliveryTime.getStartTime());
                test.assertNull(deliveryTime.getEndTime());
                test.assertNull(deliveryTime.getType());
                });

                runner.test("with full", (Test test) ->
                {
                    final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create(JSONObject.create()
                        .setString("startTime", "a")
                        .setString("endTime", "b")
                        .setString("type", "c"));
                    test.assertNotNull(deliveryTime);
                    test.assertEqual("a", deliveryTime.getStartTime());
                    test.assertEqual("b", deliveryTime.getEndTime());
                    test.assertEqual("c", deliveryTime.getType());
                });
            });

            runner.testGroup("setStartTime(String)", () ->
            {
                final Action2<String, Throwable> setStartTimeErrorTest = (String startTime, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(startTime), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        test.assertThrows(() -> deliveryTime.setStartTime(startTime), expected);
                        test.assertNull(deliveryTime.getStartTime());
                    });
                };

                setStartTimeErrorTest.run(null, new PreConditionFailure("startTime cannot be null."));

                final Action1<String> setStartTimeTest = (String startTime) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(startTime), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        final UPSTrackResponsePackageDeliveryTime setStartTimeResult = deliveryTime.setStartTime(startTime);
                        test.assertSame(deliveryTime, setStartTimeResult);
                        test.assertEqual(startTime, deliveryTime.getStartTime());
                    });
                };

                setStartTimeTest.run("");
                setStartTimeTest.run("a");
            });

            runner.testGroup("setEndTime(String)", () ->
            {
                final Action2<String, Throwable> setEndTimeErrorTest = (String endTime, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(endTime), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        test.assertThrows(() -> deliveryTime.setEndTime(endTime), expected);
                        test.assertNull(deliveryTime.getEndTime());
                    });
                };

                setEndTimeErrorTest.run(null, new PreConditionFailure("endTime cannot be null."));

                final Action1<String> setEndTimeTest = (String endTime) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(endTime), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        final UPSTrackResponsePackageDeliveryTime setEndTimeResult = deliveryTime.setEndTime(endTime);
                        test.assertSame(deliveryTime, setEndTimeResult);
                        test.assertEqual(endTime, deliveryTime.getEndTime());
                    });
                };

                setEndTimeTest.run("");
                setEndTimeTest.run("a");
            });

            runner.testGroup("setType(String)", () ->
            {
                final Action2<String, Throwable> setTypeErrorTest = (String type, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        test.assertThrows(() -> deliveryTime.setType(type), expected);
                        test.assertNull(deliveryTime.getType());
                    });
                };

                setTypeErrorTest.run(null, new PreConditionFailure("type cannot be null."));
                setTypeErrorTest.run("", new PreConditionFailure("type cannot be empty."));

                final Action1<String> setTypeTest = (String type) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageDeliveryTime deliveryTime = UPSTrackResponsePackageDeliveryTime.create();
                        final UPSTrackResponsePackageDeliveryTime setTypeResult = deliveryTime.setType(type);
                        test.assertSame(deliveryTime, setTypeResult);
                        test.assertEqual(type, deliveryTime.getType());
                    });
                };

                setTypeTest.run("a");
                setTypeTest.run("EOD");
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<UPSTrackResponsePackageDeliveryTime, String> toStringTest = (UPSTrackResponsePackageDeliveryTime deliveryTime, String expected) ->
                {
                    runner.test("with " + deliveryTime, (Test test) ->
                    {
                        test.assertEqual(expected, deliveryTime.toString());
                    });
                };

                toStringTest.run(
                    UPSTrackResponsePackageDeliveryTime.create(),
                    "{}");
                toStringTest.run(
                    UPSTrackResponsePackageDeliveryTime.create()
                        .setStartTime("a")
                        .setEndTime("b")
                        .setType("c"),
                    "{\"startTime\":\"a\",\"endTime\":\"b\",\"type\":\"c\"}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSTrackResponsePackageDeliveryTime, Object, Boolean> equalsTest = (UPSTrackResponsePackageDeliveryTime deliveryTime, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(deliveryTime, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, deliveryTime.equals(rhs));
                    });
                };

                equalsTest.run(
                    UPSTrackResponsePackageDeliveryTime.create(),
                    null,
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryTime.create(),
                    "hello",
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryTime.create(),
                    JSONObject.create(),
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageDeliveryTime.create(),
                    UPSTrackResponsePackageDeliveryTime.create(),
                    true);
            });
        });
    }
}
