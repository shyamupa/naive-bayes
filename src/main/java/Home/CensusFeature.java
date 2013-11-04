package Home;

public class CensusFeature implements Feature {

	private String name;
	private String value;
	public CensusFeature()
	{
		// do nothing
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	public int hashCode()
	{
		return this.name.hashCode();
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof CensusFeature)
		{
			CensusFeature other = (CensusFeature) obj; 
			if(this.getValue().equals(other.getValue()))
				return true;
		}
		return false;
		
	}
}
