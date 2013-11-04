package Home;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes implements Classifier 
{
	private DataSet trainSet;	// must have training data
    private HashMap<String,Double> Priors;
    public NaiveBayes(DataSet Train)
    {
    	trainSet=Train;
    	
    }
    // Prob of each label
    public void findPriors()
    {
    	for(Label l : trainSet.getLabels())
    	{
    		Priors.put(l.getName(), 0.0);
    	}
    	for(int i=0; i< trainSet.getSize();i++)
    	{
    		String l=trainSet.getInstance(i).getLabel().getName();
    		Priors.put(l,Priors.get(l)+1.0);
    	}
    	for(Label l : trainSet.getLabels())
    	{
    		Priors.put(l.getName(),Priors.get(l.getName())/trainSet.getSize());
    		System.out.println(Priors.get(l.getName()));
    	}
    }
    public void findLikeLihood()
    {
    	
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
    	double post=0.0;
    	return post;
    	// Bayes Theorem
    	// Posterior P (Label | instance) ~ Prior P(Label) * Likelihood P(instance | Label)
    	//post=Priors.get(label.getName()) * Likelihood.()
    }
    
    
	public void train() 
	{
		Priors=new HashMap<String, Double>(trainSet.getLabels().size());
		findPriors();	
	}
	public void test(String testfile) 
	{
		
	}
	
	public void test() {
		
	}
	public static void main( String[] args ) throws IOException
	{
		DataSet ds=new DataSet("data/train_data.txt");
		NaiveBayes nb= new NaiveBayes(ds);
		nb.train();
		
	}
}
