package main;

import javax.swing.*;
import java.io.File;
import java.util.Vector;

public class Model {

    private String path;
    private File files;
    DefaultListModel<String> model;

    public Model(String path) {
        this.path = path;
        this.files = new File(path);
        this.model = new DefaultListModel<>();
    }

    public void refresh(){
        setPath(getPath());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String newPath){
        this.path = newPath;
        this.files = new File(path);
        this.model.clear();
        getListElems().forEach(item -> model.addElement(item));
    }

    public Vector<String> getListElems() {
        return UtilsOS.getListElems(files);
    }
}
