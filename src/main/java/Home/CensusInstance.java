package Home;

import java.util.ArrayList;
import java.util.Arrays;

public class CensusInstance implements Instance{

	private CensusFeature[] features; // list of features
	private ArrayList<String> FeatureNames=new ArrayList<String>(Arrays.asList("workclass","education","marital status","occupation","relationship","race","sex","native-country"));
	private CensusLabel label;
	public CensusInstance(String line)
	{
		features=new CensusFeature[FeatureNames.size()]; // last feature is label
		setFeaturesAndLabel(line); // set features and label from line
	}
	private void setFeaturesAndLabel(String line)
	{
		String[] values= line.split(","); //
//		System.out.println(values[8]);
		int i;
		for(i=0; i<FeatureNames.size(); i++)
		{	
			features[i]= new CensusFeature();	// create feature object
			features[i].setName(FeatureNames.get(i));
			features[i].setValue(values[i]);
		}
		label = new CensusLabel(values[i]);	// final feature is label
	}
	public Feature[] getFeatures() 
	{
		return features;	// return all features pertaining to this instance
	}
	public Label getLabel() 
	{
		return label;	// return the label
	}

	public void print() 
	{
		for(int i=0;i<FeatureNames.size();i++)
			System.out.println(features[i].getName()+":"+features[i].getValue());
		System.out.println("-----"+label.getName());
	}
	public Feature getFeature(String featName) 
	{
		FeatureNames.indexOf(featName);
		return features[FeatureNames.indexOf(featName)];
	}
	public static void main(String args[])
	{
		CensusInstance c = new CensusInstance("State-gov,Bachelors,Never-married,Adm-clerical,Not-in-family,White,Male,United-States,<=50K");
		c.print();
	}
}
