package qub;

public class FakeUPSClient implements UPSClient
{
    private final MutableMap<String,UPSResponse> responses;
    private String accessLicenseNumber;

    private FakeUPSClient()
    {
        this.responses = ListMap.create(Comparer::equalIgnoreCase);
    }

    public static FakeUPSClient create()
    {
        return new FakeUPSClient();
    }

    public FakeUPSClient setAccessLicenseNumber(String accessLicenseNumber)
    {
        PreCondition.assertNotNullAndNotEmpty(accessLicenseNumber, "accessLicenseNumber");

        this.accessLicenseNumber = accessLicenseNumber;

        return this;
    }

    public String getAccessLicenseNumber()
    {
        return this.accessLicenseNumber;
    }

    public FakeUPSClient setResponse(String inquiryNumber, UPSResponse response)
    {
        PreCondition.assertNotNullAndNotEmpty(inquiryNumber, "inquiryNumber");
        PreCondition.assertNotNull(response, "response");
        PreCondition.assertInstanceOf(response, Iterable.create(UPSErrorResponse.class, UPSTrackResponse.class), "response");

        this.responses.set(inquiryNumber, response);

        return this;
    }

    @Override
    public Result<UPSTrackResponse> sendTrackRequest(UPSTrackRequest trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");
        PreCondition.assertTrue(!Strings.isNullOrEmpty(trackRequest.getAccessLicenseNumber()) || !Strings.isNullOrEmpty(this.getAccessLicenseNumber()), "!Strings.isNullOrEmpty(trackRequest.getAccessLicenseNumber()) || !Strings.isNullOrEmpty(this.getAccessLicenseNumber())");
        PreCondition.assertNotNullAndNotEmpty(trackRequest.getInquiryNumber(), "trackRequest.getInquiryNumber()");

        return Result.create(() ->
        {
            final UPSResponse response = this.responses.get(trackRequest.getInquiryNumber()).await();

            if (response instanceof UPSErrorResponse)
            {
                throw (UPSErrorResponse)response;
            }
            final UPSTrackResponse result = (UPSTrackResponse)response;

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }
}
