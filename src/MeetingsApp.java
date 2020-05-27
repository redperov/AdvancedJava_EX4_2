import javax.swing.*;

/**
 * A meetings app for scheduling meetings in a calendar.
 */
public class MeetingsApp {

    public MeetingsApp() {
        MeetingsFrame meetingsFrame = new MeetingsFrame("Meetings App");
        meetingsFrame.setSize(1100,800);
        meetingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meetingsFrame.setVisible(true);
    }
}
