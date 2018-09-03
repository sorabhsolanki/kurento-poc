package org.kurento.tutorial.cache;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class CameraUriCache {

    private static final CameraUriCache CAMERA_URIS = new CameraUriCache();

    private final Map<String, String> cameraUriMap;

    private CameraUriCache() {
        cameraUriMap = new HashMap<>();
    }

    public static CameraUriCache getInstance(){
        return CAMERA_URIS;
    }

    public void insert(String cameraId, String cameraURI){
        cameraUriMap.put(cameraId, cameraURI);
    }

    public String getCameraUri(String cameraId){
        return cameraUriMap.get(cameraId);
    }
}


