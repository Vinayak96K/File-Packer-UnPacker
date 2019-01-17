import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class BannerFrame extends JFrame
{
    public BannerFrame() throws Exception
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(725,275);
        this.setBounds(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-(this.getSize().height/2)), this.getSize().width, this.getSize().height); 
        this.add(new JLabel(new ImageIcon("./BannerImgIcon.png")));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
		Thread.sleep(8000);
        this.dispose();
        LoginWindow lObg=new LoginWindow();
    }
}