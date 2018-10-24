package main;

import java.io.IOException;

class EditorController {

    String open(String path) throws IOException {
        return UtilsOS.getFileText(path);
    }

    void save(String path, String content){
        UtilsOS.writeToFile(path, content);
    }
}
