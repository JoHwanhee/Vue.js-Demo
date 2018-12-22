import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;


public class DataManager {
	private static final String FILENAME = "./data.json";
	private String jsonData = "";
	
	private DataManager() {
		loadData();
	}
	
	private static class Singleton {
		private static final DataManager instance = new DataManager();
	}
	
	public static DataManager getInstance () {
		return Singleton.instance;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	
	public void loadData() {
		BufferedReader br = null;
		FileReader fr = null;
		
		StringBuilder sb = new StringBuilder();
		
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
			
			this.jsonData = sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
}
