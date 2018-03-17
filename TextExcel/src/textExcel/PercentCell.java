//Shivneel Chand 
//3-9-18
package textExcel;

public class PercentCell extends RealCell {
	
	public PercentCell(String input) {
		super(input);
	}

	public String abbreviatedCellText() {
		String temp = value.substring(0, value.indexOf(".")) + "%";
		for(int i = temp.length(); i < 10; i++) {
			temp += " ";
		}
		return temp;
	}

	public String fullCellText() {
		return ""+getDoubleValue();
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value.replace("%", "")) * .01;
	}

}
