package com.mkdika.jclassfinder.ui.swing;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.mkdika.jclassfinder.config.AppConfig;
import com.mkdika.jclassfinder.helper.AppUtil;
import com.mkdika.jclassfinder.helper.filefilter.GenericExtFilter;
import com.mkdika.jclassfinder.helper.filefilter.JarExtFilter;
import com.mkdika.jclassfinder.model.ClassModel;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author maikel
 */
public class MainFrame extends javax.swing.JFrame {

    private JFileChooser jfcBrowseFolder;
    private DefaultListModel jarListModel, classListModel;
    private List<File> listFile;
    private List<ClassModel> classListIndex;
    private LoadIndexWorker loadIndexWorker;
    private LoadSearchWorker loadSearchWorker;
    private int totalClass = 0;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initListContextMenu();
        setLocationRelativeTo(null);
        setTitle(AppConfig.APP_TITLE + " v" + AppConfig.APP_VERSION);

        // init all instance variable.
        listFile = new ArrayList<>();
        jarListModel = new DefaultListModel();
        classListModel = new DefaultListModel();
        classListIndex = new ArrayList<>();
    }

    private void initListContextMenu() {

        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openFolder = new JMenuItem("Open Containing Folder");
        openFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File f = (File) (Object) listJarFile.getSelectedValue();
                openFolderToExplorer(f.getParent());
            }
        });
        popupMenu.add(openFolder);
        listJarFile.setComponentPopupMenu(popupMenu);

        final JPopupMenu popupMenu2 = new JPopupMenu();
        JMenuItem openFolder2 = new JMenuItem("Open Containing Folder");
        openFolder2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp[] = listClass.getSelectedValue().split(":");
                File f = new File(tmp[0].trim());
                openFolderToExplorer(f.getParent());
            }
        });
        popupMenu2.add(openFolder2);
        listClass.setComponentPopupMenu(popupMenu2);
    }

    private void openFolderToExplorer(String path) {
        try {
            File f = new File(path);
            if (path.trim().isEmpty()) {
                AppUtil.msg(this, "Folder Path is empty.");
            } else if (!f.exists()) {
                AppUtil.msg(this, "Folder does not exists.");
            } else {
                Desktop.getDesktop().open(f);
            }

        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            AppUtil.msg(this, ex.getLocalizedMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtLocation = new javax.swing.JTextField();
        btnBrowseLocation = new javax.swing.JButton();
        chkInclude = new javax.swing.JCheckBox();
        btnOpenFolder = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pbmain = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        lblTotJar = new javax.swing.JLabel();
        lblTotClass = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listClass = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listJarFile = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JAR File / Folder Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11))); // NOI18N

        txtLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLocationFocusLost(evt);
            }
        });
        txtLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocationActionPerformed(evt);
            }
        });

        btnBrowseLocation.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btnBrowseLocation.setText("Browse");
        btnBrowseLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseLocationActionPerformed(evt);
            }
        });

        chkInclude.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        chkInclude.setSelected(true);
        chkInclude.setText("Include Sub-Folder");
        chkInclude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkIncludeStateChanged(evt);
            }
        });
        chkInclude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIncludeActionPerformed(evt);
            }
        });

        btnOpenFolder.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btnOpenFolder.setText("Open Folder");
        btnOpenFolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnOpenFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkInclude)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOpenFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowseLocation)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkInclude)
                    .addComponent(btnOpenFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pbmain.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        pbmain.setForeground(new java.awt.Color(0, 0, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("About");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        lblTotJar.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblTotJar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotJar.setText("Total JAR File: 0");
        lblTotJar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotClass.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblTotClass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotClass.setText("Total Class: 0");
        lblTotClass.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotJar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotClass, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(pbmain, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbmain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTotJar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTotClass, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblTotClass, lblTotJar});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html>JAR File : Class <i>(right click to show context menu)</i></html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11))); // NOI18N

        listClass.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(listClass);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html>JAR Files <i>(right click to show context menu)</i></html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11))); // NOI18N

        listJarFile.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPane2.setViewportView(listJarFile);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Java Class (Can be wildcard search)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11))); // NOI18N

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSearch)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseLocationActionPerformed
        browseFolder();
    }//GEN-LAST:event_btnBrowseLocationActionPerformed

    private void txtLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationActionPerformed
        loadIndexWorker = new LoadIndexWorker();
        loadIndexWorker.execute();
    }//GEN-LAST:event_txtLocationActionPerformed

    private void txtLocationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLocationFocusLost

    }//GEN-LAST:event_txtLocationFocusLost

    private void chkIncludeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkIncludeStateChanged
