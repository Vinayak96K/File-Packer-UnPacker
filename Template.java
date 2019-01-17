import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Template extends JFrame implements ActionListener 
{
    public JPanel _UIPanel,_HeaderPanel,_Footer;
    public JLabel _Label1; 
    public JLabel _TopLabel;
    public JButton _PrevBtn;
    protected final int iWidth=400;
    protected final int iHeight=400;
    public static final String USR_NAME="Admin";
    GridBagConstraints gbc=new GridBagConstraints();

    public Template()
    {
        //super("Marvellous Packer-UnPacker");
        this.setTitle("Marvellous Packer-UnPacker");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setUndecorated(false);
        //Runtime.getRuntime().exec(arg0, arg1, arg2);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(iWidth,iHeight);
        this.setBounds(((dim.width/2)-(this.iWidth/2)), ((dim.height/2)-(this.iHeight/2)), this.iWidth, this.iHeight);
        this.setResizable(false);

        _HeaderPanel=new JPanel();
        _HeaderPanel.setLayout(null);
        _HeaderPanel.setBounds(0, 0, iWidth, 100);
        _TopLabel=new JLabel("Some_Text_Will_Appear_Here:");
        _TopLabel.setBounds(120, 70, 200, 20);
        _TopLabel.setFont(new Font("Dyuthi", Font.PLAIN, 22));
        _HeaderPanel.add(_TopLabel);
        _HeaderPanel.setBackground(new Color(0, 175, 255));
        _PrevBtn=new JButton(new ImageIcon("./Prev.png")); 
        _PrevBtn.setBounds(0, 5, 50, 50);
        _PrevBtn.setBackground(new Color(0, 175, 255));
        _PrevBtn.setBorder(null);
        _PrevBtn.setVisible(true);
        _PrevBtn.setToolTipText("Go back");
        _HeaderPanel.add(_PrevBtn);
        _HeaderPanel.setVisible(true);
        getContentPane().add(_HeaderPanel);
        
        _UIPanel=new JPanel();
        _UIPanel.setLayout(null);
        _UIPanel.setBounds(0, 100, iWidth, 300);
        _UIPanel.setBackground(new Color(215, 255, 255));
        _UIPanel.setVisible(true);
        getContentPane().add(_UIPanel);
        
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println("Reach1");
    }

}