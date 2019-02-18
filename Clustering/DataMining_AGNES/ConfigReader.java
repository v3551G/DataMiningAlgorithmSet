import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ConfigReader {

	public void loadConfig() throws ApplicationException {
		
		Logger.logMessage("Loading config...");
		String filePath = getClass().getResource("/App.config").getPath();
		if (filePath == null)
		{
			Logger.logMessage("Error while reading config file");
			return;
		//	throw new ApplicationException("Error while reading config file");
		}
		
		File inputFile = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			inputFile = new File(filePath);
			fileReader = new FileReader(inputFile);
			reader = new BufferedReader(fileReader);
			String strLine = reader.readLine();
			
			String[] keyValue = strLine.split("=");
			if (keyValue[0].compareTo("LogFilePath") == 0) {
				TextFileLogListener.LogPath = keyValue[1];
			}
		} catch (IOException ex) {
			Logger.logMessage("Error while reading config file");
			return;
			// throw new ApplicationException("Error while reading config file: " + ex.getMessage());
		} finally {
			try {
				reader.close();
				fileReader.close();
			}
			catch (IOException ex) {
				throw new ApplicationException("Error while closing file: " + ex.getMessage());
			}
		}
		
	}
	
}
