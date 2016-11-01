package mainProgram;

public class Time {
	public long getStartTime(long startTime){
		startTime = System.currentTimeMillis();
		return startTime;
	}

	public void outputTotalTime(String action, long endTime, long totalTime, long startTime){
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println(totalTime + " milliseconds for " + action);
	}
}
