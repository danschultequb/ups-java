package qub;

public interface UPSClient
{
    static RealUPSClient create(Network network)
    {
        return RealUPSClient.create(network);
    }

    static RealUPSClient create(HttpClient httpClient)
    {
        return RealUPSClient.create(httpClient);
    }

    Result<UPSTrackResponse> sendTrackRequest(UPSTrackRequest trackRequest);
}
