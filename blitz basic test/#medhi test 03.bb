SetBuffer BackBuffer ()

Graphics3D 1024,1024,0,2

;Creation Camera
varcamera = CreateCamera ()
PositionEntity varcamera,0,0,-10

;Creation Light
varlight = CreateLight (1)
RotateEntity varlight,45,45,0

;Creation Texture
vartex = LoadTexture ("testure.jpg")


;----------- Declaration des variables --------------


;affiche repere orthonorm�
Line 512,1024,512,0
Line 0,512,1024,512


;Creation d'un mesh vide
varmesh = CreateMesh ()


;Creation d'une surface
varsurface = CreateSurface (varmesh)


;Nombre de colonnes (x) et lignes (y) du plan carre 3D
x = 5
y = 5


;Point d'origine du plan
coord_x = 1
coord_y = 1
coord_z = 0


;Definition des UVs
u=1
v=1
w=1


;duree de l'attente en ms
varDelay = 00

;----------- Debut du programme ----------------------


;creation du vertex de depart (vertex nul) afin qu'il prenne la position "0" dans la liste d'index des vertices
AddVertex (varsurface,0,0,0, 0,0,0)


;BOUCLE X (colonnes) comprise dans BOUCLE Y (lignes)
For l=0 To y Step 1
	
	For c=1 To x+1 Step 1
		If l<=y Then
		
		AddVertex (varsurface, (coord_x - 1 + c) , (coord_y + l) , 1+0* Rand(0,1) , u,v,w)
		
		End If
			
				UpdateNormals varmesh
				RenderWorld
				texting (coord_x,coord_y,l,c,varsurface)
		
			
		
	
		If l<>0 And c>1 Then
			t1 = (x * (l-1)) + c
			t2 = (x * (l-1)) + c+1
			t3 = (x * l) + c
			t4 = (x * l) + c+1

			
			AddTriangle	(varsurface, t3 , t2 , t1) : Flip
			
			Text 512,200, "TRIANGLE 1 CREATED - PRESS KEY"
			t(t1,t2,t3,t4)

			;WaitKey()
			
			AddTriangle (varsurface, t2 , t3 , t4) : Flip
			t(t1,t2,t3,t4)

			Text 512,210, "TRIANGLE 2 CREATED - PRESS KEY"
			;WaitKey()
				
				UpdateNormals varmesh
				RenderWorld

				Text 512,250, "-------- CONDITIONS IF = OK --------"
				texting (coord_x,coord_y,l,c,varsurface)
				t(t1,t2,t3,t4)

			
						
		EndIf
		
		Delay varDelay	
		
	Next
	
	Delay varDelay
	
				RenderWorld
				Text 512,250, "-------- CONDITIONS IF = OK --------"
				texting (coord_x,coord_y,l,c,varsurface)
				t(t1,t2,t3,t4)


Next




RenderWorld
EntityTexture varmesh,vartex
UpdateNormals varmesh
				Text 512,150, "-------- END --------"
				texting (coord_x,coord_y,l,c,varsurface)
				t(t1,t2,t3,t4)


Text 512,200, "-------- PRESS KEY TO CONTINUE --------"

RenderWorld

WaitKey()


;----------- Rotation de l'objet sur les 3 axes x,y,z ----------------------


;rotation sur axe Z
For r=0 To 360
	RotateEntity varmesh,0,0,r : Flip	
	;Delay 1
	RenderWorld
	texting (coord_x,coord_y,l,c,varsurface)

Next


;rotation sur axe Y
For r=0 To 360
	RotateEntity varmesh,0,r,0 : Flip	
	;Delay 1
	RenderWorld
texting (coord_x,coord_y,l,c,varsurface)

Next

;rotation sur axe X
For r=0 To 360
	RotateEntity varmesh,r,0,0 : Flip	
	;Delay 1
	RenderWorld
texting (coord_x,coord_y,l,c,varsurface)

Next

FlipMesh varmesh
WaitKey()


;----------- Fin du programme ----------------------


End




Function texting (coord_x,coord_y,l,c,varsurface)
	Line 512,1024,512,0 : Flip
Line 0,512,1024,512 : Flip
Text 512,520, "X COORD = " + (coord_x -1 + c)
Text 512,540, "Y COORD = " + (coord_y + l)
Text 512,560, "LIGNE N�" + l
Text 512,580, "COLONNE N�" + c
Text 512,600, "Tris :" + CountTriangles (varsurface)
Text 512,620, "Vertices :" + CountVertices (varsurface)
End Function

Function t(t1,t2,t3,t4)
				Text 512,640, "t1 = " + t1 : Flip
				Text 512,660, "t2 = " + t2 : Flip
				Text 512,680, "t3 = " + t3 : Flip
				Text 512,700, "t4 = " + t4 : Flip
End Function
