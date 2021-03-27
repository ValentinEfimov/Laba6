package bsu.rfe.java.group7.lab6.Efimov.varB3;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {

    // Константы, задающие размер окна приложения, если оно
// не распахнуто на весь экран
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem magnetismOFFMenuItem;
    private JMenuItem magnetismONMenuItem;
    // Поле, по которому прыгают мячи
    private Field field = new Field();

    // Конструктор главного окна приложения
    public MainFrame() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
        setExtendedState(MAXIMIZED_BOTH);
// Создать меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() &&
                        !resumeMenuItem.isEnabled()) {
// Ни один из пунктов меню не являются
// доступными - сделать доступным "Паузу"
                    pauseMenuItem.setEnabled(true);
                    magnetismOFFMenuItem.setEnabled(true);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);

        JMenu magnetism = new JMenu("Магнетизм");
        menuBar.add(magnetism);
        Action magnetismOFFAction = new AbstractAction("Выключить") {
            public void actionPerformed(ActionEvent event) {
                //TODO
                field.magnetismOFF();
                magnetismOFFMenuItem.setEnabled(false);
                magnetismONMenuItem.setEnabled(true);
            }
        };
        magnetismOFFMenuItem = magnetism.add(magnetismOFFAction);
        magnetismOFFMenuItem.setEnabled(false);

        Action magnetismONAction = new AbstractAction("Включить") {
            public void actionPerformed(ActionEvent event) {
                //TODO
                field.magnetismON();
                magnetismOFFMenuItem.setEnabled(true);
                magnetismONMenuItem.setEnabled(false);
            }
        };
        magnetismONMenuItem = magnetism.add(magnetismONAction);
        magnetismONMenuItem.setEnabled(false);

        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение") {
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
// Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);
    }

    // Главный метод приложения
    public static void main(String[] args) {
// Создать и сделать видимым главное окно приложения
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
