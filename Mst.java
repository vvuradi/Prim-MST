public class Mst {
	/*
	 * The program starts here. Parsing the input parameters and calls
	 * corresponding methods
	 */
	public static void main(String[] args) {
		int argLen = args.length;
		GraphGenerator minSpanTree = new GraphGenerator();
		// no of params should be either 2 or 3
		if (argLen != 2 && argLen != 3) {
			System.out.println("Please enter valid input arguments");
			return;
		}
		String arg1 = args[0];
		if (arg1.equalsIgnoreCase("-f")) {// fibonacci heap scheme
			FheapScheme fScheme;
			try {
				String fileName = args[1];
				fScheme = new FheapScheme();
				// generating graph
				minSpanTree.generateGraph(fScheme, fileName);
				int noOfVertices = minSpanTree.getNoOfVertices();
				// calling the method that runs Prim's algo
				fScheme.spanTree(noOfVertices, minSpanTree.getVtxPool());
				fScheme.printSol();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (arg1.equalsIgnoreCase("-s")) {// simple scheme
			SimpleScheme sScheme;
			try {
				String fileName = args[1];
				sScheme = new SimpleScheme();
				// generating graph
				minSpanTree.generateGraph(sScheme, fileName);
				int noOfVertices = minSpanTree.getNoOfVertices();
				// calling the method that runs Prim's algo
				sScheme.spanTree(noOfVertices, minSpanTree.getVtxPool());
				sScheme.printSol();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (arg1.equalsIgnoreCase("-r")) {// random generator case
			try {
				int limit = Integer.parseInt(args[1]);
				int density = Integer.parseInt(args[2]);
				// generating random graph
				minSpanTree.generateRandomGraph(limit, density);

				SimpleScheme ss = new SimpleScheme();
				FheapScheme fs = new FheapScheme();

				long t1 = System.currentTimeMillis();
				// calling fibonacci scheme
				fs.spanTree(limit, minSpanTree.getVtxPool());
				long t2 = System.currentTimeMillis();

				// calling simple scheme
				ss.spanTree(limit, minSpanTree.getVtxPool());
				long t3 = System.currentTimeMillis();

				System.out.println("Fibona Scheme - " + (t2 - t1));
				System.out.println("Simple Scheme - " + (t3 - t2));
				//ss.printSol();
				//fs.printSol();

			} catch (NumberFormatException numFormat) {
				System.out.println("Invalid Input - " + numFormat.getMessage());
			} catch (ArrayIndexOutOfBoundsException arrEx) {
				System.out.println("Array Index Out of bound for input - "
						+ arrEx.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("Enter valid inputs");
		}
	}
}
