package ApplicationData;

//This class is just a way to bypass the TableView's parameterized object form

public class RocketProperty {

	private String property;
	private String value;

	public RocketProperty(String property, String value) {
		//TODO fix this check
		if(value.isBlank() || value.equals(null)) {
			this.property = "";
			this.property = "";
		}
		this.property = property;
		this.value = value;
	}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		property = property;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
