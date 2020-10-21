package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RenderingEngine {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public RenderingEngine() {
        initialiseFrame();
        initialisePanel();
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.setVisible(false);
        frame.dispose();
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints  rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }

    public void addInputListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    private void initialisePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

    private void initialiseFrame() {
        // URL iconURL = getClass().getResource("../");
        // ImageIcon icon = new ImageIcon(iconURL);
        // frame.setIconImage(icon.getImage());

        frame = new JFrame();
        frame.setSize(800, 600);
        // Pour centrer
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Moving Rectangle Game");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
