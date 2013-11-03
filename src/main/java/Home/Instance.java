package Home;

public interface Instance
{
    public Feature[] getFeatures(); // get feature vector
    public Label getLabel();
    public void print();
    public Feature getFeature(String featName);
}
