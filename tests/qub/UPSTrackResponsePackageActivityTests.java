package qub;

public interface UPSTrackResponsePackageActivityTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSTrackResponsePackageActivity.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                test.assertNotNull(activity);
                test.assertNull(activity.getCity());
                test.assertNull(activity.getStateProvince());
                test.assertNull(activity.getPostalCode());
                test.assertNull(activity.getCountry());
                test.assertNull(activity.getType());
                test.assertNull(activity.getDescription());
                test.assertNull(activity.getCode());
                test.assertNull(activity.getDate());
                test.assertNull(activity.getTime());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSTrackResponsePackageActivity.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with empty", (Test test) ->
                {
                    final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create(JSONObject.create());
                    test.assertNotNull(activity);
                    test.assertNull(activity.getCity());
                    test.assertNull(activity.getStateProvince());
                    test.assertNull(activity.getPostalCode());
                    test.assertNull(activity.getCountry());
                    test.assertNull(activity.getType());
                    test.assertNull(activity.getDescription());
                    test.assertNull(activity.getCode());
                    test.assertNull(activity.getDate());
                    test.assertNull(activity.getTime());
                });

                runner.test("with full", (Test test) ->
                {
                    final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create(JSONObject.create()
                        .setObject("location", JSONObject.create()
                            .setObject("address", JSONObject.create()
                                .setString("city", "a")
                                .setString("stateProvince", "b")
                                .setString("postalCode", "c")
                                .setString("country", "d")))
                        .setObject("status", JSONObject.create()
                            .setString("type", "e")
                            .setString("description", "f")
                            .setString("code", "g"))
                        .setString("date", "h")
                        .setString("time", "i"));
                    test.assertNotNull(activity);
                    test.assertEqual("a", activity.getCity());
                    test.assertEqual("b", activity.getStateProvince());
                    test.assertEqual("c", activity.getPostalCode());
                    test.assertEqual("d", activity.getCountry());
                    test.assertEqual("e", activity.getType());
                    test.assertEqual("f", activity.getDescription());
                    test.assertEqual("g", activity.getCode());
                    test.assertEqual("h", activity.getDate());
                    test.assertEqual("i", activity.getTime());
                });
            });

            runner.testGroup("setCity(String)", () ->
            {
                final Action2<String,Throwable> setCityErrorTest = (String city, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(city), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setCity(city), expected);
                        test.assertNull(activity.getCity());
                    });
                };

                setCityErrorTest.run(null, new PreConditionFailure("city cannot be null."));

                final Action1<String> setCityTest = (String city) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(city), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setCityResult = activity.setCity(city);
                        test.assertSame(activity, setCityResult);
                        test.assertEqual(city, activity.getCity());
                    });
                };

                setCityTest.run("");
                setCityTest.run("a");
            });

            runner.testGroup("setStateProvince(String)", () ->
            {
                final Action2<String,Throwable> setStateProvinceErrorTest = (String stateProvince, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(stateProvince), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setStateProvince(stateProvince), expected);
                        test.assertNull(activity.getStateProvince());
                    });
                };

                setStateProvinceErrorTest.run(null, new PreConditionFailure("stateProvince cannot be null."));

                final Action1<String> setStateProvinceTest = (String stateProvince) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(stateProvince), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setStateProvinceResult = activity.setStateProvince(stateProvince);
                        test.assertSame(activity, setStateProvinceResult);
                        test.assertEqual(stateProvince, activity.getStateProvince());
                    });
                };

                setStateProvinceTest.run("");
                setStateProvinceTest.run("a");
            });

            runner.testGroup("setPostalCode(String)", () ->
            {
                final Action2<String,Throwable> setPostalCodeErrorTest = (String postalCode, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(postalCode), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setPostalCode(postalCode), expected);
                        test.assertNull(activity.getPostalCode());
                    });
                };

                setPostalCodeErrorTest.run(null, new PreConditionFailure("postalCode cannot be null."));

                final Action1<String> setPostalCodeTest = (String postalCode) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(postalCode), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setPostalCodeResult = activity.setPostalCode(postalCode);
                        test.assertSame(activity, setPostalCodeResult);
                        test.assertEqual(postalCode, activity.getPostalCode());
                    });
                };

                setPostalCodeTest.run("");
                setPostalCodeTest.run("a");
            });

            runner.testGroup("setCountry(String)", () ->
            {
                final Action2<String,Throwable> setCountryErrorTest = (String country, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(country), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setCountry(country), expected);
                        test.assertNull(activity.getCountry());
                    });
                };

                setCountryErrorTest.run(null, new PreConditionFailure("country cannot be null."));

                final Action1<String> setCountryTest = (String country) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(country), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setCountryResult = activity.setCountry(country);
                        test.assertSame(activity, setCountryResult);
                        test.assertEqual(country, activity.getCountry());
                    });
                };

                setCountryTest.run("");
                setCountryTest.run("a");
            });

            runner.testGroup("setType(String)", () ->
            {
                final Action2<String,Throwable> setTypeErrorTest = (String type, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setType(type), expected);
                        test.assertNull(activity.getType());
                    });
                };

                setTypeErrorTest.run(null, new PreConditionFailure("type cannot be null."));

                final Action1<String> setTypeTest = (String type) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setTypeResult = activity.setType(type);
                        test.assertSame(activity, setTypeResult);
                        test.assertEqual(type, activity.getType());
                    });
                };

                setTypeTest.run("");
                setTypeTest.run("a");
            });

            runner.testGroup("setDescription(String)", () ->
            {
                final Action2<String,Throwable> setDescriptionErrorTest = (String description, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(description), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setDescription(description), expected);
                        test.assertNull(activity.getDescription());
                    });
                };

                setDescriptionErrorTest.run(null, new PreConditionFailure("description cannot be null."));
                setDescriptionErrorTest.run("", new PreConditionFailure("description cannot be empty."));

                final Action1<String> setDescriptionTest = (String description) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(description), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setDescriptionResult = activity.setDescription(description);
                        test.assertSame(activity, setDescriptionResult);
                        test.assertEqual(description, activity.getDescription());
                    });
                };

                setDescriptionTest.run("a");
            });

            runner.testGroup("setCode(String)", () ->
            {
                final Action2<String,Throwable> setCodeErrorTest = (String code, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(code), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setCode(code), expected);
                        test.assertNull(activity.getCode());
                    });
                };

                setCodeErrorTest.run(null, new PreConditionFailure("code cannot be null."));
                setCodeErrorTest.run("", new PreConditionFailure("code cannot be empty."));

                final Action1<String> setCodeTest = (String code) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(code), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setCodeResult = activity.setCode(code);
                        test.assertSame(activity, setCodeResult);
                        test.assertEqual(code, activity.getCode());
                    });
                };

                setCodeTest.run("a");
            });

            runner.testGroup("setDate(String)", () ->
            {
                final Action2<String,Throwable> setDateErrorTest = (String date, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(date), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setDate(date), expected);
                        test.assertNull(activity.getDate());
                    });
                };

                setDateErrorTest.run(null, new PreConditionFailure("date cannot be null."));
                setDateErrorTest.run("", new PreConditionFailure("date cannot be empty."));

                final Action1<String> setDateTest = (String date) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(date), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setDateResult = activity.setDate(date);
                        test.assertSame(activity, setDateResult);
                        test.assertEqual(date, activity.getDate());
                    });
                };

                setDateTest.run("a");
            });

            runner.testGroup("setTime(String)", () ->
            {
                final Action2<String,Throwable> setTimeErrorTest = (String time, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(time), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        test.assertThrows(() -> activity.setTime(time), expected);
                        test.assertNull(activity.getTime());
                    });
                };

                setTimeErrorTest.run(null, new PreConditionFailure("time cannot be null."));
                setTimeErrorTest.run("", new PreConditionFailure("time cannot be empty."));

                final Action1<String> setTimeTest = (String time) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(time), (Test test) ->
                    {
                        final UPSTrackResponsePackageActivity activity = UPSTrackResponsePackageActivity.create();
                        final UPSTrackResponsePackageActivity setTimeResult = activity.setTime(time);
                        test.assertSame(activity, setTimeResult);
                        test.assertEqual(time, activity.getTime());
                    });
                };

                setTimeTest.run("a");
            });
            
            runner.testGroup("toString()", () ->
            {
                final Action2<UPSTrackResponsePackageActivity,String> toStringTest = (UPSTrackResponsePackageActivity activity, String expected) ->
                {
                    runner.test("with " + activity, (Test test) ->
                    {
                        test.assertEqual(expected, activity.toString());
                    });
                };

                toStringTest.run(
                    UPSTrackResponsePackageActivity.create(),
                    "{}");
                toStringTest.run(
                    UPSTrackResponsePackageActivity.create()
                        .setCity("a")
                        .setStateProvince("b")
                        .setPostalCode("c")
                        .setCountry("d")
                        .setType("e")
                        .setDescription("f")
                        .setCode("g")
                        .setDate("h")
                        .setTime("i"),
                    "{\"location\":{\"address\":{\"city\":\"a\",\"stateProvince\":\"b\",\"postalCode\":\"c\",\"country\":\"d\"}},\"status\":{\"type\":\"e\",\"description\":\"f\",\"code\":\"g\"},\"date\":\"h\",\"time\":\"i\"}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSTrackResponsePackageActivity,Object,Boolean> equalsTest = (UPSTrackResponsePackageActivity activity, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(activity, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, activity.equals(rhs));
                    });
                };

                equalsTest.run(
                    UPSTrackResponsePackageActivity.create(),
                    null,
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageActivity.create(),
                    "hello",
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageActivity.create(),
                    JSONObject.create(),
                    false);
                equalsTest.run(
                    UPSTrackResponsePackageActivity.create(),
                    UPSTrackResponsePackageActivity.create(),
                    true);
            });
        });
    }
}
