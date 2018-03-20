division = 3
size# = 1

	division = division +1
	d2# = division*division
	S# = size/(division-1);step size
	
	;center = 0 -> corner, center = 1 -> center offset
	center = 0
	offset# = 0:	If center = 1 Then offset = - (size) / 2

For i = 0 To d2-1
axis=1
		;Stop
		a# = offset + ( i Mod division )*s	*100
		b# = offset + ( i  /  division )*s	*100
		
		Select axis
			;__________________________________________
			Case 0;xz floor (top)
			x# = a
			z# = b
			y# = s
			;__________________________________________
			Case 1;xy forward wall (front)
			x# = a
			y# = b
			z# = s
			;__________________________________________
			Case 2;zy side1 wall 
			z# = a
			y# = b
			x# = s
			;__________________________________________
			Case 3;xz floor (bottom)
			x# = a
			z# = b
			y# = -s
			;__________________________________________
			Case 4;xy forward wall (back)
			x# = a
			y# = b
			z# = -s
			;__________________________________________
			Case 5;zy side2 wall 
			z# = a
			y# = b
			x# = -s
			;__________________________________________

		End Select
		
		Plot x+10,y+10

Next

WaitKey()

