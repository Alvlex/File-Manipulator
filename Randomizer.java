package mainProgram;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

	BufferedReader br;
	int randomNo;
	List<String> randomized = new ArrayList<String>();
	Random random = new Random();

	public List<String> randomizer(List<String> everything){
		ReadingFiles readingFiles = new ReadingFiles();
		FileEditer fileEditer = new FileEditer();
		everything = readingFiles.readFile(everything, "file.txt", 0);
		for (int i = 0; i < everything.size() + randomized.size(); i ++){
			randomNo = random.nextInt(everything.size());
			randomized.add(everything.get(randomNo));
			everything.remove(randomNo);
		}
		fileEditer.editFile(randomized, "file.txt");
		return randomized;
	}
}
