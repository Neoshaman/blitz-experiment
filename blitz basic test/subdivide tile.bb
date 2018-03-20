Include "base Setup.bb"

depth = 3
size = 10
pt.point3d = New point3d
;-------------------------------------------

t = Createtile(size)

;-------------------------------------------



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
Function Createtile(size)
	mesh = CreateMesh()
	surface = CreateSurface (mesh)
	v1 = AddVertex (surface, 0,1,0, 0,1,0)
	v2 = AddVertex (surface, 1,1,0, 1,1,0)
	v3 = AddVertex (surface, 1,0,0, 1,0,0)
	v4 = AddVertex (surface, 0,0,0, 0,0,0)
	AddTriangle (surface, v1,v2,v3)
	AddTriangle (surface, v1,v3,v4)
End Function


Function sub()
	sub = size/division
	For i = 0 To division
		pos = i*sub
		;if divisible range, recurse, else
		;draw tile at pos with size sub
	Next
End Function

;-------------------------------------------
Type Tile
	Field v1.point3d
	Field v2.point3d
	Field v3.point3d
	Field v4.point3d
End Type
