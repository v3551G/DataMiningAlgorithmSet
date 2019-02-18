import java.util.ArrayList;

public class Logger {


//	private static ArrayList<String> m_lstEventHistory = new ArrayList<String>();
	private static ArrayList<LogListener> m_lstListeners = new ArrayList<LogListener>();
	
	public static void logMessage(String message) {
	//	m_lstEventHistory.add(message);
		
		notifyListeners(message);
	}
	
	
	public static void attachListener(LogListener listener) {
		m_lstListeners.add(listener);
	}
	
	public static void detachListener(LogListener listener) {
		m_lstListeners.remove(listener);
	}
	
	private static void notifyListeners(String message) {
		
		for (LogListener listener : m_lstListeners) {
			listener.update(message);
		}
	}
	
	/*
	public static String DumpLog() {
		StringBuilder sb = new StringBuilder();
		for (String str : m_lstEventHistory) {
			sb.append(str);	
		}
		return sb.toString();
	}
	*/	
}
