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

    public void clear() {
        this.dayNumber.setText("");
        this.meeting.setText("");
    }
}
