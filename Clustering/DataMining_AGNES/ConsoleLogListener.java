
public class ConsoleLogListener extends LogListener{

	@Override
	public void update(String message) {
		System.out.print(message);
		System.out.println();
	}

	
}
