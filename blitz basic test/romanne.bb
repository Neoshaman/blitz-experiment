Graphics3D 256,512

cube = CreateCube()
camera = CreateCamera()
light = CreateLight()
plane = CreatePlane()

PositionEntity camera, 0,3, -25
PositionEntity cube, 0, 1, 0

texture = LoadTexture("testure.jpg")

EntityTexture plane, texture

While Not KeyHit (1)
	If KeyHit (200) Then MoveEntity camera, 0,0,1
	If KeyHit (208) Then MoveEntity camera, 0,0,-1
	
	RenderWorld : Flip
	
Wend


Print "romane"



