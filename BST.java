import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

class BSTFilesBuilder {

	public void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter("C:/Users/prerna/Desktop/"+i+".txt", "UTF-8");
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
public class BST<E extends Comparable<E>> {
	List<E> gen=new ArrayList<E>();
	public Node<E> root;
	String s="";
	private class Node<E> {
        E data;
        Node<E> left, right;

        Node(E t) {
            data=t;
            left=null;
            right=null;
        }
    }

    public BST(){
        root=null;
    }
   
    private void insertI(E value, Node<E> parent) {
        int comp=value.compareTo(parent.data);
        if(comp > 0) {
            if(parent.right == null) {
                parent.right = new Node<E>(value);
            }
            else {
                insertI(value, parent.right);
            }
        }
        else{
            if(parent.left == null) {
                parent.left = new Node<E>(value);
            }
            else {
                insertI(value, parent.left);
            }
        }
    }

    public void insert(E value) {
        if(root == null) {
            root = new Node<E>(value);
            return;
        }
        insertI(value, root);
    }

    
    public void inOrder(Node<E> r)
    {
       if (r != null)
       {
          inOrder(r.left);
          gen.add(r.data);
          s=s+r.data+" ";
          inOrder(r.right);
       }
    } 
    public static class block{
		ArrayList<String> a;
		block(){
			this.a=new ArrayList<String>();;
		}
    }

    public static void main(String[] args) throws IOException,FileNotFoundException,UnsupportedEncodingException {
    	BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(b.readLine());
    	int P=Integer.parseInt(st.nextToken());	//NumTree
    	int Q=Integer.parseInt(st.nextToken());	//NumStu
    	block[] A=new block[Q+1];
    	for(int i=0;i<=Q;i++){
    		A[i]=new block();
    	}
    	BSTFilesBuilder build=new BSTFilesBuilder();
    	build.createBSTFiles(Q, P);          //(NUMSTU,NUMTREE)
    	int N=0;
    	PrintWriter w1 = new PrintWriter("C:/Users/prerna/Desktop/output.txt", "UTF-8");
    	for(int a=1;a<=P;a++){
    		BST bst=new BST();
    		List<String> lines = Files.readAllLines(Paths.get("C:/Users/prerna/Desktop/"+a+".txt"), StandardCharsets.UTF_8);
    		if(lines.get(0).equals("String")){
    			bst = new BST<String>();
    			N=Integer.parseInt(lines.get(1));
    			StringTokenizer s = new StringTokenizer(lines.get(2));
    			for(int i=0;i<N;i++){
    				bst.insert(s.nextToken());
    			}
    		}
    		else if(lines.get(0).equals("Float")){
    			bst = new BST<Float>();
    			N=Integer.parseInt(lines.get(1));
    			StringTokenizer s = new StringTokenizer(lines.get(2));
    			for(int i=0;i<N;i++){
    				bst.insert(Float.parseFloat(s.nextToken()));
    			}
    		}else if(lines.get(0).equals("Integer")){
    			bst = new BST<Integer>();
    			N=Integer.parseInt(lines.get(1));
    			StringTokenizer s = new StringTokenizer(lines.get(2));
    			for(int i=0;i<N;i++){
    				bst.insert(Integer.parseInt(s.nextToken()));
    			}
    		}
    		bst.inOrder(bst.root);
    		int k=0;
    		for(;k<N;k++){
    			if(bst.gen.get(k).equals(bst.root.data)){
    				break;
    			}
    		}
    		
    		Float f=0.0f;
    		int t=0;
    		String s1="";
    		if(bst.root.data instanceof String){
    			s1=bst.s.replaceAll(" ", "");
    		}
    		else if(bst.root.data instanceof Integer){
    			StringTokenizer sc=new StringTokenizer(bst.s);
    			for(int i=0;i<N;i++){
    				t+=Integer.parseInt(sc.nextToken());
    			}
    			s1=s1+t;
    		}
    		else if(bst.root.data instanceof Float){
    			StringTokenizer sc=new StringTokenizer(bst.s);
    			for(int i=0;i<N;i++){
    				f+=Float.parseFloat(sc.nextToken());
    			}
    			s1=s1+f;
    		}
    		A[k+1].a.add(s1);
    	}
    	int count=0;
        for(int k1=1;k1<=N;k1++){
        	if(A[k1].a.isEmpty()){
        		count++;
        	}else{
        		w1.print(k1+" ");
        		System.out.print(k1+" ");
        		int n=A[k1].a.size();
        		int i=0;
        		while(i<n){
        			w1.print(A[k1].a.get(i)+" ");
        			System.out.print(A[k1].a.get(i)+" ");
        			i++;
        		}w1.println();
        		System.out.println();
        	}
        }if(count!=0){
        w1.println(count);	
        System.out.println(count);
        }w1.close();
    }
}