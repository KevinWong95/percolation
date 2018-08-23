import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
 private int n;
   private int openSites;
   private int[] grid;
   private int[] size;
   private int topRoot;
   private int bottomRoot;
   private WeightQuickUnionUF myUnionFind;
   
   public Percolation (int n){
     this.myUnionFind = new WeightedQuickUnionUF(n);
     this.n = n;
     this.openSites = 0;
     
     this.topRoot = n*n;
     this.bottomRoot = 1+(n*n);
     
     this.grid = new int[bottomRoot];
     
     grid[topRoot] = topRoot;
     size[topRoot] = 1;
     
     grid[bottomRoot] = bottomRoot;
     size[bottomRoot] = 1;
    
     for (int i = 0; i<(n*n); i++){
       grid[i] = -1;
       size[i] = 1;
     }
     
   }
   
   public void open(int row, int col){
     if(row<1||col<1||row>n||col>n){
       throw new IllegalArgumentException();
     }
     int gridNumber = n*(row-1) + col-1;
     
     if(gridNumber<n){
         union(topRoot, gridNumber);
     }
     if(gridNumber>(n-1)*n){
       union(bottomRoot, gridNumber);
     }
     if(gridNumber<(n-1)*n){
       if(this.grid[gridNumber+n]>=0){
         if((size[gridNumber]>size[gridNumber+n])){
           union(gridNumber+n, gridNumber);
         }
         else{
           union(gridNumber,gridNumber+n)
         }
     }
     if(gridNumber>n-1){
       if(this.grid[gridNumber-n]>=0){
         if(size[gridNumber]>size[gridNumber-n]){
           union(gridNumber-n, gridNumber);
         }
         else{
           union(gridNumber, gridNumber-n)
         }
       }
     }
     if(gridNumber%n!=n-1){
       if(this.grid[gridNumber+1]>=0){
         if(size[gridNumber]>size[gridNumber+1]){
           union(gridNumber+1, gridNumber);
         }
         else{
           union(gridNumber, gridNumber+1)
         }
       }
     }
     if(gridNumber%n!=0){
       if(this.grid[gridNumber-1]>=0){
         if(size[gridNumber]>size[gridNumber-1]){
           union(gridNumber-1, gridNumber);
         }
         else{
           union(gridNumber, gridNumber-1)
         }
       }
     }
     openSites +=1;
   }
   
   public int root(int i){
     while(i!=this.grid[i]){
       if(grid[i]>-1){
         i=this.grid[i];
       }
       else{
         break;
       }
     }
     return i;
   }
   
   public int count(){
     return myUnionFind.count();
   }
   
   public boolean connected(int a, int b){
     return myUnionFind.connected(a, b);
   }
   
   public int find(int p){
     return myUnionFind.find(p);
   }
   
   public void union(int p, int q){
     return myUnionFind.union(p, q);
   }
   
   public boolean isOpen(int row, int col){
     if(row<1||col<1||row>n||col>n){
       throw new IllegalArgumentException();
     }
     int gridNumber = (row-1)*n + col-1;
     return grid[gridNumber]>=0;
   }
   
   public boolean isFull(int row, int col){
     if(row<1||col<1||row>n||col>n){
       throw new IllegalArgumentException();
     }
     int gridNumber = (row-1)*n + col-1;
     return connected(gridNumber, topRoot);
   }
   
   public int numberOfOpenSites(){
     return openSites;
   }
   
   public boolean percolates(){
     return (connected(bottomRoot, topRoot));
   }
   
  
