package mainProgram;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class userInterfaceSupport {
	long longNumber, startTime;
	List<String> everything = new ArrayList<String>();

	Randomizer randomizer = new Randomizer();
	FileEditer fileEditer = new FileEditer();
	Time time = new Time();

	public String repeatedCode(String modulation, List<String> original){
		String question = "What file do you want to " + modulation + "?";
		for (int i = 0; i < original.size(); i ++){
			question = question + "\n" + original.get(i);
		}
		return JOptionPane.showInputDialog(null, question);
	}

	public void randomize(List<String> original){
		startTime = time.getStartTime(longNumber);
		randomizer.randomizer(original);
		time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
	}

	public void sort(List<String> original, ReadingFiles readingFiles){
		startTime = time.getStartTime(longNumber);
		everything = readingFiles.sortFile(original);
		fileEditer.editFile(everything, "file.txt");
		time.outputTotalTime("sorting", longNumber, longNumber, startTime);
	}

	public void open(List<String> original){
		String answer = repeatedCode("open", original);
		startTime = time.getStartTime(longNumber);
		fileEditer.open(answer);
		time.outputTotalTime("opening", longNumber, longNumber, startTime);
	}

	public void add(List<String> original){
		int reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?\nBetween 0 and " + original.size()));
		startTime = time.getStartTime(longNumber);
		fileEditer.add(reply);
		time.outputTotalTime("adding names", longNumber, longNumber, startTime);
	}

	public void eraseSpaces(List<String> original){
		String answer = repeatedCode("erase spaces from", original);
		startTime = time.getStartTime(longNumber);
		fileEditer.spaceEraser(answer);
		time.outputTotalTime("erase spaces", longNumber, longNumber, startTime);
	}

	public void restore(){
		startTime = time.getStartTime(longNumber);
		fileEditer.restore();
		time.outputTotalTime("restore", longNumber, longNumber, startTime);
	}

	public void randomDelete(List<String> original){
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

	public void save(List<String> original){
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

	public void getName(List<String> original){
		int index = Integer.parseInt(JOptionPane.showInputDialog("Input an index\nType '0' to go back\nOtherwise type '1' to " + (original.size() + 1)));
		if (index != 0){
			startTime = time.getStartTime(longNumber);
			String output = original.get(index - 1);
			time.outputTotalTime("retrieving a name", longNumber, longNumber, startTime);
			JOptionPane.showMessageDialog(null, output);	
		}
	}

	public void deleteIndex(List<String> original){
		int index = Integer.parseInt(JOptionPane.showInputDialog("Input an index to remove\nType '0' to go back\nOtherwise type '1' to " + (original.size() + 1)));
		if (index != 0){
			everything = original;
			startTime = time.getStartTime(longNumber);
			everything.remove(index - 1);
			fileEditer.editFile(everything, "file.txt");
			time.outputTotalTime("deleting an index", longNumber, longNumber, startTime);
		}
	}

	public void findOrdinalNumber(List<String> original){
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
