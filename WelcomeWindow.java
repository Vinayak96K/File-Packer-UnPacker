import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

class WelcomeWindow extends Template implements ActionListener
{
    public JButton PackBtn, UnPackBtn;

    public WelcomeWindow(String str)
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        _TopLabel.setText("Welcome:"+str);
        _PrevBtn.addActionListener(this);

        PackBtn=new JButton(new ImageIcon("./Pack1.jpg"));
        PackBtn.setBounds(160, 40, 75, 75);
        PackBtn.setBackground(Color.WHITE);
        PackBtn.setToolTipText("Pack");
        PackBtn.addActionListener(this);
        _UIPanel.add(PackBtn);

        UnPackBtn=new JButton(new ImageIcon("./UnPack.png"));
        UnPackBtn.setBounds(160, 130, 75, 75);
        UnPackBtn.setToolTipText("UnPack");
        UnPackBtn.addActionListener(this);
        _UIPanel.add(UnPackBtn);


    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println("Reach");
        if(ae.getSource() == _PrevBtn)
        {
            this.dispose();
            LoginWindow lObj=new LoginWindow();
        }
        else if(ae.getSource()==PackBtn)
        {
            this.dispose();   
            PackWindow pObj=new PackWindow();
        }
        else if(ae.getSource()==UnPackBtn)
        {
            this.dispose();   
            UnpackWindow pObj=new UnpackWindow();
        }
    }
}