package vanya.lab3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab3  extends JFrame{
    private JTextField min;
    private JTextField sum;
    private JButton button1;
    private JPanel mainJP;
    private JTextField restextField;
    private JTextField avg;

    public lab3() {
        setContentPane(mainJP);
        setTitle("Программа для расчета требуемого количества продавцов в супермаркете");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,350);
        setLocationRelativeTo(null);
        setVisible(true);
        restextField.setEditable(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minSellers = Integer.parseInt(min.getText());
                int sumBuyerInHour = Integer.parseInt(sum.getText());
                double avgServiceTime =Double.parseDouble(avg.getText());

                double res = sumBuyerInHour < 30 ? minSellers :
                        sumBuyerInHour >= 30 && sumBuyerInHour <= 50 ? minSellers * 1.5 :
                                sumBuyerInHour > 50 ? minSellers * 2 :
                                        -1;
                res = avgServiceTime >= 5 ? res * 1.6 : res;
                restextField.setText(String.valueOf((int)Math.ceil(res)));
            }
        });
    }

    public static void main(String[] args) {
        new lab3();
    }
}
