import java.awt.event.*;
class WindowCloser extends WindowAdapter{
    // Method to exit the system when closing the window
    public void windowClosing(WindowEvent evt) {
	System.exit(0);
    }
}
