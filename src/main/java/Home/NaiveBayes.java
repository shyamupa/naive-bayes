package Home;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes implements Classifier 
{
	private DataSet trainSet;	// must have training data
    private HashMap<String,Double> Priors;
    private HashMap<String,HashMap<String,Double>> Likelihood; // first index is Label, second index is FeatureValue
    private HashMap<String,Double>FeatureCount;
    public NaiveBayes(DataSet Train)
    {
    	trainSet=Train;
    }

    // Prob of each label
    public void findPriorsAndLikelihood()
    {
    	HashMap<String,Double> h;
    	for(Label l : trainSet.getLabels())
    	{
    		Priors.put(l.getName(), 0.0);
    	}
    	
    	for(int i=0; i< trainSet.getSize();i++)
    	{
    		Instance d=trainSet.getInstance(i);
    		String l=d.getLabel().getName();
    		Priors.put(l,Priors.get(l)+1.0);
    		h=Likelihood.get(l);
    		for(Feature f: d.getFeatures())
    		{
    			if(!h.containsKey(f.getValue()))	// no key with feature value present
    				h.put(f.getValue(),1.0);
    			else
    				h.put(f.getValue(),h.get(f.getValue())+1.0);
    			if(!FeatureCount.containsKey(f.getValue()))
    				FeatureCount.put(f.getValue(),1.0);
    			else
    				FeatureCount.put(f.getValue(),FeatureCount.get(f.getValue())+1.0);
    				
//    			System.out.println(l+":"+f.getValue()+":"+h.get(f.getValue()));
//    			System.out.println(f.getValue()+":"+FeatureCount.get(f.getValue()));
    			
    		}
    	}
    	
    	for(Label l : trainSet.getLabels())
    	{
    		Priors.put(l.getName(),Priors.get(l.getName())/trainSet.getSize());
    		//System.out.println(Priors.get(l.getName()));
    	}
    }
    public Double getLikelihood(String l, String FeatureVal)
    {
    	Double lh,temp;
    	HashMap<String,Double> h=Likelihood.get(l);
    	//System.out.println(h.get(FeatureVal)+":"+FeatureVal);

    	if(h.get(FeatureVal)==null)
    		temp=0.0;
    	else
    		temp=h.get(FeatureVal);
    		
    	lh=temp/FeatureCount.get(FeatureVal);
    	//System.out.println(lh);
    	return lh;
    }
    
    public Label classify(Instance i)
    {
    	double max=0.0;
    	Label prediction=null;
		for(Label l : trainSet.getLabels())
    	{
			double prob = getPosterior(l,i);
			if(prob > max)
			{
				max=prob;
				prediction=l;
			}
    	}
    	return prediction;
    }
    
    // finds P(Label | instance)
    private double getPosterior(Label label, Instance i)
    {
    	double post=Priors.get(label.getName());
    	// Bayes Theorem
    	// Posterior P (Label | instance) ~ Prior P(Label) * Likelihood P(instance | Label)
    	for(Feature f: i.getFeatures())
    	{
    		post*=getLikelihood(label.getName(),f.getValue());
    		
    	}
    	return post;
    		
    }
    
    
	public void train() 
	{
		Priors=new HashMap<String, Double>(trainSet.getLabels().size());
		Likelihood=new HashMap< String, HashMap<String, Double>>();
		FeatureCount=new HashMap<String,Double>();
		for(Label l: trainSet.getLabels())
			Likelihood.put(l.getName(),new HashMap<String,Double>());
		findPriorsAndLikelihood();	
	}
	public void test(DataSet testSet) 
	{
		for(int i=0;i<testSet.getSize();i++)
		{
			Instance d=testSet.getInstance(i);
			System.out.println(this.classify(d).getName());
		}
	}
	
	public void test() 
	{
		
	}
	public static void main( String[] args ) throws IOException
	{
		DataSet ds1=new DataSet("data/train_data.txt");
		NaiveBayes nb= new NaiveBayes(ds1);
		nb.train();
		//nb.getLikelihood(">50K", "Female");
		DataSet ds2=new DataSet("data/test_data.txt");
		nb.test(ds2);
		//nb.getPosterior(">50K","Female");
	}
}
