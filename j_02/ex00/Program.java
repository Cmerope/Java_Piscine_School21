package ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		FileInputStream fileIn = new FileInputStream("/Users/cmerope/Desktop/PJ_day02/src/ex00/signatures.txt");
		FileOutputStream rslt = new FileOutputStream("/Users/cmerope/Desktop/PJ_day02/src/ex00/result.txt", true);
		Map<String, String> map = new HashMap<>();
		readFile(fileIn, map);
		readLine(rslt, map);
	}

	private static void readLine(FileOutputStream rslt, Map<String, String> map) {
		Scanner scanIn = new Scanner(System.in);
		String input;
		while (!(input = scanIn.nextLine()).equals("42")) {
			try {
				int proc = 0;
				FileInputStream file = new FileInputStream(input);
				StringBuilder Hex = new StringBuilder();
				for (int i = 0; i < 16; i++)
					Hex.append(String.format("%02X", file.read()));
				for (String item : map.keySet()) {
					String tmp = Hex.toString();
					String value = "";
					if (tmp.startsWith(item)) {
						value = map.get(item);
						rslt.write(value.getBytes());
						rslt.write('\n');
						System.out.println("PROCESSED");
						proc = 1;
					}
				}
				if (proc == 0) {
					System.out.println("UNDEFINED");
				}
			} catch (IOException e) {
				scanIn.close();
				e.printStackTrace();
			}
		}
		scanIn.close();
	}

	private static void readFile(FileInputStream fileInput, Map<String, String> mapSig) {
		Scanner scanner = new Scanner(fileInput);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			String[] value = str.split(", ");
			mapSig.put(value[1].replaceAll("\\s+", ""), value[0]);
		}
		scanner.close();
	}
}
