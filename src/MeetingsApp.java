import javax.swing.*;

public class MeetingsApp {

    private MeetingsFrame meetingsFrame;

    public MeetingsApp() {
        this.meetingsFrame = new MeetingsFrame("Meetings App");
        this.meetingsFrame.setSize(1100,800);
        this.meetingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.meetingsFrame.setVisible(true);
    }
}
