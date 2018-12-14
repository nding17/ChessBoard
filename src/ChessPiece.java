import javafx.scene.image.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
public class ChessPiece extends ImageView implements ChessObserver{
	private ChessPieceType type;
	private ChessColor color;
	private static EventHandler<InputEvent> filter = new EventHandler<InputEvent>(){
		public void handle(InputEvent event) {
			event.consume();
		}
	};
	
	public ChessPiece(ChessPieceType type, ChessColor color){
		super();
		setFitWidth(100);
		setPreserveRatio(true);
		this.type = type;
		this.color = color;
	}
	
	public ChessPieceType getType(){
		return type;
	}
	
	public ChessColor getColor(){
		return color;
	}

	@Override
	public void update(ChessColor color) {
		if(color == getColor()){
			try{
			this.removeEventFilter(MouseEvent.MOUSE_PRESSED, filter);
			this.removeEventFilter(MouseEvent.MOUSE_DRAGGED, filter);
			this.removeEventFilter(MouseEvent.MOUSE_RELEASED, filter);
			}
			catch (NullPointerException e){
				//this is actually not a problem
			}
		}
		else{
			this.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);
			this.addEventFilter(MouseEvent.MOUSE_DRAGGED, filter);
			this.addEventFilter(MouseEvent.MOUSE_RELEASED, filter);
		}
		
	}
	
	public void promote(String type){
		ChessPieceType newType;
		switch (type.toLowerCase().trim()){
		case "queen":
			newType = ChessPieceType.QUEEN;
			break;
		case "knight":
			newType = ChessPieceType.KNIGHT;
			break;
		case "rook":
			newType = ChessPieceType.ROOK;
			break;
		case "bishop":
			newType = ChessPieceType.BISHOP;
			break;
		default: return;
		}
		if (getType() == ChessPieceType.PAWN){
			this.type = newType;
			setImage(ImageGenerator.getImage(newType, color));
		}
		
	}
	
	

}
