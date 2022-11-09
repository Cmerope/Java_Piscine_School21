package ex01;

import java.io.*;
import java.util.*;

public class Program {

	private static HashSet<String> dict = new HashSet<>();
	private static ArrayList<String> file1 = new ArrayList<>();
	private static ArrayList<String> file2 = new ArrayList<>();

	private static void fillArrays(BufferedReader inputStreamA, ArrayList<String> file) throws IOException {
		Scanner scanFile = new Scanner(inputStreamA);
		while (scanFile.hasNext()) {
			String strA = scanFile.next();
			String[] arrA = strA.split(" ");
			for (int i = 0; i < arrA.length; i++) {
				file.add(arrA[i]);
			}
		}
		dict.addAll(file);
		FileOutputStream fileDic = new FileOutputStream("/Users/cmerope/Desktop/PJ_day02/src/ex01/dictionary.txt");
		for (Iterator<String> it = dict.iterator(); it.hasNext(); ) {
			byte[] bytes = (it.next() + " ").getBytes();
			fileDic.write(bytes);
		}
	}

	private static void start(String filenameA, String filenameB) throws IOException {
		BufferedReader input1 = new BufferedReader(new FileReader(filenameA));
		BufferedReader input2 = new BufferedReader(new FileReader(filenameB));
		fillArrays(input1, file1);
		fillArrays(input2, file2);
		input1.close();
		input2.close();
	}

	public static int numerator(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		int sum = 0;
		for (int i = 0; i < dict.size(); i++)
			sum += arr1.get(i) * arr2.get(i);
		return sum;
	}

	public static double denominator(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		double sumSq1 = 0;
		double sumSq2 = 0;

		for (Integer x : arr1)
			sumSq1 += x * x;
		for (Integer x : arr2)
			sumSq2 += x * x;
		return Math.sqrt(sumSq1) * Math.sqrt(sumSq2);
	}

	public static double similarity() {
		ArrayList<Integer> frequencyOfiIn1 = new ArrayList<>(dict.size());
		ArrayList<Integer> frequencyOfiIn2 = new ArrayList<>(dict.size());
		fillArrays(frequencyOfiIn1, frequencyOfiIn2);
		int numerator = numerator(frequencyOfiIn1, frequencyOfiIn2);
		double denominator = denominator(frequencyOfiIn1, frequencyOfiIn2);
		return numerator / denominator;
	}

	public static void fillArrays(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		int count = 0;
		int i = 0;
		for (String elem : dict) {
			for (String elemFromA : file1)
				if (elem.equals(elemFromA))
					count++;
			arr1.add(i, count);
			count = 0;
			i++;
		}
		count = 0;
		i = 0;
		for (String elem : dict) {
			for (String elemFromB : file2)
				if (elem.equals(elemFromB))
					count++;
			arr2.add(i, count);
			count = 0;
			i++;
		}
	}

	public static void main(String[] args) {
		if (args.length != 2){
			System.err.println("Bad number of args");
			System.exit(-1);
		}
		try {
			start(args[0], args[1]);
			String string = String.format("%.3f", similarity());
			if (string.substring(0, string.length() - 1).equals("Na"))
				System.out.println("Similarity = 0");
			else
				System.out.println("Similarity = " + string.substring(0, string.length() - 1));
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}