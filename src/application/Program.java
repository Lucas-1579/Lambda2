package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entitie.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			double total = list.stream()
					.map(x -> x.getPrice())
					.reduce(0.0, (x,y) -> x + y) / list.size();
					System.out.println("Average price: " + String.format("%.2f", total));
			
			List<Product> newList = list.stream()
					.filter(x -> x.getPrice() < total)
					.sorted().collect(Collectors.toList());
					newList.forEach(x -> System.out.println(x.getName()));
		}
		catch(IOException e) {
			System.out.println(e.getMessage( ));
		}
		
		
		
		
		sc.close();

	}

}
