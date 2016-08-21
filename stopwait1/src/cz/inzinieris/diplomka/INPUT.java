package cz.inzinieris.diplomka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class INPUT {
   //delaracia premennych
	private double bitRate; //bitRate
	private double propagationDelay; //cas na odoslanie
	private double proceedTime; //spracovanie cas
	private int infoLengthFrame; //dlzka info frame
	private int ackLengthFrame; //dlzka ack frame
	private int distribLenngth; //dlzka sisttibucie v case zo sendera na receivera
	private double errorProbability; //pravdepodbnost erroru
	private double timeOutLength; //dlzka jednej periody
	private int sumOfFrames; //pocet ramcov k spracovaniu
	
	
	public int getDistribLenngth() {
		return distribLenngth;
	}
	public void setDistribLenngth(int distribLenngth) {
		this.distribLenngth = distribLenngth;
	}
	//vzgenerovanie geterov a seterov
	public double getBitRate() {
		return bitRate;
	}
	public void setBitRate(double bitRate) {
		this.bitRate = bitRate;
	}
	public double getPropagationDelay() {
		return propagationDelay;
	}
	public void setPropagationDelay(double propagationDelay) {
		this.propagationDelay = propagationDelay;
	}
	public double getProceedTime() {
		return proceedTime;
	}
	public void setProceedTime(double proceedTime) {
		this.proceedTime = proceedTime;
	}
	public int getInfoLengthFrame() {
		return infoLengthFrame;
	}
	public void setInfoLengthFrame(int infoLengthFrame) {
		this.infoLengthFrame = infoLengthFrame;
	}
	public int getAckLengthFrame() {
		return ackLengthFrame;
	}
	public void setAckLengthFrame(int ackLengthFrame) {
		this.ackLengthFrame = ackLengthFrame;
	}
	public double getErrorProbability() {
		return errorProbability;
	}
	public void setErrorProbability(double errorProbability) {
		this.errorProbability = errorProbability;
	}
	public double getTimeOutLength() {
		return timeOutLength;
	}
	public void setTimeOutLength(double timeOutLength) {
		this.timeOutLength = timeOutLength;
	}
	public int getSumOfFrames() {
		return sumOfFrames;
	}
	public void setSumOfFrames(int sumOfFrames) {
		this.sumOfFrames = sumOfFrames;
	}
	
	//metoda vypocita optimalnu trasu, na zaciatku si premenna str vytvori objekt str, ktory si ulozi do pamati cache data na zklade vstupu
	//nasledne sa spyta ci chcete vypcitat optimalnu trasu a to je osetrene pomocou IOException a equalIgnoreCase, co je pre osetrenie vstupov
	//ak zadame A vypocita optimalnu trasu, ak N nebypocita inak vypise zla klavesa
	public boolean optimalTraceCalculate() throws IOException{
		while (true) {
			System.out.println("Prajete si vypocitat optimalnu trasu dlzky ramca?\n[A alebo N]\n");
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
               if (str.equalsIgnoreCase("A")) {
				return true;
			}else if (str.equalsIgnoreCase("N")) {
				return false;
			} else {
               System.out.println("Zly vstup, zadaj este raz");
			}
            
		}
	}
	
	//metoda na osetrenie vstupu
	public void readInput() throws IOException{
		BufferedReader bufread = new BufferedReader(new InputStreamReader(System.in));
        //3 situacie osetrenia vstupu
		//defaultne hodnoty a jej optimalizovanie
		System.out.println("Prajete si pouzit defaultne hodnoty? A alebo N");
		if (new String("A").equalsIgnoreCase(bufread.readLine())) {
			bitRate = 50000000;
			propagationDelay = 1000;
			proceedTime = 20;
			ackLengthFrame = 128;
			distribLenngth = 0;
			errorProbability = 0.0001;
			timeOutLength = 5000;
			sumOfFrames = 1000000;
			return;
		}
		
		boolean OK = false;
		//isetrenie pridanie bit rate 
		while (!OK) {
			System.out.println("Zadajte bit rate: \n");
			try {
				int x = Integer.parseInt(bufread.readLine()); //vlozi long do objektu v bufferi
				if (x<0) {
					throw new NumberFormatException(); //ak vlozime bit rate mensi ako nula hodi to vynimku a program sa ukonci
				}else {
					propagationDelay = x;
					OK = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Zly vstupny udaj");
			}
		}
		//osetrenie pridanie info frame time proceed
		while (!OK) {
			System.out.println("Zadajte cas pre spracovanie info ramca(info frame) v mikrosekundach:\n");
			try {
				int y = Integer.parseInt(bufread.readLine());
				if (y<=0) {
					throw new NumberFormatException();
				} else {
					infoLengthFrame = y;
					OK = true;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Zly vstup, opakuj volbu");
			}
		}
		//osetrrenie pridanie ack ramca time proceed
		OK = false;
		while (!OK) {
			System.out.println("Zadajte velkost ACK ramca info frame v bitoch:\n");
			try {
				int z = Integer.parseInt(bufread.readLine());
				if (z<=0) {
					throw new NumberFormatException();
				}else {
					ackLengthFrame = z;
					OK = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Zly vstup, zadajte este raz");
			}
		}
		
		OK = false;
		while (!OK) {
			double min = 1.0/infoLengthFrame;
			System.out.println("Zadajte pravdepodobnost chyby:\n");
			try {
				double k = Double.parseDouble(bufread.readLine());
				if (k <=0.0 || k>=1.0) {
					throw new NumberFormatException();
				}else {
					errorProbability = k;
					OK = true;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Zla hodnota, zadajte znovu");
			}
			
		}
		
		OK = false;
		double minTimeOut = (2*propagationDelay + proceedTime + ackLengthFrame*1000000) / (bitRate +1); //bit rate je v bitoch z sekundu a as v mikrosekundach
		while (!OK) {
			try {
				double h = Double.parseDouble(bufread.readLine());
				if (h<=0) {
				   throw new NumberFormatException();	
				}else {
					timeOutLength = h;
					OK = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Zly vstup, zadajte novy parameter");
			}
		}
		
		
		OK = false;
		while (!OK) {
			System.out.println("Zadajte 0 alebo 1 0 je konstatna dlzka  dlzky, 1 je exponencialna");
			try {
				int g = Integer.parseInt(bufread.readLine());
				if (g<0 || g>1) {
					throw new NumberFormatException();
				}else {
					distribLenngth = g;
					OK = true;
				}
			}
				
			 catch (NumberFormatException e) {
				System.out.println("Zly vstup, zadajte znovu");
			}
			
	   
		OK = false;
		while (!OK) {
			System.out.println("Zadajte maximalny pocte ramcov na odoslanie: \n");
			try {
				int f = Integer.parseInt(bufread.readLine());
				if (f<0) {
					throw new NumberFormatException();
				}else {
					sumOfFrames = f;
					OK = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Zly vstup, zadajte znovu");
			}
		}
			
		
	}
	}
}
	
	
	
   

