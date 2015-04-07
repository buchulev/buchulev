import klicenka.presentation.controller.MainController;
import klicenka.presentation.ui.MainWindow;


public class MainExe {
public static void main(String [] args) {
	
	java.awt.EventQueue.invokeLater(new Runnable() {

        public void run() {
        	new MainController();
        }

    });
}
}
