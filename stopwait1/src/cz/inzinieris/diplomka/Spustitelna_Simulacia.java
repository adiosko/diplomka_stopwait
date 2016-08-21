package cz.inzinieris.diplomka;

public class Spustitelna_Simulacia {

	
	public static void main(String[] args) {
		INPUT input = new INPUT();
		//input.readInput();
		Channel channel = new Channel(input.getErrorProbability(), input.getBitRate(), input.getPropagationDelay());
		Sender sender = new Sender(input.getSumOfFrames(), input.getTimeOutLength(), input.getAckLengthFrame(), input.getInfoLengthFrame(), input.getDistribLenngth());
		Receiver receiver = new Receiver(input.getSumOfFrames(), input.getProceedTime(), input.getAckLengthFrame());
		TImeStats time = new TImeStats();
		
		
		
		
	}

}
