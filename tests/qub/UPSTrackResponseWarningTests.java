package qub;

public interface UPSTrackResponseWarningTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSTrackResponseWarning.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create();
                test.assertNotNull(warning, "warning");
                test.assertNull(warning.getCode());
                test.assertNull(warning.getMessage());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSTrackResponseWarning.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with empty", (Test test) ->
                {
                    final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create(JSONObject.create());
                    test.assertNotNull(warning, "warning");
                    test.assertNull(warning.getCode());
                    test.assertNull(warning.getMessage());
                });

                runner.test("with full", (Test test) ->
                {
                    final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create(JSONObject.create()
                        .setString("code", "a")
                        .setString("message", "b"));
                    test.assertNotNull(warning, "warning");
                    test.assertEqual("a", warning.getCode());
                    test.assertEqual("b", warning.getMessage());
                });
            });

            runner.testGroup("setCode(String)", () ->
            {
                final Action2<String,Throwable> setCodeErrorTest = (String code, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(code), (Test test) ->
                    {
                        final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create();
                        test.assertThrows(() -> warning.setCode(code), expected);
                        test.assertNull(warning.getCode());
                    });
                };

                setCodeErrorTest.run(null, new PreConditionFailure("code cannot be null."));
                setCodeErrorTest.run("", new PreConditionFailure("code cannot be empty."));

                final Action1<String> setCodeTest = (String code) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(code), (Test test) ->
                    {
                        final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create();
                        final UPSTrackResponseWarning setCodeResult = warning.setCode(code);
                        test.assertSame(warning, setCodeResult);
                        test.assertEqual(code, warning.getCode());
                    });
                };

                setCodeTest.run("a");
                setCodeTest.run("TW0001");
            });

            runner.testGroup("setMessage(String)", () ->
            {
                final Action2<String,Throwable> setMessageErrorTest = (String message, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(message), (Test test) ->
                    {
                        final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create();
                        test.assertThrows(() -> warning.setMessage(message), expected);
                        test.assertNull(warning.getMessage());
                    });
                };

                setMessageErrorTest.run(null, new PreConditionFailure("message cannot be null."));
                setMessageErrorTest.run("", new PreConditionFailure("message cannot be empty."));

                final Action1<String> setMessageTest = (String message) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(message), (Test test) ->
                    {
                        final UPSTrackResponseWarning warning = UPSTrackResponseWarning.create();
                        final UPSTrackResponseWarning setMessageResult = warning.setMessage(message);
                        test.assertSame(warning, setMessageResult);
                        test.assertEqual(message, warning.getMessage());
                    });
                };

                setMessageTest.run("a");
                setMessageTest.run("Tracking Information Not Found");
            });

            runner.testGroup("toString()", () ->
            {
                final Action2<UPSTrackResponseWarning,String> toStringTest = (UPSTrackResponseWarning warning, String expected) ->
                {
                    runner.test("with " + warning, (Test test) ->
                    {
                        test.assertEqual(expected, warning.toString());
                    });
                };

                toStringTest.run(
                    UPSTrackResponseWarning.create(),
                    "{}");
                toStringTest.run(
                    UPSTrackResponseWarning.create()
                        .setCode("a")
                        .setMessage("b"),
                    "{\"code\":\"a\",\"message\":\"b\"}");
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSTrackResponseWarning,Object,Boolean> equalsTest = (UPSTrackResponseWarning warning, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(warning, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, warning.equals(rhs));
                    });
                };

                equalsTest.run(
                    UPSTrackResponseWarning.create(),
                    null,
                    false);
                equalsTest.run(
                    UPSTrackResponseWarning.create(),
                    "hello",
                    false);
                equalsTest.run(
                    UPSTrackResponseWarning.create(),
                    JSONObject.create(),
                    false);
                equalsTest.run(
                    UPSTrackResponseWarning.create(),
                    UPSTrackResponseWarning.create(),
                    true);
            });
        });
    }
}
