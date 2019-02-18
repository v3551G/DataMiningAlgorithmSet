import java.io.*;

public class TextFileDatabase extends Database {

	private Cluster m_clusterObjectList;   
	
	public Cluster loadData(String filePath) throws ApplicationException {
		
		Logger.logMessage("Loading data...");
		
		m_clusterObjectList = new Cluster();
		
		File inputFile = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			inputFile = new File(filePath);
			fileReader = new FileReader(inputFile);
			reader = new BufferedReader(fileReader);
			String strLine = reader.readLine();
			if (strLine == null) {
				throw new ApplicationException("No data found");
			}
			
			int nLineNumber = 0;
			do 
			{
				// Create clusterobjects from every row. Index of the row in the database
				// is the object's ID. Default cluster ID is -1. 
				ClusterObject newObject = ClusterObject.fromRow(strLine);
				newObject.setObjectId(nLineNumber);
				m_clusterObjectList.add(newObject);
				
				strLine = reader.readLine();
				nLineNumber++;
				
			} while (strLine != null);
			
		} catch (IOException ex) {
			throw new ApplicationException("Error while reading database: " + ex.getMessage());
		} finally {
			try {
				reader.close();
				fileReader.close();
			}
			catch (IOException ex) {
				throw new ApplicationException("Error while closing file: " + ex.getMessage());
			}
		}
		
		Logger.logMessage(String.format("Data loaded. Total number of objects : %d", m_clusterObjectList.getCount()));
		
		return m_clusterObjectList;
	}
	
	
	
	
}
