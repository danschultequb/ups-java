package qub;

public class UPSErrorResponse extends RuntimeException implements UPSResponse
{
    private final UPSResponseBase base;
    
    public UPSErrorResponse()
    {
        this.base = UPSResponseBase.create();
    }
    
    @Override
    public int getStatusCode()
    {
        return this.base.getStatusCode();
    }

    public UPSErrorResponse setStatusCode(int statusCode)
    {
        this.base.setStatusCode(statusCode);

        return this;
    }

    @Override
    public String getReasonPhrase()
    {
        return this.base.getReasonPhrase();
    }

    public UPSErrorResponse setReasonPhrase(String reasonPhrase)
    {
        this.base.setReasonPhrase(reasonPhrase);

        return this;
    }

    @Override
    public String getHttpVersion()
    {
        return this.base.getHttpVersion();
    }

    public UPSErrorResponse setHttpVersion(String httpVersion)
    {
        this.base.setHttpVersion(httpVersion);

        return this;
    }

    @Override
    public HttpHeaders getHeaders()
    {
        return this.base.getHeaders();
    }

    public UPSErrorResponse setHeaders(HttpHeaders headers)
    {
        this.base.setHeaders(headers);

        return this;
    }
    
    public String getErrorDescription()
    {
        return this.getHeaderValue("ErrorDescription")
            .catchError()
            .await();
    }

    public String getErrorCode()
    {
        return this.getHeaderValue("ErrorCode")
            .catchError()
            .await();
    }

    @Override
    public JSONObject getBodyJson()
    {
        return this.base.getBodyJson();
    }

    public UPSErrorResponse setBodyJson(JSONObject bodyJson)
    {
        this.base.setBodyJson(bodyJson);

        return this;
    }

    @Override
    public boolean equals(Object rhs)
    {
        return UPSResponse.equals(this, rhs);
    }
}
