import javafx.scene.image.*;
public class ImageGenerator {
public static Image getImage(ChessPieceType type, ChessColor color){
	if(ChessColor.WHITE == color){
		switch(type){
		case PAWN: return new Image("/Images/White Pawn.png");
		case KING: return new Image("/Images/White King.png");
		case ROOK: return new Image("/Images/White Rook.png");
		case QUEEN: return new Image("/Images/White Queen.png");
		case BISHOP: return new Image("/Images/White Bishop.png");
		case KNIGHT: return new Image("/Images/White Knight.png");
		default: return null;
		}
		
	}
	else
		switch(type){
		case PAWN: return new Image("/Images/Black Pawn.png");
		case KING: return new Image("/Images/Black King.png");
		case ROOK: return new Image("/Images/Black Rook.png");
		case QUEEN: return new Image("/Images/Black Queen.png");
		case BISHOP: return new Image("/Images/Black Bishop.png");
		case KNIGHT: return new Image("/Images/Black Knight.png");
		default: return null;
		}
}
}
