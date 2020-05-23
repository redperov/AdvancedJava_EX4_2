import javax.swing.*;
import java.awt.*;

public class MeetingsFrame extends JFrame {

    public MeetingsFrame(String title) {
        super(title);

        // Set layout
        setLayout(new BorderLayout());

        // Components
        ChooseDatePanel chooseDatePanel = new ChooseDatePanel();
        CalendarPanel calendarPanel = new CalendarPanel(chooseDatePanel);

        // Add components to content pane
        Container container = getContentPane();
        container.add(chooseDatePanel, BorderLayout.NORTH);
        container.add(calendarPanel, BorderLayout.CENTER);
    }


}
