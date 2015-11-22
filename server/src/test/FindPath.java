package test;

import java.util.ArrayList;

import pathfind.NoPathException;
import pathfind.Node;

public class FindPath {

	private static final boolean X = true;
	private static final boolean O = false;

	private boolean[][] level;

	/*j
	private enum Moves {
		up,
		down,
		left,
		right
	}
	*/

	boolean[][] colored = new boolean[6][6];

	public ArrayList<Node> getPath(int startX, int startY, int endX, int endY) throws NoPathException {

		level = new boolean[][]{
			{ X, X, X, X, X, X },
			{ X, O, X, O, O, X },
			{ X, O, X, X, O, X },
			{ X, O, O, O, O, X },
			{ X, O, O, X, O, X },
			{ X, X, X, X, X, X }
		};

		colored = level;







		return bfs( new Node(startX, startY), new Node(endX, endY) );
	}

	/*
	private static void backUp( Moves direction ) {
	}
	*/

	private ArrayList<Node> bfs( Node startPos, Node endPos ) throws NoPathException {
		ArrayList<Node> queue = new ArrayList<Node>();
		Node[] nextMoves = new Node[4];
		Node finalNode = null;

		queue.add(startPos);

		boolean isDone = false;
		while( queue.size() > 0 && !isDone ) {
			Node item = queue.get(0);
			queue.remove(item);
			color( item );

			nextMoves[0] = new Node( item.getXPos(), item.getYPos() - 1, item );
			nextMoves[1] = new Node( item.getXPos(), item.getYPos() + 1, item );
			nextMoves[2] = new Node( item.getXPos() - 1, item.getYPos(), item );
			nextMoves[3] = new Node( item.getXPos() + 1, item.getYPos(), item );

			//System.out.println("-----------");
			//System.out.println(item);
			//System.out.println("-----------");

			for( Node node : nextMoves ) {
				if( !getColored(node) ) {
					if( node.getXPos() == endPos.getXPos() &&
						node.getYPos() == endPos.getYPos() ) {
						finalNode = node;
						isDone = true;
						break;
					} else {
						queue.add(node);
					}
				}
			}
		}

		System.out.println("===========");

		ArrayList<Node> path = new ArrayList<Node>();
		try {
			Node tempNode = finalNode;
			try {
				//while(!(tempNode.getXPos() == startPos.getXPos() &&
				//	    tempNode.getYPos() == startPos.getYPos() )) {
				boolean hmmm = true;
				while(hmmm) {
					//System.out.println(tempNode);
					path.add(tempNode);
					tempNode = tempNode.getParent();
				}
			} catch( NullPointerException e ) {
			}
		} catch( NullPointerException e ) {
			throw new NoPathException();
		}

		// Note that the path will be reversed
		return path;
	}
	
	private void printBoard() {
		for( boolean[] outer : colored ) {
			for( boolean inner : outer ) {
				if( inner ) 
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("------");
	}

	private void color( Node node ) {
		try {
			colored[node.getYPos()][node.getXPos()] = true;
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	private boolean getColored( Node node ) {
		boolean returnValue;
		try {
			returnValue = colored[node.getYPos()][node.getXPos()];
		} catch( IndexOutOfBoundsException e ) {
			returnValue = true;
		}

		//System.out.println( node + "\t| " + returnValue );
		//this.printBoard();

		return returnValue;

	}


	public static void main(String[] args) {
		//FindPath.getPath(0, 2, 2, 0);
		ArrayList<Node> data;
		try {
			data = new FindPath().getPath(1, 1, 4, 4);
			System.out.println("===========");
			for( Node node : data ) {
				System.out.println( node );
			}
		} catch (NoPathException e) {
			System.out.println("no path");
			e.printStackTrace();
		}
	}
}
