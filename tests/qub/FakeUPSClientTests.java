package qub;

public interface FakeUPSClientTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(FakeUPSClient.class, () ->
        {
            UPSClientTests.test(runner, (Test test) ->
            {
                return FakeUPSClient.create()
                    .setResponse("fakeinquirynumber", UPSTrackResponse.create()
                        .setStatusCode(200)
                        .setReasonPhrase("OK")
                        .setHttpVersion("HTTP/1.1")
                        .addWarning(
                            UPSTrackResponseWarning.create()
                                .setCode("TW0001")
                                .setMessage("Tracking Information Not Found")))
                    .setResponse("1Z5338FF0107231059", UPSTrackResponse.create()
                        .setStatusCode(200)
                        .setReasonPhrase("OK")
                        .setHttpVersion("HTTP/1.1")
                        .setTrackingNumber("1Z5338FF0107231059")
                        .addActivities(
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
                                .setTime("132642")))
                    .setResponse("92055900100111152280003029", UPSTrackResponse.create()
                        .setStatusCode(200)
                        .setReasonPhrase("OK")
                        .setHttpVersion("HTTP/1.1")
                        .setTrackingNumber("92055900100111152280003029")
                        .addDeliveryDate(
                            UPSTrackResponsePackageDeliveryDate.create()
                                .setType("SDD")
                                .setDate("20200123"))
                        .addActivities(
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
                                .setTime("020200")))
                    .setResponse("7798339175", UPSTrackResponse.create()
                        .setStatusCode(200)
                        .setReasonPhrase("OK")
                        .setHttpVersion("HTTP/1.1")
                        .setTrackingNumber("7798339175")
                        .addDeliveryDate(
                            UPSTrackResponsePackageDeliveryDate.create()
                                .setType("SDD")
                                .setDate("20200721"))
                        .addActivities(
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
                                .setTime("023812")))
                    .setResponse("572254454", UPSTrackResponse.create()
                        .setStatusCode(200)
                        .setReasonPhrase("OK")
                        .setHttpVersion("HTTP/1.1")
                        .setTrackingNumber("572254454")
                        .addDeliveryDates(
                            UPSTrackResponsePackageDeliveryDate.create()
                                .setType("DEL")
                                .setDate("20191207"),
                            UPSTrackResponsePackageDeliveryDate.create()
                                .setType("SDD")
                                .setDate("20191210"))
                        .setDeliveryTime(
                            UPSTrackResponsePackageDeliveryTime.create()
                                .setStartTime("")
                                .setEndTime("")
                                .setType("EOD"))
                        .addActivities(
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
                                .setTime("080000")));
            });
        });
    }
}
