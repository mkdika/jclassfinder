package com.mkdika.jclassfinder.helper;

import com.mkdika.jclassfinder.config.AppConfig;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author maikel
 */
public class AppUtil {

    public static final int MESSAGE_INFORMATION = 1;
    public static final int MESSAGE_WARNING = 2;
    public static final int MESSAGE_QUESTION = 3;

    public static void msg(Component comp, String msg, int tipe) {
        switch (tipe) {
            case 1:
                JOptionPane.showMessageDialog(comp, "<html><h4>" + msg + "</h4></html>", AppConfig.APP_SHORT_TITLE, tipe);
                break;
            case 2:
                JOptionPane.showMessageDialog(comp, "<html><color ='#FF0000'><h4>" + msg + "</h4></color></html>", AppConfig.APP_SHORT_TITLE, tipe);
                break;
            case 3:
                JOptionPane.showMessageDialog(comp, "<html><h4>" + msg + "</h4></html>", AppConfig.APP_SHORT_TITLE, tipe);
                break;
            default:
                break;
        }
    }

    public static void msg(Component comp, String msg) {
        msg(comp, msg, MESSAGE_INFORMATION);
    }

    public static String getFileExtensions(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static void showAbout(Component comp) {
        // for copying style
        JLabel label = new JLabel();
        Font font = label.getFont();

        // create some css from the label's font
        StringBuffer style = new StringBuffer("font-family:").append(font.getFamily()).append(";");
        style.append("font-weight:").append(font.isBold() ? "bold" : "normal").append(";");
        style.append("font-size:").append(font.getSize()).append("pt;");

        StringBuilder strb = new StringBuilder();
        strb.append("<html><body style='");
        strb.append(style.toString());
        strb.append("'>");
        strb.append(AppConfig.APP_TITLE + "<br/>");
        strb.append("Version." + AppConfig.APP_VERSION + "<br/><br/>");
        strb.append("Originally written by:<br/>Maikel Chandika (mkdika@gmail.com)" + "<br/><br/>");
        strb.append("Fork me on GitHub:<br/><a href='https://github.com/mkdika/jclassfinder'>https://github.com/mkdika/jclassfinder</a>");
        strb.append("</body></html>");

        // html content
        JEditorPane ep = new JEditorPane("text/html", strb.toString());

        // handle link events
        ep.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                    try {
                        try {
                            Desktop.getDesktop().browse(new URI(e.getURL().toString()));
                        } catch (URISyntaxException ex) {
                            Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        ep.setEditable(false);
        ep.setBackground(label.getBackground());

        // show
        JOptionPane.showMessageDialog(comp, ep,AppConfig.APP_SHORT_TITLE,1);
    }
}
