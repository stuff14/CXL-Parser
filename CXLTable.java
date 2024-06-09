import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CXLTable extends JPanel {

    private String[][] data;
    private int packetNum;
    private ArrayList<tableGrids> gridsList = new ArrayList<tableGrids>();

    String[] fields = {"Packet #:", "System Header", "Valid", "Mem Opcode", "SNP Type", "Meta Field", "Meta Value", "Tag", "Address", "LD ID", "Reserved", "TC"};

    public CXLTable(String[][] data, int packetNum) {
        this.data = data;
        this.packetNum = packetNum;

        for (int i = 0; i < packetNum; i++) {
            int xValue = 0;

            for (int j = 0; j < fields.length; j++) {
                gridsList.add(new tableGrids(fields[j], data[i][j], xValue, i));
                xValue += gridsList.get(j).getWidth();
            }
        }

        setBackground(Color.WHITE);
        this.setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for (tableGrids tg : gridsList) {
            tg.draw(g);
        }
    }
}
