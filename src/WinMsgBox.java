
public class WinMsgBox {
	static {
		System.loadLibrary("WinMsgDll"); // (1)
	}

	public native void showMsgBox(String str); // (2)
}