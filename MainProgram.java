package mainProgram;

public class MainProgram {
	public static void main(String[] args) throws Exception{
		userInterface UserInterface = new userInterface();
		boolean continu = true;
		while(continu){
			continu = UserInterface.choice(continu);
		}
	}
}
