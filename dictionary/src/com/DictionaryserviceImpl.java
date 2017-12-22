package com;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
public class DictionaryserviceImpl extends UnicastRemoteObject implements IDictionaryservice {

	public static List<Dictionary> dictionarys=new ArrayList<Dictionary>(); 
	
	protected DictionaryserviceImpl() throws RemoteException {
        super();
        
        File[] filess = new File("data").listFiles();
     	for (File file1 : filess) {
           
     	  if (file1.isFile() && file1.getName().equals("dictionary.txt")) {
     		String str=new FileWriteAndRead().readFile(file1);
     		String[] strs=str.split("#");
     		for(int i=0;i<strs.length;i++){
     			String[] estr=strs[i].split(":");
     			dictionarys.add(new Dictionary(estr[0].replace("\n", ""),estr[1]));
     			
     		}
     	  }
     	}
        
    }
	
   public boolean  saveData() throws RemoteException {
    	String str="";
    	for(int i=0;i<dictionarys.size();i++){
    		str+=dictionarys.get(i).getCode()+":"+dictionarys.get(i).getDefinition()+"#";
    	}
	    if(!str.equals("")){
	    	str=str.substring(0,str.length()-1);
	    }
    	
	    File[] filess = new File("data").listFiles();
     	for (File file1 : filess) {
     		 if (file1.isFile() && file1.getName().equals("dictionary.txt")) {
     			 new FileWriteAndRead().writerFileByfile(str, file1);
     		 }
     	}
	    
	   
        return true;
    }
	
    
    private static final long serialVersionUID = 4077329331699640331L;
    
    public String sayTxt(String name) throws RemoteException {
        return "text:" + name;
    }
    public String  getDictionaryList() throws RemoteException {
    	String result="";
    	for(int i=0;i<dictionarys.size();i++){
    		result+=dictionarys.get(i).getCode()+"::::"+dictionarys.get(i).getDefinition()+"\n";
    	}
    	
        return result;
    }
    public boolean  add(String code,String definition) throws RemoteException {
    	
    	
    	dictionarys.add(new Dictionary(code,definition));
    	saveData();
        return true;
    }
    public boolean  delete(String code) throws RemoteException {
    	
    	for(int i=0;i<dictionarys.size();i++){
    		if(code.equals(dictionarys.get(i).getCode())){
    			dictionarys.remove(i);
    			i--;
    		}
    	}
    	saveData();
    	return true;
    }
    
    
    
    public String comparison(String txt) throws RemoteException {
    	
    	
    	for(int i=0;i<dictionarys.size();i++){
    		if(txt.equals(dictionarys.get(i).getCode())){
    			return dictionarys.get(i).getDefinition();
    		}
    	}
        return "String not found";
    }
    public static void main(String[] args) {
        try {
        	IDictionaryservice server = new DictionaryserviceImpl();
            LocateRegistry.createRegistry(1099); 
            java.rmi.Naming.rebind("rmi://localhost:1099/hello", server);
            System.out.print("Reading");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   class Dictionary{
	   private String code;
	   private String definition;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Dictionary(String code, String definition) {
		super();
		this.code = code;
		this.definition = definition;
	}
	   
	   
   }
    
    }