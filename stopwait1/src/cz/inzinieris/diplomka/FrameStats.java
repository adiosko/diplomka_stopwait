package cz.inzinieris.diplomka;

public class FrameStats {
	public int distribLen = 201;
	
	private int sumFrameNumber; //premenna anznacujuca pocet ramcov na odoslanie
	private int sumReceived; //premenna ulozi pocet prijatych ramcov
	private int corruptedReceived; //premenna ulozj pocet poskodenych ramcov
	private int sumSent; //premenna ulozi pocet odoslanych ramcov
	private int attemptCounter; //premenna pocitajuca presun ramca
	private int attemptDistrib[]; //premenna ktora povie pocet attemptov na jeden ramec
	private int attemptDistribIndex; //premenna vzpise index ramca pri prenose
	private int attemptDitrib2[]; //premenna cislujuca ramce

	private int attemptDitrib;
	
	//konstruktor triedy
	public FrameStats(int sumNumber){
		sumFrameNumber = sumNumber; //nastavi cislo ramca
		sumReceived = 0; //pocet prijatych ramcov je 0
		corruptedReceived = 0;//pocet poskodenych ramcov je 0
		sumSent = 0; //pocet odoslanych ramcov je 0
		attemptCounter = 0; //ppcet pokusov je 0
		attemptDistrib = new int[sumFrameNumber]; //distibucia ma pole nastaveny na poct indexov poctu ramcov
		attemptDistribIndex = -1; //primarny index je -1 nastaveny
		attemptDitrib2 = new int[distribLen];
		
		for (int i = 0; i < distribLen; i++) { //cyklus vybuluje distibuciu ramcov 
			attemptDitrib2[i] = 0;
		}
	}
	
	//metoda next frame vezme si index a ak je rozny od -1 potom mu priradi pole s tymto indexom  a pokial to je menej ako dlzka ramcatak to zvysi
	//obecne sa zvysi index a counter sa nastavi na hodnotu 0
	//metoda pre nastavenie odoslania dalsieho ramca
	public void nextFrame(){
		if (attemptDistribIndex != -1) {
			attemptDistrib[attemptDistribIndex] = attemptCounter;
			if (attemptCounter < distribLen) {
				attemptDitrib2[(int)attemptCounter]++; //dalo by sa este ako nova premenna a ta ++
			}
		}
		attemptDistribIndex++;
		attemptCounter = 0;
	}
	
	//metoda upesne odosle ramec a to tak ze mu navysi counter a pocetodoslanych ramcov
	public void frameSent(){
		attemptCounter++;
		sumSent++;
	}
	
	//receiver will only use this method, since receiver only counts corrupted info frames
	//metoda zisti ci je error, ak je error mnavysi pocet prousenych ramcov inak navysi prijate ramce 
	public void frameReceived(boolean hasError){
		sumReceived++;
		if (hasError) {
			corruptedReceived++;
		}
	}
	
	//vzgenerovane geterz a setery
	public int getDistribLen() {
		return distribLen;
	}
	public void setDistribLen(int distribLen) {
		this.distribLen = distribLen;
	}
	public int getSumFrameNumber() {
		return sumFrameNumber;
	}
	public void setSumFrameNumber(int sumFrameNumber) {
		this.sumFrameNumber = sumFrameNumber;
	}
	public int getSumReceived() {
		return sumReceived;
	}
	public void setSumReceived(int sumReceived) {
		this.sumReceived = sumReceived;
	}
	public int getCorruptedReceived() {
		return corruptedReceived;
	}
	public void setCorruptedReceived(int corruptedReceived) {
		this.corruptedReceived = corruptedReceived;
	}
	public int getSumSent() {
		return sumSent;
	}
	public void setSumSent(int sumSent) {
		this.sumSent = sumSent;
	}
	public int getAttemptCounter() {
		return attemptCounter;
	}
	public void setAttemptCounter(int attemptCounter) {
		this.attemptCounter = attemptCounter;
	}
	public int[] getAttemptDistrib() {
		return attemptDistrib;
	}
	public void setAttemptDistrib(int[] attemptDistrib) {
		this.attemptDistrib = attemptDistrib;
	}
	public int getAttemptDistribIndex() {
		return attemptDistribIndex;
	}
	public void setAttemptDistribIndex(int attemptDistribIndex) {
		this.attemptDistribIndex = attemptDistribIndex;
	}
	public int getAttemptDitrib() {
		return getAttemptDitrib();
	}
	public void setAttemptDitrib(int attemptDitrib) {
		this.attemptDitrib = attemptDitrib;
	}
	
	
	
	
	
	
}
