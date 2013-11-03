package Home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class DataSet 
{
	private HashMap <Integer,Instance> instances; //
	private HashSet<Label> labels; // 
	private HashSet<Feature> features; // should have same number of feature objects as number of instances
	
	public DataSet(Instance[] data)
	{
		int i=0;
		instances = new HashMap<Integer,Instance>();
		labels = new HashSet<Label>();
		features = new HashSet<Feature>();
		for(Instance d : data)
		{
			instances.put(i++,d);
			Label label=d.getLabel();
			if(!labels.contains(label))
			{
				labels.add(label);
			}
			for(Feature f : d.getFeatures())	// feature of data instance
			{
				features.add(f);
			}
		}
	}
	public DataSet()
	{
		instances = new HashMap<Integer,Instance>();
		labels = new HashSet<Label>();
		features = new HashSet<Feature>();
	}
	
	public void ReadCSV(String csv, String[] featureNames) throws IOException
	{
		int i=0;	
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String line;
		while ((line = br.readLine()) != null) 
		{
			String[] featValues=line.split(",");
			for(String val : featValues)
			{
				
			}
			
		}
		br.close();
	}

}
