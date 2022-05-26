
public class Stock {

	private String name;
	private double buyPrice;
	private double sellPrice;
	
	
	public Stock(String name, double buyPrice, double sellPrice) {
		setName(name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}
	
	
	private void setName(String name) {
		if(StockName.valueOf(name.toUpperCase()) != null) { //throws IllegalArgumentException
			this.name=name;
		}
	}
	
	
	public double getBuyPrice() {
		return buyPrice;
	}
	
	public double getSellPrice() {
		return sellPrice;
	}
	
	public void updateBuyPrice() {
		this.buyPrice = buyPrice*1.02;
	}
	
	public void updateSellPrice() {
		this.sellPrice = sellPrice*0.99;
	}
	
	
	
	@Override
	public String toString() {
		return "Stock [name=" + name + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + "]";
	}
//	public static void main(String[] args) {
//		Stock stock = new Stock("doodle", 3.0, 4.0);
//		System.out.println(stock);
//	}
}
