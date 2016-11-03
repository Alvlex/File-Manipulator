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
		everything = readingFiles.readFile(everything, "file.txt", 0);
		int answer = Integer.parseInt(JOptionPane.showInputDialog("What do you want to do?\n(1) Randomize\n(2) Sort\n(3) Open\n(4) Add Names\n(5) Erase"));
		switch(answer){
		case 1: 
			randomize(time); 
			break;
		case 2:
			sort(time, fileEditer);
			break;
		case 3:
			open(fileEditer, time);
			break;
		case 4:
			add(fileEditer, time);
			break;
		case 5:
			erase(fileEditer, time);
			break;
		}
	}

	public String repeatedCode(String modulation, FileEditer fileEditer){
		ReadingFiles readingFiles = new ReadingFiles();
		List<String> files = readingFiles.textFiles();
		String question = "What file do you want to " + modulation + "?";
		for (int i = 0; i < files.size(); i ++){
			question = question + "\n" + files.get(i);
		}
		return JOptionPane.showInputDialog(null, question);
	}
	public void randomize(Time time){
		startTime = time.getStartTime(longNumber);
		everything = randomizer.randomizer(everything);
		time.outputTotalTime("randomizing", longNumber, longNumber, startTime);
	}
	public void sort(Time time, FileEditer fileEditer){
		startTime = time.getStartTime(longNumber);
		everything = readingFiles.sortFile(everything);
		fileEditer.editFile(everything, "file.txt");
		time.outputTotalTime("sorting", longNumber, longNumber, startTime);
	}
	public void open(FileEditer fileEditer, Time time){
		String answer = repeatedCode("open", fileEditer);
		startTime = time.getStartTime(longNumber);
		fileEditer.open(answer);
		time.outputTotalTime("opening", longNumber, longNumber, startTime);
	}
	public void add(FileEditer fileEditer, Time time){
		int reply = Integer.parseInt(JOptionPane.showInputDialog("How many names?"));
		startTime = time.getStartTime(longNumber);
		fileEditer.add(reply);
		time.outputTotalTime("adding names", longNumber, longNumber, startTime);
	}
	public void erase(FileEditer fileEditer, Time time){
		String answer = repeatedCode("erase spaces from", fileEditer);
		startTime = time.getStartTime(longNumber);
		fileEditer.spaceEraser(answer);
		time.outputTotalTime("erase spaces", longNumber, longNumber, startTime);
	}
}
