package mainProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class userInterface {

	long longNumber, startTime;
	Scanner x = new Scanner(System.in);
	List<String> everything = new ArrayList<String>();
	ReadingFiles readingFiles = new ReadingFiles();
	Randomizer randomizer = new Randomizer();

	public void choice(){
		FileEditer fileEditer = new FileEditer();
		Time time = new Time();
		everything = readingFiles.readFile(everything, "file.txt");

		int reply = JOptionPane.showConfirmDialog(null, "Do you want to randomize the file?");
		if (reply == JOptionPane.YES_OPTION){
			startTime = time.getStartTime(longNumber);
			everything = randomizer.randomizer(everything);
			time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
		}

		reply = JOptionPane.showConfirmDialog(null, "Do you want to sort the names?");
		if (reply == JOptionPane.YES_OPTION){
			startTime = time.getStartTime(longNumber);
			everything = readingFiles.sortFile(everything);
			fileEditer.editFile(everything, "file.txt");
			time.outputTotalTime("sorting", longNumber, longNumber, startTime);
		}

		reply = JOptionPane.showConfirmDialog(null, "Do you want to open a file?");
		if (reply == JOptionPane.YES_OPTION){
			String answer = repeatedCode("open", reply, fileEditer);
			fileEditer.open(answer);
		}

		reply = JOptionPane.showConfirmDialog(null, "Do you want to add names to the file?");
		if (reply == JOptionPane.YES_OPTION){
			reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?"));
			fileEditer.add(reply);
		}

		reply = JOptionPane.showConfirmDialog(null, "Do you want to erase spaces from a file?");
		if (reply == JOptionPane.YES_OPTION){
			String answer = repeatedCode("erased spaces from", reply, fileEditer);
			fileEditer.spaceEraser(answer);
		}
	}

	public String repeatedCode(String modulation, int reply, FileEditer fileEditer){
		ReadingFiles readingFiles = new ReadingFiles();
		List<String> files = readingFiles.textFiles();
		String question = "What file do you want to " + modulation + "?";
		for (int i = 0; i < files.size(); i ++){
			question = question + "\n" + files.get(i);
		}
		return JOptionPane.showInputDialog(null, question);
	}
}
