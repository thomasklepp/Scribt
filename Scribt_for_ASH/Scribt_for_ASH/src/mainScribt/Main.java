package mainScribt;

import javax.swing.SwingUtilities;

import ashGui.ASH_JFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new ASH_JFrame();
            }
		});
		
//		@SuppressWarnings("unused")
//		WorkerBeeForASH workerBee = new WorkerBeeForASH();

		// TODO Auto-generated method stub
		/*try {
		//
		/*parsePdf parser = new parsePdf("C:/TEMP/NOE/test.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
