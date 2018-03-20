SetBuffer BackBuffer ()

Graphics3D 1024,1024,0,2

;Creation Camera
varcamera = CreateCamera ()
PositionEntity varcamera,0,-15,-10



;Rotation de la camera
RotateEntity varcamera,-45,0,0


;Creation Light
varlight = CreateLight (1)
RotateEntity varlight,45,45,0

;Creation Texture
varTexTonSurf = LoadTexture ("testure.jpg")

;pause entre les frames
pauseframe = 300

;Function makeQuad(division, size#=1)

	division = 4
	size# = 16

	quad = CreateMesh()
	surface = CreateSurface (quad)
	
	division = division +1
	d2 = division*division
	S# = size/(division-1);step size
		
	
	;center = 0 -> corner, center = 1 -> center offset
	center = 1
	offset# = 0:	If center = 1 Then offset = - (size) / 2

	offset_u# = 0
	offset_v# = 0
	
	
	For i=0 To d2;

	;generate vertex position, depend on axis xz=0, xy=1, zy=2
			x# = offset + ( i Mod division )*s
			y# = offset + ( i/division )*s ;( i/s )
			z# = Rand (0,5)
			
				;uv
			u# = offset_u + x / division / (size/division) ;
			v# = offset_v + y / division / (size/division) ;
			w# = 0				
	
		AddVertex (surface, x,y,z,  u,-v,w)
		
		RenderWorld
		
			Text 512,700, "Valeur de for(i) " + i + " jusqu'a :" + d2
			Text 512,720, "Nombre de Tris :" + CountTriangles (surface)
			Text 512,740, "Nombre de Vertices :" + CountVertices (surface)
			Text 256,760, "Vertex X= " + x
			Text 256,780, "Vertex Y= " + y
			Text 256,800, "Vertex Z= " + z
			Text 512,760, "valeur Tri w1= " + w1
			Text 512,780, "valeur Tri w2= " + w2
			Text 512,800, "valeur Tri w3= " + w3
			Text 512,820, "valeur Tri w4= " + w4
	
	;generate triangle (independant from vertex generation but need to correlate at the end)
		w1 = i
		w2 = i+1
		w3 = i+division +1
		w4 = i+division 
		
		If i Mod division = division -1 Then Goto skip
		If i => d2 - division Then Goto skip
					
		AddTriangle (surface, w1,w3,w2) : Flip
		AddTriangle (surface, w1,w4,w3) : Flip

		Delay pauseframe
		;WaitKey()
		UpdateNormals quad
		EntityTexture quad,varTexTonSurf
		
		RenderWorld
			Text 512,700, "Valeur de for(i) " + i + " jusqu'a :" + d2
			Text 512,720, "Nombre de Tris :" + CountTriangles (surface)
			Text 512,740, "Nombre de Vertices :" + CountVertices (surface)
			Text 256,760, "Vertex X= " + x
			Text 256,780, "Vertex Y= " + y
			Text 256,800, "Vertex Z= " + z
			Text 512,760, "valeur Tri w1= " + w1
			Text 512,780, "valeur Tri w2= " + w2
			Text 512,800, "valeur Tri w3= " + w3
			Text 512,820, "valeur Tri w4= " + w4
	
		.skip		
	Next	



	;Delay 200
	;Return quad
	
;End Function

UpdateNormals quad : Flip	
EntityTexture quad,varTexTonSurf : Flip
RenderWorld
			Text 512,700, "Valeur de for(i) " + i + " jusqu'a :" + d2
			Text 512,720, "Nombre de Tris :" + CountTriangles (surface)
			Text 512,740, "Nombre de Vertices :" + CountVertices (surface)
			Text 256,760, "Vertex X= " + x
			Text 256,780, "Vertex Y= " + y
			Text 256,800, "Vertex Z= " + z
			Text 512,760, "valeur Tri w1= " + w1
			Text 512,780, "valeur Tri w2= " + w2
			Text 512,800, "valeur Tri w3= " + w3
			Text 512,820, "valeur Tri w4= " + w4

WaitKey()

;rotation sur axe Z
For r=0 To 360
	RotateEntity quad,0,0,r : Flip	
	;UpdateNormals quad
	;EntityTexture quad,varTexTonSurf
	;Delay 1
	RenderWorld
		Text 512,600, "position UV de 0 a 1 :" + u
		Text 512,620, "Nombre de Tris :" + CountTriangles (surface)
		Text 512,640, "Nombre de Vertices :" + CountVertices (surface)
Next


;rotation sur axe Y
For r=0 To 360
	RotateEntity quad,0,r,0 : Flip	
	;UpdateNormals quad
	;EntityTexture quad,varTexTonSurf
	;Delay 1
	RenderWorld
		Text 512,600, "position UV de 0 a 1 :" + u
		Text 512,620, "Nombre de Tris :" + CountTriangles (surface)
		Text 512,640, "Nombre de Vertices :" + CountVertices (surface)
Next

;rotation sur axe X
For r=0 To 360
	RotateEntity quad,r,0,0 : Flip	
	;UpdateNormals quad
	;EntityTexture quad,varTexTonSurf
	;Delay 1
	RenderWorld
		Text 512,600, "position UV de 0 a 1 :" + u
		Text 512,620, "Nombre de Tris :" + CountTriangles (surface)
		Text 512,640, "Nombre de Vertices :" + CountVertices (surface)
Next


WaitKey()

End