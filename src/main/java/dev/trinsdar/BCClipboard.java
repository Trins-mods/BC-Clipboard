package dev.trinsdar;

import com.mojang.logging.LogUtils;
import dev.trinsdar.client.ClientHandler;
import dev.trinsdar.client.ClipboardBER;
import dev.trinsdar.clipboard.ClipboardSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

@Mod(BCClipboard.ID)
public class BCClipboard {
    public static final String ID = "bc_clipboard";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private int currMessageId = 0;

    public BCClipboard() {
        BCClipboardData.init();
        INSTANCE.registerMessage(currMessageId++, ClipboardSyncPacket.class, ClipboardSyncPacket::encode, ClipboardSyncPacket::decode, ClipboardSyncPacket::handle);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::init);
    }
}
