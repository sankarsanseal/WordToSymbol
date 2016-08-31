package WordToSymbol;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class FindOut {
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int no_of_instances;
		int i,j,k;
		Integer temp;
	
		String inputstr[];
		String symbolstr[];
		String words[];
		String tokens[];
		String tempword;
		String tempsym;
		HashMap <String, Integer> htable =new HashMap<String, Integer>();
		HashMap <String, Integer> hscount=new HashMap<String, Integer>();
		HashMap <String,Integer> hwcount=new HashMap<String,Integer>();
		HashMap <String, String> hmap=new HashMap<String, String>();
		Iterator wn;
		Iterator sn;
		Set wset;
		Set sset;
		try{
			System.out.println("Enter number of the Sentence pairs:");
			no_of_instances=sc.nextInt();
			System.out.println("First Line is usual sentence "+
			"and second line is sentence in symbolic represntation:");
			inputstr=new String [no_of_instances];
			symbolstr=new String[no_of_instances];
			for(i=0;i<no_of_instances;i++)
			{
				inputstr[i]=br.readLine();
				symbolstr[i]=br.readLine();
				
				words=inputstr[i].split("\\s+");
				tokens=symbolstr[i].split("\\s+");
				
				for(j=0;j<words.length;j++)
				{
					for(k=0;k<tokens.length;k++)
					{
					

						temp=1;
						if(htable.containsKey(words[j].toLowerCase()+tokens[k].toLowerCase()))
						{
							temp=htable.get(words[j].toLowerCase()+tokens[k].toLowerCase());
							
							temp++;
							System.out.println(words[j].toLowerCase()+tokens[k].toLowerCase()+"->"+temp);
							
						}
						
							htable.put(words[j].toLowerCase()+tokens[k].toLowerCase(), temp);

							

					}
					
					temp=1;
					if(hwcount.containsKey(words[j].toLowerCase()))
					{
						temp=hwcount.get(words[j].toLowerCase());
						temp++;
						
						
					}
					hwcount.put(words[j].toLowerCase(), temp);
				}

				for(k=0;k<tokens.length;k++)
				{
				temp=1;
				if(hscount.containsKey(tokens[k].toLowerCase()))
				{
					temp=hscount.get(tokens[k].toLowerCase());
					temp++;
				}
				hscount.put(tokens[k].toLowerCase(), temp);
				
				}
				
				//System.out.println(inputstr);
				//System.out.println(symbolstr);
				//sc.next();
				
			}
			
			wset=hwcount.keySet();
			sset=hscount.keySet();
			wn=wset.iterator();
			sn=sset.iterator();
			while(wn.hasNext())
			{
				tempword=(String)wn.next();
				
				while(sn.hasNext())
				{
					tempsym=(String)sn.next();
					if(hwcount.get(tempword)==hscount.get(tempsym) && 
							htable.get(tempword.toLowerCase()+tempsym.toLowerCase())==hwcount.get(tempword))
					{
						hmap.put(tempword, tempsym);
					}
				}
			}
			
			
/*			for(i=0;i<no_of_instances;i++)
			{
				words=inputstr[i].split("\\s+");
				
			}
	*/				
			System.out.println("The identified mapping is:");
			
			wset=hmap.keySet();
			wn=wset.iterator();
			while(wn.hasNext())
			{
				tempword=(String)wn.next();
				System.out.println(tempword+"->"+hmap.get(tempword));
			}

			sc.close();
		
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
	}

}
