import java.util.concurrent.Callable;

public class BuyCallable implements Callable<Double> {

	Stock stock;
	
	public BuyCallable(Stock stock) {
		this.stock = stock;	
	}

	@Override
	public Double call() {
		if(stock==null) {
			System.out.println("Stock is not found in DB");
			return 0.0;
		}
		double sellPrice=stock.getBuyPrice();
		stock.updateBuyPrice();
		return sellPrice;
	}
	
}
