package net.frozenblock.wilderwild.common;

import dev.architectury.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WilderWild {

    public static final String MOD_ID = "wilderwild";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static boolean DEV_LOGGING = false;
    public static boolean UNSTABLE_LOGGING = Platform.isDevelopmentEnvironment(); //Used for features that may be unstable and crash in public builds - it's smart to use this for at least registries.
    public static boolean areConfigsInit = false;

    public static void init() {

    }
}
