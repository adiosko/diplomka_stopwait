package cz.inzinieris.diplomka;

public class Frame {
    private int length; // dlzka ramca v bitoch
	private int type; //type INFO alebo ACK
	private int number; //cislovanie ramca
	private boolean isError; //poskodeny frame
	
	//2stavy ramca
	public static byte INFO = 0; //INFO frame 
	public static byte ACK = 1; //ACK frame
	
	//konstruktor
	public Frame(int numero, int tipo, int len){
		number = numero;
		type = tipo;
		length = len;
	}
	
	public byte getINFO() {
		return INFO;
	}

	public void setINFO(byte iNFO) {
		INFO = iNFO;
	}

	public byte getACK() {
		return ACK;
	}

	public void setACK(byte aCK) {
		ACK = aCK;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	//metoda co anstavi ramec na cislo, typ a dlzku
	public Frame(Frame fr){
		this(fr.number,fr.type,fr.length);
		}
	


}
