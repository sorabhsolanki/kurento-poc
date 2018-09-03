package org.kurento.tutorial.util;

import org.kurento.tutorial.cache.CameraUriCache;
import org.kurento.tutorial.player.PlayerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 */
@Component
public class LoadOnStartup {

    private final Logger log = LoggerFactory.getLogger(PlayerHandler.class);

    private final PropertyFileReader propertyFileReader;

    @Autowired
    public LoadOnStartup(PropertyFileReader propertyFileReader) {
        this.propertyFileReader = propertyFileReader;
    }

    @PostConstruct
    public void init() {
        reloadCache();
    }

    private void reloadCache() {
        log.info("::Initializing in-memory cache for Camera-URIs::");
        try {
            Properties prop = PropertyFileReader.readPropertyFile();
            CameraUriCache cameraUriCache = CameraUriCache.getInstance();

            StringTokenizer stringTokenizerCameraID = new StringTokenizer(prop.getProperty("camera.id"), ",");
            StringTokenizer stringTokenizerCameraURL = new StringTokenizer(prop.getProperty("camera.url"), ",");

            while (stringTokenizerCameraID.hasMoreTokens()){
                cameraUriCache.insert(stringTokenizerCameraID.nextToken().trim(), stringTokenizerCameraURL.nextToken().trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in reading property file : " + e.getMessage());
        }

    }
}
