Include "base Setup.bb"

HideEntity plane
WireFrame True

planet = CreateSphere (100)
;FitMesh planet, 0,0,0, 1,1,1
ScaleMesh planet, 512,512,512
PositionMesh planet, 0,0,1024

cube = CreateCube ()
PositionEntity cube, 0,0,512
ScaleMesh cube, 50,50,50

cube = CreateCube ()
PositionEntity cube, 512,0,1024
ScaleMesh cube, 50,50,50

ground = LoadTexture ("random 4096.png", 9):EntityTexture planet, ground



While Not KeyDown(1)

	

	;-------------------------------------------
	;refresh
	UpdateWorld 1
	RenderWorld 1
	;-------------------------------------------
	
	
	
	;-------------------------------------------
	VWait:Flip False
	
	
Wend

ClearWorld
