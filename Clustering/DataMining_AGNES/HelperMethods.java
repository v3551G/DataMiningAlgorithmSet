
public class HelperMethods {
	
	public static boolean Int2Bool(int n) {
		if (n == 1) {
			return true;
		} else if (n == 0) {
			return false;
		} else {
			throw new ApplicationException("Cannot cast int to boolean");
		}
		
	}
	
	public static int Bool2Int(boolean b) {
		if (b == true) {
			return 1;
		} else if (b == false) {
			return 0;
		} else {
			throw new ApplicationException("Cannot cast boolean to int");
		}
	}
	
	
	
	
	
}
