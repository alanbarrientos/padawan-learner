package balan.codes.crazylist.spotify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackEssentialMetadataList {
    Map<String, TrackEssentialMetadata> trackEssentialMetadataList = new HashMap<>();

    public Map<String, TrackEssentialMetadata> getTrackEssentialMetadataList() {
        return trackEssentialMetadataList;
    }

    public void setTrackEssentialMetadataList(Map<String, TrackEssentialMetadata> trackEssentialMetadataList) {
        this.trackEssentialMetadataList = trackEssentialMetadataList;
    }

    @Override
    public String toString() {
        return "TrackEssentialMetadataList{" +
                "trackEssentialMetadataList=" + trackEssentialMetadataList +
                '}';
    }
}
