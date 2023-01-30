import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.plaf.FontUIResource;

public class Mygame extends JFrame implements ActionListener{
    JLabel heading,clockLabel,temp;
    Font font = new Font("",Font.PLAIN, 40);
    JPanel mainPanel;

    JButton[] btns=new JButton[9];

    // game instance variable
    int[] gamechances ={2,2,2,2,2,2,2,2,2};
    int activeplayer=0;

    int wps[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    int winner = 2;
    boolean gameover=false;
   Mygame(){
    setTitle("my tic tav toe game ----");
    setSize(550,550);
    ImageIcon icon =new ImageIcon("img/title.png");
    setIconImage(icon.getImage());

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    createGUI();
   }

   public void createGUI(){
    this.setLayout( new BorderLayout());

    // north heading
    heading=new JLabel("Tic TAC TOE");

    heading.setFont(font);
    heading.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(heading,BorderLayout.NORTH);

    //creating object of JLABEL for clock
    clockLabel=new JLabel("clock");

    clockLabel.setFont(font);
    clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(clockLabel,BorderLayout.SOUTH);


    temp=new JLabel("Tricky");
    this.add(temp,BorderLayout.EAST);   
    Thread t = new Thread(){
        public void run() {
            try {
                while(1==1){
                    String datetime = new Date().toLocaleString();

                    clockLabel.setText(datetime);

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    };
    t.start();
    /////////////////////PANEL SECTION////////////

    mainPanel = new JPanel();

    mainPanel.setLayout(new GridLayout(3,3));
           
    for (int i = 1; i <=9; i++) {
        JButton btn=new JButton();
        // btn.setIcon(new ImageIcon("img/title.jpg"));
        // btn.setBackground(Color.decode("#90caf9"));
        btn.setFont(font);
        mainPanel.add(btn);
        btns[i-1]=btn;
        btn.addActionListener(this);
        btn.setName(String.valueOf(i-1));
    }

    this.add(mainPanel,BorderLayout.CENTER);
   }

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    // System.out.println("clicked");
    JButton curbtn =(JButton) e.getSource();
    String nameStr = curbtn.getName();
    // System.out.println(nameStr);

    if(gameover){
        JOptionPane.showMessageDialog(this,"game already over");

        return;
    }

    int name = Integer.parseInt(nameStr.trim());
    if(gamechances[name] == 2){
        if(activeplayer==1){
            // ImageIcon x =new ImageIcon("img/x.png");
            // curbtn.setIcon(x);
            curbtn.setText("X");
            gamechances[name]=activeplayer;
            activeplayer=0;
        }
        else{
            // ImageIcon x =new ImageIcon("img/o.png");
            // curbtn.setIcon(x);
            curbtn.setText("O");
            gamechances[name]=activeplayer;
            activeplayer=1;
        }

        // find the winner
        for (int []temp : wps) {
            if((gamechances[temp[0]]==gamechances[temp[1]] && gamechances[temp[1]]==gamechances[temp[2]]) && gamechances[temp[2]] != 2){
                winner=gamechances[temp[0]];
                gameover=true;
                JOptionPane.showMessageDialog(null,"player "+winner+" has won the game");
                int i = JOptionPane.showConfirmDialog(this, "do you want to play the game");
                if(i==0){
                    this.setVisible(false);
                    new Mygame();
                }
                else if(i==1){
                    System.exit(34234);
                }
                break;
            }
        }
        // draw logic
        int c=0;
        for (int x : gamechances) {
            if(x==2){
                c++;
                break;
            }
        }
        if(c==0&& gameover==false){
            JOptionPane.showMessageDialog(null,"its draw.......");

           int i=JOptionPane.showConfirmDialog(this, "play more.....");
            if(i==0){
                this.setVisible(false);
            }
            else if(i==1){
                System.exit(1212);
            }
            gameover=true;
        }
    }
    else{
        JOptionPane.showMessageDialog(null, "occupied");
    }
    
}

    
}

