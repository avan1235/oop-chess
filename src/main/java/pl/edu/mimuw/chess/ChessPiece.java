package pl.edu.mimuw.chess;

public class ChessPiece {
  protected final ChessColor color;
  protected final char blackSymbol;
  protected final char whiteSymbol;
  protected Vector position;

  protected ChessPiece(ChessColor color, Vector position, char blackSymbol, char whiteSymbol) {
    this.color = color;
    this.position = position;
    this.blackSymbol = blackSymbol;
    this.whiteSymbol = whiteSymbol;
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public char getSymbol() {
    return color == ChessColor.BLACK ? blackSymbol : whiteSymbol;
  }

  @Override
  public int hashCode() {
    return color.hashCode() + position.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final ChessPiece other = (ChessPiece) obj;
    if (this.color != other.color) return false;
    return this.position.equals(other.position);
  }
}
