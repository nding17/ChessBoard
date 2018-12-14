import java.util.ArrayList;
import java.util.Stack;

public class Board implements EndSubject {

	public static final int BOARD_WIDTH = 8, BOARD_HEIGHT = 8;
	public static final int ILLEGAL_MOVE = 0, NORMAL_MOVE = 1, SPECIAL_MOVE = 2;
	private ChessPiece[][] pieces;
	private int numCaptured = 0;
	private Turns chessTurn = new Turns();
	private Stack<Command> moves = new Stack<Command>();
	private ArrayList<ChessPiece> movedPieces = new ArrayList<ChessPiece>();
	private boolean canPromote;

	public Board() {
		pieces = new ChessPiece[BOARD_WIDTH][BOARD_HEIGHT];
	}

	public Board(BoardInitializer init) {
		this();
		init.createBoard(this, chessTurn);
		chessTurn.notifyObservers();
	}

	private int[] convertAlgebraicToComputerScience(char col, int row) {
		int[] arr = new int[2];
		int colAsInt = col - 'a';
		int rowAdjusted = row - 1;
		arr[0] = colAsInt;
		arr[1] = rowAdjusted;
		return arr;
	}

	private char convertComputerScienceToAlgebraicCol(int col) {
		char algebraicCol;
		switch (col) {
		case 0:
			algebraicCol = 'a';
			break;
		case 1:
			algebraicCol = 'b';
			break;
		case 2:
			algebraicCol = 'c';
			break;
		case 3:
			algebraicCol = 'd';
			break;
		case 4:
			algebraicCol = 'e';
			break;
		case 5:
			algebraicCol = 'f';
			break;
		case 6:
			algebraicCol = 'g';
			break;
		case 7:
			algebraicCol = 'h';
			break;
		default:
			algebraicCol = (char) -1;
			break;
		}
		return algebraicCol;
	}

	public ArrayList<ChessPiece> getPiecesMoved() {
		return movedPieces;
	}

	public ChessPiece pieceAt(char col, int row) {
		int[] adjustedCoordinates = convertAlgebraicToComputerScience(col, row);
		return pieces[adjustedCoordinates[0]][adjustedCoordinates[1]];
	}

	public void setPiece(ChessPiece piece, char col, int row) {
		int[] adjustedCoordinates = convertAlgebraicToComputerScience(col, row);
		pieces[adjustedCoordinates[0]][adjustedCoordinates[1]] = piece;
	}

	public int isMoveLegal(char fromCol, int fromRow, char toCol, int toRow) {
		ChessPiece fromPiece = pieceAt(fromCol, fromRow);

		MoveStrategyFactory factory = new MoveStrategyFactory();
		MoveStrategy strategy = factory.getStrategy(fromPiece.getType(), fromPiece.getColor(), this);

		boolean isPatternLegal = strategy.isPatternLegal(fromCol, fromRow, toCol, toRow, this);
		boolean isEndOccupied = strategy.checkCollisionOnSpot(this, toCol, toRow);
		boolean isWayOccupied = strategy.checkCollisionOnWay(this, fromCol, fromRow, toCol, toRow);
		boolean canCapture = strategy.canCapture(this, fromCol, fromRow, toCol, toRow);
		boolean isSpecial = strategy.isSpecialCase(this, fromCol, fromRow, toCol, toRow);

		if (isSpecial || isPatternLegal && !isWayOccupied) {
			if (isSpecial)
				return SPECIAL_MOVE;
			if (canCapture && isEndOccupied)
				return NORMAL_MOVE;
			if (!isEndOccupied) 
				return NORMAL_MOVE;
		}
		
		return ILLEGAL_MOVE;
	}

