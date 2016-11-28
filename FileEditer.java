package mainProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
			e.printStackTrace();
		}
	}

	public void editFile(List<String> everything, String file, boolean append){
		try {
			File file1 = new File("/" + file);
			FileWriter fw = new FileWriter(file1.getAbsoluteFile(), append);
			if (append == true){
				for(int i = 0; i < everything.size(); i ++){
					if (i == 0){
						fw.write("\n" + everything.get(i) + "\n");
					}
					else if (i == everything.size() - 1){
						fw.write(everything.get(i));
					}
					else{
						fw.write(everything.get(i) + "\n");
					}
				}
			}
			else{
				for(int i = 0; i < everything.size(); i ++){
					if (i == everything.size() - 1){
						fw.write(everything.get(i));
					}
					else{
						fw.write(everything.get(i) + "\n");
					}
				}
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void add(int NoOfNames, String file){
		if (NoOfNames != 0){
			everything = readingFiles.readFile("randomNames.txt", NoOfNames);
			editFile(everything, file, true);
		}
	}

	public void spaceEraser(String file){
		everything = readingFiles.readFile(file, 0);
		for (int i = 0; i < everything.size(); i ++){
			if (everything.get(i).equals("") || everything.get(i).contains(" ")){
				everything.remove(i);
				i --;
			}
		}
		editFile(everything, file, false);
	}

	public void restore(String file){
		everything = readingFiles.readFile("original.txt", 0);
		editFile(everything, file, false);
	}

	public void deleteRandom(int NoOfNames, String file){
		randomized.clear();
		everything = readingFiles.readFile(file, 0);
		for (int i = 0; i < everything.size() + randomized.size(); i ++){
			randomNo = random.nextInt(everything.size());
			randomized.add(everything.get(randomNo));
			everything.remove(randomNo);
		}
		for(int i = 0; i < NoOfNames; i ++){
			randomized.remove(0);
		}
		editFile(randomized, file, false);
	}

	public boolean deleteSpecific(String name, String file){
		everything = readingFiles.readFile(file, 0);
		int originalSize = everything.size();
		everything.remove(name);
		if (everything.size() < originalSize){
			editFile(everything, file, false);
			return true;
		}
		else{
			return false;
		}
	}
	public void addName(String Name, String file){
		everything.clear();
		everything.add(Name);
		editFile(everything, file, true);
	}
}
