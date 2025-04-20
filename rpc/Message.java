package rpc;

import java.io.Serializable; 
public class Message implements Serializable{ 
    private String method; 
    private int arg1; 
    private int arg2; 
    private int result;

    public Message(int arg1, int arg2, String method){ 
        this.arg1 = arg1; 
        this.arg2 = arg2; 
        this.method = method; 
    } 

    public String getMethod(){return method;} 
    public int getArg1(){return arg1;} 
    public int getArg2(){return arg2;} 
    public int getResult(){return result;} 
    public void setResult(int result){this.result = result;} 
}
