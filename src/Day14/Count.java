package Day14;

public class Count {
    public long[] counts;
    public int last;

    public Count(int last){
        this.counts = new long[10];
        this.last = last;
    }

    public Count(Count c){
        this.last = c.last;
        this.counts = new long[10];
        for(int i=0; i<10; i++){
            this.counts[i] = c.counts[i];
        }
    }

    public Count(Count c1, Count c2){
        this.last = c2.last;
        this.counts = new long[10];
        for(int i=0; i<10; i++){
            this.counts[i] = c1.counts[i] + c2.counts[i];
        }
        this.counts[c1.last] --;
    }

    public long getFinalAnswer(){
        long max = counts[0];
        long min = counts[0];
        for(long n : counts){
            if(n > max) max = n;
            else if(n < min) min = n;
        }
        return max - min;
    }
}
