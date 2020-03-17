package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public void writeFile(String filename, List<Student> students) {
		try{
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for(Student student : students){
				bufferedWriter.write(student.toString());
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		List<Future<List<Student>>> results = new ArrayList<>();
		List<Student> students = new ArrayList<>();
		Main m = new Main();
		
		//list all files from directory
		try (Stream<Path> walk = Files.walk(Paths.get("E:\\WebDev\\TechSchool\\facultate"))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//parse files and populate list
		results.add(exec.submit(new MyFileReader("facultate\\studenti-automatica.txt")));
		results.add(exec.submit(new MyFileReader("facultate\\studenti-calculatoare.txt")));
		results.stream().forEach(f->{
			try {
				students.addAll(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		exec.shutdown();
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student stud1, Student stud2) {
				// TODO Auto-generated method stub
				int meanComparison = -Double.compare(stud1.getMean(), stud2.getMean());
				int firstNameComparison = stud1.getFirstName().compareTo(stud2.getFirstName());
				int secondNameComparison = stud1.getLastName().compareTo(stud2.getLastName());
				
				if(meanComparison == 0 && firstNameComparison == 0)
					return secondNameComparison;
				else if (meanComparison == 0)
					return firstNameComparison;
				else
					return meanComparison;
			}
			
		});
		
		m.writeFile("studenti.txt", students);

	}
}
