package mainProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class userInterfaceSupport {
	long longNumber, startTime;
	List<String> everything = new ArrayList<String>();

	Randomizer randomizer = new Randomizer();
	FileEditer fileEditer = new FileEditer();
	ReadingFiles readingFiles = new ReadingFiles();
	Time time = new Time();

	public String fileOption(String modulation){
		Object[] options = new Object[readingFiles.textFiles().size()];
		for (int i = 0; i < readingFiles.textFiles().size(); i ++){
			options[i] = readingFiles.textFiles().get(i); 
		}
		JComboBox<?> optionList = new JComboBox<Object>(options); 
		optionList.setSelectedIndex(0); 
		JPanel jpan = new JPanel (); 
		jpan.add(new JLabel("Choose a file to " + modulation + " :")); 
		jpan.add(optionList); 
		int n = JOptionPane.showOptionDialog(null, jpan, "File Selector", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				null, 
				null); 
		if (n != -1){ 
			n = optionList.getSelectedIndex(); 
		} 
		return options[n].toString();
	}

	public void randomize(List<String> original){
		startTime = time.getStartTime(longNumber);
		randomizer.randomizer(original);
		time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
	}

	public void sort(List<String> original, ReadingFiles readingFiles){
		startTime = time.getStartTime(longNumber);
		everything = readingFiles.sortFile(original);
		fileEditer.editFile(everything, "file.txt", false);
		time.outputTotalTime("sorting", longNumber, longNumber, startTime);
	}

	public void open(List<String> original, String answer){
		startTime = time.getStartTime(longNumber);
		fileEditer.open(answer);
		time.outputTotalTime("opening", longNumber, longNumber, startTime);
	}

	public void add(List<String> original, String file){
		int reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?\nBetween 0 and " + readingFiles.readFile("randomNames.txt", 0).size()));
		startTime = time.getStartTime(longNumber);
		fileEditer.add(reply, file);
		time.outputTotalTime("adding names", longNumber, longNumber, startTime);
	}

	public void eraseSpaces(List<String> original, String answer){
		startTime = time.getStartTime(longNumber);
		fileEditer.spaceEraser(answer);
		time.outputTotalTime("erase spaces", longNumber, longNumber, startTime);
	}

	public void restore(String file){
		startTime = time.getStartTime(longNumber);
		fileEditer.restore(file);
		time.outputTotalTime("restore", longNumber, longNumber, startTime);
	}

	public void randomDelete(List<String> original, String file){
		int answer = Integer.parseInt(JOptionPane.showInputDialog("How many names do you want to randomly delete?\nBetween 0 and " + original.size()));
		startTime = time.getStartTime(longNumber);
		fileEditer.deleteRandom(answer, file);
		time.outputTotalTime("randomly delete", longNumber, longNumber, startTime);
	}

	public void specificDelete(String file){
		boolean nameExists;
		while(true){
			String answer = JOptionPane.showInputDialog("What name do you want to delete?\nType 'false' if you want to stop");
			startTime = time.getStartTime(longNumber);
			if (answer.equals("false")){
				break;
			}
			else{
				nameExists = fileEditer.deleteSpecific(answer, file);
			}
			time.outputTotalTime("specifically delete", longNumber, longNumber, startTime);
			if (nameExists == false){
				JOptionPane.showMessageDialog(null, "Name doesn't exist in file");
			}
		}
	}

	public void save(List<String> original){
		startTime = time.getStartTime(longNumber);
		fileEditer.editFile(original, "original.txt", false);
		time.outputTotalTime("saving the file", longNumber, longNumber, startTime);
	}

	public void addName(String file){
		while(true){
			String name = JOptionPane.showInputDialog("Input a name\nType 'null' to go back");
			if (name.equals("null")){
				break;
			}
			else{
				startTime = time.getStartTime(longNumber);
				fileEditer.addName(name, file);
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
			fileEditer.editFile(everything, "file.txt", false);
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

	public void addNewFile(){
		String newFileName = JOptionPane.showInputDialog("Input a name for the file you want to create");
		startTime = time.getStartTime(longNumber);
		File file = new File("/" + newFileName + ".txt");
		String path = file.getAbsolutePath();
		file = new File(path);
		file.getParentFile().mkdirs(); 
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		time.outputTotalTime("adding a new file", longNumber, longNumber, startTime); //time out from this is only sometimes 0 milliseconds (not sure why)
	}
	
	public void deleteFile(String file, List<String> original){
		startTime = time.getStartTime(longNumber);
		original.clear();
		fileEditer.editFile(original, file, false);
		File file1 = new File("/" + file);
		String Path = file1.getAbsolutePath();
		file1 = new File(Path);
		Path path = file1.toPath();
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		time.outputTotalTime("deleting a file", longNumber, longNumber, startTime);
	}
}
