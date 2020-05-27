import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarItemPanel extends JPanel implements Subject {

    private static final int NUM_OF_LABELS = 3;
    private JLabel dayNumber;
    private JLabel[] meeting;
    private boolean isActiveItem;
    private Date date;
    private List<Observer> panelClickedObservers;

    public CalendarItemPanel() {
        this.dayNumber = new JLabel();
        this.meeting = new JLabel[NUM_OF_LABELS];
        this.isActiveItem = false;
        this.panelClickedObservers = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(dayNumber);

        for (int i = 0; i < this.meeting.length; i++) {
            this.meeting[i] = new JLabel("");
            add(this.meeting[i]);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateAll();
            }
        });
        this.setBackground(Color.GRAY);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDayNumber() {
        return dayNumber.getText();
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber.setText(dayNumber);
    }

    public String getMeeting() {
        StringBuilder stringBuilder = new StringBuilder();

        for (JLabel label : this.meeting) {
            stringBuilder.append(label.getText());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public void setMeeting(String newMeeting) {

        if (newMeeting == null) {
            return;
        }

        for (JLabel label : this.meeting) {
            label.setText("");
        }

        String[] splitMeeting = newMeeting.split("\n");
        int counter;

        for (counter = 0; counter < this.meeting.length; counter++) {
            if (counter < splitMeeting.length) {
                this.meeting[counter].setText(splitMeeting[counter]);
            }
        }

        if (counter < splitMeeting.length) {

            for (int i = counter; i < splitMeeting.length; i++) {
                JLabel lastLabel = this.meeting[this.meeting.length - 1];
                lastLabel.setText(String.format("%s\n%s", lastLabel.getText(), splitMeeting[i]));
            }
        }
    }

    public boolean getActiveItem() {
        return this.isActiveItem;
    }

    public void setActiveItem(boolean activeItem) {
        isActiveItem = activeItem;

        if (isActiveItem) {
            this.setBackground(Color.WHITE);
        }
        else {
            this.setBackground(Color.GRAY);
        }
    }

    public void clear() {
        this.dayNumber.setText("");

        for (JLabel label : this.meeting) {
            label.setText("");
        }
        this.setBackground(Color.GRAY);
        this.setActiveItem(false);
    }

    @Override
    public void addObserver(Observer observer) {
        this.panelClickedObservers.add(observer);
    }

    @Override
    public void updateAll() {
        for(Observer observer : this.panelClickedObservers) {
            observer.update(this);
        }
    }
}
