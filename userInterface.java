package mainProgram;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class userInterface {

	long longNumber, startTime;
	List<String> everything = new ArrayList<String>();
	ReadingFiles readingFiles = new ReadingFiles();
	Randomizer randomizer = new Randomizer();
	FileEditer fileEditer = new FileEditer();
	Time time = new Time();
	public boolean choice(boolean continu){

		everything = readingFiles.readFile(everything, "file.txt", 0);
		int answer = Integer.parseInt(JOptionPane.showInputDialog("What do you want to do?\n"
				+ "(1)  Randomize file.txt\n"
				+ "(2)  Sort file.txt\n"
				+ "(3)  Open a file\n"
				+ "(4)  Add Names to file.txt\n"
				+ "(5)  Erase spaces in a file\n"
				+ "(6)  Save file.txt as the 'Original Form'\n"
				+ "(7)  Restore file.txt to original form\n"
				+ "(8)  Randomly delete names from file.txt\n"
				+ "(9)  Specifically delete names from file.txt\n"
				+ "(10) Stop the program"));
		switch(answer){
		case 1: 
			randomize(); 
			break;
		case 2:
			sort();
			break;
		case 3:
			open();
			break;
		case 4:
			add();
			break;
		case 5:
			eraseSpaces();
			break;
		case 6:
			save();
			break;
		case 7:
			restore();
			break;
		case 8:
			randomDelete();
			break;
		case 9:
			specificDelete();
			break;
		case 10:
			continu = false;
			break;
		}
		return continu;
	}

	public String repeatedCode(String modulation){
		everything = readingFiles.textFiles();
		String question = "What file do you want to " + modulation + "?";
		for (int i = 0; i < everything.size(); i ++){
			question = question + "\n" + everything.get(i);
		}
		return JOptionPane.showInputDialog(null, question);
	}
	public void randomize(){
		startTime = time.getStartTime(longNumber);
		everything = randomizer.randomizer(everything);
		time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
	}
	public void sort(){
		startTime = time.getStartTime(longNumber);
		everything = readingFiles.sortFile(everything);
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
		int reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?\nBetween 0 and " + readingFiles.readFile(everything, "randomNames.txt", 0).size()));
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
		int answer = Integer.parseInt(JOptionPane.showInputDialog("How many names do you want to randomly delete?\nBetween 0 and " + readingFiles.readFile(everything, "file.txt", 0).size()));
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
		everything = readingFiles.readFile(everything, "file.txt", 0);
		fileEditer.editFile(everything, "original.txt");
	}
}
