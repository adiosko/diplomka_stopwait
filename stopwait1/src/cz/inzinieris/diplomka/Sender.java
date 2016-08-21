package cz.inzinieris.diplomka;

public class Sender {
	private static final int CONST = 0; //constantna hodnota dlzka distribucie
	//private static final int EXP = 1;
	
	
	private int currentFrameNumber; //premenna uklada sucasne cislo ramca
	private boolean timedOut;  //ak bude sender timeoutovany 
	private int timeOutPeriod; //perioda timeoutu nastavena v mikrosekundach
	private int infoFrameLength; //dlzka info ramca
	private int ackFrameLength; //dlzka ack ramca
	private int lengthDistribution; //dlzka distribucie ramca CONST alebo EXP
	private Frame frameCopy; //currentFrame, pre neuspesne prenosy, kopiruje ramec 
	private FrameStats statistics; //stats ramca
	
	//konstruktor triedy Sender
	public Sender(int sumNumber, double d, int ackLength, int infoLength, int infoDistrib){
		currentFrameNumber = 1;
		timedOut = false;
		infoFrameLength = infoLength;
		ackFrameLength = ackLength;
		lengthDistribution = infoDistrib;
		frameCopy = null;
		statistics = new FrameStats(sumNumber);
	}
	
	//metoda frameTransTime
	public int frameTransmitionTime(int length, int dataRate){
		return (int)length*1000000/dataRate; //lebo je to v b/s
	}
	
	//metoda inkrementacie ramcov
	private void incrementFrameNumber() {
		if(currentFrameNumber == 0)
			currentFrameNumber = 1;
		else
			currentFrameNumber = 0;
	}
	
	
	//meotda ktora vezme novy ramec !!!!!!!!POPISAT!!!!!!!!!!!!!!
	public Frame takeNewFrame(){ //novy frame na prenos vytvorenie
		int frameLength;
		
		if (lengthDistribution == CONST) {
			frameLength = infoFrameLength;
		} else {
			double random;
			//vygenerovanire random cisla a jeho neustala generacia pokial nie je nula
			do {
				random = Math.random();
			} while (random == 0);
			int medianDataLength = infoFrameLength - ackFrameLength;
			int actDataLength = (int)(-medianDataLength *Math.log(random));
			frameLength = ackFrameLength + actDataLength;
		}
		incrementFrameNumber();
		statistics.nextFrame();
		frameCopy = new Frame(currentFrameNumber, Frame.INFO, frameLength);
		return new Frame(frameCopy);
	}
	
	//!!!!!!!!!odosli frame s novum cislom
	public Frame send(Frame f){
		statistics.frameSent();
		timedOut = false;
		return f;
	}
	
	//resend frame, preposlanie framu !!!!!!
	public Frame resend(){
		statistics.frameSent();
		timedOut = false;
		return frameCopy;
	}
	
	//metoda receive frame !!!!!!!! 
	public boolean receive(Frame f){
		statistics.frameReceived(f.isError());
		if (f.isError() || (f.getNumber() != frameCopy.getNumber()) ) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
	
	
	//generovanie geterov a setterov
	public int getCurrentFrameNumber() {
		return currentFrameNumber;
	}
	public void setCurrentFrameNumber(int currentFrameNumber) {
		this.currentFrameNumber = currentFrameNumber;
	}
	public boolean isTimedOut() {
		return timedOut;
	}
	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}
	public int getTimeOutPeriod() {
		return timeOutPeriod;
	}
	public void setTimeOutPeriod(int timeOutPeriod) {
		this.timeOutPeriod = timeOutPeriod;
	}
	public int getInfoFrameLength() {
		return infoFrameLength;
	}
	public void setInfoFrameLength(int infoFrameLength) {
		this.infoFrameLength = infoFrameLength;
	}
	public int getAckFrameLength() {
		return ackFrameLength;
	}
	public void setAckFrameLength(int ackFrameLength) {
		this.ackFrameLength = ackFrameLength;
	}
	public int getLengthDistribution() {
		return lengthDistribution;
	}
	public void setLengthDistribution(int lengthDistribution) {
		this.lengthDistribution = lengthDistribution;
	}
	public Frame getFrameCopy() {
		return frameCopy;
	}
	public void setFrameCopy(Frame frameCopy) {
		this.frameCopy = frameCopy;
	}
	public FrameStats getStatistics() {
		return statistics;
	}
	public void setStatistics(FrameStats statistics) {
		this.statistics = statistics;
	}
	public static int getConst() {
		return CONST;
	}
	
	
}
