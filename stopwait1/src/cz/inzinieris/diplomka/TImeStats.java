package cz.inzinieris.diplomka;

public class TImeStats {
	
	private int elapsedTime; //pridanie elapsedtime
	
	
	//!!!!!!!!!
	public TImeStats(){
		elapsedTime = 0;
	}
	
	//!!!!!!
	public void add(int time){
		elapsedTime += time;
	}
	
	//!!!!!!!!priepustnost test
	public double throughputSimulation(int frameToSendNumber, int infoTransmissionTime){
		return ((double)(frameToSendNumber * infoTransmissionTime)/elapsedTime);
	}
	
	//teoreticka priepustnost!!!!!!!!
	public double throughputTheretically(int infoTransTime, int ackTransmissionTime, int timeOut, double frameErrorProbability, int propDelay, int procTime ){
		double a = (timeOut+infoTransTime) * frameErrorProbability /(1.0 - frameErrorProbability);
		double b = infoTransTime + 2 * propDelay + procTime + ackTransmissionTime;
		return (double)infoTransTime / (a+b);
	}
	
	
	
	
	//getter+setter
	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	//calculate optimal info frame length, given bit error probability!!!!!!!!!!
		public int optInfoLength(INPUT in) {
			return 8192;
		}
	 
}
