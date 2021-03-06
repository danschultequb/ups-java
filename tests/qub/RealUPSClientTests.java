package qub;

public interface RealUPSClientTests
{
    String fakeAccessLicenseNumber = "fake-access-license-number";
    String accessLicenseNumber = RealUPSClientTests.fakeAccessLicenseNumber;
    Skip skipRealTests = (RealUPSClientTests.fakeAccessLicenseNumber == RealUPSClientTests.accessLicenseNumber)
        ? new Skip("Skipping tests that need a real access license number.")
        : null;

    String upsHost = RealUPSClient.integrationTestingHost;

    static RealUPSClient create(Test test)
    {
        return RealUPSClient.create(test.getNetwork())
            .setHost(RealUPSClientTests.upsHost);
    }

    static void test(TestRunner runner)
    {
        runner.testGroup(RealUPSClient.class, () ->
        {
            UPSClientTests.test(runner, RealUPSClientTests.skipRealTests, RealUPSClientTests::create);

            runner.testGroup("create(Network)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> RealUPSClient.create((Network)null),
                        new PreConditionFailure("network cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final Network network = test.getNetwork();
                    final RealUPSClient upsClient = RealUPSClient.create(network);
                    test.assertNotNull(upsClient);
                    test.assertEqual(RealUPSClient.productionHost, upsClient.getHost());
                    test.assertNull(upsClient.getAccessLicenseNumber());
                });
            });

            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> RealUPSClient.create((HttpClient)null),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final Network network = test.getNetwork();
                    final HttpClient httpClient = HttpClient.create(network);
                    final RealUPSClient upsClient = RealUPSClient.create(httpClient);
                    test.assertNotNull(upsClient);
                    test.assertEqual(RealUPSClient.productionHost, upsClient.getHost());
                    test.assertNull(upsClient.getAccessLicenseNumber());
                });
            });

            runner.testGroup("setHost(String)", () ->
            {
                final Action2<String,Throwable> setHostErrorTest = (String host, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(host), (Test test) ->
                    {
                        final RealUPSClient upsClient = RealUPSClient.create(test.getNetwork());
                        final String originalHost = upsClient.getHost();
                        test.assertThrows(() -> upsClient.setHost(host), expected);
                        test.assertEqual(originalHost, upsClient.getHost());
                    });
                };

                setHostErrorTest.run(null, new PreConditionFailure("host cannot be null."));
                setHostErrorTest.run("", new PreConditionFailure("host cannot be empty."));

                final Action1<String> setHostTest = (String host) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(host), (Test test) ->
                    {
                        final RealUPSClient upsClient = RealUPSClient.create(test.getNetwork());
                        final RealUPSClient setHostResult = upsClient.setHost(host);
                        test.assertSame(upsClient, setHostResult);
                        test.assertEqual(host, upsClient.getHost());
                    });
                };

                setHostTest.run("a");
                setHostTest.run(RealUPSClient.productionHost);
                setHostTest.run(RealUPSClient.integrationTestingHost);
            });

            runner.testGroup("sendTrackRequest(UPSTrackRequest)", () ->
            {
                final Action2<UPSTrackRequest,Action2<Test,UPSErrorResponse>> sendTrackRequestErrorResponseTest = (UPSTrackRequest trackRequest, Action2<Test,UPSErrorResponse> errorResponseValidation) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final UPSClient upsClient = RealUPSClientTests.create(test);
                        errorResponseValidation.run(test, test.assertThrows(() -> upsClient.sendTrackRequest(trackRequest).await(), UPSErrorResponse.class));
                    });
                };

                sendTrackRequestErrorResponseTest.run(
                    UPSTrackRequest.create()
                        .setAccessLicenseNumber("fakeaccesslicensenumber")
                        .setInquiryNumber("1Z5338FF0107231059"),
                    (Test test, UPSErrorResponse errorResponse) ->
                    {
                        test.assertEqual(401, errorResponse.getStatusCode());
                        test.assertEqual("Unauthorized", errorResponse.getReasonPhrase());
                        test.assertEqual("HTTP/1.1", errorResponse.getHttpVersion());
                        test.assertEqual("Invalid Access License number", errorResponse.getErrorDescription());
                        test.assertEqual("250003", errorResponse.getErrorCode());
                        test.assertEqual(
                            JSONObject.create()
                                .setObject("response", JSONObject.create()
                                    .setArray("errors", JSONArray.create()
                                        .add(JSONObject.create()
                                            .setString("code", "250003")
                                            .setString("message", "Invalid Access License number")))),
                            errorResponse.getBodyJson());
                    });
            });
        });
    }
}
