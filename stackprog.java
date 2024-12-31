package ncu;

public class stackprog {
int s[] = new int[20];
int sp, n;
stackprog(int nn)
{
		sp=-1;
		n = nn;
		for(int i=0; i<n; i++)
		{
		s[i] = 0;
	}
}
boolean isFull()
{
	return sp == (n-1);
}

boolean isEmpty()
{
return sp==-1;

}
void pushdata(int element)
{
	if(isFull())
	{
		System.out.println("Stack overflows");
	}
		else
		{
		sp = sp+1;
		s[sp] = element;
		System.out.println("Pushed element is " +element);
	}}

void popdata()
{
	if(isEmpty())
		System.out.println("Stack underflows");
	else
	{
	int v;
	v = s[sp];
	System.out.println("Popped out element is " +v);
	sp = sp-1;
}
}
void peek() {
    if (isEmpty()) {
        System.out.println("Stack is empty, nothing to peek");
    } else {
        System.out.println("Top element: " + s[sp]);
    }
}
void display() {
    if (isEmpty()) {
        System.out.println("Stack is empty");
    } else {
        System.out.println("Stack elements:");
        for (int i = sp; i >= 0; i--) {
            System.out.println(s[i]);
        }
    }
}

public static void main(String args[])
{
	stackprog ob = new stackprog(20);
    ob.pushdata(10);  
    ob.pushdata(20); 
    ob.pushdata(30);  

    ob.display();

    ob.peek();    

    ob.popdata();     
    ob.popdata();     

    ob.display(); 

    ob.popdata();     
    ob.popdata();     

    ob.isEmpty(); 
}	
}

