s# Chess game

We're going to implement the chess play simulation with simplified rules and some console animation of the play.
We don't have to take care of:
- mating and checking,
- castles,
- beating (by risers) in flight,
- the possibility of the (first) movement of a pawn by two squares,
  but of course if you want you can think of including some of these features into your game models.

We would like to provide a model of chess pieces, where 2 players plays on 8x8 chess board. The first player
is white player, the second is black. There can always be only a single piece on board field and players beat
each other by taking the same field by some other piece.

The simulation finishes when:
- every player used 50 moves
- some player cannot move by any piece
- one of the kings has been beaten

The whole simulation can be done in rando manner - there is no need to implement some player strategy
for the purpose of this task.

You can use symbols from this sample board to implement your console animation:
```java
╔═╤═╤═╤═╤═╤═╤═╤═╗
║♜│♞│♝│♛│♚│♝│♞│♜║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♟│♟│♟│♟│♟│♟│♟│♟║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║ │█│ │█│ │█│ │█║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║█│ │█│ │█│ │█│ ║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║ │█│ │█│ │█│ │█║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║█│ │█│ │█│ │█│ ║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♙│♙│♙│♙│♙│♙│♙│♙║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♖│♘│♗│♕│♔│♗│♘│♖║
╚═╧═╧═╧═╧═╧═╧═╧═╝
```
or design your own 😉

Deadline for this task is 18.05.2022 (11:59 😎)
