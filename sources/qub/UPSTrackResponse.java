package qub;

public class UPSTrackResponse extends UPSResponseBase
{
    private static final String trackResponsePropertyName = "trackResponse";
    private static final String shipmentPropertyName = "shipment";
    private static final String warningsPropertyName = "warnings";
    private static final String packagePropertyName = "package";
    private static final String trackingNumberPropertyName = "trackingNumber";
    private static final String deliveryDatePropertyName = "deliveryDate";
    private static final String deliveryTimePropertyName = "deliveryTime";
    private static final String activityPropertyName = "activity";
    
    private UPSTrackResponse()
    {
    }

    public static UPSTrackResponse create()
    {
        return new UPSTrackResponse();
    }
    
    @Override
    public UPSTrackResponse setStatusCode(int statusCode)
    {
        return (UPSTrackResponse)super.setStatusCode(statusCode);
    }

    @Override
    public UPSTrackResponse setReasonPhrase(String reasonPhrase)
    {
        return (UPSTrackResponse)super.setReasonPhrase(reasonPhrase);
    }

    @Override
    public UPSTrackResponse setHttpVersion(String httpVersion)
    {
        return (UPSTrackResponse)super.setHttpVersion(httpVersion);
    }

    @Override
    public UPSTrackResponse setHeaders(HttpHeaders headers)
    {
        return (UPSTrackResponse)super.setHeaders(headers);
    }

    @Override
    public UPSTrackResponse setBodyJson(JSONObject body)
    {
        return (UPSTrackResponse)super.setBodyJson(body);
    }

    private Result<JSONObject> getTrackResponseJson()
    {
        return this.getBodyJson().getObject(UPSTrackResponse.trackResponsePropertyName);
    }
    
    private JSONObject getOrCreateTrackResponseJson()
    {
        return this.getOrCreateBodyJson().getOrCreateObject(UPSTrackResponse.trackResponsePropertyName).await();
    }

    private Result<JSONArray> getShipmentJson()
    {
        return this.getTrackResponseJson()
            .then((JSONObject trackResponseJson) ->
            {
                return trackResponseJson.getArray(UPSTrackResponse.shipmentPropertyName).await();
            });
    }
    
    private JSONArray getOrCreateShipmentJson()
    {
        return this.getOrCreateTrackResponseJson().getOrCreateArray(UPSTrackResponse.shipmentPropertyName).await();
    }

    private Result<JSONObject> getShipmentElementJson()
    {
        return this.getShipmentJson()
            .then((JSONArray shipmentJson) ->
            {
                return (JSONObject)shipmentJson.get(0);
            });
    }
    
