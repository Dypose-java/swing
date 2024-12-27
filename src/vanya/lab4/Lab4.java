package vanya.lab4;

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
    private JTextField name;
    private JTextField family;
    private JTextField maxSpeed;
    private JButton addBut;
    private JButton saveBut;
    private JButton printBut;
    private JTextArea textArea1;
    private JTextField x1MaxSpeed;
    private JTextField x2MaxSpeed;
    private JButton searchBut;
    private JTextArea textArea2;
    private JLabel butJL;
    private JLabel actionJL;
    private JLabel butJl2;
    private JPanel mainJP;
    private JLabel actionJL2;
    final String fileName="src/resources/lab4.dat";

    public Lab4() {
        setContentPane(mainJP);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600, 550);
        actionJL.setForeground(Color.RED);

        List<Mammal> addUser = new ArrayList<>();

        addBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser.add(new Mammal(name.getText(), family.getText(), Float.parseFloat(maxSpeed.getText())));
                textArea1.append(addUser.get(addUser.size() - 1) + "\n");
                butJL.setText("Добавление в массив");
                actionJL.setText("Обьект добавлен");
                actionJL.setForeground(Color.GREEN);
            }
        });
        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(addUser);
                    butJL.setText("Сохранение в файл");
                    actionJL.setText("Массив сохранен в файл");
                    actionJL.setForeground(Color.GREEN);
                } catch (Exception ex) {
                    actionJL.setText("Фалйл не сохранен");
                    actionJL.setForeground(Color.RED);
                }
                //textArea1.setText("");
            }
            });

        printBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                butJL.setText("Чтение массива из файла");
                try {
                    List<Mammal> read = readFile(fileName);
                    System.out.println(read);
                    read.stream().forEach(el->textArea1.append(el+"\n"));
                    actionJL.setText("Чтение успешно");
                    actionJL.setForeground(Color.GREEN);
                } catch (Exception ex) {
                    actionJL.setText("Чтение не успешно");
                    actionJL.setForeground(Color.RED);
                }
            }
        });
        searchBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    float x1= Float.parseFloat(x1MaxSpeed.getText());
                    float x2= Float.parseFloat(x2MaxSpeed.getText());
                    butJl2.setText("maxSpeed от " +x1+"\n(включительно) до " + x2+"(включительно).");

                    List<Mammal> read = readFile(fileName);
                    List<Mammal> res =read.stream().
                            filter(el->el.getMaxSpeed()>=x1&&el.getMaxSpeed()<=x2).toList();
                    int sizeRes=res.size();

                    if (sizeRes>0){
                        res.stream().forEach(el->textArea2.append(el+"\n"));
                        actionJL2.setText("Согласно условию");
                        actionJL2.setForeground(Color.GREEN);
                    }else {
                        actionJL2.setText("Таких обьектов нет!");
                        actionJL2.setForeground(Color.RED);
                    }


                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    static List<Mammal> readFile(String nameFile){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile))){

            return ((ArrayList<Mammal>)ois.readObject());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args) {
        new Lab4();

    }
}
