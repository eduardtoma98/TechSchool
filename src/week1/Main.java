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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public void parseFile(String filename, List<Student> students) {
		String line = null;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				String[] info = line.split(",");
				students.add(new Student(info[0], info[1], info[2], Integer.parseInt(info[3]),
																Double.parseDouble(info[4])));
			}
			
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch(IOException e) {
			e.printStackTrace();
		}
	} 
	
	public static void main(String[] args) {
		
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
		m.parseFile("facultate\\studenti-automatica.txt", students);
		m.parseFile("facultate\\studenti-calculatoare.txt", students);
		
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
		
		
		//write output
		try{
			FileWriter fileWriter = new FileWriter("studenti.txt");
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
}
