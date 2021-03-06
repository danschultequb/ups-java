package qub;

public interface UPSClientTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(UPSClient.class, () ->
        {
            runner.testGroup("create(Network)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSClient.create((Network)null),
                        new PreConditionFailure("network cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final Network network = test.getNetwork();
                    final RealUPSClient upsClient = UPSClient.create(network);
                    test.assertNotNull(upsClient);
                    test.assertEqual(RealUPSClient.productionHost, upsClient.getHost());
                    test.assertNull(upsClient.getAccessLicenseNumber());
                });
            });

            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> UPSClient.create((HttpClient)null),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final Network network = test.getNetwork();
                    final HttpClient httpClient = HttpClient.create(network);
                    final RealUPSClient upsClient = UPSClient.create(httpClient);
                    test.assertNotNull(upsClient);
                    test.assertEqual(RealUPSClient.productionHost, upsClient.getHost());
                    test.assertNull(upsClient.getAccessLicenseNumber());
                });
            });
        });
    }

    static void test(TestRunner runner, Function1<Test,? extends UPSClient> creator)
    {
        UPSClientTests.test(runner, null, creator);
    }

    static void test(TestRunner runner, Skip skipRealTests, Function1<Test,? extends UPSClient> creator)
    {
        runner.testGroup(UPSClient.class, () ->
        {
            runner.testGroup("sendTrackRequest(UPSTrackRequest)", () ->
            {
                final Action2<UPSTrackRequest,Throwable> sendTrackRequestErrorTest = (UPSTrackRequest trackRequest, Throwable expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final UPSClient upsClient = creator.run(test);
                        test.assertThrows(() -> upsClient.sendTrackRequest(trackRequest),
                            expected);
                    });
                };

                sendTrackRequestErrorTest.run(
                    null,
                    new PreConditionFailure("trackRequest cannot be null."));
                sendTrackRequestErrorTest.run(
                    UPSTrackRequest.create(),
                    new PreConditionFailure("!Strings.isNullOrEmpty(trackRequest.getAccessLicenseNumber()) || !Strings.isNullOrEmpty(this.getAccessLicenseNumber()) cannot be false."));
                sendTrackRequestErrorTest.run(
                    UPSTrackRequest.create()
                        .setAccessLicenseNumber("a"),
                    new PreConditionFailure("trackRequest.getInquiryNumber() cannot be null."));

                runner.testGroup("with real access license number", skipRealTests, () ->
                {
                    final Action2<UPSTrackRequest,Action2<Test,UPSTrackResponse>> sendTrackRequestTest = (UPSTrackRequest trackRequest, Action2<Test,UPSTrackResponse> trackResponseValidation) ->
                    {
                        runner.test("with " + trackRequest, (Test test) ->
                        {
                            final UPSClient upsClient = creator.run(test);
                            final UPSTrackResponse trackResponse = upsClient.sendTrackRequest(trackRequest).await();
                            test.assertNotNull(trackResponse);
                            test.assertEqual(200, trackResponse.getStatusCode());
                            test.assertEqual("OK", trackResponse.getReasonPhrase());
                            test.assertEqual("HTTP/1.1", trackResponse.getHttpVersion());
                            trackResponseValidation.run(test, trackResponse);
                        });
                    };

                    sendTrackRequestTest.run(
                        UPSTrackRequest.create()
                            .setAccessLicenseNumber(RealUPSClientTests.accessLicenseNumber)
                            .setInquiryNumber("fakeinquirynumber"),
                        (Test test, UPSTrackResponse trackResponse) ->
                        {
                            test.assertEqual(
                                JSONObject.create()
                                    .setObject("trackResponse", JSONObject.create()
                                        .setArray("shipment", JSONArray.create()
                                            .add(JSONObject.create()
                                                .setArray("warnings", JSONArray.create()
                                                    .add(JSONObject.create()
                                                        .setString("code", "TW0001")
                                                        .setString("message", "Tracking Information Not Found")))))),
                                trackResponse.getBodyJson());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponseWarning.create()
                                        .setCode("TW0001")
                                        .setMessage("Tracking Information Not Found")),
                                trackResponse.getWarnings());
                            test.assertEqual(null, trackResponse.getTrackingNumber());
                            test.assertEqual(Iterable.create(), trackResponse.getDeliveryDates());
                            test.assertEqual(Iterable.create(), trackResponse.getActivities());
                        });

                    sendTrackRequestTest.run(
                        UPSTrackRequest.create()
                            .setAccessLicenseNumber(RealUPSClientTests.accessLicenseNumber)
                            .setInquiryNumber("1Z5338FF0107231059"),
                        (Test test, UPSTrackResponse trackResponse) ->
                        {
                            test.assertEqual(
                                JSONObject.create()
                                    .setObject("trackResponse", JSONObject.create()
                                        .setArray("shipment", JSONArray.create()
                                            .add(JSONObject.create()
                                                .setArray("package", JSONArray.create()
                                                    .add(JSONObject.create()
                                                        .setString("trackingNumber", "1Z5338FF0107231059")
                                                        .setArray("activity", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "BALDWIN")
                                                                        .setString("stateProvince", "MD")
                                                                        .setString("postalCode", "")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "X")
                                                                    .setString("description", "DeliveryAttempted")
                                                                    .setString("code", "48"))
                                                                .setString("date", "20191121")
                                                                .setString("time", "140400"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Sparks")
                                                                        .setString("stateProvince", "MD")
                                                                        .setString("postalCode", "")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "X")
                                                                    .setString("description", "Thereceiverwasnotavailablefordelivery.We'llmakeasecondattemptthenextbusinessday.")
                                                                    .setString("code", "48"))
                                                                .setString("date", "20191121")
                                                                .setString("time", "135800"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "")
                                                                        .setString("stateProvince", "")
                                                                        .setString("postalCode", "")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "M")
                                                                    .setString("description", "OrderProcessed:ReadyforUPS")
                                                                    .setString("code", "MP"))
                                                                .setString("date", "20191119")
                                                                .setString("time", "132642"))
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                trackResponse.getBodyJson());
                            test.assertEqual(Iterable.create(), trackResponse.getWarnings());
                            test.assertEqual("1Z5338FF0107231059", trackResponse.getTrackingNumber());
                            test.assertEqual(
                                Iterable.create(),
                                trackResponse.getDeliveryDates());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("BALDWIN")
                                        .setStateProvince("MD")
                                        .setPostalCode("")
                                        .setCountry("US")
                                        .setType("X")
                                        .setDescription("DeliveryAttempted")
                                        .setCode("48")
                                        .setDate("20191121")
                                        .setTime("140400"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Sparks")
                                        .setStateProvince("MD")
                                        .setPostalCode("")
                                        .setCountry("US")
                                        .setType("X")
                                        .setDescription("Thereceiverwasnotavailablefordelivery.We'llmakeasecondattemptthenextbusinessday.")
                                        .setCode("48")
                                        .setDate("20191121")
                                        .setTime("135800"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("")
                                        .setStateProvince("")
                                        .setPostalCode("")
                                        .setCountry("US")
                                        .setType("M")
                                        .setDescription("OrderProcessed:ReadyforUPS")
                                        .setCode("MP")
                                        .setDate("20191119")
                                        .setTime("132642")),
                                trackResponse.getActivities());
                        });

                    sendTrackRequestTest.run(
                        UPSTrackRequest.create()
                            .setAccessLicenseNumber(RealUPSClientTests.accessLicenseNumber)
                            .setInquiryNumber("92055900100111152280003029"),
                        (Test test, UPSTrackResponse trackResponse) ->
                        {
                            test.assertEqual(
                                JSONObject.create()
                                    .setObject("trackResponse", JSONObject.create()
                                        .setArray("shipment", JSONArray.create()
                                            .add(JSONObject.create()
                                                .setArray("package", JSONArray.create()
                                                    .add(JSONObject.create()
                                                        .setString("trackingNumber", "92055900100111152280003029")
                                                        .setArray("deliveryDate", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setString("type", "SDD")
                                                                .setString("date", "20200123")))
                                                        .setArray("activity", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Butner")
                                                                        .setString("stateProvince", "NC")
                                                                        .setString("postalCode", "27509")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "PostagePaid/Readyfordestinationpostofficeentry")
                                                                    .setString("code", "ZW"))
                                                                .setString("date", "20200116")
                                                                .setString("time", "103700"))
                                                            .add(JSONObject.create()
                                                              .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Butner")
                                                                        .setString("stateProvince", "NC")
                                                                        .setString("postalCode", "27509")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "PackagedepartedUPSMailInnovationsfacilityenroutetoUSPSforinduction")
                                                                    .setString("code", "ZR"))
                                                                .setString("date", "20200116")
                                                                .setString("time", "103700"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Butner")
                                                                        .setString("stateProvince", "NC")
                                                                        .setString("postalCode", "27509")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "PackageprocessedbyUPSMailInnovationsoriginfacility")
                                                                    .setString("code", "ZT"))
                                                                .setString("date", "20200116")
                                                                .setString("time", "103400"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Butner")
                                                                        .setString("stateProvince", "NC")
                                                                        .setString("postalCode", "27509")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "PackagereceivedforprocessingbyUPSMailInnovations")
                                                                    .setString("code", "ZS"))
                                                                .setString("date", "20200116")
                                                                .setString("time", "095500"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "DURHAM")
                                                                        .setString("stateProvince", "NC")
                                                                        .setString("postalCode", "27713")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "P")
                                                                    .setString("description", "ShipmenttenderedtoUPSMailInnovations")
                                                                    .setString("code", "VS"))
                                                                .setString("date", "20200116")
                                                                .setString("time", "020200")))))))),
                                trackResponse.getBodyJson());
                            test.assertEqual(Iterable.create(), trackResponse.getWarnings());
                            test.assertEqual("92055900100111152280003029", trackResponse.getTrackingNumber());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageDeliveryDate.create()
                                        .setType("SDD")
                                        .setDate("20200123")),
                                trackResponse.getDeliveryDates());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Butner")
                                        .setStateProvince("NC")
                                        .setPostalCode("27509")
                                        .setCountry("US")
                                        .setType("I")
                                        .setDescription("PostagePaid/Readyfordestinationpostofficeentry")
                                        .setCode("ZW")
                                        .setDate("20200116")
                                        .setTime("103700"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Butner")
                                        .setStateProvince("NC")
                                        .setPostalCode("27509")
                                        .setCountry("US")
                                        .setType("I")
                                        .setDescription("PackagedepartedUPSMailInnovationsfacilityenroutetoUSPSforinduction")
                                        .setCode("ZR")
                                        .setDate("20200116")
                                        .setTime("103700"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Butner")
                                        .setStateProvince("NC")
                                        .setPostalCode("27509")
                                        .setCountry("US")
                                        .setType("I")
                                        .setDescription("PackageprocessedbyUPSMailInnovationsoriginfacility")
                                        .setCode("ZT")
                                        .setDate("20200116")
                                        .setTime("103400"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Butner")
                                        .setStateProvince("NC")
                                        .setPostalCode("27509")
                                        .setCountry("US")
                                        .setType("I")
                                        .setDescription("PackagereceivedforprocessingbyUPSMailInnovations")
                                        .setCode("ZS")
                                        .setDate("20200116")
                                        .setTime("095500"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("DURHAM")
                                        .setStateProvince("NC")
                                        .setPostalCode("27713")
                                        .setCountry("US")
                                        .setType("P")
                                        .setDescription("ShipmenttenderedtoUPSMailInnovations")
                                        .setCode("VS")
                                        .setDate("20200116")
                                        .setTime("020200")),
                                trackResponse.getActivities());
                        });

                    sendTrackRequestTest.run(
                        UPSTrackRequest.create()
                            .setAccessLicenseNumber(RealUPSClientTests.accessLicenseNumber)
                            .setInquiryNumber("7798339175"),
                        (Test test, UPSTrackResponse trackResponse) ->
                        {
                            test.assertEqual(
                                JSONObject.create()
                                    .setObject("trackResponse", JSONObject.create()
                                        .setArray("shipment", JSONArray.create()
                                            .add(JSONObject.create()
                                                .setArray("package", JSONArray.create()
                                                    .add(JSONObject.create()
                                                        .setString("trackingNumber", "7798339175")
                                                        .setArray("deliveryDate", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setString("type", "SDD")
                                                                .setString("date", "20200721")))
                                                        .setArray("activity", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Vancouver")
                                                                        .setString("stateProvince", "BC")
                                                                        .setString("country", "CA")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "ETA at Port of Discharge")
                                                                    .setString("code", "006"))
                                                                .setString("date", "20200706")
                                                                .setString("time", "000100"))
                                                            .add(JSONObject.create()
                                                              .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Tokyo")
                                                                        .setString("stateProvince", "13")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Documents received from shipper")
                                                                    .setString("code", "045"))
                                                                .setString("date", "20200615")
                                                                .setString("time", "102702"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Tokyo")
                                                                        .setString("stateProvince", "13")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Date Available to Ship")
                                                                    .setString("code", "003"))
                                                                .setString("date", "20200615")
                                                                .setString("time", "102702"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Kobe")
                                                                        .setString("stateProvince", "28")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "ETD from Load Port")
                                                                    .setString("code", "004"))
                                                                .setString("date", "20200614")
                                                                .setString("time", "141800"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Kobe")
                                                                        .setString("stateProvince", "28")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Departure")
                                                                    .setString("code", "005"))
                                                                .setString("date", "20200614")
                                                                .setString("time", "141800"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Osaka")
                                                                        .setString("stateProvince", "27")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Planned pickup date")
                                                                    .setString("code", "072"))
                                                                .setString("date", "20200611")
                                                                .setString("time", "100000"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Tokyo")
                                                                        .setString("stateProvince", "13")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Received into UPS possession")
                                                                    .setString("code", "002"))
                                                                .setString("date", "20200611")
                                                                .setString("time", "100000"))
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Tokyo")
                                                                        .setString("stateProvince", "13")
                                                                        .setString("country", "JP")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "")
                                                                    .setString("description", "Booking Received from Client")
                                                                    .setString("code", "037"))
                                                                .setString("date", "20200604")
                                                                .setString("time", "023812")))))))),
                                trackResponse.getBodyJson());
                            test.assertEqual(Iterable.create(), trackResponse.getWarnings());
                            test.assertEqual("7798339175", trackResponse.getTrackingNumber());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageDeliveryDate.create()
                                        .setType("SDD")
                                        .setDate("20200721")),
                                trackResponse.getDeliveryDates());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Vancouver")
                                        .setStateProvince("BC")
                                        .setCountry("CA")
                                        .setType("I")
                                        .setDescription("ETA at Port of Discharge")
                                        .setCode("006")
                                        .setDate("20200706")
                                        .setTime("000100"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Tokyo")
                                        .setStateProvince("13")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Documents received from shipper")
                                        .setCode("045")
                                        .setDate("20200615")
                                        .setTime("102702"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Tokyo")
                                        .setStateProvince("13")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Date Available to Ship")
                                        .setCode("003")
                                        .setDate("20200615")
                                        .setTime("102702"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Kobe")
                                        .setStateProvince("28")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("ETD from Load Port")
                                        .setCode("004")
                                        .setDate("20200614")
                                        .setTime("141800"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Kobe")
                                        .setStateProvince("28")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Departure")
                                        .setCode("005")
                                        .setDate("20200614")
                                        .setTime("141800"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Osaka")
                                        .setStateProvince("27")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Planned pickup date")
                                        .setCode("072")
                                        .setDate("20200611")
                                        .setTime("100000"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Tokyo")
                                        .setStateProvince("13")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Received into UPS possession")
                                        .setCode("002")
                                        .setDate("20200611")
                                        .setTime("100000"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Tokyo")
                                        .setStateProvince("13")
                                        .setCountry("JP")
                                        .setType("")
                                        .setDescription("Booking Received from Client")
                                        .setCode("037")
                                        .setDate("20200604")
                                        .setTime("023812")),
                                trackResponse.getActivities());
                        });

                    sendTrackRequestTest.run(
                        UPSTrackRequest.create()
                            .setAccessLicenseNumber(RealUPSClientTests.accessLicenseNumber)
                            .setInquiryNumber("572254454"),
                        (Test test, UPSTrackResponse trackResponse) ->
                        {
                            test.assertEqual(
                                JSONObject.create()
                                    .setObject("trackResponse", JSONObject.create()
                                        .setArray("shipment", JSONArray.create()
                                            .add(JSONObject.create()
                                                .setArray("package", JSONArray.create()
                                                    .add(JSONObject.create()
                                                        .setString("trackingNumber", "572254454")
                                                        .setArray("deliveryDate", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setString("type", "DEL")
                                                                .setString("date", "20191207"))
                                                            .add(JSONObject.create()
                                                                .setString("type", "SDD")
                                                                .setString("date", "20191210")))
                                                        .setObject("deliveryTime", JSONObject.create()
                                                            .setString("startTime", "")
                                                            .setString("endTime", "")
                                                            .setString("type", "EOD"))
                                                        .setArray("activity", JSONArray.create()
                                                            .add(JSONObject.create()
                                                                .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Truckload")
                                                                        .setString("stateProvince", "VA")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "D")
                                                                    .setString("description", "Shipmenthasbeendeliveredtoconsignee"))
                                                                .setString("date", "20191207")
                                                                .setString("time", "104500"))
                                                            .add(JSONObject.create()
                                                              .setObject("location", JSONObject.create()
                                                                    .setObject("address", JSONObject.create()
                                                                        .setString("city", "Truckload")
                                                                        .setString("stateProvince", "VA")
                                                                        .setString("country", "US")))
                                                                .setObject("status", JSONObject.create()
                                                                    .setString("type", "I")
                                                                    .setString("description", "Shipmenthasbeenpickedup"))
                                                                .setString("date", "20191207")
                                                                .setString("time", "080000")))))))),
                                trackResponse.getBodyJson());
                            test.assertEqual(Iterable.create(), trackResponse.getWarnings());
                            test.assertEqual("572254454", trackResponse.getTrackingNumber());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageDeliveryDate.create()
                                        .setType("DEL")
                                        .setDate("20191207"),
                                    UPSTrackResponsePackageDeliveryDate.create()
                                        .setType("SDD")
                                        .setDate("20191210")),
                                trackResponse.getDeliveryDates());
                            test.assertEqual(
                                Iterable.create(
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Truckload")
                                        .setStateProvince("VA")
                                        .setCountry("US")
                                        .setType("D")
                                        .setDescription("Shipmenthasbeendeliveredtoconsignee")
                                        .setDate("20191207")
                                        .setTime("104500"),
                                    UPSTrackResponsePackageActivity.create()
                                        .setCity("Truckload")
                                        .setStateProvince("VA")
                                        .setCountry("US")
                                        .setType("I")
                                        .setDescription("Shipmenthasbeenpickedup")
                                        .setDate("20191207")
                                        .setTime("080000")),
                                trackResponse.getActivities());
                        });
                });
            });
        });
    }
}