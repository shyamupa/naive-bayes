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
	public int getPossibleValues()
	{
		if (name=="workclass")
			return 8;
		
		if (name=="education")
			return 16;
		
		if (name=="marital-status")
			return 7;
		
		if (name=="occupation")
			return 14;
		
		if (name=="relationship")
			return 6;
		
		if (name=="race")
			return 5;
		
		if (name=="sex")
			return 2;
		if (name=="native-country")
			return 41;
		else
			return -1;
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
