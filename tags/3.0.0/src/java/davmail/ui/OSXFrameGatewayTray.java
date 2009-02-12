package davmail.ui;

import davmail.tray.DavGatewayTray;
import davmail.tray.FrameGatewayTray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MacOSX specific frame to handle menu
 */
public class OSXFrameGatewayTray extends FrameGatewayTray {

    public boolean quit() {
        return true;
    }

    protected void buildMenu() {
        // create a popup menu
        JMenu menu = new JMenu("Logs");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        JMenuItem logItem = new JMenuItem("Show logs...");
        logItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLogs();
            }
        });
        menu.add(logItem);
    }


    protected void createAndShowGUI() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        super.createAndShowGUI();
        try {
            OSXAdapter.setAboutHandler(this, FrameGatewayTray.class.getDeclaredMethod("about", (Class[]) null));
            OSXAdapter.setPreferencesHandler(this, FrameGatewayTray.class.getDeclaredMethod("preferences", (Class[]) null));
        } catch (Exception e) {
            DavGatewayTray.error("Error while loading the OSXAdapter", e);
        }
    }
}
