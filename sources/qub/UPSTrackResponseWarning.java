package qub;

public class UPSTrackResponseWarning extends JSONObjectWrapperBase
{
    private static final String codePropertyName = "code";
    private static final String messagePropertyName = "message";

    private UPSTrackResponseWarning(JSONObject json)
    {
        super(json);
    }

    public static UPSTrackResponseWarning create()
    {
        return UPSTrackResponseWarning.create(JSONObject.create());
    }

    public static UPSTrackResponseWarning create(JSONObject json)
    {
        return new UPSTrackResponseWarning(json);
    }

    public String getCode()
    {
        return this.toJson().getString(UPSTrackResponseWarning.codePropertyName).catchError().await();
    }

    public UPSTrackResponseWarning setCode(String code)
    {
        PreCondition.assertNotNullAndNotEmpty(code, "code");

        this.toJson().setString(UPSTrackResponseWarning.codePropertyName, code);

        return this;
    }

    public String getMessage()
    {
        return this.toJson().getString(UPSTrackResponseWarning.messagePropertyName).catchError().await();
    }

    public UPSTrackResponseWarning setMessage(String message)
    {
        PreCondition.assertNotNullAndNotEmpty(message, "message");

        this.toJson().setString(UPSTrackResponseWarning.messagePropertyName, message);

        return this;
    }

    @Override
    public boolean equals(Object rhs)
    {
        return JSONObjectWrapper.equals(this, rhs);
    }

    @Override
    public String toString()
    {
        return JSONObjectWrapper.toString(this);
    }
}
