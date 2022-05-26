import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class Investing {
	private static final int CORE_THREADS = 1;
	private static final int MAX_POOL_SIZE = 3;
	private static final long KEEP_ALIVE_TIME = 3;

	private static final int NUM_THREADS = 3;
	private static final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
	
	private static RejectedExecutionHandler handler = (Runnable runnable, ThreadPoolExecutor executor) -> {
		System.out.println("Rejected task");
	};
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		double totalTransactions=0;
		System.out.println("***************************");
		System.out.println(StocksDB.getStockByName("doodle"));
		System.out.println(StocksDB.getStockByName("HEADBOOK"));
		System.out.println(StocksDB.getStockByName("BARVAZON"));
		System.out.println("***************************");
		
		System.out.println(totalTransactions);
		
		List<Command> commands = Arrays.asList(
				new Command("doodle",Command.Operation.BUY)
				,new Command("HEADBOOK",Command.Operation.BUY)
				,new Command("HEADBOOK",Command.Operation.BUY)
				,new Command("HEADBOOK",Command.Operation.SELL)
				,new Command("doodle",Command.Operation.BUY)
				,new Command("BARVAZON",Command.Operation.SELL)
				,new Command("BARVAZON",Command.Operation.BUY)
				,new Command("doodle",Command.Operation.SELL)
				,new Command("doodle",Command.Operation.SELL)
				,new Command("BARVAZON",Command.Operation.BUY)
				,new Command("f",Command.Operation.BUY)
				);
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_THREADS, MAX_POOL_SIZE, KEEP_ALIVE_TIME,TimeUnit.SECONDS,
				tasks,handler);
		
	
		List<Callable<Double>> callables = new ArrayList<>();
		
		for (Command command : commands) {
			Stock stock=StocksDB.getStockByName(command.getStockName());
				
			if(command.getOperation() == Command.Operation.BUY) 
				callables.add(new BuyCallable(stock));
	
			else
				callables.add(new SellCallable(stock));
		}
				
		
		List<Future<Double>> futures = pool.invokeAll(callables);
		for (Future<Double> future : futures) {
			if (!future.isDone()) {
				future.cancel(false);
			}
			totalTransactions += future.get();
		}
		
		System.out.println(totalTransactions);
		System.out.println("***************************");
		System.out.println(StocksDB.getStockByName("doodle"));
		System.out.println(StocksDB.getStockByName("HEADBOOK"));
		System.out.println(StocksDB.getStockByName("BARVAZON"));
		System.out.println("***************************");
		
		pool.shutdown();
		pool.awaitTermination(2, TimeUnit.SECONDS);
		
		
		
	}
	


}
