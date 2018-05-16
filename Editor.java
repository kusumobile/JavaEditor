import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame {

	public static void main(String[] args) {
		new Editor();
	}
	Action Exit = new AbstractAction("Exit") {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	private JFileChooser fc = new JFileChooser();

	// Add actions
	Action Open = new AbstractAction("Open File") {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				openFile(fc.getSelectedFile().getAbsolutePath()); // add the method later
			}
		}

	};

	Action Save = new AbstractAction("Save File") {
		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	};

	private JTextArea textArea = new JTextArea(20, 60);

	// Constructor for window
	public Editor() {

		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// filter for txt file
		FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
		fc.setFileFilter(txtFilter);

		// Menu and items
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = new JMenu("File");
		menuBar.add(file);
		add(scrollPane);

		// file menu actions
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Methods
	public void openFile(String fileName) {
		FileReader fr = null;
		try {
			new FileReader(fileName);
			textArea.read(fr, null);
			fr.close();
			setTitle(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveFile() {
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				textArea.write(fw);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
