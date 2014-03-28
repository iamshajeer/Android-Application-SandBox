/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis;

import com.shajeer.aasandbox.Home;
import com.shajeer.static_analysis.dex2class.ExtractJarFile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Shajeer
 */
public class ManifestViewer extends javax.swing.JFrame {

    /**
     * Creates new form ManifestViewer
     */
    JTree jt;
    Document doc;
    File file;

    public ManifestViewer() {

        initComponents();
        createJTree();
        displayManifest();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                //if (Home.style.equals(info.getName())) {
                if (Home.style.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(244, 244, 244));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(102, 0, 102));
        jTextArea1.setRows(5);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 295, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (Home.style.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManifestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManifestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManifestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManifestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new ManifestViewer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private void createJTree() {
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("Android Manifest.xml");

        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Permissions");
        main.add(parent1);
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Activites");
        main.add(parent2);
        DefaultMutableTreeNode parent3 = new DefaultMutableTreeNode("Services");
        main.add(parent3);
        DefaultMutableTreeNode parent4 = new DefaultMutableTreeNode("Providers");
        main.add(parent4);
        DefaultMutableTreeNode parent5 = new DefaultMutableTreeNode("Recievers");
        main.add(parent5);

        DefaultMutableTreeNode parent6 = new DefaultMutableTreeNode("Uses Permissions");
        main.add(parent6);
        DefaultMutableTreeNode parent7 = new DefaultMutableTreeNode("Permission Tree");
        main.add(parent7);
        DefaultMutableTreeNode parent8 = new DefaultMutableTreeNode("Permission Group");
        main.add(parent8);
        DefaultMutableTreeNode parent9 = new DefaultMutableTreeNode("Instrumentation");
        main.add(parent9);
        DefaultMutableTreeNode parent10 = new DefaultMutableTreeNode("Uses SDK");
        main.add(parent10);
        DefaultMutableTreeNode parent11 = new DefaultMutableTreeNode("Uses Configuration");
        main.add(parent11);
        DefaultMutableTreeNode parent12 = new DefaultMutableTreeNode("Uses Features");
        main.add(parent12);
        DefaultMutableTreeNode parent13 = new DefaultMutableTreeNode("Compatible Screens");
        main.add(parent13);
        DefaultMutableTreeNode parent14 = new DefaultMutableTreeNode("Supports GL Texture");
        main.add(parent14);

        jt = new JTree(main);
        //  jt.setBackground(Color.DARK_GRAY);
        jt.setAutoscrolls(true);
      //  jt.setBackground(Color.DARK_GRAY);
        if (jt.getCellRenderer() instanceof DefaultTreeCellRenderer) {
            final DefaultTreeCellRenderer renderer
                    = (DefaultTreeCellRenderer) (jt.getCellRenderer());
            renderer.setTextNonSelectionColor(Color.BLACK);
            renderer.setTextSelectionColor(Color.YELLOW);
        } else {
            JOptionPane.showMessageDialog(this, "Error Loading Tree View", "Something went wrong", JOptionPane.ERROR_MESSAGE);
        }

        jt.setSize(285, 500);
        jt.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(jt);
        jt.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

                String action = e.getNewLeadSelectionPath().getLastPathComponent().toString();
                displayManifest();
                if (action.equals("Android Manifest.xml")) {
                    jTextArea1.setText("");
                    try {
                      //  disaplyCompleteXml(file);
                    } catch (Exception ex) {
                        
                    }
                }
                switch (action) {
                    case "Permissions":
                        jTextArea1.setText("");
                        granted("uses-permission");
                        break;
                    case "Activites":
                        jTextArea1.setText("");
                        granted("activity");
                        break;
                    case "Services":
                        jTextArea1.setText("");
                        granted("service");
                        break;
                    case "Providers":
                        jTextArea1.setText("");
                        granted("provider");
                        break;
                    case "Recievers":
                        jTextArea1.setText("");
                        granted("receiver");
                        break;
                    case "Uses Permissions":
                        jTextArea1.setText("");
                        granted("uses-permission");
                        break;
                    case "Permission Tree":
                        jTextArea1.setText("");
                        granted("permission-tree");
                        break;
                    case "Permission Group":
                        jTextArea1.setText("");
                        granted("permission-group");
                        break;
                    case "Instrumentation":
                        jTextArea1.setText("");
                        granted("instrumentation");
                        break;
                    case "Uses SDK":
                        jTextArea1.setText("");
                        granted("uses-sdk");
                        break;
                    case "Uses Configuration":
                        jTextArea1.setText("");
                        granted("uses-configuration");
                        break;
                    case "Supports Screens":
                        jTextArea1.setText("");
                        granted("supports-screens");
                        break;
                    case "Compatible Screen":
                        jTextArea1.setText("");
                        granted("compatible-screens");
                        break;
                    case "Uses Features":
                        jTextArea1.setText("");
                        granted("uses-feature");
                        break;
                    case "Supports GL Texture":
                        jTextArea1.setText("");
                        granted("supports-gl-texture");
                        break;
                    default:
                        break;
                }
            }
        });        
    }

    private void displayManifest() {
        file = new File(System.getProperty("java.io.tmpdir") + "\\android sandbox\\Manifest.xml");
        parseXml(file);
    }

    private void parseXml(File fXmlFile) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //call method for getting the fieldInXml provided 
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName().toString());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.getCause();
        }
    }

    private void disaplyCompleteXml(File file) throws FileNotFoundException, IOException {
        jTextArea1.setText("");
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null) {
            jTextArea1.append((br.readLine().replaceAll("\\s",""))+"\n");
        }
    }

    protected void granted(String tag) {
        String[] fieldInXml = fieldInXml = new String[30];//array for apanding fieldInXml from manifest

        NodeList nList = doc.getElementsByTagName(tag);
        int temp = 0;
        for (temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                //System.out.println(tag);
                Element eElement = (Element) nNode;
                fieldInXml[temp] = eElement.getAttributeNode("android:name").toString();

            }
        }
        for (int i = 0; i < temp; i++) {
            //variable for removing un necessery parts from name
            String nameFiltered = fieldInXml[i];
            //nameFiltered = fieldInXml[i].replace(".", " ");
            if (tag.equals("uses-permission")) {
                nameFiltered = nameFiltered.substring(33);

                nameFiltered = nameFiltered.replace("\"", " ");
            } else if (tag.equals("activity")) {
                nameFiltered = nameFiltered.substring(14);

                nameFiltered = nameFiltered.replace("\"", " ");
            } else if (tag.equals("service")) {
                nameFiltered = nameFiltered.substring(13);

                nameFiltered = nameFiltered.replace("\"", " ");
            } else if (tag.equals("provider")) {
                nameFiltered = nameFiltered.substring(13);

                nameFiltered = nameFiltered.replace("\"", " ");
            } else if (tag.equals("receiver")) {
                nameFiltered = nameFiltered.substring(13);

                nameFiltered = nameFiltered.replace("\"", " ");
            }
            //nameFiltered = fieldInXml[i].replace("\"", " ");
            jTextArea1.append(i + 1 + " : " + nameFiltered + "\n");
            jTextArea1.append("\n");
        }

    }

}
