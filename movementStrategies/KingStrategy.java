import java.util.*;
public class KingStrategy implements MoveStrategy {

	private Board board;

	public KingStrategy( Board board ){
		this.board = board;
	}

	@Override
	public boolean checkCollisionOnWay(Board b, char fromCol, int fromRow, 
			char toCol, int toRow){
		return false;
	}

	@Override
	public boolean isPatternLegal(char fromCol, int fromRow, char toCol, int toRow, Board b){
		int deltaColumn = Math.abs(toCol-fromCol);
		int deltaRow = Math.abs(fromRow-toRow);
		return deltaRow <= 1 && deltaColumn <= 1;
	}

	public boolean checkCollisionOnSpot(Board b, char toCol, int toRow){
		return board.pieceAt(toCol, toRow) != null;
	}

	public boolean canCapture( Board b, char fromCol, int fromRow, 
			char toCol, int toRow ){

		ChessPiece piece = b.pieceAt(fromCol, fromRow);
		boolean isPieceKing = piece.getType() == ChessPieceType.KING;

		if(checkCollisionOnSpot(b,toCol,toRow) && isPieceKing){
			ChessPiece fromPiece = b.pieceAt(fromCol, fromRow);
			ChessPiece toPiece = b.pieceAt(toCol, toRow);
			return fromPiece.getColor() != toPiece.getColor();
		}
		//empty space, nothing to capture
		return false;
	}

	@Override
	public boolean enemyKingCheck(Board b, char row, int col){
		return false;
	}

	@Override
	public boolean isSpecialCase(Board board, char fromCol, int fromRow, char toCol, int toRow) {
		ArrayList<ChessPiece>piecesmoved=board.getPiecesMoved();
		ChessColor kingColor=board.pieceAt(fromCol, fromRow).getColor();
		boolean kingHasMoved=false;
		for(int i=0; i<piecesmoved.size(); i++){
			ChessPiece currentPiece=piecesmoved.get(i);
			if(currentPiece!=null&&kingColor==ChessColor.WHITE&&currentPiece.getColor()==ChessColor.WHITE&&currentPiece.getType()==ChessPieceType.KING){
				kingHasMoved=true;

			}
			else if(currentPiece!=null&&kingColor==ChessColor.BLACK&&currentPiece.getColor()==ChessColor.BLACK&&currentPiece.getType()==ChessPieceType.KING){
				kingHasMoved=true;
			}
		}
		boolean kingIsGoingToG=(toCol=='g');
		boolean kingIsGoingToC=(toCol=='c');
		boolean rookatH=false;
		boolean nullatH=board.pieceAt('h', fromRow)==null;
		boolean nullatH8=board.pieceAt('h', 8)==null;
		boolean nullatH1=board.pieceAt('h', 1)==null;
		if(kingColor==ChessColor.WHITE){
			if(!nullatH&&!nullatH1&&board.pieceAt('h', 1).getType()==ChessPieceType.ROOK&&board.pieceAt('h', 1).getColor()==ChessColor.WHITE){
				rookatH=true;
			}
		}
		else if(kingColor==ChessColor.BLACK){
			if(!nullatH&&!nullatH8&&board.pieceAt('h', 8).getType()==ChessPieceType.ROOK&&board.pieceAt('h', 8).getColor()==ChessColor.BLACK){
				rookatH=true;
			}
		}
		boolean nullatA=board.pieceAt('a', fromRow)==null;
		boolean nullatA1=board.pieceAt('a', 1)==null;
		boolean nullatA8=board.pieceAt('a', 8)==null;
		boolean rookatA=false;
		
		if(kingColor==ChessColor.WHITE){
			if(!nullatA&&!nullatA1&&board.pieceAt('a', 1).getType()==ChessPieceType.ROOK&&board.pieceAt('a', 1).getColor()==ChessColor.WHITE){
				rookatA=true;
			}
		}
		else if(kingColor==ChessColor.BLACK){
			if(!nullatA&&!nullatA8&&board.pieceAt('a', 8).getType()==ChessPieceType.ROOK&&board.pieceAt('a', 8).getColor()==ChessColor.BLACK){
				rookatA=true;
			}
		}
		boolean gIsEmpty=(board.pieceAt('g', fromRow)==null);
		boolean bIsEmpty=board.pieceAt('b', fromRow)==null;
		boolean fIsEmpty=(board.pieceAt('f', fromRow)==null);
		boolean cIsEmpty=(board.pieceAt('c', fromRow)==null);
		boolean dIsEmpty=board.pieceAt('d', fromRow)==null;
		boolean kingSideCastle=!kingHasMoved&&kingIsGoingToG&&gIsEmpty&&fIsEmpty&&rookatH;
		boolean queenSideCastle=!kingHasMoved&&kingIsGoingToC&&cIsEmpty&&bIsEmpty&&dIsEmpty&&rookatA;
		if(kingSideCastle||queenSideCastle){
			return true;
		}
		else{return false;}

	}


}
