import javax.swing.*;

public class CalendarItemPanel extends JPanel {

    private JLabel dayNumber;
    private JLabel meeting;

    public CalendarItemPanel() {
        this.dayNumber = new JLabel();
        this.meeting = new JLabel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(dayNumber);
        add(meeting);
    }

    public JLabel getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(JLabel dayNumber) {
        this.dayNumber = dayNumber;
    }

    public JLabel getMeeting() {
        return meeting;
    }

    public void setMeeting(JLabel meeting) {
        this.meeting = meeting;
    }
}
