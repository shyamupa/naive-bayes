package Home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataSet
{
	private HashMap<Integer,Instance> instances; // instance index is the key
	private HashSet<Label> labels; // set of possible labels
	
	public DataSet(String csv) throws IOException
	{
		instances = new HashMap<Integer,Instance>();	// index and instance
		labels = new HashSet<Label>();
		ReadCSV(csv);
	}
	
	public ArrayList<Label> getLabels()	// returns the list of all possible labels
	{
		return new ArrayList<Label>(labels);
	}
	public Instance getInstance(int index)
	{
		return instances.get(index);
	}
	public int getSize()
	{
		return instances.size();
	}
	public void ReadCSV(String csv) throws IOException,FileNotFoundException
	{
		int i=0;
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String line;
		while ((line = br.readLine()) != null) 
		{
			Instance d=new CensusInstance(line);
			instances.put(i++,d);
//			System.out.println(d.getLabel().getName());
			labels.add(d.getLabel());
		}
			
		br.close();
	}
	public static void main(String args[]) throws IOException
	{
		DataSet ds=new DataSet("data/train_data.txt");
		System.out.println(ds.getSize());
		System.out.println(ds.getLabels().size());
//		ds.getInstance(0).print();
	}
}
