package ex00;

public class Nutrition extends Thread{
	private String name;
	private int count;

	public Nutrition(String name, int count) {
		this.name = name;
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			System.out.println(name);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
