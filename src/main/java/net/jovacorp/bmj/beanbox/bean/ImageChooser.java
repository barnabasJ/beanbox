package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.ImageEvent;
import net.jovacorp.bmj.beanbox.listener.ImageListener;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

public class ImageChooser extends Label {

    private File file;
    private Vector<ImageListener> listeners;

    public ImageChooser() {
        listeners = new Vector<>();
        setFileText();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openFileChooser();
                setFileText();
                fireImageEvent();
            }
        });
    }

    public void addImageListener(ImageListener listener) {
        if (listeners == null) {
            listeners = new Vector<>();
        }
        listeners.add(listener);

        fireImageEvent();
    }

    public void removeImageListener(ImageListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    public void fireImageEvent() {
        PlanarImage image = null;
        if (file != null) {
            image = JAI.create("fileload", file.getAbsolutePath());
        }

        ImageEvent event = new ImageEvent(this, image);
        for (ImageListener listener : listeners) {
            listener.sourceChanged(event);
        }
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(imageFilter);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    private void setFileText() {
        if (file == null) {
            setText("Click to choose image");
        } else {
            setText(file.getAbsolutePath());
        }
    }
}
