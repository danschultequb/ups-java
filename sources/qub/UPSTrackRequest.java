package qub;

public class UPSTrackRequest implements JSONObjectWrapper
{
    private String transId;
    private String transactionSrc;
    private String accessLicenseNumber;
    private String username;
    private String password;
    private String authenticationToken;
    private String authorization;
    private String inquiryNumber;
    private String locale;

    private UPSTrackRequest()
    {
    }

    public static UPSTrackRequest create()
    {
        return new UPSTrackRequest();
    }

    /**
     * Get the identifier unique to the request.
     * @return The identifier unique to the request.
     */
    public String getTransId()
    {
        return this.transId;
    }

    /**
     * Set the identifier unique to the request.
     * @param transId The identifier unique to the request.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setTransId(String transId)
    {
        PreCondition.assertNotNullAndNotEmpty(transId, "transId");

        this.transId = transId;

        return this;
    }

    /**
     * Get the customer provided data. This data will be echoed back in the response if provided.
     * @return The customer provided data. This data will be echoed back in the response if provided.
     */
    public String getTransactionSrc()
    {
        return this.transactionSrc;
    }

    /**
     * Set the customer provided data. This data will be echoed back in the response if provided.
     * @param transactionSrc The customer provided data. This data will be echoed back in the response if provided.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setTransactionSrc(String transactionSrc)
    {
        PreCondition.assertNotNullAndNotEmpty(transactionSrc, "transactionSrc");

        this.transactionSrc = transactionSrc;

        return this;
    }

    /**
     * Get the access key obtained through the on-boarding process.
     * @return The access key obtained through the on-boarding process.
     */
    public String getAccessLicenseNumber()
    {
        return this.accessLicenseNumber;
    }

    /**
     * Set the access key obtained through the on-boarding process.
     * @param accessLicenseNumber The access key obtained through the on-boarding process.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setAccessLicenseNumber(String accessLicenseNumber)
    {
        PreCondition.assertNotNullAndNotEmpty(accessLicenseNumber, "accessLicenseNumber");

        this.accessLicenseNumber = accessLicenseNumber;

        return this;
    }

    /**
     * Get the customer's MyUPS user ID/username.
     * @return The customer's MyUPS user ID/username.
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Set the customer's MyUPS user ID/username.
     * @param username The customer's MyUPS user ID/username.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setUsername(String username)
    {
        PreCondition.assertNotNullAndNotEmpty(username, "username");

        this.username = username;

        return this;
    }

    /**
     * Get the customer's MyUPS password. This value is required if the AuthenticationToken is not
     * present.
     * @return The customer's MyUPS password.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Set the customer's MyUPS password. This value is required if the AuthenticationToken is not
     * present.
     * @param password The customer's MyUPS password.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setPassword(String password)
    {
        PreCondition.assertNotNullAndNotEmpty(password, "password");

        this.password = password;

        return this;
    }

    /**
     * Get the value used to authenticate the API. Can be used in place of Username and Password.
     * @return The value used to authenticate the API.
     */
    public String getAuthenticationToken()
    {
        return this.authenticationToken;
    }

    /**
     * Set the value used to authenticate the API. Can be used in place of Username and Password.
     * @param authenticationToken The value used to authenticate the API.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setAuthenticationToken(String authenticationToken)
    {
        PreCondition.assertNotNullAndNotEmpty(authenticationToken, "authenticationToken");

        this.authenticationToken = authenticationToken;

        return this;
    }

    /**
     * Get the value used to authorize the access to the API. Can be used in place of Username and
     * Password.
     * @return The value used to authorize the access to the API.
     */
    public String getAuthorization()
    {
        return this.authorization;
    }

    /**
     * Set the value used to authorize the access to the API. Can be used in place of Username and
     * Password.
     * @param authorization The value used to authorize the access to the API.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setAuthorization(String authorization)
    {
        PreCondition.assertNotNullAndNotEmpty(authorization, "authorization");

        this.authorization = authorization;

        return this;
    }

    /**
     * Get the tracking number for which tracking information is requested.
     * @return The tracking number for which tracking information is requested.
     */
    public String getInquiryNumber()
    {
        return this.inquiryNumber;
    }

    /**
     * Set the tracking number for which tracking information is requested.
     * @param inquiryNumber The tracking number for which tracking information is requested.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setInquiryNumber(String inquiryNumber)
    {
        PreCondition.assertNotNullAndNotEmpty(inquiryNumber, "inquiryNumber");

        this.inquiryNumber = inquiryNumber;

        return this;
    }

    /**
     * Get the language and country code combination.
     * @return The language and country code combination.
     */
    public String getLocale()
    {
        return this.locale;
    }

    /**
     * Set the language and country code combination.
     * @param locale The language and country code combination.
     * @return This object for method chaining.
     */
    public UPSTrackRequest setLocale(String locale)
    {
        PreCondition.assertNotNullAndNotEmpty(locale, "locale");

        this.locale = locale;

        return this;
    }

    private static void set(JSONObject json, String propertyName, String propertyValue)
    {
        PreCondition.assertNotNull(json, "json");
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        if (propertyValue != null)
        {
            json.setString(propertyName, propertyValue);
        }
    }

    @Override
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();
        UPSTrackRequest.set(result, "transId", this.transId);
        UPSTrackRequest.set(result, "transactionSrc", this.transactionSrc);
        UPSTrackRequest.set(result, "accessLicenseNumber", this.accessLicenseNumber);
        UPSTrackRequest.set(result, "username", this.username);
        UPSTrackRequest.set(result, "password", this.password);
        UPSTrackRequest.set(result, "authenticationToken", this.authenticationToken);
        UPSTrackRequest.set(result, "authorization", this.authorization);
        UPSTrackRequest.set(result, "inquiryNumber", this.inquiryNumber);
        UPSTrackRequest.set(result, "locale", this.locale);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public String toString()
    {
        return JSONObjectWrapper.toString(this);
    }

    @Override
    public boolean equals(Object rhs)
    {
        return JSONObjectWrapper.equals(this, rhs);
    }
}
