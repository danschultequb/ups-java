package qub;

public class UPSTrackResponsePackageDeliveryTime extends JSONObjectWrapperBase
{
    private static final String startTimePropertyName = "startTime";
    private static final String endTimePropertyName = "endTime";
    private static final String typePropertyName = "type";

    private UPSTrackResponsePackageDeliveryTime(JSONObject json)
    {
        super(json);
    }

    public static UPSTrackResponsePackageDeliveryTime create()
    {
        return UPSTrackResponsePackageDeliveryTime.create(JSONObject.create());
    }

    public static UPSTrackResponsePackageDeliveryTime create(JSONObject json)
    {
        return new UPSTrackResponsePackageDeliveryTime(json);
    }

    private String getString(String propertyName)
    {
        return this.toJson().getString(propertyName).catchError().await();
    }

    private UPSTrackResponsePackageDeliveryTime setString(String propertyName, String propertyValue)
    {
        this.toJson().setString(propertyName, propertyValue);

        return this;
    }

    public String getStartTime()
    {
        return this.getString(UPSTrackResponsePackageDeliveryTime.startTimePropertyName);
    }

    public UPSTrackResponsePackageDeliveryTime setStartTime(String startTime)
    {
        PreCondition.assertNotNull(startTime, "startTime");

        return this.setString(UPSTrackResponsePackageDeliveryTime.startTimePropertyName, startTime);
    }

    public String getEndTime()
    {
        return this.getString(UPSTrackResponsePackageDeliveryTime.endTimePropertyName);
    }

    public UPSTrackResponsePackageDeliveryTime setEndTime(String endTime)
    {
        PreCondition.assertNotNull(endTime, "endTime");

        return this.setString(UPSTrackResponsePackageDeliveryTime.endTimePropertyName, endTime);
    }

    public String getType()
    {
        return this.getString(UPSTrackResponsePackageDeliveryTime.typePropertyName);
    }

    public UPSTrackResponsePackageDeliveryTime setType(String type)
    {
        PreCondition.assertNotNullAndNotEmpty(type, "type");

        return this.setString(UPSTrackResponsePackageDeliveryTime.typePropertyName, type);
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
