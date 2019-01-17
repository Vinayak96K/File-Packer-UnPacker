import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

class LoginWindow extends Template implements ActionListener
{
    public javax.swing.JLabel UnameLabel,PwdLabel;
    public JTextField UnameTxtField;
    public JPasswordField PwdTextField;
    public JButton Submit_btn,Close_Btn;
    public static int Attempts=0;

    public LoginWindow()
    {
        _UIPanel.setVisible(false);
        _TopLabel.setText("Enter Login Details");    
        _PrevBtn.setVisible(false);

        UnameLabel =new JLabel("Username:");
        UnameLabel.setBounds(50, 50, 75, 25);
        _UIPanel.add(UnameLabel);

        UnameTxtField =new JTextField();
        UnameTxtField.setBounds(150, 50, 175, 25);
        UnameTxtField.setToolTipText("Enter Username!");
        _UIPanel.add(UnameTxtField);

        PwdLabel =new JLabel("Password:");
        PwdLabel.setBounds(50, 100, 75, 25);
        _UIPanel.add(PwdLabel);

        PwdTextField =new JPasswordField();
        PwdTextField.setBounds(150, 100, 175, 25);
        PwdTextField.setToolTipText("Password should contain atleast 3 capital and 3 small letters, 2 digits and 1 special symbol!");
        _UIPanel.add(PwdTextField);

        Submit_btn=new JButton("SUBMIT");
        Submit_btn.setBounds(50, 150, 100, 25);
        Submit_btn.setVisible(true);
        Submit_btn.setEnabled(true);
        Submit_btn.addActionListener(this);
        _UIPanel.add(Submit_btn);

        Close_Btn=new JButton("CANCEL");
        Close_Btn.setBounds(222, 150, 100, 25);
        Close_Btn.setVisible(true);
        Close_Btn.setEnabled(true);
        Close_Btn.addActionListener(this);
        _UIPanel.add(Close_Btn);
        _UIPanel.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==Close_Btn)
        {
            System.exit(0);
        }
        else if(ae.getSource()==Submit_btn)
        {
            String strUname = UnameTxtField.getText();
            String strPass=PwdTextField.getText();
            if((strUname.length()==0)||(strPass.length()==0))
            {
                System.out.println("Some fields are empty!");
                JOptionPane.showMessageDialog(this, "Some fields are empty!", "Marvellous Packer-Unpacker Alert",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(strUname.equals(USR_NAME)==false)
            {
                System.out.println("Invalid Username!");
                Attempts++;
                JOptionPane.showMessageDialog(this, "Invalid Username!(Attempts:"+Attempts+"/3)", "Marvellous Packer-Unpacker Alert",JOptionPane.ERROR_MESSAGE);
                if(Attempts>=3)
                {
                    this.dispose();
                    System.exit(0);
                }
                return;
            }
            boolean bRet=ValidatePassword(strPass);
            if(bRet==false)
            {
                System.out.println("Invalid Password!");
                Attempts++;
                JOptionPane.showMessageDialog(this, "Invalid Password!(Attempts:"+Attempts+"/3)", "Marvellous Packer-Unpacker Alert",JOptionPane.ERROR_MESSAGE);                
                if(Attempts>=3)
                {
                    this.dispose();
                    System.exit(0);
                }
                return;
            }
            this.dispose();
            WelcomeWindow wObj=new WelcomeWindow(strUname);
        }
    }

    public boolean ValidatePassword(String Pwd)
    {
        boolean bRet=false;
        String regex1="[a-z]";
        String regex2="[A-Z]";
        String regex3="[0-9]";
        int CapCnt=0,SmallCnt=0,DigCount=0,OthrCnt=0;
        for(int i=0;i<Pwd.length();i++)
        {
            char ch =Pwd.charAt(i);
            if(String.valueOf(ch).matches(regex1))
            {
                SmallCnt++;
            }
            else if(String.valueOf(ch).matches(regex2))
            {
                CapCnt++;
            }
            else if(String.valueOf(ch).matches(regex3))
            {
                DigCount++;
            }
            else
            {
                OthrCnt++;
            }
        }
        if((CapCnt>=3)&&(SmallCnt>=3)&&(DigCount>=2)&&(OthrCnt>=1))
        {
            bRet=true;
        }
        else
        {
            bRet=false;
        }
        return bRet;
    }
}