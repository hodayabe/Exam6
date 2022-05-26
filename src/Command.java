
public class Command {

	String stockName;
	Operation operation;
	
	public Command(String stockName, Operation operation) {
		this.stockName = stockName;
		this.operation = operation;
	}
	
	
	public String getStockName() {
		return stockName;
	}


	public Operation getOperation() {
		return operation;
	}
	
//	public void setStockName(String stockName) {
//		if(stockName != )
//		this.stockName = stockName;
//	}





	



	public static enum Operation{
		BUY, SELL;
	}
	
	
	
	
	
}
