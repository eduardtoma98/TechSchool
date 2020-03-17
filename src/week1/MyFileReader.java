package week1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MyFileReader implements Callable{
private String filename;
	
	public MyFileReader(String filename) {
		super();
		this.filename = filename;
	}

	@Override
	public List<Student> call() throws Exception {
		List<Student> students = new ArrayList<>();
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
		return students;
	}
}
