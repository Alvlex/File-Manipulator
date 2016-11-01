package mainProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadingFiles {

	BufferedReader br;

	public List<String> readFile(List<String> everything, String file){
		try {
			br = new BufferedReader(new FileReader(file));

			try {
				String line = br.readLine();
				everything.clear();
				while (line != null) {
					everything.add(line);
					line = br.readLine();
				}
				br.close();
			} 

			catch (IOException e) {
				e.printStackTrace();
			}
		} 

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return everything;
	}

	public List<String> sortFile(List<String> everything){
		Collections.sort(everything);
		return everything;
	}

	public List<String> textFiles() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		List<String> textFiles = new ArrayList<String>();
		File dir = new File(s);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".txt"))) {
				textFiles.add(file.getName());
			}
		}
		return textFiles;
	}

}


