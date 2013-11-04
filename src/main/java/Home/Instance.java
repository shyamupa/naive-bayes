package Home;

public interface Instance
{
    public Feature[] getFeatures(); // get feature vector
    public Label getLabel(); // Class Label
    public void print();
    public Feature getFeature(String featName); // return Feature with the given name
}
