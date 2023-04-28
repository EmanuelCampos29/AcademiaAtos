public class Calculadora {
	public float calcular(float a, float b, char operacao) {
		float resultado = 0;
		switch (operacao) {
		case '+': {
			resultado = a + b;
			break;
		}
		case '-': {
			if (a < 0 || b < 0) { 
				float absA = Math.abs(a);
				float absB = Math.abs(b);
				resultado = absB + absA; 
				break;
			}
			resultado = a - b;
			break;
		}
		case '/': {
			resultado = a / b;
			break;
		}
		case '*': {
			resultado = a * b;
			break;
		}
		}
		return resultado;
	}
}