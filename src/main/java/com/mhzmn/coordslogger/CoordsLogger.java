package com.mhzmn.coordslogger;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordsLogger implements ModInitializer {
	public static final String MOD_ID = "coords-logger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        LOGGER.info("Coords Logger initialized!");
	}
}
