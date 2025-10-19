import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Frame extends JPanel{
    private JFrame frame;
    private User user;
    private String username;
    private String firstName;

    public Frame() {
        user = new User();
        frame = new JFrame("mAIk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        showWelcomeScreen();
        frame.setVisible(true);
    }

    private void showWelcomeScreen() {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(30, 144, 255)); // Dodger Blue
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(60, 60, 60, 60),
        BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            "MigraineMike",
            0, 0,
            new Font("SansSerif", Font.BOLD, 18),
            Color.WHITE
        )
    ));

    JLabel title = new JLabel("Welcome");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("SansSerif", Font.BOLD, 32));
    title.setForeground(Color.WHITE);

    JButton loginButton = new JButton("Login");
    styleButton(loginButton);
    loginButton.addActionListener(e -> showLoginScreen());

    JButton signupButton = new JButton("Sign Up");
    styleButton(signupButton);
    signupButton.addActionListener(e -> showSignupScreen());

    panel.add(title);
    panel.add(Box.createRigidArea(new Dimension(0, 40)));
    panel.add(loginButton);
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(signupButton);

    frame.getContentPane().removeAll();
    frame.getContentPane().add(panel);
    frame.revalidate();
    frame.repaint();
}

    private void showLoginScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JButton submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        submitButton.addActionListener(e -> {
            username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            firstName = user.login(username, password);
            if (firstName != null) showHomePage(firstName);
        });

        backButton.addActionListener(e -> showWelcomeScreen());
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showSignupScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        JLabel nameLabel = new JLabel("First Name:");
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        JButton signupButton = new JButton("Sign Up");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(signupButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridy = 5;
        panel.add(backButton, gbc);

        signupButton.addActionListener(e -> {
            String firstName = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            user.signUp(username, password, firstName);
            JOptionPane.showMessageDialog(frame, "Account created!");
            showWelcomeScreen();
        });

        backButton.addActionListener(e -> showWelcomeScreen());

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showHomePage(String firstName) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180)); // Steel Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Dashboard",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        JLabel welcomeLabel = new JLabel("Welcome, " + firstName + "!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] buttons = {"Profile","Head", "Calendar", "Talk to Mike", "Help", "Logout"};
        for (String label : buttons) {
            JButton btn = new JButton(label);
            styleButton(btn);

            switch (label) {
                case "Profile" -> btn.addActionListener(e -> showProfilePanel());
                case "Head" -> btn.addActionListener(e -> showBodyPanel());
                case "Calendar" -> btn.addActionListener(e -> showCalendarPanel());
                case "Talk to Mike" -> btn.addActionListener(e -> showPredictivePanel());
                case "Help" -> btn.addActionListener(e -> showHelpPanel());
                case "Logout" -> btn.addActionListener(e -> showWelcomeScreen());
            }

            panel.add(btn);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showProfilePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180)); // Steel Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Profile",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        String name = this.firstName;       // e.g., "Jackson"
        String username = this.username;   // e.g., "jn8"
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        JLabel titleLabel = new JLabel("Hello, " + name + "!");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username: " + username);
        styleProfileLabel(usernameLabel);

        JLabel dateLabel = new JLabel("Today: " + today);
        styleProfileLabel(dateLabel);

        JButton backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(e -> showHomePage(name));

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(usernameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(dateLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void styleProfileLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(new Color(25, 25, 112));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void showHelpPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180)); // Steel Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Help & Info",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        JLabel title = new JLabel("About Migraine Mike");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        JTextArea description = new JTextArea(10, 40); // Adjusted size to fit all text
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setBackground(new Color(240, 248, 255)); // Light background for readability
        description.setText(
            "This app helps users track and analyze migraine patterns.\n\n" +
            "• Input pain levels (1–10) for different parts of your head before, during, or after a migraine.\n" +
            "• Add personal notes to document symptoms, triggers, or treatments.\n" +
            "• Use the calendar to visualize pain trends over time.\n" +
            "• Ask Mike, your AI assistant, to answer questions, identify causation patterns, or predict upcoming migraines based on your data."
        );
        description.setMaximumSize(new Dimension(600, 300));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(description);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> showHomePage(firstName)); // Replace "User" with actual name if available
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showBodyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Head Region",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        JLabel title = new JLabel("Select Head View");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton front = new JButton("Front of Head");
        styleButton(front);
        front.addActionListener(e -> showFrontPanel());
        panel.add(front);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton back = new JButton("Back of Head");
        styleButton(back);
        back.addActionListener(e -> showBackPanel());
        panel.add(back);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton notes = new JButton("Additional Notes");
        styleButton(notes);
        notes.addActionListener(e -> showNotesPanel());
        panel.add(notes);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(e -> showHomePage("User"));
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(60, 90, 180));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
    }

    private void showFrontPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 149, 237)); // Consider gradient or softer tone
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Pain Entry", 0, 0, new Font("SansSerif", Font.BOLD, 18), Color.WHITE)
        ));

        JLabel title = new JLabel("Front of Head");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel partLabel = new JLabel("Select Body Part:");
        partLabel.setForeground(Color.WHITE);
        partLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        partLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(partLabel);

        String[] parts = {"Upper Left", "Upper Right", "Mid Left", "Mid Right", "Lower Left", "Lower Right",
                        "Left Eye", "Right Eye", "Left Ear", "Right Ear", "Teeth", "Nose"};
        JComboBox<String> frontPartsBox = new JComboBox<>(parts);
        frontPartsBox.setMaximumSize(new Dimension(200, 30));
        frontPartsBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(frontPartsBox);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel painLabel = new JLabel("Pain Level (1–10):");
        painLabel.setForeground(Color.WHITE);
        painLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        painLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(painLabel);

        String[] painLevel = {"1","2","3","4","5","6","7","8","9","10"};
        JComboBox<String> painBox = new JComboBox<>(painLevel);
        painBox.setMaximumSize(new Dimension(200, 30));
        painBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(painBox);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("SansSerif", Font.BOLD, 16));
        submit.setBackground(new Color(255, 255, 255));
        submit.setForeground(new Color(100, 50, 150));
        submit.setFocusPainted(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setMaximumSize(new Dimension(200, 40));


        submit.addActionListener(e -> {
            int selectedIndex = frontPartsBox.getSelectedIndex();
            String selectedLevel = (String) painBox.getSelectedItem();

            user.writeToInfo(selectedIndex, selectedLevel);

            JOptionPane.showMessageDialog(frame, "Pain entry saved successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(submit);
        JButton back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setMaximumSize(new Dimension(200, 40));
        back.addActionListener(e -> showBodyPanel());
        panel.add(back);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showBackPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 149, 237)); // Consider gradient or softer tone
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Pain Entry", 0, 0, new Font("SansSerif", Font.BOLD, 18), Color.WHITE)
        ));

        JLabel title = new JLabel("Back of Head");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel partLabel = new JLabel("Select Body Part:");
        partLabel.setForeground(Color.WHITE);
        partLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        partLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(partLabel);

        String[] parts = {"Upper Left", "Upper Right","Lower Left", "Lower Right", "Neck"};
        JComboBox<String> backPartsBox = new JComboBox<>(parts);
        backPartsBox.setMaximumSize(new Dimension(200, 30));
        backPartsBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backPartsBox);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel painLabel = new JLabel("Pain Level (1–10):");
        painLabel.setForeground(Color.WHITE);
        painLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        painLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(painLabel);

        String[] painLevel = {"1","2","3","4","5","6","7","8","9","10"};
        JComboBox<String> painBox = new JComboBox<>(painLevel);
        painBox.setMaximumSize(new Dimension(200, 30));
        painBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(painBox);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("SansSerif", Font.BOLD, 16));
        submit.setBackground(new Color(255, 255, 255));
        submit.setForeground(new Color(100, 50, 150));
        submit.setFocusPainted(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setMaximumSize(new Dimension(200, 40));

        submit.addActionListener(e -> {
            int selectedIndex = backPartsBox.getSelectedIndex() + 12;
            String selectedLevel = (String) painBox.getSelectedItem();

            user.writeToInfo(selectedIndex, selectedLevel);

            JOptionPane.showMessageDialog(frame, "Pain entry saved successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(submit);

        JButton back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setMaximumSize(new Dimension(200, 40));
        back.addActionListener(e -> showBodyPanel());
        panel.add(back);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showNotesPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 149, 237)); // Soft purple
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Additional Notes",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        JLabel title = new JLabel("Notes");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel notesLabel = new JLabel("Enter your notes below:");
        notesLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        notesLabel.setForeground(Color.WHITE);
        notesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(notesLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextArea notesArea = new JTextArea(6, 30);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formDay = today.format(formatter);
        String currentNote = user.getUserNoteFromDate(username, formDay);
        if(currentNote != "User not found.") notesArea.setText(currentNote);
        notesArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(notesArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(400, 120));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);
        submitButton.addActionListener(e -> {
            String notes = notesArea.getText().trim();
            if (!notes.isEmpty()) {
                user.writeNoteToInfo(notes);
                JOptionPane.showMessageDialog(frame, "Notes saved successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter some notes before submitting.", "Empty Notes", JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(submitButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> showBodyPanel());
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        LocalDate today = LocalDate.now();
        final YearMonth[] currentMonth = { YearMonth.from(today) };

        JLabel monthLabel = new JLabel();
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> showBodyPanel());
        panel.add(backButton);

        JPanel calendarGrid = new JPanel();
        calendarGrid.setLayout(new GridLayout(0, 7, 5, 5));
        calendarGrid.setBackground(Color.WHITE);
        calendarGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        styleNavButton(prevButton);
        styleNavButton(nextButton);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        updateCalendar(currentMonth[0], today, calendarGrid, monthLabel);

        prevButton.addActionListener(e -> {
            currentMonth[0] = currentMonth[0].minusMonths(1);
            updateCalendar(currentMonth[0], today, calendarGrid, monthLabel);
        });

        nextButton.addActionListener(e -> {
            currentMonth[0] = currentMonth[0].plusMonths(1);
            updateCalendar(currentMonth[0], today, calendarGrid, monthLabel);
        });


        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(calendarGrid, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void updateCalendar(YearMonth month, LocalDate today, JPanel grid, JLabel label) {
        // Clear previous calendar content
        grid.removeAll();

        // Set month label (e.g., "OCTOBER 2025")
        label.setText(month.getMonth().toString() + " " + month.getYear());

        // Add weekday headers
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            grid.add(dayLabel);
        }


        // Calculate starting position and number of days
        LocalDate firstOfMonth = month.atDay(1);
        int startDay = firstOfMonth.getDayOfWeek().getValue() % 7; // Sunday = 0
        int length = month.lengthOfMonth();

        // Add empty labels for days before the first of the month
        for (int i = 0; i < startDay; i++) {
            grid.add(new JLabel(""));
        }

        // Add buttons for each day of the month
        for (int day = 1; day <= length; day++) {
            LocalDate date = month.atDay(day);
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.setPreferredSize(new Dimension(50, 50)); // Square shape
            dayButton.setMargin(new Insets(0, 0, 0, 0)); // Optional: tighter fit
            dayButton.setFocusPainted(false); // Optional: cleaner look
            
            styleDayButton(dayButton);

            // Highlight today's date
            if (date.equals(today)) {
                dayButton.setBackground(new Color(255, 215, 0)); // Gold
            }

            // Add click listener to open detail panel
            dayButton.addActionListener(e -> showDayDetailPanel(date));
            grid.add(dayButton);
        }
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> showHomePage("User"));
        grid.add(backButton);

        // Refresh the calendar grid
        grid.revalidate();
        grid.repaint();
    }
    
    private void styleNavButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(25, 90, 160));
        button.setFocusPainted(false);
    }

    private void styleDayButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.PLAIN, 5));
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
    }

    private void showDayDetailPanel(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formDay = date.format(formatter);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255)); // Alice Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel title = new JLabel("Pain Levels on " + date.format(DateTimeFormatter.ofPattern("MM/d/yyyy")));
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112)); // Midnight Blue

        JTextArea infoArea = new JTextArea(8, 30);
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        String gatheredData = user.parseOutput(username, formDay);
        gatheredData += "\nNotes: " + user.getUserNoteFromDate(username, formDay);
        if(gatheredData == null) infoArea.setText("No data collected for this day");
        else{
            infoArea.setText(gatheredData);
        }

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Calendar");
        styleButton(backButton);
        backButton.addActionListener(e -> showCalendarPanel());

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showPredictivePanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 149, 237)); // Soft purple
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(40, 40, 40, 40),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Mikes Prediction",
                0, 0,
                new Font("SansSerif", Font.BOLD, 18),
                Color.WHITE
            )
        ));

        JLabel title = new JLabel("Ask");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        JTextArea notesArea = new JTextArea(6, 30);
        notesArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(notesArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(400, 120));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);

        JLabel output = new JLabel("Mikes Response");
        output.setFont(new Font("SansSerif", Font.BOLD, 22));
        output.setForeground(Color.WHITE);
        output.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(output);

        JTextArea outputArea = new JTextArea(6, 30);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPaneOut = new JScrollPane(outputArea);
        scrollPaneOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPaneOut.setMaximumSize(new Dimension(400, 300));
        panel.add(scrollPaneOut);

        submitButton.addActionListener(e -> {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formDay = today.format(formatter);
            String prompt = notesArea.getText().trim();
            outputArea.setText("Thinking...");
            if (!prompt.isEmpty()) {
                String aiOutput = AIStuff.sendPostAsync("This prompt should be asking about migraines, if it doesn't ask just return (Please ask about migraines!)  This is the users notes for today: " + User.getUserNoteFromDate(username, formDay)+", and here is the pain data stored from today."+ "Here is the prompt:" + prompt, .2,"kl_f87fb65c5db03b2f73fdbe67ab313c4fefe96b97b19d64936169c1d8d2eaf657");
                outputArea.setText(aiOutput);

            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a question before submitting.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            }
        });

        
        panel.add(submitButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(e -> showHomePage(firstName));
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showSimplePanel(String titleText) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        JLabel title = new JLabel(titleText + " Panel");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Home");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.addActionListener(e -> showHomePage("User")); 

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

}