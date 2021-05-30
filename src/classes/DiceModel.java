package classes;

public class DiceModel {
    private int r;
    private int s;
    private int[] arr = new int[3];

    public DiceModel(){

    }
    public int getRandom(){
        r = (int)(Math.random()*6+1);
      /*  r2=(int)(Math.random()*6+1);
        r3=(int)(Math.random()*6+1);*/
        return r;
    }
    public int getSum(int a, int b, int c){
        s=a+b+c;
        return s;
    }
   /* public int getrArray(){
        for (int i=0; i<3; i++){
            arr[i]=getRandom();
            System.out.println(arr[i]);
        }
    }*/
}
