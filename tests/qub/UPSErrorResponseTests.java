package qub;

public interface UPSErrorResponseTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSErrorResponse.class, () ->
        {
            runner.test("constructor()", (Test test) ->
            {
                final UPSErrorResponse errorResponse = new UPSErrorResponse();
                test.assertNotNull(errorResponse);
                test.assertEqual(0, errorResponse.getStatusCode());
                test.assertEqual(null, errorResponse.getReasonPhrase());
                test.assertEqual(null, errorResponse.getHttpVersion());
                test.assertEqual(null, errorResponse.getHeaders());
                test.assertEqual(null, errorResponse.getBodyJson());
            });

            runner.testGroup("setStatusCode(int)", () ->
            {
                final Action1<Integer> setStatusCodeTest = (Integer statusCode) ->
                {
                    runner.test("with " + statusCode, (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        final UPSErrorResponse setStatusCodeResult = errorResponse.setStatusCode(statusCode);
                        test.assertSame(errorResponse, setStatusCodeResult);
                        test.assertEqual(statusCode, errorResponse.getStatusCode());
                        test.assertNull(errorResponse.getReasonPhrase());
                    });
                };

                setStatusCodeTest.run(1);
                setStatusCodeTest.run(200);
                setStatusCodeTest.run(400);
                setStatusCodeTest.run(401);
            });

            runner.testGroup("setReasonPhrase(String)", () ->
            {
                final Action2<String,Throwable> setReasonPhraseErrorTest = (String reasonPhrase, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(reasonPhrase), (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        test.assertThrows(() -> errorResponse.setReasonPhrase(reasonPhrase), expected);
                        test.assertNull(errorResponse.getReasonPhrase());
                        test.assertEqual(0, errorResponse.getStatusCode());
                    });
                };

                setReasonPhraseErrorTest.run(null, new PreConditionFailure("reasonPhrase cannot be null."));
                setReasonPhraseErrorTest.run("", new PreConditionFailure("reasonPhrase cannot be empty."));

                final Action1<String> setReasonPhraseTest = (String reasonPhrase) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(reasonPhrase), (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        final UPSErrorResponse setReasonPhraseResult = errorResponse.setReasonPhrase(reasonPhrase);
                        test.assertSame(errorResponse, setReasonPhraseResult);
                        test.assertEqual(reasonPhrase, errorResponse.getReasonPhrase());
                        test.assertEqual(0, errorResponse.getStatusCode());
                    });
                };

                setReasonPhraseTest.run("a");
                setReasonPhraseTest.run("OK");
                setReasonPhraseTest.run("Unauthorized");
            });

            runner.testGroup("setHttpVersion(String)", () ->
            {
                final Action2<String,Throwable> setHttpVersionErrorTest = (String httpVersion, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(httpVersion), (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        test.assertThrows(() -> errorResponse.setHttpVersion(httpVersion), expected);
                        test.assertNull(errorResponse.getHttpVersion());
                    });
                };

                setHttpVersionErrorTest.run(null, new PreConditionFailure("httpVersion cannot be null."));
                setHttpVersionErrorTest.run("", new PreConditionFailure("httpVersion cannot be empty."));

                final Action1<String> setHttpVersionTest = (String httpVersion) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(httpVersion), (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        final UPSErrorResponse setHttpVersionResult = errorResponse.setHttpVersion(httpVersion);
                        test.assertSame(errorResponse, setHttpVersionResult);
                        test.assertEqual(httpVersion, errorResponse.getHttpVersion());
                    });
                };

                setHttpVersionTest.run("a");
                setHttpVersionTest.run("HTTP/1.0");
                setHttpVersionTest.run("HTTP/1.1");
                setHttpVersionTest.run("HTTP/2.0");
            });

            runner.testGroup("setHeaders(HttpHeaders)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UPSErrorResponse errorResponse = new UPSErrorResponse();
                    test.assertThrows(() -> errorResponse.setHeaders(null),
                        new PreConditionFailure("headers cannot be null."));
                    test.assertNull(errorResponse.getHeaders());
                });

                runner.test("with empty", (Test test) ->
                {
                    final UPSErrorResponse errorResponse = new UPSErrorResponse();
                    final UPSErrorResponse setHeadersResult = errorResponse.setHeaders(HttpHeaders.create());
                    test.assertSame(errorResponse, setHeadersResult);
                    test.assertEqual(HttpHeaders.create(), errorResponse.getHeaders());
                });
            });

            runner.testGroup("getErrorDescription()", () ->
            {
                final Action2<HttpHeaders,String> getErrorDescriptionTest = (HttpHeaders headers, String expected) ->
                {
                    runner.test("with " + headers, (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        if (headers != null)
                        {
                            errorResponse.setHeaders(headers);
                        }
                        test.assertEqual(expected, errorResponse.getErrorDescription());
                    });
                };

                getErrorDescriptionTest.run(
                    null,
                    null);
                getErrorDescriptionTest.run(
                    HttpHeaders.create(),
                    null);
                getErrorDescriptionTest.run(
                    HttpHeaders.create()
                        .set("ErrorDescription", "a"),
                    "a");
            });

            runner.testGroup("getErrorCode()", () ->
            {
                final Action2<HttpHeaders,String> getErrorCodeTest = (HttpHeaders headers, String expected) ->
                {
                    runner.test("with " + headers, (Test test) ->
                    {
                        final UPSErrorResponse errorResponse = new UPSErrorResponse();
                        if (headers != null)
                        {
                            errorResponse.setHeaders(headers);
                        }
                        test.assertEqual(expected, errorResponse.getErrorCode());
                    });
                };

                getErrorCodeTest.run(
                    null,
                    null);
                getErrorCodeTest.run(
                    HttpHeaders.create(),
                    null);
                getErrorCodeTest.run(
                    HttpHeaders.create()
                        .set("ErrorCode", "a"),
                    "a");
            });

            runner.testGroup("setBodyJson(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UPSErrorResponse errorResponse = new UPSErrorResponse();
                    test.assertThrows(() -> errorResponse.setBodyJson(null),
                        new PreConditionFailure("bodyJson cannot be null."));
                    test.assertNull(errorResponse.getBodyJson());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final UPSErrorResponse errorResponse = new UPSErrorResponse();
                    final UPSErrorResponse setBodyJsonResult = errorResponse.setBodyJson(JSONObject.create());
                    test.assertSame(errorResponse, setBodyJsonResult);
                    test.assertEqual(JSONObject.create(), errorResponse.getBodyJson());
                });
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<UPSErrorResponse,Object,Boolean> equalsTest = (UPSErrorResponse errorResponse, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(errorResponse, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, errorResponse.equals(rhs));
                    });
                };

                equalsTest.run(
                    new UPSErrorResponse(),
                    null,
                    false);
                equalsTest.run(
                    new UPSErrorResponse(),
                    "hello",
                    false);
                equalsTest.run(
                    new UPSErrorResponse(),
                    UPSTrackResponse.create(),
                    false);
                equalsTest.run(
                    new UPSErrorResponse(),
                    new UPSErrorResponse(),
                    true);
            });
        });
    }
}
