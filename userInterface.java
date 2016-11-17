package mainProgram;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class userInterface {

	long longNumber, startTime;
	List<String> everything = new ArrayList<String>(), original;
	ReadingFiles readingFiles = new ReadingFiles();
	Randomizer randomizer = new Randomizer();
	FileEditer fileEditer = new FileEditer();
	Time time = new Time();
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
		original = readingFiles.readFile(everything, "file.txt", 0);
		switch(n){
		case 0: 
			randomize(); 
			break;
		case 1:
			sort();
			break;
		case 2:
			open();
			break;
		case 3:
			add();
			break;
		case 4:
			eraseSpaces();
			break;
		case 5:
			save();
			break;
		case 6:
			restore();
			break;
		case 7:
			randomDelete();
			break;
		case 8:
			specificDelete();
			break;
		case 9:
			addName();
			break;
		case 10:
			getName();
			break;
		case 11:
			deleteIndex();
			break;
		case 12:
			findOrdinalNumber();
			break;
		case 13:
			continu = false;
			break;
		}
		return continu;
	}

	public String repeatedCode(String modulation){
		String question = "What file do you want to " + modulation + "?";
		for (int i = 0; i < original.size(); i ++){
			question = question + "\n" + original.get(i);
		}
		return JOptionPane.showInputDialog(null, question);
	}
	public void randomize(){
		startTime = time.getStartTime(longNumber);
		randomizer.randomizer(original);
		time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
	}
	public void sort(){
		startTime = time.getStartTime(longNumber);
		everything = readingFiles.sortFile(original);
		fileEditer.editFile(everything, "file.txt");
		time.outputTotalTime("sorting", longNumber, longNumber, startTime);
	}
	public void open(){
		String answer = repeatedCode("open");
		startTime = time.getStartTime(longNumber);
		fileEditer.open(answer);
		time.outputTotalTime("opening", longNumber, longNumber, startTime);
	}
	public void add(){
		int reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?\nBetween 0 and " + original.size()));
		startTime = time.getStartTime(longNumber);
		fileEditer.add(reply);
		time.outputTotalTime("adding names", longNumber, longNumber, startTime);
	}
	public void eraseSpaces(){
		String answer = repeatedCode("erase spaces from");
		startTime = time.getStartTime(longNumber);
		fileEditer.spaceEraser(answer);
		time.outputTotalTime("erase spaces", longNumber, longNumber, startTime);
	}
	public void restore(){
		startTime = time.getStartTime(longNumber);
		fileEditer.restore();
		time.outputTotalTime("restore", longNumber, longNumber, startTime);
	}
	public void randomDelete(){
		int answer = Integer.parseInt(JOptionPane.showInputDialog("How many names do you want to randomly delete?\nBetween 0 and " + original.size()));
		startTime = time.getStartTime(longNumber);
		fileEditer.deleteRandom(answer);
		time.outputTotalTime("randomly delete", longNumber, longNumber, startTime);
	}
	public void specificDelete(){
		boolean nameExists;
		while(true){
			String answer = JOptionPane.showInputDialog("What name do you want to delete?\nType 'false' if you want to stop");
			startTime = time.getStartTime(longNumber);
			if (answer.equals("false")){
				break;
			}
			else{
				nameExists = fileEditer.deleteSpecific(answer);
			}
			time.outputTotalTime("specifically delete", longNumber, longNumber, startTime);
			if (nameExists == false){
				JOptionPane.showMessageDialog(null, "Name doesn't exist in file");
			}
		}
	}
	public void save(){
		startTime = time.getStartTime(longNumber);
		fileEditer.editFile(original, "original.txt");
		time.outputTotalTime("saving the file", longNumber, longNumber, startTime);
	}
	public void addName(){
		while(true){
			String name = JOptionPane.showInputDialog("Input a name\nType 'null' to go back");
			if (name.equals("null")){
				break;
			}
			else{
				startTime = time.getStartTime(longNumber);
				fileEditer.addName(name);
				time.outputTotalTime("adding a name", longNumber, longNumber, startTime);
			}
		}
	}
	public void getName(){
		int index = Integer.parseInt(JOptionPane.showInputDialog("Input an index\nType '0' to go back\nOtherwise type '1' to " + (original.size() + 1)));
		startTime = time.getStartTime(longNumber);
		JOptionPane.showMessageDialog(null, original.get(index - 1));
		time.outputTotalTime("retrieving a name", longNumber, longNumber, startTime);
	}
	public void deleteIndex(){
		int index = Integer.parseInt(JOptionPane.showInputDialog("Input an index to remove\nType '0' to go back\nOtherwise type '1' to " + (original.size() + 1)));
		everything = original;
		startTime = time.getStartTime(longNumber);
		everything.remove(index - 1);
		fileEditer.editFile(everything, "file.txt");
		time.outputTotalTime("deleting an index", longNumber, longNumber, startTime);
	}

	public void findOrdinalNumber(){
		String name = JOptionPane.showInputDialog("Input a name to find the ordinal number of");
		startTime = time.getStartTime(longNumber);
		String message;
		if (original.contains(name)){
			if (((original.indexOf(name) + 1) + "").endsWith("1")){
				message = name + " is the " + (original.indexOf(name)  + 1) + "st name in the file";
			}
			else if (((original.indexOf(name) + 1) + "").endsWith("2")){
				message = name + " is the " + (original.indexOf(name) + 1) + "nd name in the file";
			}
			else if (((original.indexOf(name) + 1) + "").endsWith("3")){
				message = name + " is the " + (original.indexOf(name) + 1) + "rd name in the file"; 
			}
			else{
				message = name + " is the " + (original.indexOf(name) + 1) + "th name in the file";
			}
		}
		else{
			message = name + " not found in the file!";
		}
		time.outputTotalTime("finding the index", longNumber, longNumber, startTime);
		JOptionPane.showMessageDialog(null, message);
	}
}