	public void makeMove(int fromCol, int fromRow, int toCol, int toRow) {
		Command move;

		char algFromCol = convertComputerScienceToAlgebraicCol(fromCol);
		int algFromRow = fromRow + 1;
		char algToCol = convertComputerScienceToAlgebraicCol(toCol);
		int algToRow = toRow + 1;

		ChessPiece toPiece = pieceAt(algToCol, algToRow);

		if (toPiece == null) {
			move = new ChessMove(this, algFromCol, algFromRow, algToCol, algToRow);
		} else {
			move = new Capture(this, algFromCol, algFromRow, algToCol, algToRow);
		}

		int moveLegality = isMoveLegal(algFromCol, algFromRow, algToCol, algToRow);
		if (moveLegality == ILLEGAL_MOVE){
			move.undo();
			move = null;
		} else if (moveLegality == 2){
			if (pieceAt(algFromCol, algFromRow).getType() == ChessPieceType.KING) {
				move = new Castle(this, algFromCol, algFromRow, algToCol, algToRow);
			}

			if (pieceAt(algFromCol, algFromRow).getType() == ChessPieceType.PAWN) {
				move = new EnPassant(this, algFromCol, algFromRow, algToCol, algToRow);
			}
		}
		
		if (move!=null){
			move.execute();
			if (isInCheck(chessTurn.currentTurn()))
				move.undo();
			else {
				if (pieceAt(algToCol, algToRow).getType() == ChessPieceType.PAWN && (algToRow == 1 || algToRow == 8))
					canPromote = true;
				else
					canPromote = false;
				moves.push(move);
				movedPieces.add(pieceAt(algFromCol, algFromRow));
				notifyObservers();
				chessTurn.nextTurn();
			}
		}
	}

	public boolean canPromote() {
		return canPromote;
	}

	public int getNumberCaptured() {
		return numCaptured;
	}

	public void addCaptured(int i) {
		numCaptured += i;
	}

	public boolean isInCheck(ChessColor color) {
		for (char col = 'a'; col <= 'h'; col++) {
			for (int row = 1; row <= 8; row++) {
				ChessPiece piece = pieceAt(col, row);
				if (piece == null || piece.getColor() == color)
					continue;
				MoveStrategy strat = new MoveStrategyFactory().getStrategy(piece.getType(), piece.getColor(), this);
				if (strat.enemyKingCheck(this, col, row))
					return true;
			}
		}
		return false;
	}

	public boolean hasLegalMoves(ChessColor currentColor) {
		for (char col = 'a'; col <= 'h'; col++) {
			for (int row = 1; row <= 8; row++) {
				ChessPiece piece = pieceAt(col, row);
				if (piece == null || piece.getColor() != currentColor)
					continue;
				for (char toCol = 'a'; toCol <= 'h'; toCol++) {
					for (int toRow = 1; toRow <= 8; toRow++) {
						if (isMoveLegal(col, row, toCol, toRow) == 0)
							continue;
						Command move;
						if (pieceAt(toCol, toRow) == null)
							move = new ChessMove(this, col, row, toCol, toRow);
						else
							move = new Capture(this, col, row, toCol, toRow);
						move.execute();
						if (isInCheck(currentColor)) {
							move.undo();
							continue;
						}
						move.undo();
						return true;
					}
				}
			}
		}
		return false;
	}

	private ArrayList<EndObserver> observers = new ArrayList<EndObserver>();

	@Override
	public void addObserver(EndObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(EndObserver observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		ChessEnd endType = getEnd();
		for (EndObserver observer : observers) {
			observer.update(endType);
		}
	}

	private ChessEnd getEnd() {
		if (isInCheck(ChessColor.BLACK) && !hasLegalMoves(ChessColor.BLACK))
			return ChessEnd.WHITE_WINS;
		if (isInCheck(ChessColor.WHITE) && !hasLegalMoves(ChessColor.WHITE))
			return ChessEnd.BLACK_WINS;
		if (isInCheck(ChessColor.WHITE) || isInCheck(ChessColor.BLACK))
			return ChessEnd.CHECK;
		if (!hasLegalMoves(ChessColor.WHITE) && chessTurn.currentTurn() == ChessColor.BLACK)
			return ChessEnd.STALEMATE;
		if (!hasLegalMoves(ChessColor.BLACK) && chessTurn.currentTurn() == ChessColor.WHITE)
			return ChessEnd.STALEMATE;
		return null;
	}

	// check for en passant command
	public boolean checkEnPassant(char correctToCol) {
		if (moves.isEmpty()) {
			return false;
		}
		// check if the last move was made by a pawn
		// moving 2 squares at a time
		// even though 2 squares is already a good restriction
		// still there could be two pawns side by side and one
		// of them must be an illegal en passant capture
		Command lastMove = moves.peek();
		int fromRow = lastMove.getFromRow();
		int toRow = lastMove.getToRow();
		char toCol = lastMove.getToCol();
		ChessPiece currPiece = pieceAt(toCol, toRow);
		boolean isPawn = currPiece.getType() == ChessPieceType.PAWN;
		if (isPawn) {
			return Math.abs(fromRow - toRow) == 2 && correctToCol == toCol;
		}
		return false;
	}

}
