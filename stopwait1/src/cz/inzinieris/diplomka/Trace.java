package cz.inzinieris.diplomka;

public class Trace {
		private static boolean isOn = true;//ak je zapnute tracovanie, vypise aktivity

		//!!!!!!!!!
		public static void enable(){
			isOn = true;
		}
		
		
		//!!!!!!!
		public static void disable(){
			isOn = false;
		}
		
		
		//!!!!!!!!!
		public static void print(String string){
			System.out.println(string);
		}
		
		
		
		//get+set
		public static boolean isOn() {
			return isOn;
		}
		
		
}
