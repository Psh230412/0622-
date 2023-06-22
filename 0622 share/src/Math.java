
public class Math {
	public void printHelloWorld() {
		System.out.println("Hello World");

	}
	// 박상현 더하기  인트값 2개 합 반환
	public int plus(int a , int b) {
		return a+b;
	}
	// 명완 빼기
	private int minus(int a,int b) {
		return a-b;
	}
	// 지수 곱하기
	public int multiple(int x, int y) {
		return x * y;
	}
	// 정빈 나누기
	public void division(int num1, int num2) {
		System.out.println(num1 / num2);
	}
	// 민아 나머지
	public int remain(int a , int b){
		int dividend = 17; 
		int divisor = 5; 
		
		int remainder = dividend % divisor; 
		return remainder;
	}
	
}