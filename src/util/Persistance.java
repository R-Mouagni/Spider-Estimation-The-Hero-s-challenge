package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Persistance {


    // Writes content to a file with the specified name
	public static void writeFile(String nomF,String json) {
		try {
			FileWriter fichier = new FileWriter("./" + nomF);
			PrintWriter pw = new PrintWriter(fichier);
			pw.print(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    // Reads content from a file with the specified name
	public static String readFile(String nomF) {
		try {
			Scanner scan = new Scanner(new FileReader("./" + nomF));
			 StringBuilder builder = new StringBuilder();
			 while (scan.hasNextLine()) {
				 builder.append(scan.nextLine());
			 }
			 scan.close();
			 return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur";
		}
	}
	
    // Checks if a file with the given fileName exists in the current directory
    public static boolean isExist(String fileName) {
        Path path = Paths.get("./" + fileName);
        return Files.exists(path);
    }
}