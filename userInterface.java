package mainProgram;

import java.util.List;
import javax.swing.*;

public class userInterface {
List<String> original;
ReadingFiles readingFiles = new ReadingFiles();
userInterfaceSupport UserInterfaceSupport = new userInterfaceSupport();
	int n;
	public boolean choice(boolean continu){
		Object[] options = {"Randomize file.txt", "Sort file.txt", "Open a file", "Add Names from another file to file.txt",
				"Erase spaces in a file", "Save file.txt as the 'Original Form'", "Restore file.txt to original form", 
				"Randomly delete names from file.txt", "Specifically delete names from file.txt", 
				"Add specific names to file.txt", "Find a name from the index in the file", "Delete a name using the index",
				"Find the ordinal number of a name in the file", "Stop the program"};
		JComboBox<?> optionList = new JComboBox<Object>(options);
		optionList.setSelectedIndex(0);
		JPanel jpan = new JPanel ();
		jpan.add(new JLabel("Choose an option:"));
		jpan.add(optionList);
		int n = JOptionPane.showOptionDialog(null, jpan, "File Manipulator Program",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null);
		if (n != -1){
			n = optionList.getSelectedIndex();
		}
		original = readingFiles.readFile(original, "file.txt", 0);
		switch(n){
		case 0: 
			UserInterfaceSupport.randomize(original); 
			break;
		case 1:
			UserInterfaceSupport.sort(original, readingFiles);
			break;
		case 2:
			UserInterfaceSupport.open(original);
			break;
		case 3:
			UserInterfaceSupport.add(original);
			break;
		case 4:
			UserInterfaceSupport.eraseSpaces(original);
			break;
		case 5:
			UserInterfaceSupport.save(original);
			break;
		case 6:
			UserInterfaceSupport.restore();
			break;
		case 7:
			UserInterfaceSupport.randomDelete(original);
			break;
		case 8:
			UserInterfaceSupport.specificDelete();
			break;
		case 9:
			UserInterfaceSupport.addName();
			break;
		case 10:
			UserInterfaceSupport.getName(original);
			break;
		case 11:
			UserInterfaceSupport.deleteIndex(original);
			break;
		case 12:
			UserInterfaceSupport.findOrdinalNumber(original);
			break;
		case 13:
			continu = false;
			break;
		}
		return continu;
	}

	
}
