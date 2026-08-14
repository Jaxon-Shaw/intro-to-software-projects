package csc180.shaw.jaxon.gameshub.cookieCliker.models;

import javax.swing.*;

public class MainButton {
    private int clickCount = 0;
    public void buttonClicker(){
        JFrame frame = new JFrame();
        JButton button = new JButton();

        button.addActionListener(e -> {
            clickCount++;
            button.setText("Clicks = " + clickCount);
        });

        frame.add(button);
        frame.setVisible(true);
    }
}
