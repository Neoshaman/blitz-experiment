Include "function library.bb"

.setup

Graphics3D 800,600,16,2
SetBuffer BackBuffer()

;-------------------------------------------
cam = CreateCamera ()
PositionEntity cam, 0,0,-5


l1 = CreateLight (1)
RotateEntity l1, 45,45,0

tex=LoadTexture( "testure.jpg" )


plane = CreatePlane ()
PositionEntity plane, 0,-8,0
EntityColor plane, 64,32,16
EntityTexture plane,tex

center = CreateSphere (32)
PositionEntity center, 0,-0,0
ScaleEntity center, .5,.5,.5
EntityColor center, 255,0,0
HideEntity center

h = CreateSphere (8)
PositionEntity h, 0,-0,0
ScaleEntity h, .1,.1,.1
EntityColor h, 0,255,0
;HideEntity h

;-------------------------------------------

;put set up code here

.loop

hoveredface$ = " "

	div = 5
	div2 = div*div

	
	di = div +1
	Dim g(  di*di )
	For i = 0 To ( di*di )-1
		g(i) = CreateSphere (2)
		;FreeEntity g(i)
	Next


While Not KeyDown(1)
	
	controlentity(cam)
	PointEntity cam, center
	
	;-------------------------------------------
	
	entitypoint.point3d = New point3d
	entitypoint\x = EntityX (cam)
	entitypoint\y = EntityY (cam)
	entitypoint\z = EntityZ (cam)
	
	c0.point3d = normalizepoint3d(entitypoint);
	PositionEntity h, c0\x,c0\y,c0\z
	
	
	hoveredface = cubeface(entitypoint\x, entitypoint\y, entitypoint\z )
	axis = facetoaxis(hoveredface )

	If poscell = 0 Then poscell = createquadface()
		
	detectplanettile(entitypoint,poscell,axis, div,1)
	
	
		Delete Each point3d
		
	;-------------------------------------------
	
	;refresh
	UpdateWorld 1
	RenderWorld 1

	; put 2d drawing code here
	showjoystate(100); 2d text display of joystick state
	Text 10,10, cubeface(EntityX (cam), EntityY (cam), EntityZ (cam) )

	VWait:Flip False
	
Wend

;put exit clean up code here

ClearWorld

;------------------------------------------------------

.func

