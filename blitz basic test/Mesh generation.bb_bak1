Graphics3D 800,600,0,2
SetBuffer BackBuffer()

dimension# = 290

size_x = 3 ;n +1
size_y = 3
; row enumeration

size_total = size_x * size_y

plane = CreateMesh()
surface = CreateSurface (plane)


Tilesize# = dimension /size_x;10; x and y

;axis selection?

offset_x# = -( (size_x-1) * tilesize ) / 2
offset_y# = -( (size_y-1) * tilesize ) / 2
offset_z# = 0

offset_u# = 0.5
offset_v# = 0.5

;Print offset_x:WaitKey()

;generate vertex position
For i=0 To size_total 

	x# = offset_x + ( i Mod size_x )	*tilesize
	y# = offset_y + ( i / size_x )		*tilesize
	z# = offset_z + Cos(10*y)*5 + Sin(5*x)*5
		
	u# = offset_u + x / tilesize / ( size_x-1 )
	v# = offset_v + y / tilesize / ( size_x-1 )
	w# = 0

	AddVertex (surface, x,y,z,  u,v,w)

	w1 = i
	w2 = i+1
	w3 = i+size_x +1
	w4 = i+size_x 
	
	If i Mod size_x = size_x-1 Then Goto skip
	If i => size_total-size_x Then Goto skip
			
	AddTriangle (surface, w1,w3,w2)
	AddTriangle (surface, w1,w4,w3)

	.skip
Next


;Print CountTriangles (surface):WaitKey()

UpdateNormals plane

;-------------------------------------------
tex=LoadTexture( "testure.jpg" )
EntityTexture plane,tex

cam = CreateCamera ()
PositionEntity cam, 0,0,-250

l1 = CreateLight (1)
RotateEntity l1, 45,45,0

center = CreateSphere (3)
PositionEntity center, 0,0,0
ScaleEntity center, 5,5,5
EntityColor center, 255,0,0
;-------------------------------------------

While Not KeyDown(1)

WireFrame True 

	;refresh
	UpdateWorld 1
	RenderWorld 1

Text 0,10, MeshWidth (plane)
Text 0,20, MeshHeight (plane)
Text 0,30, MeshDepth (plane)

	VWait:Flip False
	
Wend
ClearWorld 
