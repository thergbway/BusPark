import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class View extends JFrame {
    TableEditorServiceInterface service;

    public View(TableEditorServiceInterface service) {
        this.service = service;

        setTitle("Редактор данных");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        update();

        setVisible(true);

    }

    private synchronized void update() {
        JPanel newPanel = null;
        try {
            newPanel = getNewMainPanel(service);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setContentPane(newPanel);

        pack();
    }

    private JPanel getNewMainPanel(TableEditorServiceInterface service) throws RemoteException {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        JPanel listNumberPanel = new JPanel(new GridLayout(1, 0));
        JLabel listNumberLabel = new JLabel("Номер следующего путевого листа: ");
        int nextListNumberValue = service.getNextListNumber();
        String nextListNumberValueAsString;
        if(nextListNumberValue == -1)
            nextListNumberValueAsString = "не задано";
        else
            nextListNumberValueAsString = "" + nextListNumberValue;
        JLabel listNumberValueLabel = new JLabel(nextListNumberValueAsString);
        JButton editListNumberValue = new JButton("Редактировать");
        JButton resetListNumberValue = new JButton("Сбросить");
        listNumberPanel.add(listNumberLabel);
        listNumberPanel.add(listNumberValueLabel);
        listNumberPanel.add(editListNumberValue);
        listNumberPanel.add(resetListNumberValue);

        resetListNumberValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    service.setNextListNumber(-1);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });

        mainPanel.add(listNumberPanel);

        return mainPanel;
    }
}
