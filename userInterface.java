package mainProgram; 

import java.util.List; 
import javax.swing.*;  

public class userInterface { 
	List<String> original; 
	ReadingFiles readingFiles = new ReadingFiles(); 
	userInterfaceSupport UserInterfaceSupport = new userInterfaceSupport(); 
	int n; 
	String message, file = "";
	public boolean choice(boolean continu){ 
		Object[] options = {"Stop the program",  
				"Sort a file",  
				"Open a file",  
				"Add Names from randomNames.txt to a file", 
				"Erase spaces in a file",  
				"Save a file as the 'Original Form'",  
				"Restore a file to original form", 
				"Randomly delete names from a file",  
				"Specifically delete names from a file", 
				"Add specific names to a file",  
				"Find a name from the index in a file",  
				"Delete a name using the index in a file",	 
				"Find the ordinal number of a name in a file",  
				"Randomize a file",
				"Add a new file",
		"Delete a file"}; 
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
		switch(n){
		case 0:
			continu = false; 
			break;
		case 1:
			message = "sort";
			break;
		case 2:
			message = "open";
			break;
		case 3:
			message = "add names to";
			break;
		case 4:
			message = "erase spaces from";
			break;
		case 5:
			message = "save as the 'original form'";
			break;
		case 6:
			message = "restore as the 'original form'";
			break;
		case 7:
			message = "randomly delete names from";
			break;
		case 8:
			message = "specifically delete names from";
			break;
		case 9:
			message = "add specific names to";
			break;
		case 10:
			message = "find a name from the index";
			break;
		case 11:
			message = "delete a name using the index";
			break;
		case 12:
			message = "find the ordinal number of a name";
			break;
		case 13:
			message = "randomize";
			break;
		case 14:
			break;
		case 15:
			message = "delete";
			break;
		}
		if (n != 0){
			if (n != 14){
				file = UserInterfaceSupport.fileOption(message);
				original = readingFiles.readFile(file, 0); 
			}
			switch(n){ 
			case 0:  
				break; 
			case 1: 
				UserInterfaceSupport.sort(original, readingFiles); 
				break; 
			case 2: 
				UserInterfaceSupport.open(original, file); 
				break; 
			case 3: 
				UserInterfaceSupport.add(original, file); 
				break; 
			case 4: 
				UserInterfaceSupport.eraseSpaces(original, file); 
				break; 
			case 5: 
				UserInterfaceSupport.save(original); 
				break; 
			case 6: 
				UserInterfaceSupport.restore(file); 
				break; 
			case 7: 
				UserInterfaceSupport.randomDelete(original, file); 
				break; 
			case 8: 
				UserInterfaceSupport.specificDelete(file); 
				break; 
			case 9: 
				UserInterfaceSupport.addName(file); 
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
				UserInterfaceSupport.randomize(original);  
				break; 
			case 14:
				UserInterfaceSupport.addNewFile();
				break;
			case 15:
				UserInterfaceSupport.deleteFile(file, original);
				break;
			}
		}
		return continu; 
	} 
} 