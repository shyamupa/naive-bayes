package Home;

public interface Classifier
{
	public void train();	
    public void test();
    public Label classify(Instance i);
}
