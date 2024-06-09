import java.awt.*;
import java.awt.image.BufferedImage;

public class tableGrids {

    private String field;
    private String value;
    private int width;
    private int packetNum;
    private int x;
    private int y;
    private final Image image;

    public tableGrids(String field, String value, int xValue, int packetNum) {
        this.packetNum = packetNum;
        this.field = field;
        this.value = value;
        this.width = Math.max(field.length() * 5 + 50, value.length() * 5 + 50);
        this.x = xValue;
        this.y = packetNum * 100 + 1;

        image = new BufferedImage(width + 1, 41, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        if (!field.equals("Packet #:")) {
            g.drawRect(0, 0, width, 40);
        }
        g.drawString(field, 10, 15);
        g.drawString(value, 10, 30);
        g.dispose();
    }

    public int getWidth() {
        return width;
    }

    public void draw(Graphics g) {
        int x = this.x;
        int y = this.y;
        g.drawImage(image, x, y, null);
    }
}
