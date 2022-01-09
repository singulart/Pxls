package space.pxls.server.packets;

import space.pxls.user.PlacementOverrides;
import space.pxls.user.Role;

import java.util.List;

public class UserInfo {
    private final String username;
    private final List<Role> roles;
    private final int pixelCount;
    private final int pixelCountAllTime;
    private final Boolean banned;
    private final Long banExpiry;
    private final String banReason;
    private final String method;
    private final PlacementOverrides placementOverrides;
    private final Boolean chatBanned;
    private final String chatbanReason;
    private final Boolean chatbanIsPerma;
    private final Long chatbanExpiry;
    private final Boolean renameRequested;
    private final String discordName;
    private final Number chatNameColor;



    public UserInfo(String username, List<Role> roles, int pixelCount, int pixelCountAllTime,
                    Boolean banned, Long banExpiry, String banReason, String method,
                    PlacementOverrides placementOverrides, Boolean chatBanned, String chatbanReason,
                    Boolean chatbanIsPerma, Long chatbanExpiry, Boolean renameRequested, String discordName,
                    Number chatNameColor) {
        this.username = username;
        this.roles = roles;
        this.pixelCount = pixelCount;
        this.pixelCountAllTime = pixelCountAllTime;
        this.banned = banned;
        this.banExpiry = banExpiry;
        this.banReason = banReason;
        this.method = method;
        this.placementOverrides = placementOverrides;
        this.chatBanned = chatBanned;
        this.chatbanReason = chatbanReason;
        this.chatbanIsPerma = chatbanIsPerma;
        this.chatbanExpiry = chatbanExpiry;
        this.renameRequested = renameRequested;
        this.discordName = discordName;
        this.chatNameColor = chatNameColor;
    }
}
