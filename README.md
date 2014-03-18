# Drunkard Game #

## Rules ##

Game performed on 15x15 board step by step. After each step every character have to make a move.

### Game characters and objects ###

### Column ###
Column located in (7, 7) cell. Column does not move.

### Pub ###
Pub located nearby (0, 9) cell but not on the board. Pub does not move.

### Drunkard ###
Every 20 steps new drunkard spawns from pub. After birth drunkard begins loppping about a board. 
He chooses his direction randomly. When drunkard collides with a column he start sleeping. 
If two drunkards collide they staying on this move and moving next time. If drunkard collides 
with sleeping drunkard - he also become sleeping but not lying.

### Bottle ###
Every drunkard have a bottle. He can drop it with probability 1/30 on every move. 
If drunkard collides with bottle he become sleeping and lying.

### Lantern ###
Lantern located in (3, 10) cell. Lantern is lightening all cells in a radius of 3. 
All characters avoid colliding with lantern (behaviour is the same as drunkard colliding).

### Police station ###
Police station located in (3, 14) cell.

### Policeman ###
In the beginning policeman is sitting in the police station.
He is watching about lightened cells and if he saw a drunkard - 
he come out from the police station and try to deliver drunkard to the prison (which is located in the police station).

### Glass point ###
Glass point located in (14, 4) cell.

### Hobo ###
In the beginning hobo is located in the glass point. On first move he coming out and start collecting bottles.
When hobo finds a bottle he is trying to cell it in the glass point (of course he must deliver a bottle to the glass point before selling it). 
When bottle is sold hobo leaves the board for 40 moves.

## Agenda ##

```
. - empty cell
C - column
T - pub
D - drunkard
B - bottle
L - lantern
S - police station
P - policeman
R - glass point
z - hobo
```











