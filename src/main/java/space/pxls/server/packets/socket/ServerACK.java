package space.pxls.server.packets.socket;

public class ServerACK {
    private final String type = "ACK";
    private final String ackFor;
    private final Integer x;
    private final Integer y;
    private final Integer color;

    public ServerACK(String ackFor, Integer x, Integer y, Integer color) {
        this.ackFor = ackFor;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
