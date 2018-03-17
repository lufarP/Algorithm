package Algorithm;
/*
 * Dynamic Programming
 * use to find the minimum cost of Bst
 * time complexity O(n2)
 * space complexity O(n2)  
*/
class OptimalBinarySearchTree
	{
		private int costMatrix[][];//triangular matrix 
		private int cumulativeFrequency[];
		private int size;
		private boolean costEvaluated;
		
		public OptimalBinarySearchTree(int cumulativeFrequency[])
			{
				size=cumulativeFrequency.length;
				setcumulativeFrequency(cumulativeFrequency);
				costMatrix=new int[size][];
				for(int i=0;i<size;i++)
					{
						costMatrix[i]=new int[size-i];
					}
				setCostMatrix();
			}
		private void setCostMatrix()
			{
				// TODO Auto-generated method stub
				for(int i=0;i<size;i++)
					costMatrix[i][0]=getSumOfFrequency(i, i);
			}
		private void setcumulativeFrequency(int cumulativeFrequency[])
		{
			this.cumulativeFrequency=cumulativeFrequency;
		}
		private int getSumOfFrequency(int i,int j)//inclusive i and j
		{
			if(i==0)
				return cumulativeFrequency[j];
			else
				return cumulativeFrequency[j]-cumulativeFrequency[i-1];
		}public int getOptimalCost()
		{
			costEvaluated=true;
			return optimalCost(0,size-1);
		}
		public int[][] getCostMatrix()
		{
			if(!costEvaluated)
				optimalCost(0, size-1);
			return costMatrix;
		}
		private int optimalCost(int i, int j)
			{
				if(i>j)
					return 0;
				else if(i==j)
					return costMatrix[i][j-i];
				else
					{
						if(costMatrix[i][j-i]==0)
							{
								int minCost=Integer.MAX_VALUE;
								
								for(int k=i;k<=j;k++)
									{
										minCost=Math.min(minCost,(optimalCost(i,k-1) + optimalCost(k+1, j)));
										
									}
								costMatrix[i][j-i]=getSumOfFrequency(i,j)+minCost;
							}
						return costMatrix[i][j-i];						
					}
			}
	}
