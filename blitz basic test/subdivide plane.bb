Include "base Setup.bb"

depth = 3
size
position


While Not KeyDown(1)

	

	;-------------------------------------------
	;refresh
	UpdateWorld 1
	RenderWorld 1
	;-------------------------------------------
	
	
	
	;-------------------------------------------
	VWait:Flip False
	
	
Wend

;put exit clean up code here

ClearWorld

;-------------------------------------------
