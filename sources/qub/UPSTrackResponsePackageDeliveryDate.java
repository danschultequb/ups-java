package qub;

public class UPSTrackResponsePackageDeliveryDate extends JSONObjectWrapperBase
{
    private static final String typePropertyName = "type";
    private static final String datePropertyName = "date";

    private UPSTrackResponsePackageDeliveryDate(JSONObject json)
    {
        super(json);
    }

    public static UPSTrackResponsePackageDeliveryDate create()
    {
        return UPSTrackResponsePackageDeliveryDate.create(JSONObject.create());
    }

    public static UPSTrackResponsePackageDeliveryDate create(JSONObject json)
    {
        return new UPSTrackResponsePackageDeliveryDate(json);
    }

    public String getType()
    {
        return this.toJson().getString(UPSTrackResponsePackageDeliveryDate.typePropertyName).catchError().await();
    }

    public UPSTrackResponsePackageDeliveryDate setType(String type)
    {
        PreCondition.assertNotNullAndNotEmpty(type, "type");

        this.toJson().setString(UPSTrackResponsePackageDeliveryDate.typePropertyName, type);

        return this;
    }

    public String getDate()
    {
        return this.toJson().getString(UPSTrackResponsePackageDeliveryDate.datePropertyName).catchError().await();
    }

    public UPSTrackResponsePackageDeliveryDate setDate(String date)
    {
        PreCondition.assertNotNullAndNotEmpty(date, "date");

        this.toJson().setString(UPSTrackResponsePackageDeliveryDate.datePropertyName, date);

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
