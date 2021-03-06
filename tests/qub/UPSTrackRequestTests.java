package qub;

public interface UPSTrackRequestTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSTrackRequest.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                test.assertNotNull(trackRequest);
                test.assertNull(trackRequest.getTransId());
                test.assertNull(trackRequest.getTransactionSrc());
                test.assertNull(trackRequest.getAccessLicenseNumber());
                test.assertNull(trackRequest.getUsername());
                test.assertNull(trackRequest.getPassword());
                test.assertNull(trackRequest.getAuthenticationToken());
                test.assertNull(trackRequest.getAuthorization());
                test.assertNull(trackRequest.getInquiryNumber());
                test.assertNull(trackRequest.getLocale());
            });

            runner.testGroup("setTransId(String)", () ->
            {
                final Action2<String,Throwable> setTransIdErrorTest = (String transId, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(transId), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setTransId(transId), expected);
                        test.assertNull(trackRequest.getTransId());
                    });
                };

                setTransIdErrorTest.run(null, new PreConditionFailure("transId cannot be null."));
                setTransIdErrorTest.run("", new PreConditionFailure("transId cannot be empty."));

                final Action1<String> setTransIdTest = (String transId) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(transId), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setTransIdResult = trackRequest.setTransId(transId);
                        test.assertSame(trackRequest, setTransIdResult);
                        test.assertEqual(transId, trackRequest.getTransId());
                    });
                };

                setTransIdTest.run("a");
            });

            runner.testGroup("setTransactionSrc(String)", () ->
            {
                final Action2<String,Throwable> setTransactionSrcErrorTest = (String transactionSrc, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(transactionSrc), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setTransactionSrc(transactionSrc), expected);
                        test.assertNull(trackRequest.getTransactionSrc());
                    });
                };

                setTransactionSrcErrorTest.run(null, new PreConditionFailure("transactionSrc cannot be null."));
                setTransactionSrcErrorTest.run("", new PreConditionFailure("transactionSrc cannot be empty."));

                final Action1<String> setTransactionSrcTest = (String transactionSrc) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(transactionSrc), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setTransactionSrcResult = trackRequest.setTransactionSrc(transactionSrc);
                        test.assertSame(trackRequest, setTransactionSrcResult);
                        test.assertEqual(transactionSrc, trackRequest.getTransactionSrc());
                    });
                };

                setTransactionSrcTest.run("a");
            });

            runner.testGroup("setAccessLicenseNumber(String)", () ->
            {
                final Action2<String,Throwable> setAccessLicenseNumberErrorTest = (String accessLicenseNumber, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(accessLicenseNumber), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setAccessLicenseNumber(accessLicenseNumber), expected);
                        test.assertNull(trackRequest.getAccessLicenseNumber());
                    });
                };

                setAccessLicenseNumberErrorTest.run(null, new PreConditionFailure("accessLicenseNumber cannot be null."));
                setAccessLicenseNumberErrorTest.run("", new PreConditionFailure("accessLicenseNumber cannot be empty."));

                final Action1<String> setAccessLicenseNumberTest = (String accessLicenseNumber) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(accessLicenseNumber), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setAccessLicenseNumberResult = trackRequest.setAccessLicenseNumber(accessLicenseNumber);
                        test.assertSame(trackRequest, setAccessLicenseNumberResult);
                        test.assertEqual(accessLicenseNumber, trackRequest.getAccessLicenseNumber());
                    });
                };

                setAccessLicenseNumberTest.run("a");
            });

            runner.testGroup("setUsername(String)", () ->
            {
                final Action2<String,Throwable> setUsernameErrorTest = (String username, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(username), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setUsername(username), expected);
                        test.assertNull(trackRequest.getUsername());
                    });
                };

                setUsernameErrorTest.run(null, new PreConditionFailure("username cannot be null."));
                setUsernameErrorTest.run("", new PreConditionFailure("username cannot be empty."));

                final Action1<String> setUsernameTest = (String username) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(username), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setUsernameResult = trackRequest.setUsername(username);
                        test.assertSame(trackRequest, setUsernameResult);
                        test.assertEqual(username, trackRequest.getUsername());
                    });
                };

                setUsernameTest.run("a");
            });

            runner.testGroup("setPassword(String)", () ->
            {
                final Action2<String,Throwable> setPasswordErrorTest = (String password, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(password), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setPassword(password), expected);
                        test.assertNull(trackRequest.getPassword());
                    });
                };

                setPasswordErrorTest.run(null, new PreConditionFailure("password cannot be null."));
                setPasswordErrorTest.run("", new PreConditionFailure("password cannot be empty."));

                final Action1<String> setPasswordTest = (String password) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(password), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setPasswordResult = trackRequest.setPassword(password);
                        test.assertSame(trackRequest, setPasswordResult);
                        test.assertEqual(password, trackRequest.getPassword());
                    });
                };

                setPasswordTest.run("a");
            });

            runner.testGroup("setAuthenticationToken(String)", () ->
            {
                final Action2<String,Throwable> setAuthenticationTokenErrorTest = (String authenticationToken, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authenticationToken), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setAuthenticationToken(authenticationToken), expected);
                        test.assertNull(trackRequest.getAuthenticationToken());
                    });
                };

                setAuthenticationTokenErrorTest.run(null, new PreConditionFailure("authenticationToken cannot be null."));
                setAuthenticationTokenErrorTest.run("", new PreConditionFailure("authenticationToken cannot be empty."));

                final Action1<String> setAuthenticationTokenTest = (String authenticationToken) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authenticationToken), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setAuthenticationTokenResult = trackRequest.setAuthenticationToken(authenticationToken);
                        test.assertSame(trackRequest, setAuthenticationTokenResult);
                        test.assertEqual(authenticationToken, trackRequest.getAuthenticationToken());
                    });
                };

                setAuthenticationTokenTest.run("a");
            });

            runner.testGroup("setAuthorization(String)", () ->
            {
                final Action2<String,Throwable> setAuthorizationErrorTest = (String authorization, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authorization), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setAuthorization(authorization), expected);
                        test.assertNull(trackRequest.getAuthorization());
                    });
                };

                setAuthorizationErrorTest.run(null, new PreConditionFailure("authorization cannot be null."));
                setAuthorizationErrorTest.run("", new PreConditionFailure("authorization cannot be empty."));

                final Action1<String> setAuthorizationTest = (String authorization) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authorization), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setAuthorizationResult = trackRequest.setAuthorization(authorization);
                        test.assertSame(trackRequest, setAuthorizationResult);
                        test.assertEqual(authorization, trackRequest.getAuthorization());
                    });
                };

                setAuthorizationTest.run("a");
            });

            runner.testGroup("setInquiryNumber(String)", () ->
            {
                final Action2<String,Throwable> setInquiryNumberErrorTest = (String inquiryNumber, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(inquiryNumber), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setInquiryNumber(inquiryNumber), expected);
                        test.assertNull(trackRequest.getInquiryNumber());
                    });
                };

                setInquiryNumberErrorTest.run(null, new PreConditionFailure("inquiryNumber cannot be null."));
                setInquiryNumberErrorTest.run("", new PreConditionFailure("inquiryNumber cannot be empty."));

                final Action1<String> setInquiryNumberTest = (String inquiryNumber) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(inquiryNumber), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setInquiryNumberResult = trackRequest.setInquiryNumber(inquiryNumber);
                        test.assertSame(trackRequest, setInquiryNumberResult);
                        test.assertEqual(inquiryNumber, trackRequest.getInquiryNumber());
                    });
                };

                setInquiryNumberTest.run("a");
            });

            runner.testGroup("setLocale(String)", () ->
            {
                final Action2<String,Throwable> setLocaleErrorTest = (String locale, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(locale), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        test.assertThrows(() -> trackRequest.setLocale(locale), expected);
                        test.assertNull(trackRequest.getLocale());
                    });
                };

                setLocaleErrorTest.run(null, new PreConditionFailure("locale cannot be null."));
                setLocaleErrorTest.run("", new PreConditionFailure("locale cannot be empty."));

                final Action1<String> setLocaleTest = (String locale) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(locale), (Test test) ->
                    {
                        final UPSTrackRequest trackRequest = UPSTrackRequest.create();
                        final UPSTrackRequest setLocaleResult = trackRequest.setLocale(locale);
                        test.assertSame(trackRequest, setLocaleResult);
                        test.assertEqual(locale, trackRequest.getLocale());
                    });
                };

                setLocaleTest.run("a");
            });

            runner.testGroup("toJson()", () ->
            {
                final Action2<UPSTrackRequest,JSONObject> toJsonTest = (UPSTrackRequest trackRequest, JSONObject expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        test.assertEqual(expected, trackRequest.toJson());
                    });
                };

                toJsonTest.run(
                    UPSTrackRequest.create(),
                    JSONObject.create());
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setTransId("fake-trans-id"),
                    JSONObject.create()
                        .setString("transId", "fake-trans-id"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setTransactionSrc("fake-transaction-src"),
                    JSONObject.create()
                        .setString("transactionSrc", "fake-transaction-src"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setAccessLicenseNumber("fake-access-license-number"),
                    JSONObject.create()
                        .setString("accessLicenseNumber", "fake-access-license-number"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setUsername("fake-username"),
                    JSONObject.create()
                        .setString("username", "fake-username"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setPassword("fake-password"),
                    JSONObject.create()
                        .setString("password", "fake-password"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setAuthenticationToken("fake-authentication-token"),
                    JSONObject.create()
                        .setString("authenticationToken", "fake-authentication-token"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setAuthorization("fake-authorization"),
                    JSONObject.create()
                        .setString("authorization", "fake-authorization"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setInquiryNumber("fake-inquiry-number"),
                    JSONObject.create()
                        .setString("inquiryNumber", "fake-inquiry-number"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setLocale("fake-locale"),
                    JSONObject.create()
                        .setString("locale", "fake-locale"));
                toJsonTest.run(
                    UPSTrackRequest.create()
                        .setTransId("fake-trans-id")
                        .setTransactionSrc("fake-transaction-src")
                        .setAccessLicenseNumber("fake-access-license-number")
                        .setUsername("fake-username")
                        .setPassword("fake-password")
                        .setAuthenticationToken("fake-authentication-token")
                        .setAuthorization("fake-authorization")
                        .setInquiryNumber("fake-inquiry-number")
                        .setLocale("fake-locale"),
                    JSONObject.create()
                        .setString("transId", "fake-trans-id")
                        .setString("transactionSrc", "fake-transaction-src")
                        .setString("accessLicenseNumber", "fake-access-license-number")
                        .setString("username", "fake-username")
                        .setString("password", "fake-password")
                        .setString("authenticationToken", "fake-authentication-token")
                        .setString("authorization", "fake-authorization")
                        .setString("inquiryNumber", "fake-inquiry-number")
                        .setString("locale", "fake-locale"));
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSTrackRequest,Object,Boolean> equalsTest = (UPSTrackRequest trackRequest, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(trackRequest, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, trackRequest.equals(rhs));
                    });
                };

                equalsTest.run(
                    UPSTrackRequest.create(),
                    null,
                    false);
                equalsTest.run(
                    UPSTrackRequest.create(),
                    "hello",
                    false);
                equalsTest.run(
                    UPSTrackRequest.create(),
                    JSONObject.create(),
                    false);
                equalsTest.run(
                    UPSTrackRequest.create(),
                    UPSTrackRequest.create(),
                    true);
                equalsTest.run(
                    UPSTrackRequest.create()
                        .setInquiryNumber("a")
                        .setLocale("b"),
                    UPSTrackRequest.create()
                        .setLocale("b")
                        .setInquiryNumber("a"),
                    true);
            });
        });
    }
}
