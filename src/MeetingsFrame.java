import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the meetings app.
 */
public class MeetingsFrame extends JFrame {

    public MeetingsFrame(String title) {
        super(title);

        // Set layout
        setLayout(new BorderLayout());

        // Components
        ChooseDatePanel chooseDatePanel = new ChooseDatePanel();
        MeetingsEditor meetingsEditor = new MeetingsEditor();
        CalendarPanel calendarPanel = new CalendarPanel(chooseDatePanel, meetingsEditor);
        chooseDatePanel.addObserver(calendarPanel);
        meetingsEditor.addObserver(calendarPanel);

        // Add components to content pane
        Container container = getContentPane();
        container.add(chooseDatePanel, BorderLayout.NORTH);
        container.add(calendarPanel, BorderLayout.CENTER);
        container.add(meetingsEditor, BorderLayout.SOUTH);
    }
}
