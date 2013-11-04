package Home;

public class CensusLabel implements Label
{
	private String name=null;
	public CensusLabel(String name)
	{
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public int hashCode()
	{
		return this.name.hashCode();
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof CensusLabel)
		{
			CensusLabel other = (CensusLabel) obj; 
			if(this.getName().equals(other.getName()))
				return true;
		}
		return false;
		
	}
}