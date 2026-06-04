import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MusicManagementGUI extends JFrame {

    private final MusicLibrary arrayListLibrary =
            new ArrayListMusicLibrary();

    private final MusicLibrary hashMapLibrary =
            new HashMapMusicLibrary();

    private final Playlist arrayListPlaylist =
            new ArrayListPlaylist();

    private final Playlist linkedListPlaylist =
            new LinkedListPlaylist();

    private final JComboBox<String> implementationBox;

    private final DefaultTableModel tableModel;

    private final JTable table;

    public MusicManagementGUI() {

        setTitle("Music Management System");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        implementationBox = new JComboBox<>(new String[]{
                "ArrayList Music Library",
                "HashMap Music Library",
                "ArrayList Playlist",
                "LinkedList Playlist"
        });

        JButton addButton = new JButton("Add Song");
        JButton searchButton = new JButton("Search Song");
        JButton removeButton = new JButton("Remove Song");
        JButton reorderButton = new JButton("Reorder Song");
        JButton refreshButton = new JButton("Refresh");

        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Implementation:"));
        topPanel.add(implementationBox);
        topPanel.add(addButton);
        topPanel.add(searchButton);
        topPanel.add(removeButton);
        topPanel.add(reorderButton);
        topPanel.add(refreshButton);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Title",
                        "Artist",
                        "Duration (s)",
                        "Publish Date"
                },
                0
        );

        table = new JTable(tableModel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> addSong());

        searchButton.addActionListener(e -> searchSong());

        removeButton.addActionListener(e -> removeSong());

        reorderButton.addActionListener(e -> reorderSong());

        refreshButton.addActionListener(e -> refreshTable());

        implementationBox.addActionListener(
                e -> refreshTable()
        );

        refreshTable();
    }

    private boolean isLibrary() {

        int selected =
                implementationBox.getSelectedIndex();

        return selected == 0 || selected == 1;
    }

    private MusicLibrary getCurrentLibrary() {

        return switch (
                implementationBox.getSelectedIndex()
        ) {

            case 0 -> arrayListLibrary;
            case 1 -> hashMapLibrary;

            default -> null;
        };
    }

    private Playlist getCurrentPlaylist() {

        return switch (
                implementationBox.getSelectedIndex()
        ) {

            case 2 -> arrayListPlaylist;
            case 3 -> linkedListPlaylist;

            default -> null;
        };
    }

    private void addSong() {

        try {

            String title =
                    JOptionPane.showInputDialog(
                            this,
                            "Title:"
                    );

            if (title == null) return;

            String artist =
                    JOptionPane.showInputDialog(
                            this,
                            "Artist:"
                    );

            if (artist == null) return;

            String durationInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Duration (seconds):"
                    );

            if (durationInput == null) return;

            int duration =
                    Integer.parseInt(durationInput);

            String dateInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Publish Date (YYYY-MM-DD):"
                    );

            if (dateInput == null) return;

            LocalDate date =
                    LocalDate.parse(dateInput);

            Song song =
                    new Song(
                            title,
                            artist,
                            duration,
                            date
                    );

            if (isLibrary()) {

    boolean success =
            getCurrentLibrary()
                    .addSong(song);

    JOptionPane.showMessageDialog(
            this,
            success
                    ? "Song added successfully."
                    : "Duplicate title detected."
    );

} else {

    boolean success =
            getCurrentPlaylist()
                    .addSong(song);

    JOptionPane.showMessageDialog(
            this,
            success
                    ? "Song added successfully."
                    : "Operation failed."
    );
}
            refreshTable();

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duration must be numeric."
            );

        } catch (
                DateTimeParseException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Date must be YYYY-MM-DD."
            );

        } catch (
                IllegalArgumentException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void searchSong() {

        String title =
                JOptionPane.showInputDialog(
                        this,
                        "Song title:"
                );

        if (title == null) return;

        Song song;

        if (isLibrary()) {

            song =
                    getCurrentLibrary()
                            .searchSong(title);

        } else {

            song =
                    getCurrentPlaylist()
                            .searchSong(title);
        }

        JOptionPane.showMessageDialog(
                this,
                song == null
                        ? "Song not found."
                        : song.toString()
        );
    }

    private void removeSong() {

        String title =
                JOptionPane.showInputDialog(
                        this,
                        "Song title:"
                );

        if (title == null) return;

        boolean removed;

        if (isLibrary()) {

            removed =
                    getCurrentLibrary()
                            .removeSong(title);

        } else {

            removed =
                    getCurrentPlaylist()
                            .removeSong(title);
        }

        JOptionPane.showMessageDialog(
                this,
                removed
                        ? "Song removed."
                        : "Song not found."
        );

        refreshTable();
    }

    private void reorderSong() {

        if (isLibrary()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reorder is only available for playlists."
            );

            return;
        }

        try {

            String title =
                    JOptionPane.showInputDialog(
                            this,
                            "Song title:"
                    );

            if (title == null) return;

            String indexInput =
                    JOptionPane.showInputDialog(
                            this,
                            "New index:"
                    );

            if (indexInput == null) return;

            int newIndex =
                    Integer.parseInt(indexInput);

            boolean success =
                    getCurrentPlaylist()
                            .reorderSong(
                                    title,
                                    newIndex
                            );

            JOptionPane.showMessageDialog(
                    this,
                    success
                            ? "Song reordered."
                            : "Reorder failed."
            );

            refreshTable();

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Index must be numeric."
            );
        }
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        List<Song> songs;

        if (isLibrary()) {

            songs =
                    getCurrentLibrary()
                            .getAllSongs();

        } else {

            songs =
                    getCurrentPlaylist()
                            .getAllSongs();
        }

        for (Song song : songs) {

            tableModel.addRow(
                    new Object[]{
                            song.getTitle(),
                            song.getArtist(),
                            song.getDurationSeconds(),
                            song.getPublishDate()
                    }
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new MusicManagementGUI()
                        .setVisible(true)
        );
    }
}