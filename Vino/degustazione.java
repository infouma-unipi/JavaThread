package Vino;

//main
public class degustazione {
	public static void main(String[] args){
		botte rubinetto = new botte();
		degustatore r1=new degustatore("Pinco Panco", rubinetto);
		degustatore r2=new degustatore("Panco Pinco", rubinetto);
		degustatore r3=new degustatore("Piripicchio", rubinetto);
		degustatore r4=new degustatore("Piripacchio", rubinetto);
		degustatore r5=new degustatore("Tizio", rubinetto);
		degustatore r6=new degustatore("Caio", rubinetto);
		degustatore r7=new degustatore("Sepronio", rubinetto);
		degustatore r8=new degustatore("Mario Rossi", rubinetto);
		degustatore r9=new degustatore("Gennarino", rubinetto);
		degustatore r10=new degustatore("Pierino", rubinetto);
	} 
}