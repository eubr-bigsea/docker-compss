
package matmul.objects;

import java.io.FileOutputStream;
import java.io.Serializable;


public class Block implements Serializable {
	
	private int M;
	private double[][] data;
	
	public Block() { }
	
	public Block(int bSize) {
		M = bSize;
		data = new double[M][M];
		
		for (int i = 0 ; i < M; i++)
			for (int j = 0; j < M; j++)
				data[i][j] = 0.0;
	}
	
	public int getM() {
		return M;
	}
	
	public void setM(int i) {
		M = i;
	}
	
	public double[][] getData() {
		return data;
	}
	
	public void setData(double[][] d) {
		data = d;
	}
	
	public void printBlock() {
		for (int i = 0 ; i < M; i++)
			for (int j = 0; j < M; j++)
				System.out.print(data[i][j] + " ");
    	System.out.println("");
    }
	
	public void blockToDisk(int i, int j, String name) {
        try {
            FileOutputStream fos = new FileOutputStream(name + "." + i + "." + j);

            for (int k1 = 0; k1 < M; k1++) {
                for (int k2 = 0; k2 < M; k2++) {
                	String str = data[k1][k2] + " ";
                	fos.write(str.getBytes());
                }
                fos.write( "\n".getBytes() );
            }
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void multiplyAccumulative(Block a, Block b) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					data[i][j] += a.data[i][k] * b.data[k][j];
				}
			}
		}
	}
	
	public static Block initBlock(int M, double initVal) {
    	Block block = new Block(M);
    	for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				block.data[i][j] = initVal;
			}
    	}
    	
		return block;
    }
	
}
