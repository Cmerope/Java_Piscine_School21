package ex01;

public class Nutrution extends Thread {
	private static String str = "Hen";

	private String name;
	private int count;



	public Nutrution(String name, int count) {
		this.name = name;
		this.count = count;

	}

	@Override
	public void run() {
		for (int i = 0; i < count;) {
			synchronized(Nutrution.class){
				if (!str.equals(name)) {
					System.out.println(name);
					str = name;
					++i;
				}
			}
		}
	}
}
