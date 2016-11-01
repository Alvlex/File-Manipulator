package mainProgram;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FileEditer {

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
		
	}
	
	public void spaceEraser(String file){
		List<String> everything = new ArrayList<String>();
		ReadingFiles readingFiles = new ReadingFiles();
		everything = readingFiles.readFile(everything, file);
		for (int i = 0; i < everything.size(); i ++){
			if (everything.get(i).equals("") || everything.get(i).contains(" ")){
				everything.remove(i);
				i --;
			}
		}
		editFile(everything, file);
	}
}
