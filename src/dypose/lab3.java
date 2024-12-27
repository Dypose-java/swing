package dypose;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab3 extends JFrame {
    private JTextField textFieldX;
    private JButton Button;
    private JTextField textFieldRes;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JLabel text;
    private JLabel text2;
    private JPanel MainPanel;

    public lab3(){
        setContentPane(MainPanel);
        setTitle("Программа для расчета оплаты за горячее водоснабжение");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,350);
        setLocationRelativeTo(null);
        setVisible(true);
        textFieldRes.setEditable(false);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int K=52,L=60,A=2000,D=49,C=45,B=55;
                String strX=textFieldX.getText();
                int sumWaterX=Integer.parseInt(strX);


               if (rb1.isSelected()){

                   if (sumWaterX<K)
                       textFieldRes.setText(A+"");
                   else if(sumWaterX>=K)
                       textFieldRes.setText((D*sumWaterX)+"");

               }
               if (rb2.isSelected()){

                   if (sumWaterX<=K)
                       textFieldRes.setText((C*sumWaterX)+"");
                   else if(sumWaterX>K&&sumWaterX<L)
                       textFieldRes.setText((D*sumWaterX)+"");
                   else if(sumWaterX>=L)
                       textFieldRes.setText((B*sumWaterX)+"");
               }


            }
        });
    }

    public static void main(String[] args) {
        new lab3();
    }


}

