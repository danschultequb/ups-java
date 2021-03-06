package qub;

public class UPSTrackResponsePackageActivity extends JSONObjectWrapperBase
{
    private static final String locationPropertyName = "location";
    private static final String addressPropertyName = "address";
    private static final String cityPropertyName = "city";
    private static final String stateProvincePropertyName = "stateProvince";
    private static final String postalCodePropertyName = "postalCode";
    private static final String countryPropertyName = "country";
    private static final String statusPropertyName = "status";
    private static final String typePropertyName = "type";
    private static final String descriptionPropertyName = "description";
    private static final String codePropertyName = "code";
    private static final String datePropertyName = "date";
    private static final String timePropertyName = "time";

    private UPSTrackResponsePackageActivity(JSONObject json)
    {
        super(json);
    }

    public static UPSTrackResponsePackageActivity create()
    {
        return UPSTrackResponsePackageActivity.create(JSONObject.create());
    }

    public static UPSTrackResponsePackageActivity create(JSONObject json)
    {
        return new UPSTrackResponsePackageActivity(json);
    }

    private Result<JSONObject> getLocationJson()
    {
        return this.toJson().getObject(UPSTrackResponsePackageActivity.locationPropertyName);
    }

    private JSONObject getOrCreateLocationJson()
    {
        return this.toJson().getOrCreateObject(UPSTrackResponsePackageActivity.locationPropertyName).await();
    }

    private Result<JSONObject> getAddressJson()
    {
        return this.getLocationJson()
            .then((JSONObject locationJson) ->
            {
                return locationJson.getObject(UPSTrackResponsePackageActivity.addressPropertyName).await();
            });
    }

    private JSONObject getOrCreateAddressJson()
    {
        return this.getOrCreateLocationJson().getOrCreateObject(UPSTrackResponsePackageActivity.addressPropertyName).await();
    }

    private String getAddressString(String propertyName)
    {
        return this.getAddressJson()
            .then((JSONObject addressJson) ->
            {
                return addressJson.getString(propertyName).await();
            })
            .catchError()
            .await();
    }

    private UPSTrackResponsePackageActivity setAddressString(String propertyName, String propertyValue)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");
        PreCondition.assertNotNull(propertyValue, "propertyValue");

        this.getOrCreateAddressJson()
            .setString(propertyName, propertyValue);

        return this;
    }

    private Result<JSONObject> getStatusJson()
    {
        return this.toJson().getObject(UPSTrackResponsePackageActivity.statusPropertyName);
    }

    private JSONObject getOrCreateStatusJson()
    {
        return this.toJson().getOrCreateObject(UPSTrackResponsePackageActivity.statusPropertyName).await();
    }

    private String getStatusString(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return this.getStatusJson()
            .then((JSONObject statusJson) ->
            {
                return statusJson.getString(propertyName).await();
            })
            .catchError()
            .await();
    }

    private UPSTrackResponsePackageActivity setStatusString(String propertyName, String propertyValue)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");
        PreCondition.assertNotNull(propertyValue, "propertyValue");

        this.getOrCreateStatusJson()
            .setString(propertyName, propertyValue);

        return this;
    }

    public String getCity()
    {
        return this.getAddressString(UPSTrackResponsePackageActivity.cityPropertyName);
    }

    public UPSTrackResponsePackageActivity setCity(String city)
    {
        PreCondition.assertNotNull(city, "city");

        return this.setAddressString(UPSTrackResponsePackageActivity.cityPropertyName, city);
    }

    public String getStateProvince()
    {
        return this.getAddressString(UPSTrackResponsePackageActivity.stateProvincePropertyName);
    }

    public UPSTrackResponsePackageActivity setStateProvince(String stateProvince)
    {
        PreCondition.assertNotNull(stateProvince, "stateProvince");

        return this.setAddressString(UPSTrackResponsePackageActivity.stateProvincePropertyName, stateProvince);
    }

    public String getPostalCode()
    {
        return this.getAddressString(UPSTrackResponsePackageActivity.postalCodePropertyName);
    }

    public UPSTrackResponsePackageActivity setPostalCode(String postalCode)
    {
        PreCondition.assertNotNull(postalCode, "postalCode");

        return this.setAddressString(UPSTrackResponsePackageActivity.postalCodePropertyName, postalCode);
    }

    public String getCountry()
    {
        return this.getAddressString(UPSTrackResponsePackageActivity.countryPropertyName);
    }

    public UPSTrackResponsePackageActivity setCountry(String country)
    {
        PreCondition.assertNotNull(country, "country");

        return this.setAddressString(UPSTrackResponsePackageActivity.countryPropertyName, country);
    }

    public String getType()
    {
        return this.getStatusString(UPSTrackResponsePackageActivity.typePropertyName);
    }

    public UPSTrackResponsePackageActivity setType(String type)
    {
        PreCondition.assertNotNull(type, "type");

        return this.setStatusString(UPSTrackResponsePackageActivity.typePropertyName, type);
    }

    public String getDescription()
    {
        return this.getStatusString(UPSTrackResponsePackageActivity.descriptionPropertyName);
    }

    public UPSTrackResponsePackageActivity setDescription(String description)
    {
        PreCondition.assertNotNullAndNotEmpty(description, "description");

        return this.setStatusString(UPSTrackResponsePackageActivity.descriptionPropertyName, description);
    }

    public String getCode()
    {
        return this.getStatusString(UPSTrackResponsePackageActivity.codePropertyName);
    }

    public UPSTrackResponsePackageActivity setCode(String code)
    {
        PreCondition.assertNotNullAndNotEmpty(code, "code");

        return this.setStatusString(UPSTrackResponsePackageActivity.codePropertyName, code);
    }

    public String getDate()
    {
        return this.toJson().getString(UPSTrackResponsePackageActivity.datePropertyName).catchError().await();
    }

    public UPSTrackResponsePackageActivity setDate(String date)
    {
        PreCondition.assertNotNullAndNotEmpty(date, "date");

        this.toJson().setString(UPSTrackResponsePackageActivity.datePropertyName, date);

        return this;
    }

    public String getTime()
    {
        return this.toJson().getString(UPSTrackResponsePackageActivity.timePropertyName).catchError().await();
    }

    public UPSTrackResponsePackageActivity setTime(String time)
    {
        PreCondition.assertNotNullAndNotEmpty(time, "time");

        this.toJson().setString(UPSTrackResponsePackageActivity.timePropertyName, time);

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
