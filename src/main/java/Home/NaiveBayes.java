package Home;

import java.util.HashMap;
import java.util.Map;

public class NaiveBayes implements Classifier 
{
	private 
	private Map<Label,Double> labelPrior; // map for storing priors
    private HashMap<Integer,Instance> iMap; // training instances
    
    public NaiveBayes()
    {
    	
    }
    public void findPriors()
    {
    	
    }
    public Label classify()
    {
	
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
	public void train() {
		
	}
	public void test(String testfile) {
		
	}
	public Label classify(Instance i) {
		return null;
	}
	@Override
	public void setTrain(String filename) {
		
	}
	@Override
	public void test() {
		
	}
}
