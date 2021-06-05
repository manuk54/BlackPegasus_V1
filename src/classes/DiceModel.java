package classes;

public class DiceModel {
    private int r;
    private int s;
    private int[] arr = new int[3];

    public DiceModel(){

    }
    public int getRandom(){
        r = (int)(Math.random()*6+1);
        return r;
    }
    public int getSum(int a, int b, int c){
        s=a+b+c;
        return s;
    }
    public int getMoney() {
        int m = 50;// wo findet diesen Wert?
        return m;
    }
}
