Graphics 800,600,0,2


;type array

Global cx = 20
Global cy = 20 

Global cellsize_x = GraphicsWidth()/cx
Global cellsize_y = GraphicsHeight()/cy

Dim unit.cell(cellsize_x,cellsize_y)

	
			; for each point
			;for each adjacent cell
			;- find iteration number base on seed
			;- for each coordinate with pair of random
			;-- look if min distance to point
			;-- if min store min point
			; for each point within this cell
			; look if min
		

;_____________________________________________________________________________

For i = 0 To cellsize_x ; Step gridsize
	For j = 0 To cellsize_y; Step gridsize
		
		unit.cell(i,j) = New cell
	
		unit(i,j)\seed = hashseed (i,j)
		
		SeedRnd unit(i,j)\seed
		
		it = Rand(0,3)
		
			unit(i,j)\number = it
			unit(i,j)\i = i
			unit(i,j)\j = j
		
		
		For k = 0 To it 
		
			temp.point = New point
			temp\x = Rand (0,cx) ;best -> hash the space further then add jittering to center position with margin
			temp\y = Rand (0,cy) ; currently 20, hash to 4 (5 pixel), add 3 to center, jitter with (0,1)  
			
			temp\i = i
			temp\j = j
			
			Select k
				Case 0: unit(i,j)\n0 = temp
				Case 1: unit(i,j)\n1 = temp
				Case 2: unit(i,j)\n2 = temp
				Case 3: unit(i,j)\n3 = temp
			End Select
			
		Next		
	Next
Next

;_____________________________________________________________________________
cellscreen = CreateImage ( GraphicsWidth(), GraphicsHeight() )


SetBuffer ImageBuffer(cellscreen)
;grid
Color 64,0,0
For i = 0 To cellsize_x
	Line i*cx,0 ,i*cx, 600
Next 

For i = 0 To cellsize_y
	Line 0,i*cx, 800 ,i*cx
Next


;trade routes
	For i=0 To cellsize_x -1
		For j=0 To cellsize_y -1
			cellpath(i,j)
		Next
	Next

;draw star
For  x.cell = Each cell

				Color 0,64,64

		Select x\number
			Case 0
				;nothing!
			Case 1
				Line x\n0\x + x\i*cx, x\n0\y + x\j*cy,	x\n1\x + x\i*cx, x\n1\y + x\j*cy	
			Case 2
				Line x\n0\x + x\i*cx, x\n0\y + x\j*cy,	x\n1\x + x\i*cx, x\n1\y + x\j*cy
				Line x\n1\x + x\i*cx, x\n1\y + x\j*cy,	x\n2\x + x\i*cx, x\n2\y + x\j*cy
				Line x\n2\x + x\i*cx, x\n2\y + x\j*cy,	x\n0\x + x\i*cx, x\n0\y + x\j*cy
			Case 3
				Line x\n0\x + x\i*cx, x\n0\y + x\j*cy,	x\n1\x + x\i*cx, x\n1\y + x\j*cy
				Line x\n1\x + x\i*cx, x\n1\y + x\j*cy,	x\n2\x + x\i*cx, x\n2\y + x\j*cy
				Line x\n2\x + x\i*cx, x\n2\y + x\j*cy,	x\n3\x + x\i*cx, x\n3\y + x\j*cy 
				Line x\n3\x + x\i*cx, x\n3\y + x\j*cy,	x\n0\x + x\i*cx, x\n0\y + x\j*cy 
		End Select



	starsize = 2
	Color 255,255,255
	If x\n0 <> Null Then Rect x\n0\x + x\i*cx, x\n0\y + x\j*cy , starsize ,starsize , 0
	Color 255,0,0
	If x\n1 <> Null Then Rect x\n1\x + x\i*cx, x\n1\y + x\j*cy , starsize ,starsize , 0
	Color 0,255,0
	If x\n2 <> Null Then Rect x\n2\x + x\i*cx, x\n2\y + x\j*cy , starsize ,starsize , 0
	Color 0,0,255
	If x\n3 <> Null Then Rect x\n3\x + x\i*cx, x\n3\y + x\j*cy , starsize ,starsize , 0
	Color 255,255,255
	
Next


	SetBuffer BackBuffer()
 
;_____________________________________________________________________________
mx = 0
my = 0


While Not KeyHit(1)
	mx = MouseX()/cx
	my = MouseY()/cy
	
	
	Cls
	DrawImage cellscreen, 0,0
	Rect mx*cx,my*cy, cx, cy,0
	
	
	
	VWait:Flip False
Wend
;_____________________________________________________________________________


Function Hashseed(i,j)
			width = (i*10000) + (j*100)
			Return (1 + (i*width) + (j*width*width) )
End Function



Function cellpath(i,j)
		
		;for each point in the cell
			;find the closest point
				;in all surrounded cell
				;in current cell
		;distance² = (x2-x1)² + (y2-y1)²
		
		
		Cell_C.cell		= unit.cell(i,j)
		
;		Cell_UL.cell	= unit.cell(i+1,j-1)
;		Cell_U.cell		= unit.cell(i+1,j)
;		Cell_UR.cell	= unit.cell(i+1,j+1)

;		Cell_L.cell		= unit.cell(i,j-1)
;		Cell_R.cell		= unit.cell(i,j+1)

;		Cell_DL.cell	= unit.cell(i-1,j-1)
;		Cell_D.cell		= unit.cell(i-1,j)
;		Cell_DR.cell	= unit.cell(i-1,j+1)		
		
		distanceSqr = 0
		distanceMin = 800^2
		pointMin.point = Null
		
		For t = 0 To Cell_C\number
			pos.point = cellpoint(Cell_C,t)
				
				;Stop
				;loop all points to compute distance
				For g = -1 To 1
					For h = -1 To 1
						
						If g = 0 And h = 0 Then Goto label
						If i+g < 0 Or j+h < 0 Then Goto label
						
						
						
						looped.cell = unit.cell(i+g,j+h)
						For l = 0 To looped\number
						
							loopt.point = cellpoint(looped,l)
							
							lsx = Abs ( (i*cx + g*cx + loopt\x) - (i*cx + pos\x) )
							lsy = Abs ( (j*cy + h*cy + loopt\y) - (j*cy + pos\y) );(loopt\y - pos\y)
							
							
							distanceSqr = lsx^2 + lsy^2
							
							If distanceSqr < distanceMin Then
								distanceMin = distanceSqr
								pointMin = loopt
								
								pointMin\i = g
								pointMin\j = h
							EndIf 
							
						Next
						
						.label
					Next
				Next
				
				icx = i*cx
				jcy = j*cy
				
				pmx = pointMin\i*cx 
				pmy = pointMin\j*cy 
				
				Color 128,128,128
				Line icx + pos\x,  jcy + pos\y,  icx + pointMin\x + pmx, jcy + pointMin\y + pmy
				
				pos\close = pointMin
				
		Next
		
		
		
		


End Function


Function cellpoint.point(p.cell,n); comparison?
	Select n
		Case 0: Return p\n0
		Case 1: Return p\n1
		Case 2: Return p\n2
		Case 3: Return p\n3
	End Select
End Function


Type point
	Field x
	Field y
	
	Field i
	Field j
	
	Field close.point
End Type

Type cell;? unit.cell
	Field seed
	Field number
	Field i
	Field j
	Field n0.point
	Field n1.point
	Field n2.point
	Field n3.point
End Type