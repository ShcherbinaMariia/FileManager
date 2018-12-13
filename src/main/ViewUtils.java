package main;

import javax.swing.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ViewUtils {

    public static boolean confirm(){

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", dialogButton);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    public static String getNameWindow(String msg){
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, msg);
        return result.toString();
    }

    public static void processExitCode(int exitCode){

        /*EXIT_CODE
         * 0 - OK
         * 1 - Already exists
         * -1 - IO exception
         * 2 - Delete a directory
         * 3 - Cancelled
         */

        String msg = "Something went wrong. Please try again";
        String title = "Failure";
        if (exitCode == 3){
            return;
        }

        if (exitCode == 0){
            msg = "Success";
            title = "OK";
        }
        if (exitCode == 1){
            msg = "Already exists";
        }
        if (exitCode == 2){
            msg = "Trying to delete a directory";
        }

        if (exitCode == 4){
            msg = "Invalid expression - leads to recursion error";
        }
        JOptionPane.showMessageDialog(null, msg, "InfoBox: " + title, JOptionPane.INFORMATION_MESSAGE);
    }

    static void showFileInfo(Map<String, String> info){
        JOptionPane.showMessageDialog(null,
                "Name: " + info.get("name") + "\n" +
                        "Type: " + info.get("type") + "\n" +
                        "Absolute path: " + info.get("path") + "\n" +
                        "Length: " + getLength(Double.parseDouble(info.get("length"))) + "\n" +
                        "Last modified: " + getTime(Long.parseLong(info.get("last modified"))),

                "File info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static String getLength(double length){
        int base = 1024;
        if (length < base)
            return length + "b";

        length /= base;
        if (length < base)
            return length + "Kb";

        length /= base;
        if (length < base)
            return length + "Mb";

        return length/base  + "Gb";
    }

    private static String getTime(long t){
        Date date = new Date(t);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
}
