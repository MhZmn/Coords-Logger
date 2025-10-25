package com.mhzmn.coordslogger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.KeyBinding.Category;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;


public class CoordsLoggerClient implements ClientModInitializer {
    public static final String MOD_ID = "coords-logger";
    private static KeyBinding logCordsKey;

    @Override
    public void onInitializeClient() {
        Category customCategory = Category.create(Identifier.of("coords-logger", "controls"));
        logCordsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.coords-logger.log_coordinates",
                InputUtil.Type.SCANCODE,
                GLFW.GLFW_KEY_K,
                customCategory
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (logCordsKey.wasPressed()) {
                handleKeyPress(client);
            }
        });

        System.out.println("Coords Logger Client initialized!");
    }

    private void handleKeyPress(MinecraftClient client) {
        if (client.player == null) return;


        World world = client.world;
        if (world == null) return;

        String dimension = world.getRegistryKey().getValue().toString();
        double x = client.player.getX();
        double y = client.player.getY();
        double z = client.player.getZ();

        String dimName;
        if (dimension.contains("overworld")) {
            dimName = "Overworld";
        } else if (dimension.contains("the_nether")) {
            dimName = "The Nether";
        } else if (dimension.contains("the_end")) {
            dimName = "The End";
        } else {
            dimName = dimension;
        }

        String message = String.format("§6[Coordinates] §fDimension: §b%s §f| X: §a%.2f §f| Y: §a%.2f §f| Z: §a%.2f",
                dimName, x, y, z);

        client.player.sendMessage(Text.literal(message), false);

    }
}
