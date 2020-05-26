import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarItemPanel extends JPanel implements Subject {

    private JLabel dayNumber;
    private JLabel meeting;
    private boolean isActiveItem;
    private Date date;
    private List<Observer> panelClickedObservers;

    public CalendarItemPanel() {
        this.dayNumber = new JLabel();
        this.meeting = new JLabel();
        this.isActiveItem = false;
        this.panelClickedObservers = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(dayNumber);
        add(meeting);

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
        return meeting.getText();
    }

    public void setMeeting(String meeting) {
        this.meeting.setText(meeting);
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
        this.meeting.setText("");
        this.setBackground(Color.GRAY);
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
