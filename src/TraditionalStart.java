
public class TraditionalStart implements BoardInitializer {

	@Override
	public void createBoard(Board createOn, Turns chessTurn) {
		createOn.setPiece(new ChessPiece(ChessPieceType.ROOK,ChessColor.WHITE),'a',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.KNIGHT,ChessColor.WHITE),'b',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.BISHOP,ChessColor.WHITE),'c',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.QUEEN,ChessColor.WHITE),'d',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.KING,ChessColor.WHITE),'e',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.BISHOP,ChessColor.WHITE),'f',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.KNIGHT,ChessColor.WHITE),'g',1);
		createOn.setPiece(new ChessPiece(ChessPieceType.ROOK,ChessColor.WHITE),'h',1);
		
		createOn.setPiece(new ChessPiece(ChessPieceType.ROOK,ChessColor.BLACK),'a',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.KNIGHT,ChessColor.BLACK),'b',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.BISHOP,ChessColor.BLACK),'c',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.QUEEN,ChessColor.BLACK),'d',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.KING,ChessColor.BLACK),'e',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.BISHOP,ChessColor.BLACK),'f',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.KNIGHT,ChessColor.BLACK),'g',8);
		createOn.setPiece(new ChessPiece(ChessPieceType.ROOK,ChessColor.BLACK),'h',8);
		
		for (char i = 'a'; i<='h'; i++){
			createOn.setPiece(new ChessPiece(ChessPieceType.PAWN,ChessColor.WHITE), i, 2);
			createOn.setPiece(new ChessPiece(ChessPieceType.PAWN,ChessColor.BLACK), i, 7);
		}
		
		for (char i = 'a'; i<='h'; i++){
			chessTurn.addObserver(createOn.pieceAt(i, 1));
			chessTurn.addObserver(createOn.pieceAt(i, 2));
			chessTurn.addObserver(createOn.pieceAt(i, 7));
			chessTurn.addObserver(createOn.pieceAt(i, 8));
		}

	}

}
