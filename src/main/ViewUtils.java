package main;

import javax.swing.*;

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
}
