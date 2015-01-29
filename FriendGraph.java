//Name: Ammar Abou-Rahma
//ID: aabourah

public class FriendGraph {

	int[][] Graph = new int[10][10];
	Person[] Persons = new Person[10];
	int nextEmpty = 0;

	public void addPerson(Person h) {
		if (nextEmpty != 1000) {
			Persons[nextEmpty] = h;
			nextEmpty = nextEmpty + 1;
		}
	}

	public void addFriendship(Person a, Person b) {
		if (!(a.equals(b))) {
			int tempa = -1;
			int tempb = -1;
			for (int i = 0; i < nextEmpty; i++) {
				if ((Persons[i].equals(a))) {
					tempa = i;
				}
				if ((Persons[i].equals(b))) {
					tempb = i;
				}
			}
			if ((tempa == -1) || (tempb == -1)) {
			} else {
				Graph[tempa][tempb] = 1;
				Graph[tempb][tempa] = 1;
			}
		}
	}

	public int getDistance(Person a, Person b) {
		int tempa = -1;
		int tempb = -1;
		if (a.equals(b)) {
			return 0;
		}
		if (!(a.equals(b))) {
			for (int i = 0; i < nextEmpty; i++) {
				if ((Persons[i].equals(a))) {
					tempa = i;
				}
				if ((Persons[i].equals(b))) {
					tempb = i;
				}
			}
			if ((tempa == -1) || (tempb == -1)) {
				return -1;
			}
			for (int i = 1; i < nextEmpty; i++) {
				int[][] newGraph = powerMatrix(Graph, i);
				if ((newGraph[tempa][tempb]) > 0) {
					return i;
				}
			}
		}
		return -1;
	}

	// The website below is the reference i used just to raise a matrix to a specific power, that is for 
	// the multiplyMatrices and powerMatrix funcions.
	// http://stackoverflow.com/questions/22900872/raising-a-matrix-to-the-power-method-java
	public int[][] multiplyMatrices(int[][] a, int[][] b) {
		// compute and return a x b, similar to your existing multiplication
		// algorithm, and of course taking into account the comments about
		// the 'temp' output matrix above
		int temp[][] = new int[a.length][a.length];

		for (int i = 0; i < a.length; i++) {
			temp[i] = new int[a.length];
			for (int j = 0; j < a.length; j++) {
				int sum = 0;
				for (int l = 0; l < a.length; l++) {
					sum += a[i][l] * b[l][j];
				}
				temp[i][j] = sum;
			}
		}
		return temp;
	}

	public int[][] powerMatrix(int[][] a, int p) {
		int[][] result = a;
		for (int n = 1; n < p; ++n)
			result = multiplyMatrices(result, a);
		return result;
	}

	public void showme() {
		for (int i = 0; i < nextEmpty; i++) {
			for (int j = 0; j < nextEmpty; j++) {
				System.out.print(Graph[i][j]);
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FriendGraph graph = new FriendGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addPerson(rachel);
		graph.addPerson(ross);
		graph.addPerson(ben);
		graph.addPerson(kramer);
		graph.addFriendship(rachel, ross);
		graph.addFriendship(ross, ben);
		// System.out.println(graph.Graph[0][0]);
		graph.showme();
		int[][] yo = graph.powerMatrix(graph.Graph, 2);
		for (int i = 0; i < yo.length; i++) {
			for (int j = 0; j < yo.length; j++) {
				System.out.print(yo[i][j]);
			}
			System.out.print("\n");
		}
		 System.out.println(graph.getDistance(rachel, ross)); //should print 1
		 System.out.println(graph.getDistance(rachel, ben)); //should print 2
		 System.out.println(graph.getDistance(rachel, rachel)); //should print 0
		 System.out.println(graph.getDistance(rachel, kramer)); //should print -1
	}
}
