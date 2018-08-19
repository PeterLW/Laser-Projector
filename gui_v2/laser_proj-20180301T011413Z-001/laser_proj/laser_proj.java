package laser_proj;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import laser_proj.laser_client;

public class laser_proj extends JFrame{

    private JTextArea console;
    private JButton clear_console;
    private JButton square, triangle, M_shape, stop_button;
    
    private ImageIcon square_img = new ImageIcon(getClass().getResource("images/square_128x128.png"));
    private ImageIcon triangle_img = new ImageIcon(getClass().getResource("images/triangle_final.png"));
    private ImageIcon m_img = new ImageIcon(getClass().getResource("images/m_shape.png"));

    private final static SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");

    laser_client client_connection;
    laser_proj instance;


    public laser_proj(){
        createView();
        //pack();
        setTitle("Laser Projector GUI");
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void createView(){

        //border layout
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);
        panel.setBorder(new EmptyBorder(10,10,10,10));

        //west: border layout
        JPanel panel_west = new JPanel(new BorderLayout());
        console = new JTextArea();
        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        //console.setPreferredSize(new Dimension(350, 1000));
        JScrollPane scrollpanel = new JScrollPane(console);
        scrollpanel.setPreferredSize(new Dimension(300, 200));
        panel_west.add(scrollpanel, BorderLayout.CENTER);
        console.setBorder(BorderFactory.createTitledBorder("Console Output"));

        clear_console = new JButton("Clear Console");
        clear_console.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.setText("");
            }
        });
        panel_west.add(clear_console, BorderLayout.SOUTH);

        panel.add(panel_west, BorderLayout.WEST);

        //centre: grid bag layout
        JPanel panel_centre = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        square = new JButton("square", square_img);
        square.setPreferredSize(new Dimension(250,128));
        panel_centre.add(square, c);
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("drawing square...");
                String s = "square.";
                byte[] msg = s.getBytes();
                client_connection.sendMessage(msg);
            }
        });
        c.gridy++;

        triangle = new JButton("triangle", triangle_img);
        triangle.setPreferredSize(new Dimension(250,128));
        panel_centre.add(triangle, c);
        triangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("drawing triangle...");
                String s = "triangle.";
                byte[] msg = s.getBytes();
                client_connection.sendMessage(msg);
            }
        });
        c.gridy++;

        M_shape = new JButton("M shape", m_img);
        M_shape.setPreferredSize(new Dimension(250,128));
        panel_centre.add(M_shape, c);
        M_shape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("drawing M shape...");
                String s = "M.";
                byte[] msg = s.getBytes();
                client_connection.sendMessage(msg);
            }
        });

        c.gridy++;

        stop_button = new JButton("stop button");
        stop_button.setPreferredSize(new Dimension(250,128));
        panel_centre.add(stop_button, c);
        stop_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("stop...");
                String s = "atop.";
                byte[] msg = s.getBytes();
                client_connection.sendMessage(msg);
            }
        });

        panel.add(panel_centre, BorderLayout.CENTER);

    }

    private void log(String msg){
        console.append(date_format.format(new Date()) + ": " + msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                laser_proj lp = new laser_proj();
                lp.setVisible(true);

                lp.client_connection = new laser_client("100.64.191.138", 6000);
                try {
                    lp.client_connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    });

    }
}
