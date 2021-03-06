package qub;

public class UPSResponseBase implements UPSResponse
{
    private int statusCode;
    private String reasonPhrase;
    private String httpVersion;
    private HttpHeaders headers;
    private JSONObject bodyJson;

    protected UPSResponseBase()
    {
    }

    public static UPSResponseBase create()
    {
        return new UPSResponseBase();
    }

    @Override
    public int getStatusCode()
    {
        return this.statusCode;
    }

    public UPSResponseBase setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;

        return this;
    }

    @Override
    public String getReasonPhrase()
    {
        return this.reasonPhrase;
    }

    public UPSResponseBase setReasonPhrase(String reasonPhrase)
    {
        PreCondition.assertNotNullAndNotEmpty(reasonPhrase, "reasonPhrase");

        this.reasonPhrase = reasonPhrase;

        return this;
    }

    @Override
    public String getHttpVersion()
    {
        return this.httpVersion;
    }

    public UPSResponseBase setHttpVersion(String httpVersion)
    {
        PreCondition.assertNotNullAndNotEmpty(httpVersion, "httpVersion");

        this.httpVersion = httpVersion;

        return this;
    }

    @Override
    public HttpHeaders getHeaders()
    {
        return this.headers;
    }

    public UPSResponseBase setHeaders(HttpHeaders headers)
    {
        PreCondition.assertNotNull(headers, "headers");

        this.headers = headers;

        return this;
    }

    @Override
    public JSONObject getBodyJson()
    {
        return this.bodyJson;
    }

    public JSONObject getOrCreateBodyJson()
    {
        if (this.bodyJson == null)
        {
            this.bodyJson = JSONObject.create();
        }
        final JSONObject result = this.bodyJson;

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public UPSResponseBase setBodyJson(JSONObject bodyJson)
    {
        PreCondition.assertNotNull(bodyJson, "bodyJson");

        this.bodyJson = bodyJson;

        return this;
    }
}
