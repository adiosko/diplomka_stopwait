package cz.inzinieris.diplomka;
//git repo added
public class Channel {
	//dekla\racia premmennych pre kanal
	private double bitErrorProbability;
	private double bitRate;
	private double delay;
	
	
	//konstruktor
	public Channel (double Error, double Rate, double Delay){
		bitErrorProbability = Error;
		bitRate = Rate;
		Delay = delay;
	}
	
	//vygenerovane getery a seteri
	public double getBitErrorProbability() {
		return bitErrorProbability;
	}
	public void setBitErrorProbability(double bitErrorProbability) {
		this.bitErrorProbability = bitErrorProbability;
	}
	public double getBitRate() {
		return bitRate;
	}
	public void setBitRate(double bitRate) {
		this.bitRate = bitRate;
	}
	public double getDelay() {
		return delay;
	}
	public void setDelay(double delay) {
		this.delay = delay;
	}
	 //vytvorenie metody, metoda si vezme dlzku ramca a vrati pravdepodbnost chyby, 
	public double getFrameErrorProb(int lengthFrame){
		return 1.0 - Math.pow((1.0 - bitErrorProbability), lengthFrame); 
	}
	
	//poskodenie ramcu, metoda si vezme ramec fr a ak je vacsi ako vygenerovana nahodna hodnota Math random funkcie, bude zahodeny
	private void damage(Frame fr) {
		boolean errorDiscovered =  Math.random() <= getFrameErrorProb(fr.getLength()) ? true:false;
		fr.setError(errorDiscovered); // natsavenie, ze error vznikol napr. fr.setError(true)
	}
	
	
	//metoda ktora overi ramec, ak je v poriadku coze ACK odosle cez nas kanal vytvoreny, ak nie tak ho poskodi schvalne
	public Frame send(Frame fr){
		if (fr.getType() == Frame.ACK) {
			return fr;
		}
		else {
			damage(fr);
			return fr;	
		}
	}
	 
	
	

}
