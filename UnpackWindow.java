import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.nio.file.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

class UnpackWindow extends Template implements ActionListener
{
    JButton UnpackBtn,ExtractHereBtn,BrwBtn1,BrwBtn2;
    JLabel DnameLbl, DestLabel;
    JTextField DnameTxtFld, DestTxtFld;

    public UnpackWindow()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        _TopLabel.setText("Unpack Files Here");
        _PrevBtn.addActionListener(this);

        DnameLbl=new JLabel("Directory path:");
        DnameLbl.setBounds(50, 50, 120, 20);
        _UIPanel.add(DnameLbl);
        DnameTxtFld=new JTextField("");
        DnameTxtFld.setBounds(180, 50, 150, 20);
        DnameTxtFld.setToolTipText("Enter the directory path of packed files!");
        _UIPanel.add(DnameTxtFld);
        BrwBtn1=new JButton(new ImageIcon("./folder-search.png"));
        BrwBtn1.setBounds(330, 50, 20, 20);
        BrwBtn1.addActionListener(this);
        BrwBtn1.setToolTipText("Browse file!");
        _UIPanel.add(BrwBtn1);

        DestLabel=new JLabel("Destination path:");
        DestLabel.setBounds(50, 80, 120, 20);
        _UIPanel.add(DestLabel);
        DestTxtFld=new JTextField("");
        DestTxtFld.setBounds(180, 80, 150, 20);
        DestTxtFld.setToolTipText("Enter a destination path to unpack files!");
        _UIPanel.add(DestTxtFld);
        BrwBtn2=new JButton(new ImageIcon("./folder-search.png"));
        BrwBtn2.setBounds(330, 80, 20, 20);
        BrwBtn2.addActionListener(this);
        BrwBtn2.setToolTipText("Browse folder!");
        _UIPanel.add(BrwBtn2);

        UnpackBtn=new JButton("UnPack");
        UnpackBtn.setBounds(150, 120, 100, 25);
        UnpackBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        UnpackBtn.setToolTipText("Press to Unpack!");
        UnpackBtn.addActionListener(this);
        _UIPanel.add(UnpackBtn);

    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println("Reach");
        if(ae.getSource() == _PrevBtn)
        {
            this.dispose();
            WelcomeWindow pObj=new WelcomeWindow(USR_NAME);
        }
        else if(ae.getSource()==BrwBtn1)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);      //to select only directory
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                DnameTxtFld.setText(selectedFile.getAbsolutePath());
            }
        }
        else if(ae.getSource()==BrwBtn2)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);      //to select only directory
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                DestTxtFld.setText(selectedFile.getAbsolutePath());
            }
        }
        else if(ae.getSource()==UnpackBtn)
        {
            if((DnameTxtFld.getText().length()!=0) && (DestTxtFld.getText().length()!=0))
            {
                try
                {
                    IOFileHelper ioFileHelper=new IOFileHelper();
                    int iRet=ioFileHelper.unPackFiles(DnameTxtFld.getText(), DestTxtFld.getText());
                    if(iRet>0)
                    {
                        JOptionPane.showMessageDialog(this, "Successfully unpacked "+ iRet +" !", "Marvellous Packer-Unpacker Alert",JOptionPane.WARNING_MESSAGE);
                        this.dispose();
                        WelcomeWindow wObj=new WelcomeWindow(USR_NAME);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "UnPack failed!", "Marvellous Packer-Unpacker Alert",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception eObj)
                {
                    System.out.println(eObj.getMessage());
                    eObj.printStackTrace();
                    JOptionPane.showMessageDialog(this, eObj.getMessage(), "Marvellous Packer-Unpacker Alert",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}