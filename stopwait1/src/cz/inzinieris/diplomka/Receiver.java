package cz.inzinieris.diplomka;

public class Receiver {
	private int expectedFrameNumber; //ocakavne cislo rmaca
	private int lastReceivedFrameNumber; //posledne prijate cislo ramca
	private int processedTime; //spracovany cas ramca v mikrosekundach 
	private int ackFrameLength; //dlzka acknowledment ramcu
	private FrameStats statistics; //statsistika ramcu u prijemcu 
	private int lastReceivedNumber;
	
	//konstruktor
	public Receiver(int sumFrameNumber,double d,int acLength){
		expectedFrameNumber = 0;
		lastReceivedFrameNumber = 1;
		processedTime = (int) d;
		ackFrameLength = acLength;
		statistics = new FrameStats(sumFrameNumber);
	}
	
	//metoda frameTransmissionTime
	public int frameTransmissionTime(int length, int dataRate){
		return (int)length*1000000/dataRate;
	}
	
	
	//metoda odosla ACK ramec receiverovi
	private Frame sendAckFrame(){
		statistics.frameSent(); //odoslanie ramca metodou frameset zvysi sa counter a suma odoslanych ramcov +1
		Frame ack = new Frame(lastReceivedNumber, Frame.ACK, ackFrameLength); //vytvorenie noveho ramcu s parametrami cislo ramca posledneho, ack ramce, a jeho dlzka
		return ack; //metoda vrati tento rammec vytvoreny z konstruktoru
	}
	
	
	//metoda incrementExpectedNumber zisti ocakavane cislp ramcu ak je 0 vrati 1 inak opacne 
	private void incrementExpectedNumber(){
		if (expectedFrameNumber == 0) {
			expectedFrameNumber = 1;
		}else {
			expectedFrameNumber = 0;
		}
	}
	
	
	//metoda osetri prijatie ramca
	//ak je ramcec bez erroru vrati true a prijme
	//inak spravi to, ze si zisti posledne cislo ramca cez getter a ak je ako ocakavne, inkrementuje ho 
	public boolean receive(Frame f){
		statistics.frameReceived(f.isError());
		if (f.isError()) {
			return true;
		}else {
			lastReceivedFrameNumber = f.getNumber(); //nastavi frameNumber na hodnotu, ktoru si precita cez metodu getNumber
			if (f.getNumber() == expectedFrameNumber) {
				incrementExpectedNumber();
			}
			return false;
		}
	}
	
	
	
	//vygenerovane getery a setery
	public int getExpectedFrameNumber() {
		return expectedFrameNumber;
	}
	public void setExpectedFrameNumber(int expectedFrameNumber) {
		this.expectedFrameNumber = expectedFrameNumber;
	}
	public int getLastReceivedNumber() {
		return getLastReceivedNumber();
	}
	public void setLastReceivedNumber(int lastReceivedNumber) {
		this.lastReceivedNumber = lastReceivedNumber;
	}
	public int getProcessedTime() {
		return processedTime;
	}
	public void setProcessedTime(int processedTime) {
		this.processedTime = processedTime;
	}
	public int getAckFrameLength() {
		return ackFrameLength;
	}
	public void setAckFrameLength(int ackFrameLength) {
		this.ackFrameLength = ackFrameLength;
	}
	public FrameStats getStatistics() {
		return statistics;
	}
	public void setStatistics(FrameStats statistics) {
		this.statistics = statistics;
	}
	
	
}
