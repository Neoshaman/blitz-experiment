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


;affiche repere orthonorme
Line 512,1024,512,0
Line 0,512,1024,512


;Creation d'un mesh vide
varmesh = CreateMesh ()


;Creation d'une surface
varsurface = CreateSurface (varmesh)


;Nombre de colonnes (x) et lignes (y) du plan carre 3D, (x) peut etre different de (y), mais aucun ne peut etre egal a zero
x = 5
y = 5


;Point d'origine du plan
coord_x = 0
coord_y = 0
coord_z = 0


;duree de l'attente en ms
varDelay = 100

;----------- Debut du programme ----------------------


;creation du vertex de depart (vertex nul) afin qu'il prenne la position "0" dans la liste d'index des vertices
AddVertex (varsurface,0,0,0, 0,0,0)


;application de la texture sur la surface
EntityTexture varmesh,vartex


;BOUCLE x (colonnes) comprise dans BOUCLE y (lignes)
For l=0 To y Step 1
	
	For c=0 To x Step 1
	
		;calcul des UVs
		;"c" va de 0 a x, alors la valeur de uv de u est c/x car x/x=1
		;idem pour "l" : uv de v est l/y				
		
		AddVertex (varsurface, coord_x + c , coord_y + l , coord_z + Rand(0,1) , Float c/x , Float l/y ,0 )
		
			
		If l<>0 And c>=1 Then
			
			;(x+1) car il s'agit du nombre de colonne + 1
			t1 = ((x+1) * (l-1)) + c
			t2 = ((x+1) * (l-1)) + (c+1)
			t3 = ((x+1) * l) + c
			t4 = ((x+1) * l) + (c+1)

			
			AddTriangle	(varsurface, t1 , t3 , t2) : Flip
			
			AddTriangle (varsurface, t2 , t3 , t4) : Flip
			
				UpdateNormals varmesh
				RenderWorld
				Line 512,1024,512,0
				Line 0,512,1024,512
				Text 512,250, "-------- CONDITIONS IF = OK --------"
				Text 512,520, "X COORD = " + (coord_x + c)
				Text 512,540, "Y COORD = " + (coord_y + l)
				Text 512,560, "l=" + l + "  y=" + y
				Text 512,580, "c=" + c + "  x=" + x
				Text 512,600, "Tris :" + CountTriangles (varsurface)
				Text 512,620, "Vertices :" + CountVertices (varsurface)
				Text 512,640, "t1 = " + t1 : Flip
				Text 512,660, "t2 = " + t2 : Flip
				Text 512,680, "t3 = " + t3 : Flip
				Text 512,700, "t4 = " + t4 : Flip
				Text 512,720, "u= " + Float c/x : Flip
				Text 512,740, "v= " + Float l/y : Flip
				
						
		EndIf
		
		Delay varDelay	
		
	Next
	
	Delay varDelay

Next




RenderWorld
EntityTexture varmesh,vartex
UpdateNormals varmesh
RenderWorld

WaitKey()


;----------- Rotation de l'objet sur les 3 axes x,y,z ----------------------


;rotation sur axe Z
For r=0 To 360 Step 2
	RotateEntity varmesh,0,0,r : Flip
	RenderWorld		
Next


;rotation sur axe Y
For r=0 To 360 Step 2
	RotateEntity varmesh,0,r,0 : Flip
	RenderWorld	
Next

;rotation sur axe X
For r=0 To 360 Step 2
	RotateEntity varmesh,r,0,0 : Flip
	RenderWorld		
Next


WaitKey()


;----------- Fin du programme ----------------------


End