Function pointtile.point3d(i, offset#, division, s#, axis=0):;Stop

		a# = offset + ( i Mod division )*s
		b# = offset + ( i  /  division )*s
		
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
		
		p.point3d = New point3d
		p\x = x
		p\y = y
		p\z = z
		
		Return p

End Function


Function detectplanettile(entitypoint.point3d, poscell, axis, division#=10,size#=1); :Stop

	division = division +1
	d2# = division*division
	S# = size/(division-1);step size
	
	;center = 0 -> corner, center = 1 -> center offset
	center = 1 : offset# = 0:	If center = 1 Then offset = - (size) / 2
	
	surface = GetSurface (poscell,1) 
	
	close# = Abs( 2^30 )
	
	;detect side
		;enumerate cell division
			;find corner
			;compute centroid
			;project to sphere
			;detect rightcell
			;store cell within radius
			;find current cell
				;if not max depth -> enumerate cell division

	;initializing point
	n.point3d = normalizepoint3d(entitypoint);
	
	;for each face relative to current face hovered
	p1.point3d = New point3d
	p2.point3d = New point3d
	p3.point3d = New point3d
	p4.point3d = New point3d
	centroid.point3d = New point3d
	
	tile.quadpair	= New quadpair
	tile\flat		= New quad
	tile\sphere		= New quad
		
	;to store the closest
	rightcell.point3d = Null
	tilelist.quadlist = New quadlist ;tilelist\element = tile

	;s# = 1
	;--------------------------------------------------------------
	For i=0 To d2-1:;Stop;

		;generate position, depend on axis xz=0, xy=1, zy=2
;evaluatequad from cube from indice tesselation
		w1 = i
		w2 = i+1
		w3 = i+division +1
		w4 = i+division
		
		p1 = pointtile(w1, offset, division, s, axis)
		p2 = pointtile(w2, offset, division, s, axis)
		p3 = pointtile(w3, offset, division, s, axis)
		p4 = pointtile(w4, offset, division, s, axis)

					
		If (i Mod division) = (division -1) Then Goto skip
		If i => d2 - division Then Goto skip
		
		centroid\x = (p1\x + p2\x + p3\x + p4\x)/4
		centroid\y = (p1\y + p2\y + p3\y + p4\y)/4
		centroid\z = (p1\z + p2\z + p3\z + p4\z)/4
		
		.skip	
;quadtosphere

		p1c.point3d = catspherepoint(p1)
		p2c.point3d = catspherepoint(p2)
		p3c.point3d = catspherepoint(p3)
		p4c.point3d = catspherepoint(p4)

		centroidcat.point3d = catspherepoint( centroid )
	
	;quad
		tile\flat\p1			= p1
		tile\sphere\p1			= p1c
		
		tile\flat\p2			= p2
		tile\sphere\p2			= p2c
		
		tile\flat\p3			= p3
		tile\sphere\p3			= p3c
		
		tile\flat\p4			= p4
		tile\sphere\p4			= p4c
		
		tile\flat\centroid		= centroid
		tile\sphere\centroid	= centroidcat
		
;;;;;;;;;;;;;
;debug
;		PositionEntity g(i), centroidcat\x, centroidcat\y, centroidcat\z
			PositionEntity g(i), p1c\x, p1c\y, p1c\z
;			PositionEntity g(i), p2c\x, p2c\y, p2c\z
;			PositionEntity g(i), p3c\x, p3c\y, p3c\z
;			PositionEntity g(i), p4c\x, p4c\y, p4c\z
		ScaleEntity g(i), .05,.05,.05
		EntityColor g(i), 0,0,255
;;;;;;;;;;;;;
		
;select right cell base on distance
		; if cell = null, this cell = right, check distance to normalize ship, if distance to centroid less, then cell is new cell
		If rightcell = Null Then 
		
			rightcell = centroidcat;
			close = magnitude3d(n\x - centroidcat\x, n\y - centroidcat\y, n\z - centroidcat\z)
			
			debugquad(surface, p1c.point3d, p2c.point3d, p3c.point3d, p4c.point3d)
			
		Else 
			
			m# = magnitude3d(n\x - centroidcat\x, n\y - centroidcat\y, n\z - centroidcat\z) 
			
			If m < close Then
			
				rightcell = centroidcat
				close# = m
				
				debugquad(surface, p1c.point3d, p2c.point3d, p3c.point3d, p4c.point3d)
				
			EndIf
		EndIf
		UpdateNormals poscell	
	Next
	
	;----> horizon computation
		; use hovering to get the adjacent face to enumerate
		; use the radius and the distance to hovering to get the horizon
		; enumarate face and their tile and store good tile (or simply delete bad tile) into tilelist (create new tilelist for each)
	
	;end of loop with best cell from cube RECURSION
	;tesselate quad from cube
	;evaluate quad to sphere
	;find best match
	
End Function


;------------------------------------------------------

Function debugquad(surface, p1.point3d, p2.point3d, p3.point3d, p4.point3d)
	VertexCoords surface, 0, p1\x, p1\y, p1\z
	VertexCoords surface, 1, p2\x, p2\y, p2\z
	VertexCoords surface, 2, p3\x, p3\y, p3\z
	VertexCoords surface, 3, p4\x, p4\y, p4\z
End Function

Function catspherepoint.point3d(point.point3d, radius# = 1);:Stop
	
	p.point3d = New point3d
	p\x = point\x *2
	p\y = point\y *2
	p\z = point\z *2
	;method with no deformation around the pole

	x# = p\x 
	y# = p\y 
	z# = p\z 
	
	
	x2# = x*x
	y2# = y*y
	z2# = z*z
	
	a# = (x2 / 2.0)
	b# = (y2 / 2.0)
	c# = (z2 / 2.0)
	
	sx# =  Sqr(1.0 - b - c + ( (y2 * z2) / 3.0 ) ) * x /2
	sy# =  Sqr(1.0 - c - a + ( (x2 * z2) / 3.0 ) ) * y /2
	sz# =  Sqr(1.0 - a - b + ( (x2 * y2) / 3.0 ) ) * z /2
	
	sx# = sx*radius
	sy# = sy*radius
	sz# = sz*radius
	
	p\x = sx
	p\y = sy
	p\z = sz
	
	
	Return p

End Function

Function Horizontest(test.point3d,  point.point3d, radius)
	; should be planet - ship, but planet is 000, need to be adjusted later
	distance = magnitude3d(point\x, point\y, point\z)
	
	horizonsize = Sqr ( (distance*distance) - (radius*radius) )
	
End Function

Function cubeface$(x,y,z)
	
	ax = Abs(x)
	ay = Abs(y)
	az = Abs(z)
	
	If ax > ay And ax > az Then
	
		If x > 0 Then 
			Return "side1";
		Else
			Return "side2"
		EndIf
		
	Else If ay > az Then
	
		If y > 0 Then 
			Return "top";
		Else
			Return "bottom"
		EndIf
		
	Else
	
		If z > 0 Then 
			Return "back";
		Else
			Return "front"
		EndIf
		
	EndIf
	
	;side1  : N>top  E>back  W>front S>bottom /side2
	;side2  : N>top  E>front W>back  S>bottom /side1
	
	;front  : N>top  E>side1 W>side2 S>bottom /back
	;back   : N>top  E>side2 W>side1 S>bottom /front
	
	;top    : N>back E>side1 W>side2 S>front  /bottom
	;bottom : N>back E>side1 W>side2 S>front  /top
		
End Function

Function createquadface()
	poscell = CreateMesh ()
	possurface = CreateSurface(poscell)		
		
	v1 = AddVertex (possurface, 0,0,0, 0,0,0)
	v2 = AddVertex (possurface, 1,0,0, 0,0,0)
	v3 = AddVertex (possurface, 1,1,0, 0,0,0)
	v4 = AddVertex (possurface, 0,1,0, 0,0,0)
		
	t1 = AddTriangle (possurface, v1,v3,v2)
	t2 = AddTriangle (possurface, v1,v4,v3)
	
	brush = CreateBrush()
		BrushFX brush, 16
		PaintSurface possurface, brush
		
	UpdateNormals poscell
	Return poscell
End Function 	

	
Function facetoaxis( hoveredface$ )
	Select hoveredface
		Case "side1"	: axis = 2
		Case "side2"	: axis = 5
		
		Case "top" 		: axis = 0
		Case "bottom"	: axis = 3
		
		Case "back"		: axis = 1
		Case "front"	: axis = 4
	End Select
	Return axis
End Function


Type quad
	Field p1.point3d
	Field p2.point3d
	Field p3.point3d
	Field p4.point3d
	
	Field centroid.point3d	
End Type

Type quadpair
	Field flat.quad
	Field sphere.quad
End Type

Type quadlist
	Field element.quadpair
End Type