    private JSONObject getOrCreateShipmentElementJson()
    {
        final JSONArray shipmentJson = this.getOrCreateShipmentJson();
        JSONObject result = Types.as(shipmentJson.first(), JSONObject.class);
        if (result == null)
        {
            result = JSONObject.create();
            shipmentJson.insert(0, result);
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    private Result<JSONArray> getWarningsJson()
    {
        return this.getShipmentElementJson()
            .then((JSONObject shipmentElementJson) ->
            {
                return shipmentElementJson.getArray(UPSTrackResponse.warningsPropertyName).await();
            });
    }
    
    private JSONArray getOrCreateWarningsJson()
    {
        return this.getOrCreateShipmentElementJson().getOrCreateArray(UPSTrackResponse.warningsPropertyName).await();
    }

    public Indexable<UPSTrackResponseWarning> getWarnings()
    {
        final List<UPSTrackResponseWarning> result = List.create();

        final JSONArray warningsJson = this.getWarningsJson().catchError().await();
        if (warningsJson != null)
        {
            result.addAll(warningsJson
                .instanceOf(JSONObject.class)
                .map(UPSTrackResponseWarning::create));
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }
    
    public UPSTrackResponse addWarning(UPSTrackResponseWarning warning)
    {
        PreCondition.assertNotNull(warning, "warning");

        this.getOrCreateWarningsJson()
            .add(warning.toJson());

        return this;
    }

    public UPSTrackResponse addWarnings(UPSTrackResponseWarning... warnings)
    {
        PreCondition.assertNotNull(warnings, "warnings");

        return this.addWarnings(Iterable.create(warnings));
    }

    public UPSTrackResponse addWarnings(Iterable<UPSTrackResponseWarning> warnings)
    {
        PreCondition.assertNotNull(warnings, "warnings");

        for (final UPSTrackResponseWarning warning : warnings)
        {
            this.addWarning(warning);
        }

        return this;
    }

    private Result<JSONArray> getPackageJson()
    {
        return this.getShipmentElementJson()
            .then((JSONObject shipmentElementJson) ->
            {
                return shipmentElementJson.getArray(UPSTrackResponse.packagePropertyName).await();
            });
    }

    private JSONArray getOrCreatePackageJson()
    {
        return this.getOrCreateShipmentElementJson().getOrCreateArray(UPSTrackResponse.packagePropertyName).await();
    }

    private Result<JSONObject> getPackageElementJson()
    {
        return this.getPackageJson()
            .then((JSONArray packageJson) ->
            {
                return (JSONObject)packageJson.get(0);
            });
    }

    private JSONObject getOrCreatePackageElementJson()
    {
        final JSONArray packageJson = this.getOrCreatePackageJson();
        JSONObject result = Types.as(packageJson.first(), JSONObject.class);
        if (result == null)
        {
            result = JSONObject.create();
            packageJson.insert(0, result);
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public String getTrackingNumber()
    {
        return this.getPackageElementJson()
            .then((JSONObject packageElementJson) ->
            {
                return packageElementJson.getString(UPSTrackResponse.trackingNumberPropertyName).await();
            })
            .catchError()
            .await();
    }

    public UPSTrackResponse setTrackingNumber(String trackingNumber)
    {
        PreCondition.assertNotNullAndNotEmpty(trackingNumber, "trackingNumber");

        this.getOrCreatePackageElementJson().setString(UPSTrackResponse.trackingNumberPropertyName, trackingNumber);

        return this;
    }

    private Result<JSONArray> getDeliveryDateJson()
    {
        return this.getPackageElementJson()
            .then((JSONObject packageElementJson) ->
            {
                return packageElementJson.getArray(UPSTrackResponse.deliveryDatePropertyName).await();
            });
    }

    private JSONArray getOrCreateDeliveryDateJson()
    {
        return this.getOrCreatePackageElementJson().getOrCreateArray(UPSTrackResponse.deliveryDatePropertyName).await();
    }

    public Indexable<UPSTrackResponsePackageDeliveryDate> getDeliveryDates()
    {
        final List<UPSTrackResponsePackageDeliveryDate> result = List.create();

        final JSONArray deliveryDateJson = this.getDeliveryDateJson().catchError().await();
        if (deliveryDateJson != null)
        {
            result.addAll(deliveryDateJson
                .instanceOf(JSONObject.class)
                .map(UPSTrackResponsePackageDeliveryDate::create));
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public UPSTrackResponse addDeliveryDate(UPSTrackResponsePackageDeliveryDate deliveryDate)
    {
        PreCondition.assertNotNull(deliveryDate, "deliveryDate");

        this.getOrCreateDeliveryDateJson()
            .add(deliveryDate.toJson());

        return this;
    }

    public UPSTrackResponse addDeliveryDates(UPSTrackResponsePackageDeliveryDate... deliveryDates)
    {
        PreCondition.assertNotNull(deliveryDates, "deliveryDates");

        return this.addDeliveryDates(Iterable.create(deliveryDates));
    }

    public UPSTrackResponse addDeliveryDates(Iterable<UPSTrackResponsePackageDeliveryDate> deliveryDates)
    {
        PreCondition.assertNotNull(deliveryDates, "deliveryDates");

        for (final UPSTrackResponsePackageDeliveryDate deliveryDate : deliveryDates)
        {
            this.addDeliveryDate(deliveryDate);
        }

        return this;
    }

    private Result<JSONObject> getDeliveryTimeJson()
    {
        return this.getPackageElementJson()
            .then((JSONObject packageElementJson) ->
            {
                return packageElementJson.getObject(UPSTrackResponse.deliveryTimePropertyName).await();
            });
    }

    private JSONObject getOrCreateDeliveryTimeJson()
    {
        return this.getOrCreatePackageElementJson().getOrCreateObject(UPSTrackResponse.deliveryTimePropertyName).await();
    }

    public UPSTrackResponsePackageDeliveryTime getDeliveryTime()
    {
        return this.getDeliveryTimeJson()
            .then((JSONObject deliveryTimeJson) ->
            {
                return UPSTrackResponsePackageDeliveryTime.create(deliveryTimeJson);
            })
            .catchError()
            .await();
    }

    public UPSTrackResponse setDeliveryTime(UPSTrackResponsePackageDeliveryTime deliveryTime)
    {
        PreCondition.assertNotNull(deliveryTime, "deliveryTime");

        this.getOrCreatePackageElementJson().setObject(UPSTrackResponse.deliveryTimePropertyName, deliveryTime.toJson());

        return this;
    }

    private Result<JSONArray> getActivityJson()
    {
        return this.getPackageElementJson()
            .then((JSONObject packageElementJson) ->
            {
                return packageElementJson.getArray(UPSTrackResponse.activityPropertyName).await();
            });
    }

    private JSONArray getOrCreateActivityJson()
    {
        return this.getOrCreatePackageElementJson().getOrCreateArray(UPSTrackResponse.activityPropertyName).await();
    }

    public Indexable<UPSTrackResponsePackageActivity> getActivities()
    {
        final List<UPSTrackResponsePackageActivity> result = List.create();

        final JSONArray activityJson = this.getActivityJson().catchError().await();
        if (activityJson != null)
        {
            result.addAll(activityJson
                .instanceOf(JSONObject.class)
                .map(UPSTrackResponsePackageActivity::create));
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public UPSTrackResponse addActivity(UPSTrackResponsePackageActivity activity)
    {
        PreCondition.assertNotNull(activity, "activity");

        this.getOrCreateActivityJson()
            .add(activity.toJson());

        return this;
    }

    public UPSTrackResponse addActivities(UPSTrackResponsePackageActivity... activities)
    {
        PreCondition.assertNotNull(activities, "activities");

        return this.addActivities(Iterable.create(activities));
    }

    public UPSTrackResponse addActivities(Iterable<UPSTrackResponsePackageActivity> activities)
    {
        PreCondition.assertNotNull(activities, "activities");

        for (final UPSTrackResponsePackageActivity activity : activities)
        {
            this.addActivity(activity);
        }

        return this;
    }
}
