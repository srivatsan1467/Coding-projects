package dsaProject;
import java.util.*;


public class JavaStackCalulator {

	
	
	static Map <Character , Integer> opPrecedence= Map.of('+',1,'-',1,'*',2,'/',2,'^',3,'(',0,')',0);
	
     public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("welcome to the stack based calculator !");
		System.out.println("enter the arithmetic expression in the infix notation : ");
		System.out.println("it supports +,-,*,/,^for powers and floating point numbers");
		
		while(true) {
			
			System.out.println("type exit to quit from application");
			System.out.println();
			System.out.println("enter infix expression :");
			System.out.println();
			
		    
			String infix=sc.next();
			
			String postfix=infixToPostfix(infix);
			
			System.out.println("postfix : "+postfix);
			
			System.out.println();
			System.out.println();
			double postfixEvaluation=evaluatePostfix(postfix);
			System.out.println("postfix evaluation = "+postfixEvaluation);
			
			
		}
	}
     
     
	static String infixToPostfix(String infix) {
		
		StringBuilder postfix=new StringBuilder();
		Stack<Character> operatorStack=new Stack<Character>();
		
		
		StringTokenizer tokenizer = new StringTokenizer(infix,"+-*/^() ",true);
		
		while(tokenizer.hasMoreTokens()) {
			String token=tokenizer.nextToken().trim();
			if(isNumber(token)) { // 1. when token is a number 
				postfix.append(token).append(" ");
			}else if(token.equals("^")) { // 2.when token is power operator (R to L )
				operatorStack.push(token.charAt(0));
			}else if(token.equals("(")) { // 3.when token is opening bracket 
				operatorStack.push(token.charAt(0));
			}else if(token.equals(")")) { //4.when token is closing bracket 
				while(!operatorStack.isEmpty() && (operatorStack.peek())!='('){//ITERATATE STACK UNTIL CLOSING BRACKET
				postfix.append(operatorStack.pop()).append(" ");
				}
					if(operatorStack.isEmpty() || operatorStack.pop()!='('){ // if there is no corresponding opening bracket and stack is empty 
						throw new IllegalArgumentException("parenthes is unbalanced");
					}
					
					
		   }else if(isOperator(token.charAt(0))){  // 5. when token is an operator 
			   char operatorInToken =token.charAt(0);
		   
				while(!operatorStack.isEmpty() && getPriority(operatorStack.peek())>=getPriority(operatorInToken)) {
						//until  operator at top of stack has higher precedence than the operator in token 
						postfix.append(operatorStack.pop()).append(" "); //pop the operator and append to post fix  
					}
				
				operatorStack.push(operatorInToken);
			}else {//the token is an invalid character
			   throw new IllegalArgumentException("invalid character input : "+token);
		   }
			
		}
		while(!operatorStack.isEmpty()){//pop the remaining operatoers from the stack and keep it appending to the postfix expression 
			
				
			if(operatorStack.peek()=='(') {
				throw new IllegalArgumentException("parenthesis unbalanced");
			}
			else {
				postfix.append(operatorStack.pop()).append(" ");
			}
		}
		
		
		return postfix.toString();
	}
	
	static int getPriority(char op) {
		 7         7
		return opPrecedence.get(op);
	}
	
	
	static double evaluatePostfix(String postfix) {
		
		Stack<Double> operandStack=new Stack<Double>();
		StringTokenizer tokenizer=new StringTokenizer(postfix);
		
		while(tokenizer.hasMoreTokens()) {
			
			String token=tokenizer.nextToken().trim();
			System.out.println("token = "+token);
			
			if(isNumber(token)){
				operandStack.push(Double.parseDouble(token));
			}
			else if(isOperator(token.charAt(0))) {
				
				
				if(operandStack.size()<2) {
					
					System.out.println("size of stack = "+operandStack.size());
					System.out.println(operandStack.peek());
					
					throw new IllegalArgumentException("parenthesis unbalanced");
					
				}
				double y=operandStack.pop();
				double x=operandStack.pop();
				char operator=token.charAt(0);
				switch(operator) {
				
				case '+':
					operandStack.push(x+y);
					break;
					
				case '-':
					operandStack.push(x-y);
					break;
					
				case '*':
					operandStack.push(x*y);
					break;
					
				case '/':
				     if(y==0) {
				    	 throw new ArithmeticException("division by 0");
				     }
				     
				     else {
				    	 operandStack.push(x/y);
				    	 break;
				     }
				     
				case '^':
					operandStack.push(Math.pow(x,y));
					break;
				}
				
			
				
			}
			
		}
		
		if(operandStack.size()!=1) {
			System.out.println("invalid postfix expression");
			return -1;
		}
		
		return operandStack.pop();
		
	}
	
	static boolean isOperator(char ch) {
		
		return ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^';
	}
	static boolean isNumber(String token) {
		try {
			
				Double.parseDouble(token);
				return true;
			}catch(NumberFormatException e){
				return false;
			}
	}
}
	