//        if (!txtLocation.getText().trim().isEmpty()) {
//            loadJarFilesList(txtLocation.getText(), chkInclude.isSelected(), chkShowPath.isSelected());
//        }
    }//GEN-LAST:event_chkIncludeStateChanged

    private void chkIncludeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIncludeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkIncludeActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        showAbout();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        loadSearchWorker = new LoadSearchWorker();
        loadSearchWorker.execute();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadSearchWorker = new LoadSearchWorker();
        loadSearchWorker.execute();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnOpenFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFolderActionPerformed
        openFolderToExplorer(txtLocation.getText().trim());
    }//GEN-LAST:event_btnOpenFolderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            Plastic3DLookAndFeel lnf = new PlasticXPLookAndFeel();
            Plastic3DLookAndFeel.setCurrentTheme(new ExperienceBlue());
            com.jgoodies.looks.Options.setPopupDropShadowEnabled(true);
            UIManager.setLookAndFeel(lnf);

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        new MainFrame().setVisible(true);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rearrangeViewList(boolean selected) {
        if (listFile.size() > 0) {
            jarListModel.removeAllElements();
            for (File file : listFile) {
                if (selected) {
                    jarListModel.addElement(file.getAbsolutePath());
                } else {
                    jarListModel.addElement(file.getName());
                }
            }
        }

        if (classListIndex.size() > 0) {

        }
    }

    private class LoadIndexWorker extends SwingWorker<Boolean, Void> {

        @Override
        protected void done() {
            try {
                if (get() != null) {
                    pbmain.setIndeterminate(false);
                    pbmain.setString("Indexig Class Done");
                    pbmain.setValue(0);
                    enableForm(true);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        protected Boolean doInBackground() {
            enableForm(false);
            pbmain.setStringPainted(true);
            pbmain.setString("Load JAR File");
            pbmain.setIndeterminate(true);
            loadJarFilesList(txtLocation.getText(), chkInclude.isSelected());
            if (listFile.size() > 0) {
                int c = 0;
                totalClass = 0;
                classListIndex = new ArrayList<>();
                pbmain.setIndeterminate(false);
                pbmain.setString("Indexing Class 0 / " + listFile.size());
                pbmain.setValue(0);
                pbmain.setMaximum(listFile.size());
                for (File file : listFile) {
                    try {
                        JarInputStream jarFile = new JarInputStream(new FileInputStream(file.getAbsolutePath()));
                        JarEntry jarEntry;

                        while (true) {
                            jarEntry = jarFile.getNextJarEntry();
                            if (jarEntry == null) {
                                break;
                            }
                            if (jarEntry.getName().endsWith(".class")) {
                                classListIndex.add(new ClassModel(file, jarEntry.getName().replace("/", ".").substring(0, jarEntry.getName().length() - 6)));
                                totalClass++;
                            }
                        }
                        c++;
                        pbmain.setString("Indexing Class " + c + " / " + listFile.size());
                        pbmain.setValue(c);
                    } catch (IOException e) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                pbmain.setString("Sorting");
                // sort index
                Collections.sort(classListIndex, new Comparator<ClassModel>() {
                    @Override
                    public int compare(ClassModel cm1, ClassModel cm2) {
                        return cm1.getClassPath().compareTo(cm2.getClassPath());
                    }
                });
                lblTotClass.setText("Total Class: 0 / " + totalClass);
            }
            return true;
        }
    }

    private class LoadSearchWorker extends SwingWorker<Boolean, Void> {

        @Override
        protected void done() {
            try {
                if (get() != null) {
                    pbmain.setIndeterminate(false);
                    pbmain.setString("Search Done");
                    pbmain.setValue(0);
                    enableForm(true);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        protected Boolean doInBackground() {
            enableForm(false);
            pbmain.setStringPainted(true);
            pbmain.setString("Searching class");
            pbmain.setIndeterminate(true);
            searchClass(txtSearch.getText().trim().replace("/", "."));
            return true;
        }
    }

    private void enableForm(boolean b) {
        txtLocation.setEnabled(b);
        btnBrowseLocation.setEnabled(b);
        chkInclude.setEnabled(b);
        txtSearch.setEnabled(b);
        btnSearch.setEnabled(b);
        listJarFile.setEnabled(b);
        listClass.setEnabled(b);
        btnOpenFolder.setEnabled(b);
    }

    private void browseFolder() {
        jfcBrowseFolder = new JFileChooser();
        jfcBrowseFolder.setDialogTitle("Open JAR File/ Folder Path");
        jfcBrowseFolder.setFileFilter(new JarExtFilter());
        jfcBrowseFolder.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfcBrowseFolder.setApproveButtonText("Open");

        if (jfcBrowseFolder.showOpenDialog(this) != 1) {
            txtLocation.setText(jfcBrowseFolder.getSelectedFile().getAbsolutePath());
            loadIndexWorker = new LoadIndexWorker();
            loadIndexWorker.execute();
        }
    }

    private void clearJarList() {
        listFile.clear();
        jarListModel.removeAllElements();
        lblTotJar.setText("Total JAR File: 0");
        clearClassList();
    }

    private void clearClassList() {
        classListModel.removeAllElements();
        lblTotClass.setText("Total Class: 0 / 0");
    }

    private void showAbout() {
        AppUtil.showAbout(this);
    }

    private void searchClass(String key) {
        if (classListIndex.size() > 0) {
            classListModel = new DefaultListModel();
            StringBuilder pattern = new StringBuilder();
            pattern.append("*");
            pattern.append(key.toLowerCase());
            pattern.append("*");
            String searchKey = pattern.toString().replaceAll("\\*", ".*");

            for (ClassModel cm : classListIndex) {
                if (cm.getClassPath().toLowerCase().matches(searchKey)) {
                    classListModel.addElement(cm.getJarFile().getAbsolutePath() + " : " + cm.getClassPath());
                }
            }
            listClass.setModel(classListModel);
            lblTotClass.setText("Total Class: " + classListModel.size() + " / " + totalClass);
        } else {
            clearClassList();
            AppUtil.msg(this, "No JAR files in list.<br/>Load some first.", AppUtil.MESSAGE_WARNING);
        }
    }

    private void loadJarFilesList(String absPath, boolean rec) {
        File file = new File(absPath);
        GenericExtFilter filter = new GenericExtFilter("jar");

        if (absPath.trim().isEmpty()) {
            AppUtil.msg(this, "Path is empty.", AppUtil.MESSAGE_WARNING);
            clearJarList();
        } else if (file == null || !file.exists()) {
            AppUtil.msg(this, "File or Folder doesnt exist.", AppUtil.MESSAGE_WARNING);
            clearJarList();
        } else {
            jarListModel = new DefaultListModel();
            if (file.isFile()) {
                listFile = new ArrayList<>();
                jarListModel.addElement(file.getAbsoluteFile());
                listFile.add(file);
                lblTotJar.setText("Total JAR File: 1");
            } else if (file.isDirectory()) {
                if (file.list().length > 0) {
                    if (rec) {
                        String[] extensions = new String[]{"jar"};
                        listFile = (List<File>) FileUtils.listFiles(file, extensions, true);
                        Collections.sort(listFile, new Comparator<File>() {
                            @Override
                            public int compare(File file1, File file2) {
                                return file1.getName().compareTo(file2.getName());
                            }
                        });
                        for (File f : listFile) {
                            jarListModel.addElement(f.getAbsoluteFile());
                        }
                        lblTotJar.setText("Total JAR File: " + listFile.size());
                    } else {
                        listFile = new ArrayList<>();
                        String[] tmp = file.list(filter);
                        Arrays.sort(tmp);
                        for (String s : tmp) {
                            File f = new File(absPath + File.separator + s);
                            if (f.isFile()) {
                                jarListModel.addElement(absPath + File.separator + s);
                                listFile.add(new File(absPath + File.separator + s));
                            }
                        }
                        lblTotJar.setText("Total JAR File: " + listFile.size());
                    }
                } else {
                    AppUtil.msg(this, "Folder is empty.");
                }
            }
            listJarFile.setModel(jarListModel);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseLocation;
    private javax.swing.JButton btnOpenFolder;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chkInclude;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotClass;
    private javax.swing.JLabel lblTotJar;
    private javax.swing.JList<String> listClass;
    private javax.swing.JList<String> listJarFile;
    private javax.swing.JProgressBar pbmain;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
