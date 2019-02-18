import java.io.BufferedReader;
import java.io.InputStreamReader;


public class AGNESClient {

	public static void main(String args[]) {
		
		ConfigReader config = new ConfigReader();
		config.loadConfig(); 
		
		ConsoleLogListener consoleListener = new ConsoleLogListener(); 
		Logger.attachListener(consoleListener);
		
		TextFileLogListener fileListener = new TextFileLogListener();
		if (TextFileLogListener.LogPath.compareTo("") != 0)
		{
			Logger.attachListener(fileListener);
		}
		
		Logger.logMessage("---------------------------------");
		Logger.logMessage("AGNES Algorithm Implementation");
		Logger.logMessage("---------------------------------");
		
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		String strParam = null;
		AGNES agnes = new AGNES();
		try {
			
			System.out.print("Enter the path of database: ");
			strParam = in.readLine();
			agnes.setConnectionString(strParam);
			
			System.out.print("Enter the clustering method (S: Single, C:Complete, A:Average): ");
			strParam = in.readLine();
			
			if (strParam.compareTo("S") == 0) {
				agnes.setClusterMethod(new ClusteringMethod(new SingleLinkClusteringMethod()));
			}
			else
			if (strParam.compareTo("C") == 0) {
				agnes.setClusterMethod(new ClusteringMethod(new CompleteLinkClusteringMethod()));
			}
			else
			if (strParam.compareTo("A") == 0) {
				agnes.setClusterMethod(new ClusteringMethod(new AverageLinkClusteringMethod()));
			}
		} catch (Exception ex) {
			Logger.logMessage("Error while reading parameters : " + ex.getMessage());
			return;
		} 
		
		try {
			agnes.run();
		} catch (ApplicationException ex) {
			Logger.logMessage("Error while running AGNES : " + ex.getMessage());
		}
		
		Logger.logMessage("AGNES run completed");

	}
	
	
}
