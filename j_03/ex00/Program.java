package ex00;

public class Program {
	public static void main(String[] args)  {
		String str = "--count=";
		String [] arrStr = new String[0];
		if (args.length != 1)
		{
			System.err.println("Error argument");
			System.exit(-1);
		}

		if (args[0].startsWith(str))
			arrStr = args[0].split("=");
		else{
			System.err.println("Error argument");
			System.exit(-1);
		}

		int count = Integer.parseInt(arrStr[1]);

		Thread tread1 = new Thread((Runnable) new Nutrition("Egg", count));
		Thread tread2 = new Thread((Runnable) new Nutrition("Hen", count));

		tread1.start();
		tread2.start();
		try {
			tread1.join();
			tread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < count; i++) {
			System.out.println("Human");
		}
	}
}
