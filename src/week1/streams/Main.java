package week1.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class Main {
	
	public  List<Transaction> addTransactions(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> transactions = Arrays.asList(
		 new Transaction(brian, 2011, 300),
		 new Transaction(raoul, 2012, 1000),
		 new Transaction(raoul, 2011, 400),
		 new Transaction(mario, 2012, 710),
		 new Transaction(mario, 2012, 700),
		 new Transaction(alan, 2012, 950)
		);
		return transactions;
	}
	public void printAndSortByValue(List<Transaction> transactions) {
		List<Transaction> sortedList = transactions.stream()
				.filter(tr ->tr.getYear() == 2011)
				.sorted(Comparator.comparingInt(Transaction::getValue))
				.collect(Collectors.toList());
		sortedList.forEach(System.out::print);
	}

	public void printUniqueCities(List<Transaction> transactions) {
		List<String> uniqueCities = transactions.stream()
				.map(tr -> tr.getTrader().getCity())
				.distinct()
				.collect(Collectors.toList());
		uniqueCities.forEach(System.out::print);

	}
	
	public void printSortedTradersCambridge(List<Transaction> transactions) {
		List<Trader> traders = transactions.stream()
				.filter(tr -> tr.getTrader().getCity().equalsIgnoreCase("Cambridge"))
				.map(tr -> tr.getTrader())
				.distinct()
				.collect(Collectors.toList());
		traders.stream()
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
				.forEach(System.out::print);
	}
	
	public boolean areTradersFromMilan(List<Transaction> transactions) {
		return transactions.stream()
				.anyMatch(tr -> tr.getTrader().getCity() == "Milan");
	}
	
	public void highestValueTransaction(List<Transaction> transactions) {
		OptionalInt max = transactions.stream().mapToInt(Transaction::getValue).max();
		System.out.println( "max e " + max);
		
	}
	
	public void lowestValueTransaction(List<Transaction> transactions) {
		OptionalInt min = transactions.stream().mapToInt(Transaction::getValue).min();
		System.out.println( "min e " + min);
		
	}
	
	public void printTranValueFromCambridge(List<Transaction> transactions) {
		System.out.println(
			transactions.stream()
				.filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.reduce(0, (total, transaction) -> total + transaction)
			);
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		List<Transaction> transactions = m.addTransactions();
		
		//print transactions from 2011 and sort by value
		//m.printAndSortByValue(transactions);
		
		//m.printUniqueCities(transactions);
		
		//m.printSortedTradersCambridge(transactions);
		
		//System.out.println(m.areTradersFromMilan(transactions));
		//m.highestValueTransaction(transactions);
	//	m.lowestValueTransaction(transactions);
		m.printTranValueFromCambridge(transactions);
		
		
		
	}
}
