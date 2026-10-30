 /**Filename: Launcher.java - IMS
 *  Author:   Kevin Viren 
 *  Date:     4/25/2024
 *  
 *  Resources: I watched a 15-part YouTube tutorial to familiarize myself with Swing.
 *  		   The playlist is listed here: https://www.youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U
 */
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { // Provides the Nimbus Look and Feel to swing components
			        if ("Nimbus".equals(info.getName())) {
			            try {
							UIManager.setLookAndFeel(info.getClassName());
						} catch (Exception e) {
							e.printStackTrace();
						}
			            break;
			        }
			    }
				MainWindow main = new MainWindow();
				main.show();
			}
		});
	}
}