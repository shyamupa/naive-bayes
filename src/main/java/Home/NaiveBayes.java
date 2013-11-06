package Home;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class NaiveBayes implements Classifier 
{
	private DataSet trainSet;	// must have training data
    private HashMap<String,Double> Priors; // stores freq{label_i}
    private HashMap<String,HashMap<String,Double>> Likelihood; // stores freq{label_i ^ FeatureVal_j}
    //				Label_i			FeatureVal_j
    private HashMap<String,Double>FeatureCount; // stores freq(FeatureVal_j)
    
    public NaiveBayes(DataSet Train)
    {
    	trainSet=Train;
    }

    // Finds the Priors for the Labels and Likelihoods in one pass over the dataset
    public void findPriorsAndLikelihood()
    {
    	HashMap<String,Double> h;
    	// init the priors to 0
    	for(Label l : trainSet.getLabels())
    	{
    		Priors.put(l.getName(), 0.0);
    	}
    	
    	for(int i=0; i< trainSet.getSize();i++)
    	{
    		Instance d=trainSet.getInstance(i);
    		String l=d.getLabel().getName();
    		Priors.put(l,Priors.get(l)+1.0);	// increment the prior
    		h=Likelihood.get(l);	// get the map for likelihood
    		for(Feature f: d.getFeatures())	// iterate over features of the current instance
    		{
    			if(!h.containsKey(f.getValue()))	// no key with feature value present
    				h.put(f.getValue(),1.0);
    			else
    				h.put(f.getValue(),h.get(f.getValue())+1.0);	// increment joint freq. count
    			
    			if(!FeatureCount.containsKey(f.getValue()))
    				FeatureCount.put(f.getValue(),1.0);
    			else
    				FeatureCount.put(f.getValue(),FeatureCount.get(f.getValue())+1.0);
    				
//    			System.out.println(l+":"+f.getValue()+":"+h.get(f.getValue()));
//    			System.out.println(f.getValue()+":"+FeatureCount.get(f.getValue()));
    			
    		}
    	}
    	
//    	for(Label l : trainSet.getLabels())
//    	{
//    		Priors.put(l.getName(),Priors.get(l.getName())/trainSet.getSize());
//    		System.out.println(Priors.get(l.getName()));
//    	}
    }
    public Double getLikelihood(String l, String FeatureVal) // returns Probability of Instance given the Label
    {
    	Double lh,temp;
    	HashMap<String,Double> h=Likelihood.get(l);
    	//System.out.println(h.get(FeatureVal)+":"+FeatureVal);

    	if(h.get(FeatureVal)==null)	// this label and featureVal pair never seen before
		{
    		//System.out.println("WHOOPS!!");
			temp=1.0;	// use smoothing
		}
    	else
    		temp=h.get(FeatureVal);
    		
    	lh=temp/Priors.get(l); // Freq{FV_j ^ label_i}/Freq{label_i}
    	//System.out.println(lh);
    	return lh;
    }
    
    public Label classify(Instance i)
    {
    	double max=Double.NEGATIVE_INFINITY;
    	Label prediction=null;
		for(Label l : trainSet.getLabels()) // iterate over all labels
    	{
			double prob = getPosterior(l,i);
			if(prob > max)
			{
				max=prob;	
				prediction=l;	
			}
    	}
		//System.out.println("Prob is "+":"+max);
    	return prediction;
    }
    
    // finds P(Label | instance)
    private double getPosterior(Label label, Instance i)
    {
    	double post=Math.log(getPrior(label.getName()));
    	// Bayes Theorem
    	// Posterior P (Label | instance) ~ Prior P(Label) * Likelihood P(instance | Label)
    	for(Feature f: i.getFeatures())
    	{
    		post+=Math.log(getLikelihood(label.getName(),f.getValue()));
    		
    	}
    	return post;
    		
    }
    
    
	private double getPrior(String name) // returns true prior 
	{
		return Priors.get(name)/trainSet.getSize();
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
		DataSet ds2=new DataSet("data/test_data.bak");
		nb.test(ds2);
		//nb.getPosterior(">50K","Female");
	}
}
