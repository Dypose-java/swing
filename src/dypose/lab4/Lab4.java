package dypose.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Lab4 extends JFrame {
    private JTextField modelTF;
    private JButton addBut;
    private JButton saveBut;
    private JButton printBut;
    private JButton searchBut;
    private  JTextArea textArea1;
    private JTextField varietyTF;
    private JTextField diagonalTF;
    private JPanel mainJPanel;
    private JButton clearTextArea1;
    private JButton clearFile;
    private JLabel label1;
    private JLabel actionTA;
    private JTextArea textArea2;
    private JTextField maxDigTF;
    private JTextField minDigTF;
    private JLabel actionCondition;
    private JLabel label2;


    public Lab4() throws HeadlessException {

        setContentPane(mainJPanel);
        setTitle("Монитор");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        textArea1.setEditable(false);
        setSize(550,600);
        label1.setForeground(Color.BLUE);
        actionTA.setForeground(Color.RED);

       final List<Monitor> addUser = new ArrayList<>();
         final String fileName="src/resources/fileLab4";

        addBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Массив объектов");
                label1.setForeground(Color.BLUE);
                addUser.add(new Monitor(modelTF.getText(),varietyTF.getText(),Float.parseFloat(diagonalTF.getText())));
                textArea1.append(addUser.get(addUser.size()-1)+"\n");
                actionTA.setText("Объект добавлен в массив");
                actionTA.setForeground(Color.GREEN);
            }
        });

        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Сохранение файла");
                label1.setForeground(Color.BLUE);
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
                    oos.writeObject(addUser);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                actionTA.setText("Массив сохранен в файл");
                actionTA.setForeground(Color.GREEN);
                textArea1.setText("");
            }
        });

        printBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                label1.setText("Объекты в массиве");
                label1.setForeground(Color.BLUE);

                try {
                    List<Monitor> readFile =readFile(fileName);
                    readFile.stream().forEach(el->textArea1.append(el+"\n"));
                        actionTA.setText("Данные извлечены из файла");
                        actionTA.setForeground(Color.GREEN);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                    actionTA.setText("Файл пустой");
                    actionTA.setForeground(Color.RED);
                }

            }
        });

        searchBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea2.setText("");
                Float min= Float.valueOf(minDigTF.getText());
                Float max= Float.valueOf(maxDigTF.getText());
               label2.setText("Мониторы, диагональ которых не равны:"+min+","+max);
               label2.setForeground(Color.BLUE);

               try {

                   List<Monitor> readFile = readFile(fileName);
                   List<Monitor> listCondition = readFile.stream()
                           .filter(el -> el.getDiagonal() != min && el.getDiagonal() != max)
                           .toList();

                   boolean isCondition =readFile.stream().anyMatch(el->el.getDiagonal()==min || el.getDiagonal()==max);

                   if (isCondition){
                       listCondition.stream().forEach(el->textArea2.append(el+"\n"));
                       actionCondition.setText("Обьекты согласно условию");
                       actionCondition.setForeground(Color.GREEN);
                   }else {
                       actionCondition.setText("Таких объектов нет!1");
                       actionCondition.setForeground(Color.RED);
                   }

               } catch (RuntimeException ex) {
                   System.out.println(ex.getMessage());
               }
            }
        });
        clearTextArea1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Очистка текстовой области");
                label1.setForeground(Color.BLUE);
                textArea1.setText("");
                actionTA.setText("Очистка");
                actionTA.setForeground(Color.GREEN);
            }
        });

        clearFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Очистка файла");
                label1.setForeground(Color.BLUE);
                textArea1.setText("");
                try {
                    new FileOutputStream(fileName, false).close();
                    actionTA.setText("Данные удалены из файла");
                    actionTA.setForeground(Color.GREEN);
                    addUser.clear();//
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

static List<Monitor> readFile(String nameFile){
    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile))){

        return ((ArrayList<Monitor>)ois.readObject());

    } catch (Exception ex) {
        throw new RuntimeException(ex);
    }
}

}

