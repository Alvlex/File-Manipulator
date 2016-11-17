package mainProgram;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileEditer {
	ReadingFiles readingFiles = new ReadingFiles();
	List<String> everything = new ArrayList<String>(), randomized = new ArrayList<String>(), everything2 = new ArrayList<String>();
	Random random = new Random();
	int randomNo;

	public void open(String f){
		File file = new File("/" + f);
		try {
			Runtime.getRuntime().exec(new String[]
					{"rundll32", "url.dll,FileProtocolHandler",
					file.getAbsolutePath()});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editFile(List<String> everything, String file){
		try {
			PrintStream fileStream = new PrintStream(file);
			for(int i = 0; i < everything.size(); i ++){
				fileStream.println(everything.get(i));
			}
			fileStream.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void add(int NoOfNames){
		everything = readingFiles.readFile(everything, "randomNames.txt", NoOfNames);
		everything2 = readingFiles.readFile(everything2, "file.txt", 0);
		for(int i = 0; i < everything2.size(); i ++){
			everything.add(everything2.get(i));
		}
		editFile(everything, "file.txt");
	}

	public void spaceEraser(String file){
		everything = readingFiles.readFile(everything, file, 0);
		for (int i = 0; i < everything.size(); i ++){
			if (everything.get(i).equals("") || everything.get(i).contains(" ")){
				everything.remove(i);
				i --;
			}
		}
		editFile(everything, file);
	}

	public void restore(){
		everything = readingFiles.readFile(everything, "original.txt", 0);
		editFile(everything, "file.txt");
	}

	public void deleteRandom(int NoOfNames){
		randomized.clear();
		everything = readingFiles.readFile(everything, "file.txt", 0);
		for (int i = 0; i < everything.size() + randomized.size(); i ++){
			randomNo = random.nextInt(everything.size());
			randomized.add(everything.get(randomNo));
			everything.remove(randomNo);
		}
		for(int i = 0; i < NoOfNames; i ++){
			randomized.remove(0);
		}
		editFile(randomized, "file.txt");
	}

	public boolean deleteSpecific(String name){
		everything = readingFiles.readFile(everything, "file.txt", 0);
		int originalSize = everything.size();
		everything.remove(name);
		if (everything.size() < originalSize){
			editFile(everything, "file.txt");
			return true;
		}
		else{
			return false;
		}
	}
	public void addName(String Name){
		everything = readingFiles.readFile(everything, "file.txt", 0);
		everything.add(Name);
		editFile(everything, "file.txt");
	}
}
