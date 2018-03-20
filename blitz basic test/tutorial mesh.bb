Include "function library.bb"


Graphics3D 800,600,0,2
SetBuffer BackBuffer()

;-------------------------------------------
cam = CreateCamera ()
PositionEntity cam, 0,0,-25

l1 = CreateLight (1)
RotateEntity l1, 45,45,0

tex=LoadTexture( "testure.jpg" )


plane = CreatePlane ()
PositionEntity plane, 0,-8,0
EntityColor plane, 64,32,16
EntityTexture plane,tex

	test = CreateMesh()
	surface = CreateSurface(test)

;-------------------------------------------

;put set up code here

While Not KeyDown(1)
	
	;put runtime code and runtime 3d here
	controlentity(cam); function from the library, move object freely using keyboard or gamepad (reference x360), look at the library for reference

	;-------------------------------------------

	
	v1 = AddVertex (surface, 0,0,0, 0,0,0)
	v2 = AddVertex (surface, 1,0,0, 1,0,0)
	v3 = AddVertex (surface, 0,1,0, 0,1,0)
	
	AddTriangle (surface, v1,v3,v2 )
	
	UpdateNormals test
	EntityTexture test,tex
	;-------------------------------------------

	;refresh
	UpdateWorld 1
	RenderWorld 1

	; put 2d drawing code here
	showjoystate(100); 2d text display of joystick state

	VWait:Flip False
	
Wend

;put exit clean up code here

ClearWorld 