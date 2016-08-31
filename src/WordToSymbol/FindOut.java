package WordToSymbol;
import java.util.Map;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.String;


public class FindOut {
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int no_of_instances;
		int i,j,k;
		Integer max;
		Integer temp;
		boolean highprobability;
	
		String inputstr[];
		String symbolstr[];
		String words[];
		String tokens[];
		String tempword;
		String tempword2;
		String tempword3;
		String tempsym;
		HashMap <String, Integer> htable =new HashMap<String, Integer>();
		HashMap <String, Integer> hscount=new HashMap<String, Integer>();
		HashMap <String,Integer> hwcount=new HashMap<String,Integer>();
		HashMap <String, String> hmap=new HashMap<String, String>();
		Iterator wn;
		Iterator sn;
		Iterator wholen;
		Set wset;
		Set sset;
		Set wholeset;
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
							//System.out.println(words[j].toLowerCase()+tokens[k].toLowerCase()+"->"+temp);
							
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
					//System.out.println(words[j].toLowerCase()+"->"+temp);
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
				
				//System.out.println(tokens[k].toLowerCase()+"->"+temp);
				
				}
				
				//System.out.println(inputstr);
				//System.out.println(symbolstr);
				//sc.next();
				
			}
			
			wset=hwcount.keySet();
			sset=hscount.keySet();
			wn=wset.iterator();
			
			while(wn.hasNext())
			{
				tempword=(String)wn.next();
				sn=sset.iterator();
				
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
				System.out.print(tempword+"->"+hmap.get(tempword));
				tempword2=tempword+hmap.get(tempword);
				//System.out.print(tempword2);
				if(htable.containsKey(tempword2))
				{
					max=htable.get(tempword2);
					wholeset=htable.keySet();
					wholen=wholeset.iterator();
					highprobability=false;
					while(wholen.hasNext())
					{
						tempword3=(String)wholen.next();
						if(tempword3.contains(tempword))
						{
							if(htable.get(tempword3)==max &&!tempword3.equals(tempword2))
								highprobability=true;
						}
							
						
					}
					
					if(highprobability)
						System.out.print("(With high probability)");
					else
						System.out.print("(With probability 1)");
					
				}
				
				System.out.println();
			}
			
			

			sc.close();
		
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
	}

}
