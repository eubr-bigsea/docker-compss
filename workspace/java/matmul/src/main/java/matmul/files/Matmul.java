
package matmul.files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Matmul
{
	private static int MSIZE;
	private static final int BSIZE = 40;

	private String [][]_A;
	private String [][]_B;
	private String [][]_C;

	public void Run ()
	{
		// initialize arrays holding the actual array names
		initializeVariables();
		try
		{
			fillMatrices();
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace();
			return;
		}

		for (int i = 0; i < MSIZE; i++)
		{
			for (int j = 0; j < MSIZE; j++)
			{
				for (int k = 0; k < MSIZE; k++)
				{
					MatmulImpl.multiplyAccumulative( _C[i][j], _A[i][k], _B[k][j] );
				}
            }
		}
		
	}

	private void initializeVariables ()
	{
		_A = new String[MSIZE][MSIZE];
		_B = new String[MSIZE][MSIZE];
		_C = new String[MSIZE][MSIZE];
		for ( int i = 0; i < MSIZE; i ++ )
		{
			for ( int j = 0; j < MSIZE; j ++ )
			{
				_A[i][j] = "A." + i + "." + j;
				_B[i][j] = "B." + i + "." + j;
				_C[i][j] = "C." + i + "." + j;
			}
		}
	}

	private void fillMatrices ()
		throws FileNotFoundException, IOException
	{
	    for ( char c = 'A'; c < 'D'; c++ )
	    {
	        for ( int i = 0; i < MSIZE; i++ )
	        {
	            for ( int j = 0; j < MSIZE; j++ )
	            {
	                String tmp = c + "." + i + "." + j;
	                FileOutputStream fos = new FileOutputStream(tmp);
	                for ( int ii = 0; ii < BSIZE; ii++ )
	                {
	                    for(int jj = 0; jj < BSIZE; jj ++)
	                    {
	                        if(c == 'C')
	                        {
	                            fos.write("0.0 ".getBytes());
	                        }
	                        else
	                        {
	                            fos.write("2.0 ".getBytes());
	                        }
	                    }
	                    fos.write("\n".getBytes());
	                }
	                fos.close();
	            }
	        }
	    }
	}


	public static void main(String args[])
	{
		MSIZE = Integer.parseInt(args[0]);
		(new Matmul()).Run();
	}
	
}

