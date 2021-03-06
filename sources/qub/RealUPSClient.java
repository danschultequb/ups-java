package qub;

public class RealUPSClient implements UPSClient
{
    public static final String productionHost = "onlinetools.ups.com";
    public static final String integrationTestingHost = "wwwcie.ups.com";

    private final HttpClient httpClient;
    private String host;
    private String accessLicenseNumber;

    private RealUPSClient(HttpClient httpClient)
    {
        PreCondition.assertNotNull(httpClient, "httpClient");

        this.httpClient = httpClient;
        this.host = RealUPSClient.productionHost;
    }

    public static RealUPSClient create(Network network)
    {
        PreCondition.assertNotNull(network, "network");

        final HttpClient httpClient = HttpClient.create(network);
        return RealUPSClient.create(httpClient);
    }

    public static RealUPSClient create(HttpClient httpClient)
    {
        return new RealUPSClient(httpClient);
    }

    public RealUPSClient setHost(String host)
    {
        PreCondition.assertNotNullAndNotEmpty(host, "host");

        this.host = host;

        return this;
    }

    public String getHost()
    {
        return this.host;
    }

    public RealUPSClient setAccessLicenseNumber(String accessLicenseNumber)
    {
        PreCondition.assertNotNullAndNotEmpty(accessLicenseNumber, "accessLicenseNumber");

        this.accessLicenseNumber = accessLicenseNumber;

        return this;
    }

    public String getAccessLicenseNumber()
    {
        return this.accessLicenseNumber;
    }

    @Override
    public Result<UPSTrackResponse> sendTrackRequest(UPSTrackRequest trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");
        PreCondition.assertTrue(!Strings.isNullOrEmpty(trackRequest.getAccessLicenseNumber()) || !Strings.isNullOrEmpty(this.getAccessLicenseNumber()), "!Strings.isNullOrEmpty(trackRequest.getAccessLicenseNumber()) || !Strings.isNullOrEmpty(this.getAccessLicenseNumber())");
        PreCondition.assertNotNullAndNotEmpty(trackRequest.getInquiryNumber(), "trackRequest.getInquiryNumber()");

        return Result.create(() ->
        {
            final MutableHttpRequest httpRequest = HttpRequest.create()
                .setMethod(HttpMethod.GET);

            final Action2<String,String> setHeader = (String headerName, String headerValue) ->
            {
                if (headerValue != null)
                {
                    httpRequest.setHeader(headerName, headerValue);
                }
            };
            setHeader.run("transId", trackRequest.getTransId());
            setHeader.run("transactionSrc", trackRequest.getTransactionSrc());
            setHeader.run("AccessLicenseNumber", this.getAccessLicenseNumber());
            setHeader.run("AccessLicenseNumber", trackRequest.getAccessLicenseNumber());
            setHeader.run("Username", trackRequest.getUsername());
            setHeader.run("Password", trackRequest.getPassword());
            setHeader.run("AuthenticationToken", trackRequest.getAuthenticationToken());
            setHeader.run("Authorization", trackRequest.getAuthorization());

            final MutableURL url = URL.create()
                .setScheme("https")
                .setHost(this.host)
                .setPath("track/v1/details/" + trackRequest.getInquiryNumber());
            final String locale = trackRequest.getLocale();
            if (locale != null)
            {
                url.setQueryParameter("locale", locale);
            }
            httpRequest.setUrl(url);

            UPSTrackResponse result;
            try (final HttpResponse httpResponse = this.httpClient.send(httpRequest).await())
            {
                if (httpResponse.getStatusCode() != 200)
                {
                    throw new UPSErrorResponse()
                        .setStatusCode(httpResponse.getStatusCode())
                        .setReasonPhrase(httpResponse.getReasonPhrase())
                        .setHttpVersion(httpResponse.getHttpVersion())
                        .setHeaders(httpResponse.getHeaders())
                        .setBodyJson(JSON.parseObject(httpResponse.getBody()).await());
                }

                result = UPSTrackResponse.create()
                    .setStatusCode(httpResponse.getStatusCode())
                    .setReasonPhrase(httpResponse.getReasonPhrase())
                    .setHttpVersion(httpResponse.getHttpVersion())
                    .setHeaders(httpResponse.getHeaders())
                    .setBodyJson(JSON.parseObject(httpResponse.getBody()).await());
            }

            PostCondition.assertNotNull(result, "result");
            PostCondition.assertEqual(200, result.getStatusCode(), "result.getStatusCode()");

            return result;
        });
    }
}