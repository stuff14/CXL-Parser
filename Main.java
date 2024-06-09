import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;

public class Main {

    private static File[] selection;

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file as input");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CXL packet (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selection = fileChooser.getSelectedFiles();

        }

        String[][] parsedData = new String[selection.length][12];

        for (int i = 0; i < selection.length; i++) {

            BufferedReader reader;
            String str = "";

            try {
                reader = new BufferedReader(new FileReader(selection[i].getName()));
                str = reader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = hexStringToByteArray(str);
            CXLHeader cxlH = new CXLHeader(data);
            parsedData[i] = cxlH.getArr();
        }

        JFrame frame = new JFrame("CXL Memory Header Parser");
        CXLTable table = new CXLTable(parsedData, selection.length);
        frame.add(table);
        frame.setSize(1100, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}