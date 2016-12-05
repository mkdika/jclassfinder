package com.mkdika.jclassfinder.model;

import java.io.File;

/**
 *
 * @author maikel
 */
public class ClassModel {
    
    private File jarFile;
    private String classPath;
    
    public ClassModel() {}

    public ClassModel(File jarFile, String classPath) {
        this.jarFile = jarFile;
        this.classPath = classPath;
    }

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }        
}
