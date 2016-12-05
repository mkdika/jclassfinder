package com.mkdika.jclassfinder.helper.filefilter;

import com.mkdika.jclassfinder.helper.AppUtil;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author maikel
 */
public class JarExtFilter extends FileFilter {
    
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String extension = AppUtil.getFileExtensions(file);
        if (extension != null) {
            return extension.equals("jar");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Java Archive (*.jar)";
    }
    
}
