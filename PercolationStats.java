import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class PercolationStats {
  private int trials, size;
  private double[] data;
  private StdRandom random;
  
  public PercolationStats(int n, int trials){
    random = new StdRandom(n, trials);
    int row, col;
    this.size = n;
    this.trials = trials;
    this.data = new double[this.trials];
    for (int i=0; i<this.trials; i++){
      Percolation runTrial = new Percolation(n);
      while(!runTrial.percolates()){
        row = (int)(random()*n+1);
        col = (int)(random()*n+1);
        runTrial.open(row, col);
      }
      this.data[i]=(double)runTrial.numberofOpenSites()/(this.size*this.size);
    }
    
  }
  
  public static double random(){
    return StdRandom.random();
  }
  
  public double mean(){
 return StdStats.mean(this.data);
  }
  
  public double stddev(){
    return StdStats.stddev(this.data);
  }
  
  public double confidenceLo(){
    return mean()-(1.96*stddev()/Math.sqrt((double)this.trials));
  }
  
  public double confidenceHi(){
 return mean()+(1.96*stddev()/Math.sqrt((double)this.trials)); 
   }
  
}