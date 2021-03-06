package qub;

public interface UPSResponse
{
    int getStatusCode();

    String getReasonPhrase();

    String getHttpVersion();

    HttpHeaders getHeaders();

    default Result<HttpHeader> getHeader(String headerName)
    {
        return this.getHeaders().get(headerName);
    }

    default Result<String> getHeaderValue(String headerName)
    {
        return Result.create(() ->
        {
            final HttpHeaders headers = this.getHeaders();
            if (headers == null)
            {
                throw new NotFoundException(headerName);
            }
            return headers.getValue(headerName).await();
        });
    }

    JSONObject getBodyJson();

    static boolean equals(UPSResponse response, Object rhs)
    {
        PreCondition.assertNotNull(response, "response");

        return response.getClass().equals(Types.getType(rhs)) &&
            response.equals((UPSResponse)rhs);
    }

    default boolean equals(UPSResponse rhs)
    {
        return rhs != null &&
            Comparer.equal(this.getStatusCode(), rhs.getStatusCode()) &&
            Comparer.equal(this.getReasonPhrase(), rhs.getReasonPhrase()) &&
            Comparer.equal(this.getHttpVersion(), rhs.getHttpVersion()) &&
            Comparer.equal(this.getHeaders(), rhs.getHeaders()) &&
            Comparer.equal(this.getBodyJson(), rhs.getBodyJson());
    }
}
