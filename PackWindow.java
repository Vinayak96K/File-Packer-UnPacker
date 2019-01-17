import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.nio.file.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

class PackWindow extends Template implements ActionListener
{
    JButton PackBtn, BrwBtn1,BrwBtn2;
    JLabel DnameLbl, DestLabel;
    JTextField DnameTxtFld, DestTxtFld;
    public PackWindow()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        _TopLabel.setText("Pack Files Here");
        _PrevBtn.addActionListener(this);

        DnameLbl=new JLabel("Directory path:");
        DnameLbl.setBounds(50, 50, 120, 20);
        _UIPanel.add(DnameLbl);
        DnameTxtFld=new JTextField("");
        DnameTxtFld.setBounds(180, 50, 150, 20);
        DnameTxtFld.setToolTipText("Enter the directory path of files to packs!");
        _UIPanel.add(DnameTxtFld);
        BrwBtn1=new JButton(new ImageIcon("./folder-search.png"));
        BrwBtn1.setBounds(330, 50, 20, 20);
        BrwBtn1.addActionListener(this);
        BrwBtn1.setToolTipText("Browse folder!");
        _UIPanel.add(BrwBtn1);

        DestLabel=new JLabel("Destination path:");
        DestLabel.setBounds(50, 80, 120, 20);
        _UIPanel.add(DestLabel);
        DestTxtFld=new JTextField("");
        DestTxtFld.setBounds(180, 80, 150, 20);
        DestTxtFld.setToolTipText("Enter a destination path to pack file!");
        _UIPanel.add(DestTxtFld);
        BrwBtn2=new JButton(new ImageIcon("./folder-search.png"));
        BrwBtn2.setBounds(330, 80, 20, 20);
        BrwBtn2.addActionListener(this);
        BrwBtn2.setToolTipText("Browse folder!");
        _UIPanel.add(BrwBtn2);

        PackBtn=new JButton("Pack");
        PackBtn.setBounds(150, 120, 100, 25);
        PackBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        PackBtn.setToolTipText("Press to pack!");
        PackBtn.addActionListener(this);
        _UIPanel.add(PackBtn);


    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println("Reach");
        if(ae.getSource() == _PrevBtn)
        {
            this.dispose();
            WelcomeWindow lObj=new WelcomeWindow(USR_NAME);
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
        else if(ae.getSource()==PackBtn)
        {
            if((DnameTxtFld.getText().length()!=0) && (DestTxtFld.getText().length()!=0))
            {
                try
                {
                    IOFileHelper ioFileHelper=new IOFileHelper();
                    int iRet=ioFileHelper.packFile(DnameTxtFld.getText(), DestTxtFld.getText());
                    JOptionPane.showMessageDialog(this, "Successfully packed "+iRet+" files!", "Marvellous Packer-Unpacker Alert",JOptionPane.WARNING_MESSAGE);
                    this.dispose();
                    WelcomeWindow wObj=new WelcomeWindow(USR_NAME);
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