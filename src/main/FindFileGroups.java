package main;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

//java class for finding file names that matches some regular expression

 class FindFileGroups {
     static List<Integer> getFileGroup(DefaultListModel<String> fileNames, String patternStr){

        List<Integer> result = new LinkedList<>();
        Pattern pattern = Pattern.compile(patternStr);

        for (int i = 0; i < fileNames.size(); i++) {
            if (pattern.matcher(fileNames.get(i)).find()){
                result.add(i);
            }
        }
        return result;
    }
}
